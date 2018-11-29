package Simulation.Pipeline;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static Simulation.Controller.PC;

/**
 * A class to model the Instruction Decoding segment of the ARM pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Decoder extends PipelineSegment {

    private byte[] ifidRegister;
    private byte[] idexRegister;
    private String instBin;
    private int nRegister;
    private int mRegister;
    private int dRegister;
    private long[] registers;

    public Decoder(byte[] ifid, byte[] idex,long[] regs) {
        this.ifidRegister = ifid;
        this.idexRegister = idex;
        this.instBin = "";
        this.nRegister = 0;
        this.mRegister = 0;
        this.dRegister = 0;
        this.registers =regs;
    }

    /**
     *
     * @return
     */
    public String interpretPipeReg(){
        byte[] instBytes = new byte[4];
        for(int i = 8; i < ifidRegister.length; i++){
            instBytes[i] = ifidRegister[i];
        }
        ByteBuffer buf = ByteBuffer.allocate(Long.BYTES);
        buf.put(instBytes);
        buf.flip();
        int temp = buf.getInt();
        return Integer.toBinaryString(temp);
    }
    /**
     *
     */
    private void read(){
        instBin = interpretPipeReg();
        char format = 'r';
        switch (format){
            case('r'):
                //pulling apart the bin string
                String opCode = instBin.substring(0,10);
                String regM =  instBin.substring(11,15);
                String shmt = "000000";
                String regN = instBin.substring(22,26);
                String regD = instBin.substring(27,31);
                // Getting the register indices
                nRegister = Integer.parseInt(regN, 2);
                mRegister = Integer.parseInt(regM, 2);
                dRegister = Integer.parseInt(regD, 2);
        }


        //TODO strip the immediate and sign extend if found
        //Only if the opcode dictates
        //TODO strip the read registers
        // Using binary in if/id register, find the register which are being
        // read from and find the contents of them in the register array.

    }

    /**
     *
     */
    private void write(){
        //First put PC into the ex/mem
        //fix magic num
        for(int i = 0; i < 8; i++){
            idexRegister[i] = ifidRegister[i];
        }
        ByteBuffer buf = ByteBuffer.allocate(Long.BYTES);
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long temp = registers[dRegister];
        buf.putLong(temp);

        temp = registers[nRegister];
        buf.putLong(temp);

        temp = registers[mRegister];
        buf.putLong(temp);
        byte[] registerContents = buf.array();
        int j = 0;
        for(int i = 8; i < idexRegister.length; i++){
            idexRegister[i] = registerContents[j];
            j++;
        }

        //TODO Add above to the ex/mem register
        //put the contents of those registers into ex/mem so executor may
        // perform the operation on them
    }

    /**
     *
     */
    public void execute(){
        read();
        write();

    }


}
