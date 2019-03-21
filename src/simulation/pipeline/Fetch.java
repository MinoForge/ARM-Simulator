package simulation.pipeline;

import simulation.ControlUnit;
import simulation.Controller;
import simulation.Register;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;

import static simulation.Controller.PC;

/**
 * A class to model the Instruction Fetching segment of the ARM pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version February, 2019
 *
 */
public class Fetch extends PipelineSegment{

    /** All instructions from file. */
    private String[] instructions;

    private String[] bins;

    /** The if/id register. */
    private Register ifidRegister;

    /** The binary of the instruction. */
    private String instBin;




    /**
     *  Constructor of the Fetch Class that takes the list of instructions
     *  that will be fetched and the ifid pipeline register.
     * @param ifid  The byte[][] representing the pipeline register
     * @param instructions The String array containing the instructions.
     */
    public Fetch(Register ifid, String[] instructions, String[] bins) {
        this.instructions = instructions;
        this.ifidRegister = ifid;
        this.instBin = "";
        this.bins = bins;
    }

    public Fetch(Register ifid) {
        this(ifid, new String[0], new String[0]);
    }



    public void setInstructions(String[] insts) {
        instructions = insts;
    }

    public void setBins(ArrayList<String> binary){
        bins = binary.toArray(new String[0]);
    }

    /**
     * Method that builds the binary representation of the instruction
     * corresponding to the current PC, and increments the PC.
     */
    private void read() {
        System.out.println("\nStarting " +
                "Fetch\n------------------------------------------" +
                "----------------\n");

        String inst = instructions[Controller.PC / 4];
        instBin = bins[Controller.PC / 4];

        Controller.PC += 4;
        ControlUnit.newInstruction(inst);
    }



    /**
     * Writes the PC and the instruction fetched to the IFID register in bytes.
     */
    private void write(){
        //Adding the
        System.out.println(Controller.PC);
        System.out.println("This is the binary string: " + instBin);

        ifidRegister.append(correctBits(Long.toBinaryString(Controller.PC),64));
        System.out.println(ifidRegister.getBinary(0,8));
        ifidRegister.append(instBin);
        System.out.println(ifidRegister.getBinary(8,12));
        instBin = "";




        // Must reinitialize the instruction binary string
    }

    /**
     * Runs read and write for Fetch
     */
    public void execute(){
        if(ControlUnit.getGoAhead(0)) {
            read();
            ControlUnit.newInstruction(inst);

            ifidRegister.zeroOut();
            write();

        }
    }



}
