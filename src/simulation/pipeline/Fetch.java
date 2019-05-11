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
 * @version May 10, 2019
 *
 */
public class Fetch extends PipelineSegment{

    /** All instructions from file. */
    private String[] instructions;

    /** The if/id register. */
    private Register ifidRegister;

    /** The binary of the instruction. */
    private String instBin;

    private String inst;

    private Register memory;




    /**
     *  Constructor of the Fetch Class that takes the list of instructions
     *  that will be fetched and the ifid pipeline register.
     * @param ifid  The byte[][] representing the pipeline register
     * @param instructions The String array containing the instructions.
     */
    public Fetch(Register ifid, String[] instructions, Register memory) {
        this.instructions = instructions;
        this.ifidRegister = ifid;
        this.instBin = "";
        this.inst = "";
        this.memory = memory;
    }

    public Fetch(Register ifid, Register memory) {
        this(ifid, new String[0], memory);
    }



    public void setInstructions(String[] insts) {
        instructions = insts;
    }

    /**
     * Pulls binary corresponding to the current PC from the array of binary instructions
     * and updates PC.
     */
    private void read() {
        System.err.println("\nStarting " +
                "Fetch\n----------------------------------------------------------\n");

        System.err.println("Fetching instruction at: " + Controller.PC / 4);

        int address = Controller.PC / 4;
        instBin = memory.getBinary(Controller.PC, Controller.PC + 4);
        inst = instructions[address].toLowerCase(); //TODO move hashmap stuff from execute to Control unit to remove instructions entirely
//        instBin = bins[Controller.PC / 4];
    }



    /**
     * Writes the PC and the instruction fetched to the IFID register in bytes.
     */
    private void write(){

        ifidRegister.append(correctBits(Long.toBinaryString(Controller.PC),
                64, 64));
        ifidRegister.append(instBin);
        System.err.println("Instruction written to IFID: " + ifidRegister.getBinary(8,12));





        // Must reinitialize the instruction binary string
        // instBin = "";
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
            Controller.PC += 4;

        }
    }



}
