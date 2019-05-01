package simulation;

import assembling.Assembler;
import simulation.control.ControlUnit;
import simulation.pipeline.*;
import simulation.registers.Register;
import simulation.registers.RegisterFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**
 * A class to handle statically available registers, and act as a controller for the pipeline
 * segments. Interaction point between GUI and pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Controller {

    public static final int MEMORY_BYTES = 1024;
    public static final int TEXT_BASE_ADDRESS_OFFSET = 0x400000;

    public static ByteOrder BYTE_ORDER;
    public static final int DEFAULT_REGISTER_NUM = 32;
    private static final int IFID_SIZE = 12;
    private static final int IDEX_SIZE = 34;
    private static final int EXMEM_SIZE = 25;
    private static final int MEMWB_SIZE = 17;



    private String[] data;
    private ArrayList<String> instructions;
    private ArrayList<String> instBins;

    private Assembler assembler;
    private Fetch fetcher;
    private Decode decoder;
    private Execute execute;
    private Access access;
    private Writeback writeback;

    private RegisterFile regFile;
    public Register memory;



    //private  instructor;
    public static int PC = 0;

    private String filePath;
    private static boolean halt;


    public Controller(String filePath, boolean littleEnd) {
        this.filePath = filePath;

        this.regFile = new RegisterFile();


        if(littleEnd) {
            BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;
        } else {
            BYTE_ORDER = ByteOrder.BIG_ENDIAN;
        }



//        this.data = assembler.getDataArray();
    }

    public void assemble() {
        this.assembler = new Assembler(filePath);
        this.instructions = assembler.getInstructionList();
        this.instBins = assembler.makeBinaryList();
        init();
//        System.out.println(instructions);
//        System.out.println("Calling setUpStack()");


        setUpStack(); //stack initialization
        initRegisters();
        readData(); //initialize data stuff

        Register ifid = new Register(IFID_SIZE);
        Register idex = new Register(IDEX_SIZE);
        Register exmem = new Register(EXMEM_SIZE);
        Register memwb = new Register(MEMWB_SIZE);

        fetcher = new Fetch(ifid, instructions.toArray(new String[0]),
                instBins.toArray(new String[0]), memory);
        decoder = new Decode(ifid, idex, regFile);
        execute = new Execute(idex, exmem);
        access = new Access(exmem, memwb, memory);
        writeback = new Writeback(memwb, regFile);
    }

    public void start() {
        halt = false;







    }

    private void initRegisters() {
        regFile.getRegister(31).zeroOut();
        regFile.lockRegister(31); //Zero Register
        regFile.getRegister(28).writeBinary(PipelineSegment.correctBits(Integer.toBinaryString(memory.toString().length()/32), 64, 64));
    }


    /**
     * Initializes the simulator, registers, stack, etc.
     */
    private void init() {
        int extra = 4;
        while(extra-- > 0) {
            instructions.add("add r31, r31, r31");
            instBins.add("10001011000111110000001111111111");//TODO change to nop
        }

        System.out.println(instructions.toString());



        //this.instructor = new Instructor();
    }

    /** Unimplemented */
    private void readData() {

    }

    /** Unimplemented. */
    private void setUpStack() {
        this.memory = new Register(instructions.size() * 4 + MEMORY_BYTES);
//        System.out.println("Starting Stack setup");
        for(int i = 0; i < instBins.size(); i++) {
//            System.out.println("Writing Instruction to " + (i*4));
//            System.out.flush();
            System.out.println("Writing instruction :: " + instBins.get(i) + " :: to " + (i*4));
            memory.writeBinaryAtIndex(i*4, PipelineSegment.correctBits(instBins.get(i), 32, 32));
        }
//        printMemory();
//        this.memory = new Register(0x7ffffffffc); //should be 0x7ffffffffc, but too long for int
        // TODO: 4/23/2019 Don't use up all the memory
    }

    public void printMemory() {
        String str = memory.toString();
        long length = str.length()/32;
        System.out.println(length);
        StringBuilder stackStr = new StringBuilder();
        for(int i = 0; i < length; i++) {
            stackStr.append(i*4 + " ");
            stackStr.append(memory.getBinary(i*4, (i+1)*4));
            stackStr.append('\n');
        }
        System.out.println(stackStr.toString());
    }

    /** A single cycle of the cpu. */
    public boolean cycle() {
        int size = instructions.size() * 4;
        if(Controller.PC < size) {
            writeback.execute();
            access.execute();
            execute.execute();
            decoder.execute();
            fetcher.execute();
            return true;
        }
        return false;

    }

    /** Cycles to the end of the instructions */
    public void cycleToEnd() {
        int size = instructions.size() * 4;
        while(Controller.PC < size && !halt) {
            writeback.execute();
            access.execute();
            execute.execute();
            decoder.execute();
            fetcher.execute();
        }

    }

    /** Runs a single instruction from Fetch to Writeback. */
    public boolean doInstruction() {
        int size = instructions.size() * 4;
        if(Controller.PC < size - 4) {
            fetcher.execute();
            decoder.execute();
            execute.execute();
            access.execute();
            writeback.execute();
            ControlUnit.flushPipe(0,4);
            ControlUnit.setStageDataValid(0, true);
//            printMemory();
            return true;
        }
        return false;
    }


    /** Returns the registerFile. */
    public RegisterFile getRegFile() {
        return regFile;
    }

    /** Returns the text from the open file. */
    public String getFile(String file) {
        StringBuilder str = new StringBuilder();
        try {
            Scanner scan = new Scanner(new File(file));
            while(scan.hasNextLine()) {
                str.append(scan.nextLine());
                if(scan.hasNextLine()) {
                    str.append("\n");
                }
            }
        } catch(FileNotFoundException fnfe) {
            System.err.println("File not found.");
        }
        return str.toString();
    }

    /** Sets the halt boolean to a value. If true, the simulator should stop when checked. */
    public void setHalt(boolean val) {
        Controller.halt = val;
    }

    /** Stops the simulator and flushes all pipeline values. */
    public static void stop() {
        Controller.halt = true;
        Controller.PC = 0;
        ControlUnit.flushPipe(0,4);
        ControlUnit.setStageDataValid(0, true);
    }

    /** Resets the registers to their default values. Currently sets to testValues, rather than 0.*/
    public void resetReg() {
        regFile.reset();
        setTestRegs();
    }


    /** Sets registers to testing values. */
    public void setTestRegs() {
        for(int i = 0; i < DEFAULT_REGISTER_NUM; i++) {
            regFile.getRegister(i).writeBinary(
                    PipelineSegment.correctBits(Long.toBinaryString((31-i)), 64, 64));
        }
    }



}
