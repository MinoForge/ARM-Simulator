package simulation.pipeline;

import simulation.Controller;

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
    private byte[][] ifidRegister;

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
    public Fetch(byte[][] ifid, String... instructions) {
        this.instructions = instructions;
        this.ifidRegister = ifid;
        this.instBin = "";
        this.opCodes = new HashMap<>();
        //TODO Might make Enumerations out of these
        this.opCodes.put("add", "10001011000");
        this.opCodes.put("and", "10001010000");
        this.opCodes.put("addi","1001000100");
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
        PC+=4;

        // NEED TO FIX HOW DO WE GET THE FORMAT
        char format;

        format = 'r';
        //get type from file
        String[] instArray;
        String temp;

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

        /**
         * Massage the
         */
        instArray = inst.split(" ");

        for (int i  = 0; i < instArray.length; i++){
            instArray[i] = instArray[i].replace(",", "");
        }

        switch(format){
            case('r'):

                registerOne = instArray[1];
                registerTwo = instArray[2];
                registerThree = instArray[3];


                //grabbing inst command

                temp = instArray[0].toLowerCase();
                instructionName = instArray[0];


                // Adding the correct opcode to the instBin string
                instBin = instBin + opCodes.get(temp);
                instructionBinary = opCodes.get(temp);


                //TODO remove the magic num
                int[] registerNumbers = new int[3];

                for(int i = 0; i < instArray.length - 1; i++){
                    instArray[i+1] = instArray[i+1].replaceAll("[a-zA-Z]", "");

                    registerNumbers[i] = Integer.parseInt(instArray[i + 1]);
                }

                // adding rm register binary
                String reg1,reg2,reg3;

                reg1 = Integer.toBinaryString(registerNumbers[2]);
                reg2 = Integer.toBinaryString(registerNumbers[1]);
                reg3 = Integer.toBinaryString(registerNumbers[0]);

                reg1 = correctBits(reg1);
                reg2 = correctBits(reg2);
                reg3 = correctBits(reg3);

                register1Bin = reg3;
                register2Bin = reg2;
                register3Bin = reg1;


                instBin = instBin + reg1;

                // will need to check if a shamt is present but for now
                // assuming no
                instBin = instBin + "000000";
                shamt = "000000";


                // adding rn register binary

                instBin = instBin + reg2;

                // adding rd register binary

                instBin = instBin + reg3;


                break;
            //Currently unfinished for i types
            case('i'):
                instArray = inst.split(" ");
                for (String s: instArray){
                    s = s.replace(",", "");
                }
                //grabbing  inst command
                temp = instArray[0].toLowerCase();
                //
                instBin = instBin + opCodes.get(temp);



                break;
            // Not started on b types
            case('b'):
                instArray = inst.split(" ");
                for (String s: instArray){
                    s = s.replace(",", "");
                }
                //grabbing  inst command
                temp = instArray[0].toLowerCase();
                //
                instBin = instBin + opCodes.get(temp);


                break;

        }

        //TODO instruction to binary for I type and B type
        // Done by splitting up the instruction line and using each part of
        // the instruction(ie. the instruction name, which registers listed,
        // etc)

    }

    /**
     * Method to make sure that the binary representation of the registers
     * have the correct number bits. It concatenates zeros to the front of
     * the binary string and returns the corrected string.
     * @param reg the string to be checked/altered
     * @return the corrected string
     */
    public String correctBits(String reg){
        String correct = reg;
        while (correct.length() < 5) {
            correct = "0" + correct;
        }
        return correct;
    }

    /**
     * Writes the PC and the instruction fetched to the IFID register in bytes.
     */
    private void write(){
        // Creating the buffers that will hold the bytes of the PC and
        // instruction
        ByteBuffer pcBuffer = ByteBuffer.allocate(8);
        ByteBuffer instBuffer = ByteBuffer.allocate(4);
        pcBuffer.order(ByteOrder.LITTLE_ENDIAN);
        pcBuffer.putLong(PC);
        byte[] PcBytes = pcBuffer.array();
        // Writing the PC to the IFID register
        for(int i = 0; i < PcBytes.length; i++){
            ifidRegister[0][i] = PcBytes[i];
        }


        instBuffer = ByteBuffer.allocate(Long.BYTES);
        instBuffer.order(ByteOrder.LITTLE_ENDIAN);
        long temp = Long.valueOf(instBin, 2);
        instBuffer.putLong(temp);
        // Writing the binary to the IFID register
        byte[] instBytes = instBuffer.array();
        for(int i = 0; i < instBytes.length; i++){
            ifidRegister[1][i] = instBytes[i];
        }
        // Must reinitialize the instruction binary string
        instBin = "";
    }

    /**
     * Runs read and write for Fetch
     */
    public void execute(){
        read();
        write();
    }

}
