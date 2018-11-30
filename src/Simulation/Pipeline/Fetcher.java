package Simulation.Pipeline;

import Simulation.Controller;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashMap;

import static Simulation.Controller.PC;

/**
 * A class to model the Instruction Fetching segment of the ARM pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Fetcher extends PipelineSegment{
    private String[] instructions;
    private byte[][] ifidRegister;
    private String instBin;
    private HashMap<String,String> opCodes;



    /**
     *  Constructor that takes the instruction to fetch.
     * @param ifid the  to fetch.
     */
    public Fetcher(byte[][] ifid, String... instructions) {
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
     *
     */
    private void read(){
        System.out.println("\nStarting Fetch\n------------------------\n");
        System.out.println("This is the PC before incrementing: " +
                Controller.PC);
        String inst = instructions[Controller.PC/4];
        PC+=4;
        System.out.println("This is the PC after incrementing: " + Controller
                .PC);
        char format;
        format = 'r';
        //get type from file
        String[] instArray;
        String temp;
        switch(format){
            case('r'):
                instArray = inst.split(" ");
                System.out.println("This is the Instruction as an array: " +
                        Arrays.toString(instArray));
                for (int i  = 0; i < instArray.length; i++){ //String s:
                // instArray){
                    instArray[i] = instArray[i].replace(",", "");
                }
                System.out.println("This is the instruction array after " +
                        "trying drop commas: " + Arrays.toString(instArray));
                //grabbing inst command
                temp = instArray[0].toLowerCase();
                System.out.println("this is the instruction: " + temp);
                // Adding the correct opcode to the instBin string
                instBin = instBin + opCodes.get(temp);
                System.out.println("This is the opcode: " + instBin);

                //TODO remove the magic num
                int[] registerNumbers = new int[3];

                for(int i = 0; i < instArray.length - 1; i++){
                    instArray[i+1] = instArray[i+1].replaceAll("[a-zA-Z]", "");

                    registerNumbers[i] = Integer.parseInt(instArray[i + 1]);
                }
                System.out.println("This is the register numbers array: " +
                        Arrays.toString(registerNumbers));
                // adding rm register binary
                String reg1,reg2,reg3;

                reg1 = Integer.toBinaryString(registerNumbers[2]);
                reg2 = Integer.toBinaryString(registerNumbers[1]);
                reg3 = Integer.toBinaryString(registerNumbers[0]);

                reg1 = correctBits(reg1);
                reg2 = correctBits(reg2);
                reg3 = correctBits(reg3);

                System.out.println("this is the bin of dReg: " + reg1);
                instBin = instBin + reg1;
                System.out.println("This is the bin after the adding the " +
                        "first register: " + instBin);
                // will need to check if a shamt is present but for now
                // assuming no
                instBin = instBin + "000000";
                System.out.println("This is the bin after the adding the " +
                        "shift amount: " + instBin);
                // adding rn register binary

                instBin = instBin + reg2;
                System.out.println("This is the bin of nReg: " + reg2);
                System.out.println("This is the bin after the adding the " +
                        "second register:" + instBin);
                // adding rd register binary

                instBin = instBin + reg3;
                System.out.println("this is this mReg: " + reg3);
                System.out.println("This is the bin after all additions: " +
                        instBin);
                break;
            case('i'):
                instArray = inst.split(" ");
                for (String s: instArray){
                    s = s.replace(",", "");
                }
                //grabbing  inst command
                temp = instArray[0].toLowerCase();
                //
                instBin = instBin + opCodes.get(temp);
                instBin = instBin;


                break;

            case('b'):

                break;

        }

        //TODO instruction to binary
        // Done by splitting up the instruction line and using each part of
        // the instruction(ie. the instruction name, which registers listed,
        // etc)
        // Use the multiple hashmaps, initialized by constructor, and will
        // map each part of the instruction to its binary equivalent and adds
        // each to the binary instruction field.

    }

    public String correctBits(String reg){
        String correct = reg;
        while (correct.length() < 5) {
            correct = "0" + correct;
        }
        return correct;
    }

    /**
     *
     */
    private void write(){
        ByteBuffer pcBuffer = ByteBuffer.allocate(8);
        ByteBuffer instBuffer = ByteBuffer.allocate(4);
        pcBuffer.order(ByteOrder.LITTLE_ENDIAN);
        pcBuffer.putLong(PC);
        byte[] PcBytes = pcBuffer.array();
        for(int i = 0; i < PcBytes.length; i++){
            ifidRegister[0][i] = PcBytes[i];
        }

        instBuffer = ByteBuffer.allocate(Long.BYTES);
        instBuffer.order(ByteOrder.LITTLE_ENDIAN);
        long temp = Long.valueOf(instBin, 2);
        instBuffer.putLong(temp);
        byte[] instBytes = instBuffer.array();
        for(int i = 0; i < instBytes.length; i++){
            ifidRegister[1][i] = instBytes[i];
        }

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
        instBin = "";
    }

}
