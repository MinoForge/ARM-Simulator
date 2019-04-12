package simulation.pipeline;

import simulation.control.ControlUnit;
import simulation.Controller;
import simulation.registers.Register;

import java.util.ArrayList;

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

    private String inst;




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
        this.inst = "";
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
     * Pulls binary corresponding to the current PC from the array of binary instructions
     * and updates PC.
     */
    private void read() {
        System.out.println("\nStarting " +
                "Fetch\n------------------------------------------" +
                "----------------\n");

        inst = instructions[Controller.PC / 4];
//        System.out.println(inst);
        instBin = bins[Controller.PC / 4];
//        System.out.println(instBin);


        Controller.PC += 4;
    }



    /**
     * Writes the PC and the instruction fetched to the IFID register in bytes.
     */
    private void write(){
        System.out.println(Controller.PC);
        System.out.println("This is the binary string: " + instBin);
        System.out.println(instBin);
        System.out.println(ifidRegister);

        ifidRegister.append(correctBits(Long.toBinaryString(Controller.PC),64));
        System.out.println(ifidRegister.append(instBin));
        System.out.println(ifidRegister.getBinary(8,12));
        System.out.println(ifidRegister);
        System.out.println(instBin);
        instBin = "";




        // Must reinitialize the instruction binary string
    }

    /**
     * Runs read and write for Fetch and pushes assembly instruction to ControlUnit.
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
