package simulation.pipeline;

import simulation.ControlUnit;
import simulation.Register;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
//
///**
// * A class to model the Execution segment of the ARM pipeline.
// *
// * @author Caleb Dinehart
// * @author Peter Gardner
// * @version November 8, 2018
// *
// */
public class Executor extends PipelineSegment {
    private Register exmemRegister;
    private Register idexRegister;
    private String command;
    private long value1;
    private long value2;
    private long result;
    private long destReg;



//    /**
//     * Constructor to make an Executor Class. Must have the IDEX pipeline
//     * register implementation and the EXEM register implementation.
//     */
    public Executor(Register idex, Register exmem) {
        this.exmemRegister = exmem;
        this.idexRegister = idex;
    }

    /**
     * Interprets the information in the IDEX pipeline register.
     * @return an empty string at the moment
     */
    public String interpretPipeReg(){
       byte[] regContentsBytes = new byte[8];



//        for(int i = 0; i < idexRegister[1].length; i++){
//            regContentsBytes[i] = idexRegister[1][i];
//        }
////        ByteBuffer buf = ByteBuffer.allocate(Long.BYTES);
////        buf.order(ByteOrder.LITTLE_ENDIAN);
////        buf.put(regContentsBytes);
////        buf.flip();
////        long temp = buf.getLong();
//        //System.out.println("\nReading IDEX " +
//        //        "register\n-------------------------" +
//        //        "---------------------------------\n");
//        //System.out.println("This is the contents of the destination
//        // register" +
//        //        " string: " + temp);
//
//        buf.clear();
//        for(int i = 0; i < idexRegister[2].length; i++){
//            regContentsBytes[i] = idexRegister[2][i];
//        }
//        buf.put(regContentsBytes);
//        buf.flip();
//        temp = buf.getLong();
//        //System.out.println("This is the contents of the the first operand " +
//        //        "register" + " string: " + temp);
//
//        buf.clear();
//        for(int i = 0; i < idexRegister[3].length; i++){
//            regContentsBytes[i] = idexRegister[3][i];
//        }
//        buf.put(regContentsBytes);
//        buf.flip();
//        temp = buf.getLong();
//        //System.out.println("This is the contents of the the second operand " +
//        //        "register" + " string: " + temp);
        idexRegister.getBinary();


        return new String();
    }
//    /**
//     *
//     */
    private void read(){
        String temp = ControlUnit.getInstruction();
        this.command = temp.substring(0,6)
        switch (command){
            case("add"):
                result = value1 + value2;
                break;


            case("and"):
                result = value1 & value2;
                break;


            case("sub"):
                result = value1 - value2;
                break;

            case("or"):
                result = value1 | value2;
                break;

            case("branch"):


        }


        // TODO a lot
        //It will have to run all the operations that come thorough, any
        // arithmetic, logic or branch calculation


    }
//
//    /**
//     *
//     */
    private void write(){
        // TODO Add above to the ex/mem register
        // give the results of the executions to the ex/mem register.
        exmemRegister.append(Long.toBinaryString(result));

    }

    /**
     *
     */
    public void execute(){
        read();
        write();

    }
//
//
}
