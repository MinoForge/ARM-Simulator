package simulation.pipeline;

import simulation.Register;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class meant for testing the respective pipeline stages; not a part of the
 * simulation process.
 * Created by Caleb on 11/29/2018.
 */
public class PipelineTester {

    private static final int DEFAULT_REGISTER_NUM = 32;
    private static final int IFID_SIZE = 12;
    private static final int IDEX_SIZE = 34;
    private static final int EXMEM_SIZE = 25;
    private static final int MEMWB_SIZE = 17;

    private Fetch fetch;
    private Decode decode;
    private Executor execute;
    private Accessor access;
    private Writer writeback;

    private Register ifid;
    private Register idex;
    private Register exmem;
    private Register memwb;

    private Register[] registerFile;

    private ArrayList<String> instructions;

    public PipelineTester() {
        registerFile = new Register[DEFAULT_REGISTER_NUM];
        for(int i = 0; i < DEFAULT_REGISTER_NUM; i++) {
            registerFile[i] = new Register(Register.DEFAULT_BYTE_SIZE);
        }

        ifid = new Register(IFID_SIZE);
        idex = new Register(IDEX_SIZE);
        exmem = new Register(EXMEM_SIZE);
        memwb = new Register(MEMWB_SIZE);

        Fetch fetcher = new Fetch(ifid);
        Decode decoder = new Decode(ifid, idex, registerFile);
        Executor executor = new Executor(idex, exmem);
        Accessor access = new Accessor(exmem, memwb);
        Writer writeBack = new Writer(memwb, registerFile);
    }

    /**
     * Runs a test of the Fetch and Decode stages.
     * @param args
     */
    public static void main(String... args){

        String test1 = "add r1, r2, r3";
        String test2 = "AND r1, r3, r4";

        PipelineTester test = new PipelineTester();
        test.test();
    }

    /**
     * The testing method. Currently only for demo. DOES NOT TEST FUNCTIONALITY
     * TODO Make tests actually useful?
     */
    public void test(){
        //test1
        fetch.execute();
        Scanner scanIn = new Scanner(System.in);
        scanIn.next();
        decode.execute();
        scanIn.next();
        //executor.interpretPipeReg();
        //test2
        fetch.execute();
        scanIn.next();
        decode.execute();
        scanIn.next();
        execute.interpretPipeReg();
    }
}
