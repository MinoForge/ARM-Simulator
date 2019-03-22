package simulation;

import simulation.pipeline.*;
import simulation.registers.Register;

import java.nio.ByteOrder;


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
    private static final int DEFAULT_REGISTER_NUM = 32;
    private static final int IFID_SIZE = 12;
    private static final int IDEX_SIZE = 34;
    private static final int EXMEM_SIZE = 25;
    private static final int MEMWB_SIZE = 17;


    private String[] data;
    private String[] instructions;

    private Fetch fetcher;
    private Decode decoder;
    private Execute execute;
    private Access access;
    private Writeback writeback;


    //private  instructor;
    public static int PC = 0;


    public Controller(String file, boolean littleEnd) {
        //TODO read in from file to data and instructions.
//        this.data = data;
//        this.instructions = instructions;
        //init(this.data);
        if(littleEnd) {
            BYTE_ORDER = ByteOrder.LITTLE_ENDIAN;
        } else {
            BYTE_ORDER = ByteOrder.BIG_ENDIAN;
        }
    }


    /**
     * Initializes the simulator, registers, stack, etc.
     */
    public void init(String[] data) {
        Register[] registers = new Register[DEFAULT_REGISTER_NUM];
//        for(int i = 0; i < DEFAULT_REGISTER_NUM; i++) {
//            registers[i] = 0;
//        }

        Register ifid = new Register(IFID_SIZE);
        Register idex = new Register(IDEX_SIZE);
        Register exmem = new Register(EXMEM_SIZE);
        Register memwb = new Register(MEMWB_SIZE);

        fetcher = new Fetch(ifid);
        decoder = new Decode(ifid, idex, registers);
//        execute = new Execute(idex, exmem);
        //access = new Access(exmem, memwb);
        //writeback = new Writeback(memwb);



        readData(); //initialize data stuff
        setUpStack(); //stack initialization

        //this.instructor = new Instructor();
    }

    private void readData() {

    }

    private void setUpStack() {

    }




    public void cycle() {
        while(Controller.PC < instructions.length * 4) {
            writeback.execute();
            access.execute();
            execute.execute();
            decoder.execute();
            fetcher.execute();
        }
    }





}
