package simulation.pipeline;

import simulation.ControlUnit;
import simulation.Register;

import java.util.ArrayList;

/**
 * A class to model the Memory Accessing segment of the ARM pipeline.
 * NOT IMPLEMENTED YET.
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Accessor extends PipelineSegment{
    private Register  exmemRegister;
    private Register  memwbRegister;
    private ArrayList<Boolean> fields;
    private boolean   write;
    private boolean   read;
    private boolean   branch;
    private String    result;
    private String    address;

    public Accessor(Register exmem, Register memwb) {
        this.exmemRegister = exmem;
        this.memwbRegister = memwb;
        this.fields = null;
        this.read = false;
        this.write = false;
        this.branch = false;
        this.result = "";
        this.address = "";

    }



    /**
     * Method first receives the information for setting the flags in Accessor, then takes the data
     * from the exmem register.
     */
    private void read(){
        System.out.println("Starting ACCESS now");
        this.fields = ControlUnit.getControlFlags(3);
        this.write = fields.get(6);
        this.read = fields.get(5);
        this.branch = fields.get(4);

        this.address = exmemRegister.getBinary(16,24);
        this.result = exmemRegister.getBinary(8,16);

    }

    /**
     * Method the checks the write and read flags inorder to tell access what we are doing with
     * memory. On a write it takes the result from the alu and puts it into memory address specified
     * by exmem register. On a read, we retrieve what is in memory at the specified address and load
     * it into the memwb register.
     */
    private void write(){
        //

        if(write){
            //Stack.writeBinaryAtIndex(temp,Integer.parseInt(address,2));
        }else if(read){
            // this.result = Stack.getBinary(Integer.parseInt(address),
            // Integer.parseInt(address) + 8);
        }
        memwbRegister.append(result);
        memwbRegister.append(address);
        memwbRegister.append(exmemRegister.getBinary(24,25));

    }


    /**
     * Method first checks if the pipeline is stalled, if not then runs read() and write()
     */
    public void execute(){
        if(ControlUnit.getGoAhead(3)) {
            read();
            write();
        }
    }

}
