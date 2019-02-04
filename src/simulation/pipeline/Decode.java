package simulation.pipeline;

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

    private byte[][] ifidRegister;
    private byte[][] idexRegister;
    private String instBin;
    private int nRegister;
    private int mRegister;
    private int dRegister;
    private long[] registers;

    public Decode(byte[][] ifid, byte[][] idex, long[] regs) {
        this.ifidRegister = ifid;
        this.idexRegister = idex;
        this.instBin = "";
        this.nRegister = 0;
        this.mRegister = 0;
        this.dRegister = 0;
        this.registers =regs;


    }

    /**
     * Method that reads and interprets the IFID register.
     * @return a string representing the binary representation of the
     * instruction
     */
    public String interpretPipeReg(){
        byte[] instBytes = new byte[4];
        for(int i = 0; i < ifidRegister[1].length - 4; i++){
            instBytes[i] = ifidRegister[1][i];
        }
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        buf.put(instBytes);
        buf.flip();
        int temp = buf.getInt();
        System.out.println("\nStarting Decode\n-------------------------" +
                "---------------------------------\n");
        System.out.println(("This is the binary string to decode: \n\n\t\t\t"
                +Integer.toBinaryString(temp)));
        return Integer.toBinaryString(temp);
    }
    /**
     * Uses the instruction binary to find the read and write registers, and
     * will sign extend an immediate value depending on the instruction
     */
    private void read(){
        instBin = interpretPipeReg();
        char format = 'r';
        switch (format){
            case('r'):

                //pulling apart the bin string
                String opCode = instBin.substring(0,11);
                //System.out.println("this is the opcode : " + opCode);
                String regM =  instBin.substring(11,16);
                //System.out.println("this is the regM : " + regM);
                //String shmt = "000000";
                String regN = instBin.substring(22,27);
                //System.out.println("this is the regN : " + regN);
                String regD = instBin.substring(27,32);
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

                /*
                This code is only for demo. TODO Remove at start of semester.
                 */
                System.out.println
                        ("\n\n\t  Binary\t\t|\t   Meaning\t\t|\t\tContents");
                System.out.println
                        ("--------------------|-------------------|-----------------");
                System.out.printf("\t%10s   \t| %10s      \t|  %10s   \t\n",
                        opCode,  "ADD", "add");
                System.out.printf("%12s      \t|  %10s | %10s   \t\n",
                        regM,  "Operand Register", registers[mRegister]);
                System.out.printf("%12s      \t|  %10s | %10s   \t\n",
                        regN,  "Operand Register", registers[nRegister]);
                System.out.printf("%12s      \t|  %10s   | %10s   \t\n",
                        regD,  " Dest Register", registers[dRegister]);
        }


        //TODO strip the immediate and sign extend if I format
        //TODO finish for I type and B type
        //Only if the opcode dictates
    }

    /**
     * Writes the information found in read(), in this case the registers we
     * are manipulating, and writes them into the idex pipeline registers,
     * also wirtes the PC to the idex register.
     */
    private void write(){
        //First put PC into the ex/mem
        for(int i = 0; i < idexRegister[0].length; i++){
            idexRegister[0][i] = ifidRegister[0][i];
        }
        ByteBuffer regBuf = ByteBuffer.allocate(Long.BYTES);
        regBuf.order(ByteOrder.LITTLE_ENDIAN);
        long temp = registers[dRegister];
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

        //TODO Integrate I format and B format
    }

    /**
     * Executes the read and write methods
     */
    public void execute(){
        read();
        write();

    }


}
