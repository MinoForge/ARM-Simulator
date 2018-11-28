package Simulation;

import Simulation.Pipeline.*;

import java.io.File;


/**
 * A class to handle statically available registers, and act as a controller for the pipeline
 * segments.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Controller {

    private int DEFAULT_REGISTER_NUM = 13;

    private String[] data;
    private String[] instructions;

    private Fetcher fetcher;
    private Decoder decoder;
    private Executor executor;
    private Accessor accessor;
    private Writer writer;


    //private  instructor;
    public static int PC = 0;


    public Controller(File file) {
        //TODO read in from file to data and instructions.
//        this.data = data;
//        this.instructions = instructions;
        init(this.data);

    }


    /**
     * Initializes the simulator, registers, stack, etc.
     */
    public void init(String[] data) {
        long[] registers = new long[DEFAULT_REGISTER_NUM];
        for(int i = 0; i < DEFAULT_REGISTER_NUM; i++) {
            registers[i] = 0;
        }

        byte[] ifid = new byte[12];
        byte[] idex = new byte[32];
        byte[] exmem = new byte[24];
        byte[] memwb = new byte[16];

        fetcher = new Fetcher(ifid);
        decoder = new Decoder(ifid, idex, registers);
        executor = new Executor(idex, exmem);
        accessor = new Accessor(exmem, memwb);
        writer = new Writer(memwb);



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
