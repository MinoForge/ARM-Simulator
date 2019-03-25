package simulation;

import assembling.Assembler;
import simulation.pipeline.*;
import simulation.registers.Register;
import simulation.registers.RegisterFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.ByteOrder;
import java.util.ArrayList;
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



    //private  instructor;
    public static int PC = 0;


    public Controller(String file, boolean littleEnd) {
        //TODO read in from file to data and instructions.
        this.assembler = new Assembler(file);
        this.regFile = new RegisterFile();
        if(littleEnd) {
            BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;
        } else {
            BYTE_ORDER = ByteOrder.BIG_ENDIAN;
        }
//        this.data = assembler.getDataArray();
        this.instructions = assembler.getInstructionList();
        this.instBins = assembler.makeBinaryList();
        init();
    }


    /**
     * Initializes the simulator, registers, stack, etc.
     */
    public void init() {
        int extra = 4;
        while(extra-- > 0) {
            instructions.add("add r31, r31, r31");
            instBins.add("10001011000111110000001111111111");//TODO change to nop
        }
        System.out.println(instructions.toString());

        Register ifid = new Register(IFID_SIZE);
        Register idex = new Register(IDEX_SIZE);
        Register exmem = new Register(EXMEM_SIZE);
        Register memwb = new Register(MEMWB_SIZE);

        fetcher = new Fetch(ifid, instructions.toArray(new String[0]), instBins.toArray(new String[0]));
        decoder = new Decode(ifid, idex, regFile);
        execute = new Execute(idex, exmem);
        access = new Access(exmem, memwb);
        writeback = new Writeback(memwb, regFile);



        readData(); //initialize data stuff
        setUpStack(); //stack initialization

        //this.instructor = new Instructor();
    }

    private void readData() {

    }

    private void setUpStack() {

    }




    public void cycle() {
        int size = instructions.size() * 4;
        System.out.println(size);
//        Scanner scanin = new Scanner(System.in);
        while(Controller.PC < size) {
            writeback.execute();
            access.execute();
            execute.execute();
            decoder.execute();
            fetcher.execute();
//            scanin.nextLine();
        }
    }

    public void setTestRegs() {
        for(int i = 0; i < DEFAULT_REGISTER_NUM; i++) {
            regFile.getRegister(i).writeBinary(
                    PipelineSegment.correctBits(Integer.toBinaryString(i), 64));
        }
    }

    public RegisterFile getRegFile() {
        return regFile;
    }

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





}
