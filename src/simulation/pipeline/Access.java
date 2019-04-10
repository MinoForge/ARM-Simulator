package simulation.pipeline;

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
    private String memAddress;
    private String memData;

    public Access(Register exmem, Register memwb) {
        this.exmemRegister = exmem;
        this.memwbRegister = memwb;
        this.fields = null;
        this.read = false;
        this.write = false;
        this.branch = false;
        this.aluResult = "";
        this.memAddress = "";
        this.memData = "";

    }



    /**
     * Method first receives the information for setting the flags in Access, then takes the data
     * from the exmem register.
     */
    private void read(){
        System.out.println("Starting ACCESS now");

        this.aluResult = exmemRegister.getBinary(8,16);
        this.memAddress = exmemRegister.getBinary(16,24);


    }

    /**
     * Method the checks the write and read flags inorder to tell access what we are doing with
     * memory. On a write it takes the aluResult from the alu and puts it into memory memAddress specified
     * by exmem register. On a read, we retrieve what is in memory at the specified memAddress and load
     * it into the memwb register.
     */
    private void write(){
        //

        if(write){
            System.out.println("Writing to Main Memory");
            //Stack.writeBinaryAtIndex(temp,Integer.parseInt(memAddress,2));
        }else if(read){
            System.out.println("Reading from Main Memory");
            // this.memData = Stack.getBinary(Integer.parseInt(memAddress),
            // Integer.parseInt(memAddress) + 8);
        } else {
            System.out.println("Bypassing Main Memory");
        }

        memwbRegister.append(correctBits(memData, 64));
        System.out.println("Data from Memory into memwb: " + correctBits(memData, 64));
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
        }
    }

}
