package Simulation;

/**
 * Created by Caleb on 11/1/2018.
 */
public class Accessor extends PipelineSegment{
    private byte[] exmemRegister;
    private byte[] memwbRegister;


    public Accessor(byte[] exmem, byte[] memwb) {
        this.exmemRegister = exmem;
        this.memwbRegister = memwb;
    }

    /**
     *
     */
    public void read(){
        //TODO use info in pipeline register to see if mem needs to be
        // acessed


    }

    /**
     *
     */
    public void write(){
        //TODO Add above to the mem/wb register

    }

    /**
     *
     */
    public void execute(){
        read();
        write();

    }

}
