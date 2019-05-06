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
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * A class to handle statically available registers, and act as a controller for the pipeline
 * segments. Interaction point between GUI and pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Controller implements Runnable {

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
    private ArrayList<String> progBins;

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
    public static int NUM_INSTRUCTIONS;

    private String filePath;
    private static AtomicBoolean halt;

    private Semaphore run;
    private Semaphore doCycle;
    private Semaphore doInstruction;
    private Semaphore doProgram;


    public Controller(String filePath, boolean littleEnd, Assembler assembler,
                      Semaphore run, Semaphore doCycle, Semaphore doInstruction,
                      Semaphore doProgram) {
        this.filePath = filePath;
        this.assembler = assembler;
        this.run = run;
        this.doCycle = doCycle;
        this.doInstruction = doInstruction;
        this.doProgram = doProgram;

        this.regFile = new RegisterFile();


        if(littleEnd) {
            BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;
        } else {
            BYTE_ORDER = ByteOrder.BIG_ENDIAN;
        }

        halt = new AtomicBoolean(false);

    }

    public void run() {
        while(!halt.get()) {
            System.out.println("Halt is set to before: " + halt);
            try {
                run.acquire(2);
                System.out.println("Hey");
                if(doCycle.tryAcquire()) {
                    if(!cycle()) {
                        halt.set(true);
                    }
                    doCycle.release();
                }
                if(doProgram.tryAcquire()) {

                    if(!cycle()) {
                        halt.set(true);
                    }
                    doProgram.release();
                    run.release();
                }
                if(doInstruction.tryAcquire()) {
                    System.out.println("Hello");
                    if(!doInstruction()) {
                        halt.set(true);
                    }
                    doInstruction.release();
                }

            } catch(InterruptedException ie) {
                System.out.println(Arrays.toString(ie.getStackTrace()));
            } finally {
                run.release();
//                try {
//                    Thread.sleep(100);
//                } catch(InterruptedException ie){}
            }
            System.out.println("Halt is set to after: " + halt);
            System.out.flush();

        }
        System.out.println("Out of runloop.");
    }

    public void assemble() {
//        this.assembler = new Assembler(filePath);
        this.instructions = assembler.getInstructionList();
        Controller.NUM_INSTRUCTIONS = instructions.size();
        this.progBins = assembler.makeBinaryList();
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
                progBins.toArray(new String[0]), memory);
        decoder = new Decode(ifid, idex, regFile);
        execute = new Execute(idex, exmem, regFile);
        access = new Access(exmem, memwb, memory);
        writeback = new Writeback(memwb, regFile);
    }

    public void start() {
        Controller.halt.set(false);
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
            progBins.add("10001011000111110000001111111111");//TODO change to nop
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
        for(int i = 0; i < progBins.size(); i++) {
//            System.out.println("Writing Instruction to " + (i*4));
//            System.out.flush();
            System.out.println("Writing instruction :: " + progBins.get(i) + " :: to " + (i*4));
            memory.writeBinaryAtIndex(i*4, PipelineSegment.correctBits(progBins.get(i), 32, 32));
        }
        printMemory();
    }

    public void printMemory() {
        String str = memory.toString();
        long length = str.length()/32;
        System.out.println(length);
        StringBuilder stackStr = new StringBuilder();
        for(int i = 0; i < length; i++) {
            stackStr.append(Controller.TEXT_BASE_ADDRESS_OFFSET + i*4 + " ");
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
        while(Controller.PC < size && !halt.get()) {
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
            Controller.PC += 4;
            execute.execute();
            Controller.PC += 4;
            access.execute();
            Controller.PC += 4;
            writeback.execute();
            Controller.PC -= 12;
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
        Controller.halt.set(val);
    }

    /** Stops the simulator in place */
    public static void stop() {
//        Controller.halt.set(true);
    }

    /** Resets the registers to their default values. Currently sets to testValues, rather than 0.*/
    public void reset() {
        regFile.reset();
        Controller.PC = 0;
        ControlUnit.flushPipe(0,4);
        ControlUnit.setStageDataValid(0, true);
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
