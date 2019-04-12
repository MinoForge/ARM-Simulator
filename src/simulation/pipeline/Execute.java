package simulation.pipeline;

import simulation.Controller;
import simulation.control.ControlUnit;
import simulation.registers.Register;

import java.util.ArrayList;

import static simulation.control.ControlUnit.ASSERT;


/**
 * A class to model the Execution segment of the ARM pipeline.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Execute extends PipelineSegment {
    private Register exmemRegister;
    private Register idexRegister;
    private String command;
    private int localPC; //TODO change to long once Instructions are in register and get() in Register redone to accept longs.
    private long value1;
    private long value2;
    private long value3;
    private byte destReg;
    private long result;
    private ArrayList<Boolean> flags;





    /**
     * Constructor to make an Executor Class. Must have the IDEX pipeline
     * register implementation and the EXMEM register implementation.
     */
    public Execute(Register idex, Register exmem) {
        this.exmemRegister = exmem;
        this.idexRegister = idex;
    }


    private void read(){
        System.out.println("Starting EXECUTE now:");
        String temp = ControlUnit.getInstruction(2);
        this.command = temp.substring(0,temp.indexOf(' ')).toLowerCase();
//        localPC = (int)(long)(idexRegister.getLong(0));
        localPC = (int)Long.parseUnsignedLong(idexRegister.getBinary(0, 8), 2);
        value1 = Long.parseUnsignedLong(idexRegister.getBinary(8,16),2); //TODO replace calls with getLong()
        if(!flags.get(3) && !flags.get(4)) { //ALUSrc deasserted
            System.out.println("Register instruction");
            value2 = Long.parseUnsignedLong(idexRegister.getBinary(16, 24), 2);
        }else{              //ALUSrc asserted
            System.out.println("Immediate instruction");
            System.out.println(idexRegister.getBinary(24,32));
            value2 = Long.parseUnsignedLong(idexRegister.getBinary(24,32), 2);
        }
        destReg = idexRegister.getBytes(32, 33)[0];
        System.out.println(value1);
        System.out.println(value2);
        // TODO a lot
        //TODO read in value3? Not sure how far back that needs to go.
        //It will have to run all the operations that come thorough, any
        // arithmetic, logic or branch calculation


    }

    private void write(){
        // TODO Add above to the ex/mem register
        // give the results of the executions to the ex/mem register.

        System.out.println("This is the local PC change : " + localPC);
        exmemRegister.append(correctBits(Long.toBinaryString(localPC), 64));

        System.out.println("This is the result: " + Long.toBinaryString(result));
        exmemRegister.append(correctBits(Long.toBinaryString(result), 64));

        System.out.println("This is the data from reg2:" +
                idexRegister.getBinary(16,24));
        exmemRegister.append(idexRegister.getBinary(16,24));



        System.out.println("This is the dest reg binary:" + idexRegister
                .getBinary(33,34).substring(3,8));
        exmemRegister.append(correctBits(idexRegister.getBinary(33,34).substring(3,8), 8));

        System.out.println(exmemRegister);
    }

    /**
     * Runs the Execute stage.
     */
    public void execute(){
        if(ControlUnit.getGoAhead(2)) {
            flags = ControlUnit.getControlFlags(2);
//            command = ControlUnit.getInstruction(2);
//            command = command.substring(0, command.indexOf(' ');)
//            System.out.println(command);
            read();
            exmemRegister.zeroOut();
            System.out.println(command);

            switch (command){
                case("add"):
                    result = value1 + value2;
                    break;

                case("sub"):
                    result = value1 - value2;
                    break;

                case("mov"):
                    value2 = 0L; //Set the second value to 0 to be sure, then fall to orr
                case("orr"):
                    result = value1 | value2;
                    break;

                case("and"):
                    result = value1 & value2;
                    break;

                case("mul"):
                    value3 = 0L; //Set the added value to 0 to be sure, then fall to madd
                case("madd"):
                    result = value1 * value2 + value3;
                    break;

                case("div"):
                    if(value2 == 0) { //Div by zero, but no errors? By manual.
                        result = 0;
                    } else {
                        result = value1 / value2;
                    }
                    break;


                case("bl"): //Set register for return, then fall through to b
                    result = localPC + 4;
                case("b"):
//                    System.out.println("Branching with offset: " + ((int)value2 << 2) + " and current pc: " + localPC);
                    localPC = (int)value2 << 2;
                    break;

                case("blr"):
                    result = localPC + 4;
                case("br"):
                    localPC = (int)value1;
                    break;

                case("cbz"):
                    if(value1 == 0) {
                        localPC = (int)value1;
                    }
                    break;
                case("cbnz"):
                    if(value1 != 0) {
                        localPC = (int)value1;
                    }
                    break;

                case("ldur"):
                    // TODO: 4/8/2019 CRY
                    break;

                case("stur"):
                    // TODO: 4/8/2019 CRY
                    break;
            }
            write();
        }
    }
//
//
}
