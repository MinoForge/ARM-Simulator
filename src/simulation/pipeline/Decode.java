package simulation.pipeline;

import simulation.control.ControlUnit;
import simulation.registers.Register;

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
    private Register[] registers;
    private String format;

    public Decode(Register ifid, Register idex, Register[] regs) {
        this.ifidRegister = ifid;
        this.idexRegister = idex;
        this.instBin      = "";
        this.nRegister    = 0;
        this.mRegister    = 0;
        this.dRegister    = 0;
        this.registers    = regs;
        this.immediate    = 0;
        //Testing only
        this.format       = "r";
    }

    /**
     * Uses the instruction binary to find the read and write registers, and
     * will sign extend an immediate value depending on the instruction.
     */
    private void read(){
        System.out.println("Starting DECODE now!");
        instBin = ifidRegister.getBinary(8,12);
        String opCode, regM, regN, regD;

        switch (format){
            case("r"):

                //pulling apart the bin string
                opCode = instBin.substring(0,11);
                //System.out.println("this is the opcode : " + opCode);
                regM =  instBin.substring(11,16);
                //System.out.println("this is the regM : " + regM);
                //String shmt = "000000";
                regN = instBin.substring(22,27);
                //System.out.println("this is the regN : " + regN);
                regD = instBin.substring(27,32);
                //System.out.println("this is the regD : " + regD);



                // Getting the register indices
                nRegister = Integer.parseInt(regN, 2);
                //System.out.println("The first operand register: " +
                // nRegister);
                mRegister = Integer.parseInt(regM, 2);
                //System.out.println("The second operand register: " +
                //    mRegister);
                dRegister = Integer.parseInt(regD, 2);
                //System.out.println("The destination register: " + dRegister);

                break;


            case("i"):
                //opCode = instBin.substring(0,10);
                regD = instBin.substring(27,32);
                regN = instBin.substring(22,27);
                String imme = instBin.substring(10,22);

                dRegister = Integer.parseInt(regD, 2);

                nRegister = Integer.parseInt(regN, 2);
                int temp = Integer.parseInt(imme,2);
                immediate = ((long)temp << (64 - 12) >> (64-12));
                //sign extend the immediate value
//                immediate = (long)temp;
        }


        //TODO finish for B type, needs labels
        //Only if the opcode dictates
    }



    /**
     * Writes the information found in read(), in this case the registers we
     * are manipulating, and writes them into the idex pipeline registers,
     * also writes the PC to the idex register.
     */
    private void write(){
        //First put PC into the ex/mem
        if(!format.matches("[ri]")){
            //do branch magic
        }else{
            System.out.println(ifidRegister.getBinary(0,8));
            idexRegister.append(ifidRegister.getBinary(0,8));

            System.out.println("This is the first operand: " +
                    registers[nRegister].getBinary());

            idexRegister.append(registers[nRegister].getBinary());
            if(format.equals("r")){
                System.out.println("This is the second operand: " +
                        registers[mRegister].getBinary());

                idexRegister.append(registers[mRegister].getBinary());
            } else{
                idexRegister.append(Long.toBinaryString(immediate));
            }

            System.out.println("This is the immediate: " + correctBits(Long
                            .toBinaryString(immediate), 64));
            idexRegister.append(correctBits(Long.toBinaryString(immediate),
                    64));

            // Added because of book but might not be needed
            System.out.println("This is the opcode + dest register bin: " + instBin.substring(0,12) + ":" + instBin.substring(27,32));
//            idexRegister.append(instBin.substring(0,12) );

//            System.out.println("This is the dest register bin: " +
//                    instBin.substring(27,32));
            idexRegister.append(instBin.substring(0,11) + instBin.substring(27,32));

        }
        System.out.println("This is the contents  of idex: " + idexRegister
                .getBinary());


        //TODO Integrate B format, still need labels
    }



    /**
     * Executes the read and write methods
     */
    public void execute(){
        if(ControlUnit.getGoAhead(1)) {
            read();
            System.out.println("this is the thing im sending to control " +
                    "unit:" + ifidRegister.getInt(8));
            ControlUnit.newInstructionBin(ifidRegister.getInt(8));
            idexRegister.zeroOut();

            write();
        }
    }


}
