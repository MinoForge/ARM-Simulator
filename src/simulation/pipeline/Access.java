package simulation.pipeline;

import simulation.Controller;
import simulation.control.ControlUnit;
import simulation.registers.Register;

import java.util.ArrayList;

/**
 * A class to model the Memory Accessing segment of the ARM pipeline.
 * NOT IMPLEMENTED YET.
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Access extends PipelineSegment{
    private Register  exmemRegister;
    private Register  memwbRegister;
    private ArrayList<Boolean> fields;
    private boolean   write;
    private boolean   read;
    private boolean   branch;
    private String aluResult;
    private String data;
    private String memData;
    private Register memory;
    private long localPC;

    public Access(Register exmem, Register memwb, Register memory) {
        this.exmemRegister = exmem;
        this.memwbRegister = memwb;
        this.fields = null;
        this.read = false;
        this.write = false;
        this.branch = false;
        this.aluResult = "";
        this.data = "";
        this.memData = "";
        this.memory = memory;

    }



    /**
     * Method first receives the information for setting the flags in Access, then takes the data
     * from the exmem register.
     */
    private void read(){
        System.out.println("Starting ACCESS now");

        this.localPC = exmemRegister.getLong(0);
        this.aluResult = exmemRegister.getBinary(8,16);
        this.data = exmemRegister.getBinary(16,24);



    }

    /**
     * Method the checks the write and read flags inorder to tell access what we are doing with
     * memory. On a write it takes the aluResult from the alu and puts it into memory data specified
     * by exmem register. On a read, we retrieve what is in memory at the specified data and load
     * it into the memwb register.
     */
    private void write() {
        int address = Integer.parseInt(aluResult,2);
        if(address < 0x400000){
            System.err.println("Segmentation Fault");
            Controller.stop();
        }
        System.out.println("This is the memory address: " + address);
//        Controller.printMemory();

        if(write){
            System.out.println("Writing to Main Memory");
            memory.writeBinaryAtIndex(address, data);

        }else if(read){

            memData = memory.getBinary(address, address + 8);
            System.out.println("Reading from Main Memory: " + (memData));
        } else {
            System.out.println("Bypassing Main Memory");
        }

        if(branch) { //If we are branching.

            if(localPC == Controller.PC - 12) { //Not actually branching OR forever loop
                System.out.println("Not branching.");
            } else {
                System.out.println("PC is being altered to: " + localPC);
                ControlUnit.flushPipe(0, 2);
                Controller.PC = (int)localPC;
            }


        }

        memwbRegister.append(correctBits(memData, 64, 64));
        System.out.println("Data from Memory into memwb: " + correctBits(memData, 64, 64));
        memwbRegister.append(aluResult);
        System.out.println("Data from ALU into memwb   : " + aluResult);
        memwbRegister.append(exmemRegister.getBinary(24,25));
        System.out.println("Write-back register : " + exmemRegister.getBinary(24, 25));
    }


    /**
     * Method first checks if the pipeline is stalled, if not then runs read() and write()
     */
    public void execute(){
        if(ControlUnit.getGoAhead(3)) {
            read();
//            System.out.println(ControlUnit.getState(3));
            this.fields = ControlUnit.getControlFlags(3);
            this.write = fields.get(6);
            this.read = fields.get(5);
            this.branch = fields.get(4);
            memwbRegister.zeroOut();
            write();
        } else {
            ControlUnit.setStageDataValid(4, false);
        }
    }

}
