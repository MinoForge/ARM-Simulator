package Simulation.Pipeline;

import Simulation.Controller;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
    private byte[] ifidRegister;
    private String instBin;
    private HashMap<String,String> opCodes;



    /**
     *  Constructor that takes the instruction to fetch.
     * @param ifid the  to fetch.
     */
    public Fetcher(byte[] ifid, String... instructions) {
        this.instructions = instructions;
        this.ifidRegister = ifid;
        this.instBin = "";
        this.opCodes = new HashMap<>();
        //TODO Will be put into a file
        this.opCodes.put("add", "10001011000");
        this.opCodes.put("and", "10001010000");
        this.opCodes.put("addi","1001000100");
        this.opCodes.put("","");
    }

    /**
     *
     */
    private void read(){
        String inst = instructions[Controller.PC/4];
        PC+=4;
        char format;
        format = 'r';
        //get type from file
        String[] instArray;
        String temp;
        switch(format){
            case('r'):
                instArray = inst.split(" ");
                for (String s: instArray){
                    s = s.replace(",", "");
                }
                //grabbing inst command
                temp = instArray[0].toLowerCase();
                // Adding the correct opcode to the instBin string
                instBin = instBin + opCodes.get(temp);

                int[] registerNumbers = new int[3];
                for(int i = 0; i < instArray.length - 1; i++){
                    instArray[i+1] = instArray[i+1].replace("[a-zA-Z]", "");
                    registerNumbers[i] = Integer.parseInt(instArray[i + 1]);
                }
                // adding rm register binary
                temp = Integer.toBinaryString(registerNumbers[2]);
                instBin = instBin + temp;
                // will need to check if a shamt is present but for now
                // assuming no
                instBin = instBin + "000000";
                // adding rn register binary
                temp = Integer.toBinaryString(registerNumbers[1]);
                instBin = instBin + temp;
                // adding rd register binary
                temp = Integer.toBinaryString(registerNumbers[0]);
                instBin = instBin + temp;
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
        // the instruction(ie the instruction name, which registers listed, etc)
        // Use the multiple hashmaps, initialized by constructor, and will
        // map each part of the instruction to its binary equivalent and adds
        // each to the binary instruction field.

    }

    /**
     *
     */
    private void write(){
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putLong(PC);
        int temp = Integer.valueOf(instBin, 2);
        buffer.putInt(temp);
        byte[] PcBytes = buffer.array();
        for(int i = 0; i < PcBytes.length; i++){
            ifidRegister[i] = PcBytes[i];
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

    }

}
