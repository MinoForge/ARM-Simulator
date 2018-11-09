package Simulation.Pipeline;

import Simulation.Controller;

import java.util.HashMap;

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
    private HashMap<String,String> opCodes = new HashMap<String, String>();



    /**
     *  Constructor that takes the instruction to fetch.
     * @param inst the instruction to fetch.
     */
    public Fetcher(String inst) {
        this.instruction = inst;
        this.instBin = "";
        //Will be put into helper,
        this.opCodes.put("add", "10001011000");
        this.opCodes.put("and", "10001010000");
        this.opCodes.put("addi","1001000100");
        this.opCodes.put("","");
    }

    /**
     *
     */
    private void read(){
        Controller.PC+=4;

        //TODO instruction to binary
        //Done by splitting up the instruction line and using each part of
        // the instruction(ie the instruction name, which registers listed, etc)
        // Use the multiple hashmaps, initialized by constructor, and will
        // map each part of the instruction to its binary equivalent and adds
        // each to the binary instruction field.

    }

    /**
     *
     */
    private void write(){
        //TODO Add to the if/id register
        //adds the current PC to the if/id register and then adds the binary
        //translation to the if/id register

    }

    /**
     *
     */
    public void execute(){
        read();
        write();

    }

}
