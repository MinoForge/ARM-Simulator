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
 * @version May 10, 2019
 *
 */
public class Controller implements Runnable {

    public static final int MEMORY_BYTES = 2048;
    public static final int TEXT_BASE_ADDRESS_OFFSET = 0x400000;
    public static final int CPS = 8;

    public static ByteOrder BYTE_ORDER;
    public static final int DEFAULT_REGISTER_NUM = 32;
    private static final int IFID_SIZE = 12;
    private static final int IDEX_SIZE = 34;
    private static final int EXMEM_SIZE = 25;
    private static final int MEMWB_SIZE = 17;



    private ArrayList<String> instructions;
    private ArrayList<String> progBins;

    private Assembler assembler;
    private Fetch fetcher;
    private Decode decoder;
    private Execute execute;
    private Access access;
    private Writeback writeback;

    private RegisterFile regFile;
    public  Register memory;
    private SysCall sysHandler;
    private int cycleCounter;



    //private  instructor;
    public static int PC = 0;
    public static int NUM_INSTRUCTIONS;

    private String filePath;
    private static AtomicBoolean halt;

    private Semaphore run;
    private Semaphore doCycle;
    private Semaphore doInstruction;
    private Semaphore doProgram;

    private int endOfInstructions;


    public Controller(String filePath, boolean littleEnd, Assembler assembler,
                      Semaphore run, Semaphore doCycle, Semaphore doInstruction,
                      Semaphore doProgram) {
        this.filePath = filePath;
        this.assembler = assembler;
        this.run = run;
        this.doCycle = doCycle;
        this.doInstruction = doInstruction;
        this.doProgram = doProgram;
        endOfInstructions = 0;

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

            try {
                run.acquire(2);

                if(doCycle.tryAcquire()) {
                    if(!cycle()) {
                        stop();
                    }
                }

                if(doProgram.tryAcquire()) {
                    if(cycle()) {
                        doProgram.release();
                    } else {
                        stop();
                    }
                    run.release();
                    Thread.sleep(1000/CPS); //Sleep for a time period.
                }

                if(doInstruction.tryAcquire()) {
                    if(doInstruction()) {

                    } else {
                        stop();
                    }
                    doInstruction.release();
                }

            } catch(InterruptedException ie) {
                System.err.println(Arrays.toString(ie.getStackTrace()));
            } finally {
                run.release();
            }

        }
        System.err.println("Out of runloop. Exiting Controller thread.");
    }

    public void assemble() {
        if(assembler.parseInput()) {

            this.instructions = assembler.getInstructionList();
            Controller.NUM_INSTRUCTIONS = instructions.size();
            this.progBins = assembler.makeBinaryList();


            setUpStack(); //stack initialization
            initRegisters();
            this.sysHandler = new SysCall(regFile, memory, run);

            Register ifid = new Register(IFID_SIZE);
            Register idex = new Register(IDEX_SIZE);
            Register exmem = new Register(EXMEM_SIZE);
            Register memwb = new Register(MEMWB_SIZE);

            fetcher = new Fetch(ifid, instructions.toArray(new String[0]), memory);
            decoder = new Decode(ifid, idex, regFile);
            execute = new Execute(idex, exmem, regFile, sysHandler);
            access = new Access(exmem, memwb, memory);
            writeback = new Writeback(memwb, regFile);
        }
    }

    private void initRegisters() {
        regFile.getRegister(31).zeroOut();
        regFile.lockRegister(31); //Zero Register
        regFile.getRegister(28).writeBinary(Integer.toBinaryString(MEMORY_BYTES)); //TODO change this to reflect true address instead of 'byte' address
    }


    /** Unimplemented. */
    private void setUpStack() {
        this.memory = new Register(MEMORY_BYTES);
//        System.err.println("Starting Stack setup");
        for(int i = 0; i < progBins.size(); i++) {
//            System.err.println("Writing Instruction to " + (i*4));
//            System.err.flush();
            System.err.println("Writing instruction :: " + progBins.get(i) + " :: to " + (i*4));
            memory.writeBinaryAtIndex(i*4, progBins.get(i));
        }

        printMemory();
    }

    public void printMemory() {
        long length = MEMORY_BYTES / 8;

        System.err.println(length);
        StringBuilder stackStr = new StringBuilder();
        for(int i = (int)length-2; i >= 0; i -= 1) {
            stackStr.append(String.format("%x ", Controller.TEXT_BASE_ADDRESS_OFFSET + i*8));
            stackStr.append(memory.getBinary(i*8, 8*i+4));
            stackStr.append(" ");
            stackStr.append(memory.getBinary(8*i+4, (i+1)*8));
            stackStr.append("\n");

        }
        System.err.println(stackStr.toString());
    }

    /** A single cycle of the cpu. */
    public boolean cycle() {
        int size = NUM_INSTRUCTIONS * 4;
        if(Controller.PC < size) {
            endOfInstructions = 0;
        }

        if(Controller.PC <= size) {
            writeback.execute();
            access.execute();
            execute.execute();
            decoder.execute();
            fetcher.execute();
            if(Controller.PC == size) {
                ControlUnit.setStageDataValid(0,false);
                if(endOfInstructions == 0) {
                    endOfInstructions++;
                } else if(endOfInstructions == 1) {
//                    ControlUnit.setStageDataValid(endOfInstructions, false);
                    if(ControlUnit.getValidData()[2]) {
                        ControlUnit.setStageDataValid(endOfInstructions, false);
                        endOfInstructions++;
                    }
                } else if(endOfInstructions >= 2 && endOfInstructions < 4) {
                    ControlUnit.setStageDataValid(endOfInstructions++, false);
                } else if(endOfInstructions == 4) {
                    return false;
                }




            }

            System.err.println("On cycle: " + cycleCounter++);
            return true;
        }
        return false;

    }

    /**Re-unimplemented */
    /** Cycles to the end of the instructions */
    public void cycleToEnd() {
        int size = instructions.size() * 4;
        while(Controller.PC < size) {
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
        if(Controller.PC < size) {
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
//            ControlUnit.setStageDataValid(0, true);
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
//    public void setHalt(boolean val) {
//        Controller.halt.set(val);
//    }

    /** Stops the simulator in place */
    public static void stop() {
        Controller.halt.set(true);
    }

    public static void start() {
        Controller.halt.set(false);
    }

    /** Resets the registers to their default values. Currently sets to testValues, rather than 0.*/
    public void reset() {
        regFile.reset();
        Controller.PC = 0;
        ControlUnit.flushPipe(0,4);
        ControlUnit.setStageDataValid(0, true);
//        setTestRegs();
        System.err.println(regFile.getRegister(28).getRegNum() + "!!");
        regFile.getRegister(28).writeBinaryAtIndex(0, PipelineSegment.correctBits(Integer.toBinaryString(MEMORY_BYTES), 64, 64));
    }




    /** Sets registers to testing values. */
    public void setTestRegs() {
        for(int i = 0; i < DEFAULT_REGISTER_NUM; i++) {
            regFile.getRegister(i).writeBinary(
                    PipelineSegment.correctBits(Long.toBinaryString((31-i)), 64, 64));
        }
    }




}
