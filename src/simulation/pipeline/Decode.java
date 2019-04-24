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
    private String imm;
//    private String format;
    private ArrayList<Boolean> flags;

    private static final byte RETURN_ADDRESS = 30;

    public Decode(Register ifid, Register idex, RegisterFile regFile) {
        this.ifidRegister = ifid;
        this.idexRegister = idex;
        this.instBin      = "";
        this.nRegister    = 0;
        this.mRegister    = 0;
        this.dRegister    = 0;
        this.regFile    = regFile;
        this.immediate    = 0;
        this.imm = "";
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

        //TODO technically, if this is a branch, this could be set to 30.
        //However, that begs the question: What happens if we're branching without linking?
        //Because this avenue would set the RA to 0 always.
        if(flags.get(4) && flags.get(7)) { //Branch with Link must write to ReturnAddress reg.
            regD = "" + RETURN_ADDRESS;
        } else { //Anything else writes or does not write to regD as determined by flags[7]
            regD = instBin.substring(27, 32);
        }

        nRegister = Integer.parseInt(regN, 2);
        mRegister = Integer.parseInt(regM, 2);
        dRegister = Integer.parseInt(regD, 2);
        System.out.println("N:M:D == " + nRegister + ":" + mRegister + ":" + dRegister);

        String imm = "";
        int temp;
        if(flags.get(5) || flags.get(6)) { //D-type
            imm = instBin.substring(11, 20);
        } else { //I-type
            imm = instBin.substring(10, 22);
        }
        if(flags.get(4)) { //Branch
            if(flags.get(0)) { //CBZ
                imm = instBin.substring(8,27);
            } else { //Uncond branch
                imm = instBin.substring(6,32);
            }
        }
        System.out.println("Substring of immediate: " + imm);
        temp = Integer.parseInt(imm, 2);
        immediate = ((long)temp << (64 - imm.length()) >> (64 - imm.length())); //sign extend the immediate value



    }


    //TODO This method still needs to be abstracted a bit. Does not support d-type?
    //TODO Should read flags from CU.
    /**
     * Writes the information found in read(), in this case the registers we
     * are manipulating, and writes them into the idex pipeline registers,
     * also writes the PC to the idex register.
     */
    private void write(){
        System.out.println("This is the local PC in Decode: " + ifidRegister.getBinary(0,8));
        idexRegister.append(ifidRegister.getBinary(0,8));  //0-7 //TODO maybe actually read this in into a variable in read()?

        System.out.println("This is the first operand: " +
                regFile.getRegister(nRegister));

        if(!flags.get(4)) { //Not a branch
            idexRegister.append(regFile.getRegister(nRegister).getBinary()); //8-15
        } else { //Branch
            idexRegister.append(regFile.getRegister(mRegister).getBinary());
        }
        //This is inaccurate and for immediates is irrelevant.
        System.out.println("This is the second operand: " +
                regFile.getRegister(mRegister).getBinary());

        idexRegister.append(regFile.getRegister(mRegister).getBinary()); //16-23

        System.out.println("This is the immediate: " + correctBits(Long
                        .toBinaryString(immediate), 64, imm.length()));
        idexRegister.append(correctBits(Long.toBinaryString(immediate),64,imm
                .length())); //24-31


        System.out.println("This is the opcode + dest register bin: " + instBin.substring(0,11) + ":" + instBin.substring(27,32));
        idexRegister.append(instBin.substring(0,11) + instBin.substring(27,32)); //32-33


        System.out.println("This is the contents of idex: " + idexRegister
                .getBinary());
    }



    /**
     * Executes the read and write methods
     */
    public void execute(){
        if(ControlUnit.getGoAhead(1)) {
            ControlUnit.newInstructionBin(ifidRegister.getInt(8));

            System.out.println(ControlUnit.getState(1));

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

    /**
     * Checks whether the registers being requested are available for the requested operation.
     * @return true if they are available, false otherwise.
     */
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
