package simulation.pipeline;

import simulation.Assembler;
import simulation.Driver;
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
    private Execute execute;
    private Access access;
    private Writeback writeback;

    private Register ifid;
    private Register idex;
    private Register exmem;
    private Register memwb;

    private Register[] registerFile;

    private String[] instructions;

    public PipelineTester() {
        registerFile = new Register[DEFAULT_REGISTER_NUM];
        for(int i = 0; i < DEFAULT_REGISTER_NUM; i++) {
            registerFile[i] = new Register(Register.DEFAULT_BYTE_SIZE);
            registerFile[i].writeBinary(PipelineSegment.correctBits(Integer.toBinaryString(i) + "", 64)); //TODO change to writeLong()
            System.out.println("Register#" + i + " :: " + registerFile[i]);
        }


        ifid = new Register(IFID_SIZE);
        idex = new Register(IDEX_SIZE);
        exmem = new Register(EXMEM_SIZE);
        memwb = new Register(MEMWB_SIZE);

        fetch = new Fetch(ifid);
        decode = new Decode(ifid, idex, registerFile);
        execute = new Execute(idex, exmem);
        access = new Access(exmem, memwb);
        writeback = new Writeback(memwb, registerFile);
    }


    /**
     * Runs a test of the Fetch and Decode stages.
     * @param args
     */
    public static void main(String... args){


//        String test1 = "add r1, r2, r3";
//        String test2 = "AND r1, r3, r4";
//        String test3 = "orr r1, r2, r5";
//        String test4 = "add r4, r7, r9";
//        String test5 = "add r4, r5, r1";

        Assembler assemble = new Assembler("TestFile.txt");
        ArrayList<String> bin = new ArrayList<>();
        if(assemble.parseInput()){
            bin = assemble.makeBinaryList();
            for (String s : bin) {
                System.out.println(s);
            }
        }
        PipelineTester test = new PipelineTester();
//        test.instructions = new String[] {test1, test2, test3, test4, test5};
        test.fetch.setInstructions(assemble.getInstructionArray());
        test.fetch.setBins(bin);
        test.test();
    }

    /**
     * The testing method. Currently only for demo. DOES NOT TEST FUNCTIONALITY
     * TODO Make tests actually useful?
     */
    public void test(){
        //test1
        Scanner scanIn = new Scanner(System.in);
        fetch.execute();
        scanIn.next();
        decode.execute();
        scanIn.next();
        execute.execute();
        scanIn.next();
        access.execute();
        scanIn.next();
        writeback.execute();
        scanIn.next();




        //executor.interpretPipeReg();
        //test2
        fetch.execute();
        scanIn.next();
        decode.execute();
        scanIn.next();

    }
}
