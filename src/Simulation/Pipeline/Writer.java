package Simulation.Pipeline;

/**
 * Created by Caleb on 11/1/2018.
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
