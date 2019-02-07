package simulation.pipeline;

import simulation.Register;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
        this.format       = "";

    }

    /**
     * Method that reads and interprets the IFID register.
     * @return a string representing the binary representation of the
     * instruction
     */
    public String interpretPipeReg(){

        return ifidRegister.getBinary();





//        byte[] instBytes = new byte[4];
//        for(int i = 0; i < ifidRegister[1].length - 4; i++){
//            instBytes[i] = ifidRegister[1][i];
//        }
//        ByteBuffer buf = ByteBuffer.allocate(4);
//        buf.order(ByteOrder.LITTLE_ENDIAN);
//        buf.put(instBytes);
//        buf.flip();
//        int temp = buf.getInt();


//        System.out.println("\nStarting Decode\n-------------------------" +
//                "---------------------------------\n");
//        System.out.println(("This is the binary string to decode: \n\n\t\t\t"
//                +Integer.toBinaryString(temp)));

    }
    /**
     * Uses the instruction binary to find the read and write registers, and
     * will sign extend an immediate value depending on the instruction
     */
    private void read(){
        instBin = interpretPipeReg();
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
                //sign extend the immediate value
                immediate = (long)temp;
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
            //do branch magic crap
        }else{
            idexRegister.append(ifidRegister.getBinary());

//            long temp = registers[dRegister].getBinary(0,8);
            idexRegister.append(registers[dRegister].getBinary());
//            temp = registers[nRegister].getBinary();
            idexRegister.append(registers[nRegister].getBinary());

            if(format == "r"){
//                temp = registers[mRegister];
                idexRegister.append(registers[mRegister].getBinary());
            } else{
                idexRegister.append(Long.toBinaryString(immediate));
            }
        }




        /*
        for(int i = 0; i < idexRegister[0].length; i++){
            idexRegister[0][i] = ifidRegister[0][i];
        }
        ByteBuffer regBuf = ByteBuffer.allocate(Long.BYTES);
        regBuf.order(ByteOrder.LITTLE_ENDIAN);
        temp = registers[dRegister];
        regBuf.putLong(temp);
        byte[] regContents = regBuf.array();
        for(int i = 0; i < idexRegister[1].length; i++){
            idexRegister[1][i] = regContents[i];
        }

        regBuf.clear();
        temp = registers[nRegister];
        regBuf.putLong(temp);
        regContents = regBuf.array();
        for(int i = 0; i < idexRegister[2].length; i++){
            idexRegister[2][i] = regContents[i];
        }

        regBuf.clear();
        temp = registers[mRegister];
        regBuf.putLong(temp);
        regContents = regBuf.array();
        for(int i = 0; i < idexRegister[3].length; i++){
            idexRegister[3][i] = regContents[i];
        }
        */

        //TODO Integrate B format, still need labels
    }

    /**
     * Executes the read and write methods
     */
    public void execute(){
        read();
        write();

    }


}
