package Simulation.Pipeline;

import Simulation.Controller;

/**
 * A class to model the Instruction Fetching segment of the ARM pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
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
    private void read(){
        Controller.PC+=4;

        //TODO instruction to binary

    }

    /**
     *
     */
    private void write(){
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
