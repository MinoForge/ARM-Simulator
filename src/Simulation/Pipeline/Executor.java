package Simulation.Pipeline;

/**
 * A class to model the Execution segment of the ARM pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Executor extends PipelineSegment {
    private byte[] exmemRegister;
    private byte[] idexRegister;

    /**
    *
    */
    public Executor(byte[] exmem, byte[] idex) {
        this.exmemRegister = exmem;
        this.idexRegister = idex;
    }

    /**
     *
     */
    private void read(){
        // TODO a lot


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
