package Simulation;

import Simulation.Pipeline.*;

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


    public Controller(String[] data, String[] instructions) {
        this.data = data;
        this.instructions = instructions;
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

        byte[] ifid = new byte[24];
        byte[] idex = new byte[64];
        byte[] exmem = new byte[48];
        byte[] memwb = new byte[32];

        fetcher = new Fetcher(ifid);
        decoder = new Decoder(ifid, idex);
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
