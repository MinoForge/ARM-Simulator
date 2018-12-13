package Simulation.Pipeline;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * A class to model the Execution segment of the ARM pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Executor extends PipelineSegment {
    private byte[][] exmemRegister;
    private byte[][] idexRegister;

    /**
     * Constructor to make an Executor Class. Must have the IDEX pipeline
     * register implementation and the EXEM register implementation.
     */
    public Executor(byte[][] idex, byte[][] exmem) {
        this.exmemRegister = exmem;
        this.idexRegister = idex;
    }

    /**
     * Interprets the information in the IDEX pipeline register.
     * @return an empty string at the moment
     */
    public String interpretPipeReg(){
        byte[] regContentsBytes = new byte[8];
        for(int i = 0; i < idexRegister[1].length; i++){
            regContentsBytes[i] = idexRegister[1][i];
        }
        ByteBuffer buf = ByteBuffer.allocate(Long.BYTES);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        buf.put(regContentsBytes);
        buf.flip();
        long temp = buf.getLong();
        //System.out.println("\nReading IDEX " +
        //        "register\n-------------------------" +
        //        "---------------------------------\n");
        //System.out.println("This is the contents of the destination
        // register" +
        //        " string: " + temp);

        buf.clear();
        for(int i = 0; i < idexRegister[2].length; i++){
            regContentsBytes[i] = idexRegister[2][i];
        }
        buf.put(regContentsBytes);
        buf.flip();
        temp = buf.getLong();
        //System.out.println("This is the contents of the the first operand " +
        //        "register" + " string: " + temp);

        buf.clear();
        for(int i = 0; i < idexRegister[3].length; i++){
            regContentsBytes[i] = idexRegister[3][i];
        }
        buf.put(regContentsBytes);
        buf.flip();
        temp = buf.getLong();
        //System.out.println("This is the contents of the the second operand " +
        //        "register" + " string: " + temp);

        return new String();
    }
    /**
     *
     */
    private void read(){



        // TODO a lot
        //It will have to run all the operations that come thorough, any
        // arithmetic, logic or branch calculation


    }

    /**
     *
     */
    private void write(){
        //TODO Add above to the ex/mem register
        // give the results of the executions to the ex/mem register.

    }

    /**
     *
     */
    public void execute(){
        read();
        write();

    }


}
