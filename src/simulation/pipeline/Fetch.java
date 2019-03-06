package simulation.pipeline;

import simulation.ControlUnit;
import simulation.Controller;
import simulation.Register;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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

    /** The if/id register. */
    private Register ifidRegister;

    /** The binary of the instruction. */
    private String instBin;

    /** InstructionName -> Binary opCode */
    private HashMap<String,String> opCodes;



    /**
     *  Constructor of the Fetch Class that takes the list of instructions
     *  that will be fetched and the ifid pipeline register.
     * @param ifid  The byte[][] representing the pipeline register
     * @param instructions The String array containing the instructions.
     */
    public Fetch(Register ifid, String... instructions) {
        this.instructions = instructions;
        this.ifidRegister = ifid;
        this.instBin = "";
        this.opCodes = new HashMap<>();
        //TODO Might make Enumerations out of these
        this.opCodes.put("add", "10001011000");
        this.opCodes.put("and", "10001010000");
        this.opCodes.put("addi","1001000100");
    }

    public Fetch(Register ifid) {
        this(ifid, new String[0]);
    }



    public void setInstructions(String[] insts) {
        instructions = insts;
    }

    /**
     * Method that builds the binary representation of the instruction
     * corresponding to the current PC, and increments the PC.
     */
    private void read(){
        System.out.println("\nStarting " +
                "Fetch\n------------------------------------------" +
                "----------------\n");

        String inst = instructions[Controller.PC/4];
        Controller.PC+=4;
        ControlUnit.newInstruction(inst);

        /*
         *Code meant simply for the demo
         */
        String instructionName;
        String instructionBinary;

        String registerOne;
        String register1Bin;

        String registerTwo;
        String register2Bin;

        String registerThree;
        String register3Bin;

        String shamt;

        /*
         * Massage the instruction, and split it into an array.
         */
        String[] instArray;
        String temp;

        instArray = inst.split(" ");

        for (int i  = 0; i < instArray.length; i++){
            instArray[i] = instArray[i].replace(",", "");
        }
        temp = instArray[0].toLowerCase();
        instBin = instBin + opCodes.get(temp);

        char format = '\0';
        String check = instBin.substring(3,7);
        if(check.matches("100.") || check.matches(".1.0")){
            format = 'i';
        }
        else if(check.matches("101.")){
            format = 'b';
        }
        else if(check.matches(".101")){
            format = 'r';

        }

        System.out.println("This is the format: " + format);
        // Initializing the register strings
        String reg1,reg2,reg3;

        switch(format){
            case('r'):

                registerOne = instArray[1];
                registerTwo = instArray[2];
                registerThree = instArray[3];


                //grabbing inst command


                instructionName = instArray[0];


                // Adding the correct opcode to the instBin string



                //TODO remove the magic num
                int[] registerNumbers = new int[3];

                for(int i = 0; i < instArray.length - 1; i++){
                    instArray[i+1] = instArray[i+1].replaceAll("[a-zA-Z]", "");

                    registerNumbers[i] = Integer.parseInt(instArray[i + 1]);
                }

                // adding rm register binary


                reg1 = Integer.toBinaryString(registerNumbers[2]);
                reg2 = Integer.toBinaryString(registerNumbers[1]);
                reg3 = Integer.toBinaryString(registerNumbers[0]);

                reg1 = correctBits(reg1,5);
                reg2 = correctBits(reg2,5);
                reg3 = correctBits(reg3,5);


                instBin = instBin + reg1;

                // will need to check if a shamt is present but for now
                // assuming there isn't
                instBin = instBin + "000000";
                shamt = "000000";


                // adding rn register binary

                instBin = instBin + reg2;

                // adding rd register binary

                instBin = instBin + reg3;


                break;
            //Currently unfinished for i types
            case('i'):
                //grabbing  inst command
                //temp = instArray[0].toLowerCase();
                // adding opcode to instruction binary
                //instBin = instBin + opCodes.get(temp);

                //pulling the registers from the instruction
                reg1 = Integer.toBinaryString(Integer.parseInt(instArray[1]
                        .replaceAll("[a-zA-Z]", "")));
                reg2 = Integer.toBinaryString(Integer.parseInt(instArray[2]
                        .replaceAll("[a-zA-Z]", "")));
                reg1 = correctBits(reg1,5);
                reg2 = correctBits(reg2,5);

                //pulling the immediate value from the instruction
                String immediate =  instArray[3].replace("#","");
                String immediateBin = Integer.toBinaryString(Integer.parseInt
                        (immediate));
                immediateBin = correctBits(immediateBin, 12);

                instBin = instBin + immediateBin + reg2 + reg1;


                break;
            // Not started on b types
            case('b'):
                //grabbing instruction command
                //temp = instArray[0].toLowerCase();
                //adding opcode
                //instBin = instBin + opCodes.get(temp);

                String memLocation;

                break;

        }

        //TODO B type
        // Need labels to be implemented properly before much work can be
        // done on Branching instructions.

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
            write();
        }
    }



}
