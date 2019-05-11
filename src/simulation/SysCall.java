package simulation;


import simulation.pipeline.PipelineSegment;
import simulation.registers.Register;
import simulation.registers.RegisterFile;

import java.util.concurrent.Semaphore;

/**
 * Class that will handle all system calls, only handles printing to screen
 * and accepting user input.
 *
 * @author Peter Gardner
 * @author Caleb Dinehart
 * @version May 10, 2019
 */
public class SysCall {

    private int type;
    private int address;
    private RegisterFile regFile;
    private Register memory;
    private Semaphore run;

    public SysCall(RegisterFile regFile, Register memory, Semaphore run){
        this.type = 0;
        this.address = 0;
        this.regFile = regFile;
        this.memory = memory;
        this.run = run;
    }

    /**
     * Handles the system call that is being requested. goes into the
     * register file to find the arguments for the system call.
     */
    public void handle(){
        byte[] temp;
        StringBuilder str = new StringBuilder();
        this.address = Integer.parseInt(regFile.getRegister(0).getBinary(),2);
//        System.out.println("Address of print " + address);
        int offset = Integer.parseInt(regFile.getRegister(1).getBinary(),2);
        switch (type) { // check what syscall we are doing

            case 1: //1 is OUR value for printing integer to screen
                System.out.print(address);
                System.out.flush();
                break;

            case 64: //64 is the value for printing to screen
                if(offset == 0) {
                    offset = memory.indexOf(address, (byte)0) - address;
                }

                temp = memory.getBytes(address, address + offset);

                for (int i = 0; i < temp.length; i++) {
                    byte b = temp[i];
                    char c;
                    if(((Byte) b).toString().charAt(0) == '-') {
                        //Total honesty, I don't think this should work
                        c = (char)(b + 128);
                    } else {
                        c = (char) b;
                    }
                    str.append(c);
                }

                System.out.print(str.toString());
                System.out.flush();
                break;

            case 63: // value for reading input from the keyboard.
                System.out.flush();


                String input = Interface.getNewInput();
                input = PipelineSegment.correctBits(Long.toBinaryString(Long.parseLong(input)),64,64);
                regFile.getRegister(0).writeBinaryAtIndex(0, input);

                break;

            case 93: //System exit number

                Controller.stop();
                break;


        }
        for(int i = 0; i < 9; i++) {
            regFile.freeRegister(i);
        }


    }

    public void setType(int type){
        this.type = type;
    }

}

