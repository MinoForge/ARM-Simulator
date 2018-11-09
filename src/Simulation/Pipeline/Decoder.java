package Simulation.Pipeline;

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


    public Decoder(byte[] ifid, byte[] idex) {
        this.ifidRegister = ifid;
        this.idexRegister = idex;
    }

    /**
     *
     */
    private void read(){
        //TODO strip the immediate and sign extend if found
        //Only if the opcode dictates
        //TODO strip the read registers
        // Using binary in if/id register, find the register which are being
        // read from and hand and find the contents of them in the register
        // array.

    }

    /**
     *
     */
    private void write(){
        //TODO Add above to the ex/mem register
        //put the contents of those registers into ex/mem so it may performt
        // the operation on them
    }

    /**
     *
     */
    public void execute(){
        read();
        write();

    }


}
