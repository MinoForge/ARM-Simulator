package simulation.registers;

import simulation.Controller;

/**
 * A class to hold and keep track of CPU register states.
 *
 * @author Peter Gardner
 * @version 3/22/2019
 */
public class RegisterFile {

    /** An array of registers, and arrays of how they are being used. */
    private Register[] registers;
    private boolean[] beingRead;
    private boolean[] beingWritten;

    /**
     * Default constructor which creates 0 registers, with 0 bytes capacity.
     */
    public RegisterFile() {
        this(Controller.DEFAULT_REGISTER_NUM, Register.DEFAULT_BYTE_SIZE);
    }

    /**
     * Creates a Register file.
     * @param numRegisters The number of registers in this file.
     * @param numBytes The number of bytes which each register will hold.
     */
    public RegisterFile(int numRegisters, int numBytes) {
        this.registers = new Register[numRegisters];
        this.beingRead = new boolean[numRegisters];
        this.beingWritten = new boolean[numRegisters];
        for(int i = 0; i < numRegisters; i++) {
            this.registers[i] = new Register(numBytes);
            this.beingRead[i] = false;
            this.beingWritten[i] = false;
        }
    }

    /**
     * Getter for the Registers.
     * @return an array of all registers in this file.
     */
    public Register[] getRegisters() {
        return registers;
    }

    /**
     * Getter for a specific Register.
     * @param index the index of the register being requested.
     * @return the register being requested.
     */
    public Register getRegister(int index) {
        return registers[index];
    }

    /**
     * Getter for a specific Register, which checks for Read-after-Write hazards,
     * and sets the corresponding <b>beingRead</b> value to true.
     * @param index The index of the register being requested.
     * @return the register being requested if it is available, null if there is a hazard.
     */
    public Register getRegisterForRead(int index) {
        if(beingWritten[index]) { //Blocks Read-after-Write hazard
            System.out.println("Register " + index + " is currently locked for writing.");
            return null;
        }
        beingRead[index] = true;
        return registers[index];
    }



    /**
     * Getter for a specific Register, which checks for Write-after-Read and Write-after-Write
     * hazards and sets the corresponding <b>beingWritten</b> value to true.
     * @param index The index of the register being requested.
     * @return the register being requested if it is available, null if there is a hazard.
     */
    public Register getRegisterForWrite(int index) {
        //TODO: Uncomment these and write logic to handle hazards. Not for us.
//        if(beingRead[index]) {
//            //If code is added for concurrent execution/forwarding,
//            //there must be logic to handle Write-after-Read hazard.
//            return null;
//        }
//
//        if(beingWritten[index]) {
//            //If code is added for concurrent execution/forwarding,
//            //there must be logic to handle Write-after-Write hazard.
//            return null;
//        }

        beingWritten[index] = true;
        return registers[index];
    }

    /**
     * Resets a particular register's read/write flags.
     * @param index The index of the register being freed for use.
     */
    public void freeRegister(int index) {
        beingWritten[index] = false;
        beingRead[index] = false;
    }

    /**
     * Utility to 'lock' a register and disallow it from being written to. Used by architecture.
     * @param index The index of the register being locked.
     */
    public void lockRegister(int index) {
        Register.lock(registers[index]);
    }

    /**
     * Resets all registers to zero and frees them to allow writing.
     */
    public void reset() {
        for(Register r: registers) {
            r.zeroOut();
            freeRegister(r.getRegNum());
        }
    }

    public static void main(String... args) {
        RegisterFile test = new RegisterFile(32, 64);

    }
}
