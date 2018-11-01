package Simulation.Pipeline;

/**
 * Created by Caleb on 11/1/2018.
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
    public void read(){
        // TODO a lot


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
