package simulation.pipeline;

import simulation.Interface;
import simulation.SysCall;
import simulation.control.ControlUnit;
import simulation.registers.Register;
import simulation.registers.RegisterFile;

import java.util.ArrayList;


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
    private int localPC; //TODO change to long once Instructions are in
                         // register and get() in Register redone to accept
                         // longs.
    private long value1;
    private long value2;
    private long value3;
    private byte destReg;
    private long result;
    private ArrayList<Boolean> flags;
    private RegisterFile regFile;
    private SysCall sysHandler;

    /**
     * Constructor to make an Executor Class. Must have the IDEX pipeline
     * register implementation and the EXMEM register implementation.
     */
    public Execute(Register idex, Register exmem, RegisterFile regFile,
                   SysCall sysHandler) {
        this.exmemRegister = exmem;
        this.idexRegister = idex;
        this.regFile = regFile;
        this.sysHandler = sysHandler;
    }


    private void read(){
        System.out.println("Starting EXECUTE now:");
        String temp = ControlUnit.getInstruction(2);
        this.command = temp.substring(0,temp.indexOf(' ')).toLowerCase();
//        localPC = (int)(long)(idexRegister.getLong(0));
        localPC = (int)Long.parseUnsignedLong(idexRegister.getBinary(0, 8), 2);
        value1 = Long.parseUnsignedLong(idexRegister.getBinary(8,16),2);
        //TODO replace calls with getLong()
        if(!flags.get(3) && !flags.get(4)) { //ALUSrc deasserted
            System.out.println("Register instruction");
            value2 = Long.parseUnsignedLong(idexRegister.getBinary(16, 24), 2);
        }else{              //ALUSrc asserted
            System.out.println("Immediate instruction");
            System.out.println(idexRegister.getBinary(24,32));
            value2 = Long.parseUnsignedLong(idexRegister.getBinary(24,32), 2);
            System.out.println(value2);
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
        exmemRegister.append(correctBits(Long.toBinaryString(localPC), 64, 64));

        System.out.println("This is the result: " + Long.toBinaryString(result));
        exmemRegister.append(correctBits(Long.toBinaryString(result), 64, 64));

        System.out.println("This is the data from reg2:" +
                idexRegister.getBinary(16,24));
        exmemRegister.append(idexRegister.getBinary(16,24));



        System.out.println("This is the dest reg binary:" + idexRegister
                .getBinary(33,34).substring(3,8));
        exmemRegister.append(correctBits(idexRegister.getBinary(33,34)
                .substring(3,8), 8, 8));

        System.out.println(exmemRegister);
    }

    /**
     * Runs the Execute stage.
     */
    public void execute(){
        if(ControlUnit.getGoAhead(2)) {
            System.out.println("Starting Execute");
            flags = ControlUnit.getControlFlags(2);
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
                    System.out.println("Branching with offset: " + ((int)value2 << 2) + " and current pc: " + localPC);
                    localPC = localPC + ((int)value2 << 2);
                    break;

                case("blr"):
                    result = localPC + 4; //Set register for return, then fall through to br
                case("br"):
                    System.out.println("Branching with offset: " + ((int)value1 << 2) + " and current pc: " + localPC);
                    localPC = localPC + ((int)value2 << 2);
                    break;

                case("cbz"):
                    if(value1 == 0) {
                        System.out.println("Branching with offset: " + ((int)value2 << 2) + " and current pc: " + localPC);
                        localPC = localPC + ((int)value2 << 2);
                    }
                    break;
                case("cbnz"):
                    if(value1 != 0) {
                        System.out.println("Branching with offset: " + ((int)value2 << 2) + " and current pc: " + localPC);
                        localPC = localPC + ((int)value2 << 2);
                    }
                    break;

                case("ldur"):
                case("stur"):
                    result = value1 + value2;
                    break;

                case("lsl"):
                    result = value1 << value2;
                    break;
                case("lsr"):
                    result = value1 >> value2;
                    break;
                case("svc"):
                    syscall();
                    break;
                case("ldr"):
                    result = value2;
                    break;

            }
            write();
        } else {
            ControlUnit.setStageDataValid(3, false);
        }
    }

    public void syscall(){
        Register type = regFile.getRegister(8);
        int num = Integer.parseInt(type.getBinary(), 2);
        //System.out.print(">>> ");
        System.out.println("this is the type: " + num);
        sysHandler.setType(num);
        sysHandler.handle();


    }
}
