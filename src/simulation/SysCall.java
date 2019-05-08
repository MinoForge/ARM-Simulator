package simulation;

import simulation.registers.Register;
import simulation.registers.RegisterFile;

import java.io.UnsupportedEncodingException;

/**
 *Created by Caleb on 5/7/2019.
 * Class that will handle all system calls, only handles printing to screen
 * and accepting user input.
 */
public class SysCall {

    private int type;
    private int address;
    private RegisterFile regFile;
    private Register memory;

    public SysCall(RegisterFile regFile, Register memory){
        this.type = 0;
        this.address = 0;
        this.regFile = regFile;
        this.memory = memory;
    }

    /**
     * Handles the system call that is being requested. goes into the
     * register file to find the arguments for the system call.
     */
    public void handle(){
        byte[] temp;
        StringBuilder str = new StringBuilder();
        this.address = Integer.parseInt(regFile.getRegister(0).getBinary(),2);
        System.out.println("Address of print " + address);
        int offset = Integer.parseInt(regFile.getRegister(1).getBinary(),2);
        switch (type) {

            case 64: //64 is the value for printing to screen
                if(offset == 0) {
                    offset = memory.indexOf(address, (byte)0) - address;
                }

                temp = memory.getBytes(address, address + offset);

                for (byte b : temp) {
                    char c;
                    if(((Byte) b).toString().charAt(0) == '-') {
                        //Total honesty, I don't think this should work
                        c = (char)(b + 128);
                    } else {
                        c = (char) b;
                    }
                    str.append(c);
                }

                System.out.println(str.toString());
                System.out.flush();
                break;

            case 63: // value for reading input from the keyboard.
                System.out.flush();

                Interface.setBeforeInputLength(Interface.getAreas()[1].getLength());
                Interface.getInput().release();
                try {
                    Interface.getInput().acquire(3);
                } catch(InterruptedException ie) {
                    //
                }
                Interface.getInput().release(4);
                String input = Interface.getNewInput();

                break;
        }


    }

    public void setType(int type){
        this.type = type;
    }

}

