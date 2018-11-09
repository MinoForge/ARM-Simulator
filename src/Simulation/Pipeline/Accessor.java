package Simulation.Pipeline;

/**
 * A class to model the Memory Accessing segment of the ARM pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
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
    private void read(){
        //TODO use info in pipeline register to see if mem needs to be
        // accessed


    }

    /**
     *
     */
    private void write(){
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
