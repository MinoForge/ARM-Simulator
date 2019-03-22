package simulation.registers;


public class RegisterFile {
    private Register[] registers;
    private boolean[] beingRead;
    private boolean[] beingWritten;

    public RegisterFile() {
        this(0, 0);
    }

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

    public Register getRegister(int index) {
        return registers[index];
    }


    public Register getRegisterForRead(int index) {
        if(beingWritten[index]) { //Blocks Read-after-Write hazard
            return null;
        }
        beingRead[index] = true;
        return registers[index];
    }

    public Register getRegisterForWrite(int index) {
        //TODO: Uncomment these and write logic to handle hazards. Not for us.
//        if(beingRead[index]) {
//            //If code is added for concurrent execution/forwarding,
//            //there must be logic to handle Write-after-Read hazard.
//        }
//
//        if(beingWritten[index]) {
//            //If code is added for concurrent execution/forwarding,
//            //there must be logic to handle Write-after-Write hazard.
//        }

        beingWritten[index] = true;
        return registers[index];
    }

    public void freeRegister(int index) {
        beingWritten[index] = false;
        beingRead[index] = false;
    }

    public void lockRegister(int index) {
        Register.lock(registers[index]);
    }

    public static void main(String... args) {
        RegisterFile test = new RegisterFile(32, 64);

    }
}
