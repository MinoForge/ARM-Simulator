package Simulation;

/**
 * Created by Caleb on 11/1/2018.
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
    public void read(){
        //TODO strip the immediate and sign extend if found
        //TODO strip the read registers


    }

    /**
     *
     */
    public void write(){
        //TODO Add above to the ex/mem register

    }

    /**
     *
     */
    public void execute(){
        read();
        write();

    }


}
