package Simulation;

/**
 *
 */
public class Fetcher extends PipelineSegment{

    private String instruction;
    private byte[] ifidRegister = new byte[24];
    private String instBin;
    /**
     *
      * @param ifid
     */
    public Fetcher(byte[] ifid) {
        this.instruction = "";
        this.ifidRegister = ifid;
        this.instBin = "";
    }

    /**
     *
     */
    public void read(){
        Controller.PC+=4;

        //TODO instruction to binary

    }

    /**
     *
     */
    public void write(){
        //TODO Add to the if/id register

    }

    /**
     *
     */
    public void execute(){
        read();
        write();

    }

}
