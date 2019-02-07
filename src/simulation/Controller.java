package simulation;

import simulation.pipeline.*;

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
    private static final int IFID_SIZE = 2;
    private static final int IDEX_SIZE = 4;
    private static final int EXMEM_SIZE = 3;
    private static final int MEMWB_SIZE = 2;


    private String[] data;
    private String[] instructions;

    private Fetch fetcher;
    private Decode decoder;
    private Executor executor;
    private Accessor accessor;
    private Writer writer;


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

        Register ifid = new Register(12);
        Register idex = new Register(32);
        Register exmem = new Register(24);
        Register memwb = new Register(16);

        fetcher = new Fetch(ifid);
        decoder = new Decode(ifid, idex, registers);
//        executor = new Executor(idex, exmem);
        //accessor = new Accessor(exmem, memwb);
        //writer = new Writer(memwb);



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
            writer.execute();
            accessor.execute();
            executor.execute();
            decoder.execute();
            fetcher.execute();
        }
    }





}
