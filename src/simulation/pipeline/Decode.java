package simulation.pipeline;

import simulation.control.ControlUnit;
import simulation.registers.Register;
import simulation.registers.RegisterFile;

import java.util.ArrayList;

import static simulation.control.ControlUnit.DEASSERT;

/**
 * A class to model the Instruction Decoding segment of the ARM pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Decode extends PipelineSegment {

    private Register ifidRegister;
    private Register idexRegister;
    private String instBin;
    private int nRegister;
    private int mRegister;
    private int dRegister;
    private long immediate;
    private RegisterFile regFile;
//    private String format;
    private ArrayList<Boolean> flags;

    public Decode(Register ifid, Register idex, RegisterFile regFile) {
        this.ifidRegister = ifid;
        this.idexRegister = idex;
        this.instBin      = "";
        this.nRegister    = 0;
        this.mRegister    = 0;
        this.dRegister    = 0;
        this.regFile    = regFile;
        this.immediate    = 0;
        //Testing only
//        this.format       = "r";
    }

    /**
     * Uses the instruction binary to find the read and write registers, and
     * will sign extend an immediate value depending on the instruction.
     */
    private void read(){
        System.out.println("Starting DECODE now!");
        System.out.flush();
        instBin = ifidRegister.getBinary(8,12);
        String regN, regM, regD;

        regN = instBin.substring(22,27);
        if(flags.get(0)) {
            regM = instBin.substring(27, 32);
        } else {
            regM = instBin.substring(11, 16);
        }


        regD = instBin.substring(27, 32);

        nRegister = Integer.parseInt(regN, 2);
        mRegister = Integer.parseInt(regM, 2);
        dRegister = Integer.parseInt(regD, 2);

        String imm = "";
        int temp;
        if(flags.get(5) | flags.get(6)) { //D-type
            imm = instBin.substring(11, 20);
        } else {
            imm = instBin.substring(10, 22); //TODO Immediate not always 12 digits. See ldur.
        }
        temp = Integer.parseInt(imm, 2);
        immediate = ((long)temp << (64 - imm.length()) >> (64 - imm.length())); //sign extend the immediate value

//
//        switch (format){
//            case("r"):
//
//                regM =  instBin.substring(11,16);
//                regN = instBin.substring(22,27);
//                regD = instBin.substring(27,32);
//
//
//
//                // Getting the register indices
//                mRegister = Integer.parseInt(regM, 2);
//                nRegister = Integer.parseInt(regN, 2);
//                dRegister = Integer.parseInt(regD, 2);
//
//                break;
//
//
//            case("i"):
//                //opCode = instBin.substring(0,10);
//
//                regN = instBin.substring(22,27);
//                regD = instBin.substring(27,32);
//
//
//
//
//                nRegister = Integer.parseInt(regN, 2);
//                dRegister = Integer.parseInt(regD, 2);
//
//                int temp = Integer.parseInt(imme,2);
//                immediate = ((long)temp << (64 - 12) >> (64-12)); //sign extend the immediate value
//
//        }


        //TODO finish for B type, needs labels
        //Only if the opcode dictates
    }


    //TODO This method still needs to be abstracted a bit. Does not support d-type?
    //TODO Should read flags from CU.
    /**
     * Writes the information found in read(), in this case the registers we
     * are manipulating, and writes them into the idex pipeline registers,
     * also writes the PC to the idex register.
     */
    private void write(){

        //First put PC into the ex/mem
//        idexRegister.append(ifidRegister.getBinary(0,8));


//        if(!format.matches("[ri]")){
            //do branch magic
//        }else{
            System.out.println(ifidRegister.getBinary(0,8));
            idexRegister.append(ifidRegister.getBinary(0,8));

            System.out.println("This is the first operand: " +
                    regFile.getRegister(nRegister).getBinary());

            idexRegister.append(regFile.getRegister(nRegister).getBinary());

            System.out.println("This is the second operand: " +
                    regFile.getRegister(mRegister).getBinary());

            idexRegister.append(regFile.getRegister(mRegister).getBinary());

            System.out.println("This is the immediate: " + correctBits(Long
                            .toBinaryString(immediate), 64));
            idexRegister.append(correctBits(Long.toBinaryString(immediate),
                    64));

            // Added because of book but might not be needed
            System.out.println("This is the opcode + dest register bin: " + instBin.substring(0,11) + ":" + instBin.substring(27,32));
            idexRegister.append(instBin.substring(0,11) + instBin.substring(27,32));

//        }
        System.out.println("This is the contents  of idex: " + idexRegister
                .getBinary());


        //TODO Integrate B format, still need labels
    }



    /**
     * Executes the read and write methods
     */
    public void execute(){
        if(ControlUnit.getGoAhead(1)) {
//            System.out.println("this is the thing im sending to control " +
//                    "unit:" + ifidRegister.getInt(8));
            ControlUnit.newInstructionBin(ifidRegister.getInt(8));
            flags = ControlUnit.getControlFlags(1);
            read();
            idexRegister.zeroOut();
            if(registersAvailable()) {
                write();
            } else {
                System.out.println("Stalling at Decode.");
                ControlUnit.haltPipe(0, 1);
            }
        }
    }

    private boolean registersAvailable() {
        if(regFile.getRegisterForRead(nRegister) == null) {
            return false;
        }

        if(flags.get(0) == DEASSERT && regFile.getRegisterForRead(mRegister) == null) {
            return false;
        }

        if(regFile.getRegisterForWrite(dRegister) == null) { //TODO if RegisterFile.getRegisterForWrite() is fleshed out, move print there.
            System.out.println("Register " + mRegister + " is currently locked.");
            return false;
        }
        return true;
    }


}
