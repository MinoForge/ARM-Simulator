package simulation.pipeline;

/**
 * A class to model the Write-back segment of the ARM pipeline.
 * NOT IMPLEMENTED YET.
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Writer extends PipelineSegment{

    private byte[] memwbRegister;


    public Writer(byte[] memwb) {
        this.memwbRegister = memwb;
    }

    /**
     *
     */
    private void read(){
        //TODO pull register info from pipeline register
        //

    }

    /**
     *
     */
    private void write(){
        //TODO writes back to the registers

    }

    /**
     *
     */
    public void execute(){
        read();
        write();

    }

}
