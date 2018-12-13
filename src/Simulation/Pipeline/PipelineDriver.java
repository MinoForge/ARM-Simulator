package Simulation.Pipeline;

/**
 * Class meant for testing the respective pipeline stages; not a part of the
 * simulation process.
 * Created by Caleb on 11/29/2018.
 */
public class PipelineDriver {

    private static final int DEFAULT_REGISTER_NUM = 32;
    private static final int IFID_SIZE = 2;
    private static final int IDEX_SIZE = 4;
    private static final int EXMEM_SIZE = 3;
    private static final int MEMWB_SIZE = 2;

    /**
     * Runs a test of the Fetch and Decode stages.
     * @param args
     */
    public static void main(String... args){

        String test1 = "add r1, r2, r3";
        String test2 = "AND r1, r3, r4";

        long[] registers = new long[DEFAULT_REGISTER_NUM];
        for(int i = 0; i < DEFAULT_REGISTER_NUM; i++) {
            registers[i] = 0;
        }
        byte[][] ifid = new byte[IFID_SIZE][8];
        byte[][] idex = new byte[IDEX_SIZE][8];

        //Not being tested yet
        byte[][] exmem = new byte[EXMEM_SIZE][8];
        //byte[] memwb = new byte[MEMWB_SIZE];

        Fetcher fetcher = new Fetcher(ifid, test1, test2);
        Decoder decoder = new Decoder(ifid, idex, registers);
        Executor executor = new Executor(idex, exmem);
        test(fetcher,decoder,executor);
    }

    /**
     * The testing method
     * @param fetcher
     * @param decoder
     * @param executor
     */
    public static void test(Fetcher fetcher, Decoder decoder, Executor executor){
        //test1
        fetcher.execute();
        decoder.execute();
        executor.interpretPipeReg();
        //test2
        fetcher.execute();
        decoder.execute();
        executor.interpretPipeReg();
    }
}
