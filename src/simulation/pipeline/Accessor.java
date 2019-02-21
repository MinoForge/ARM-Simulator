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
     *
     */
    private void read(){
        // TODO use info in pipeline register to see if mem needs to be
        // accessed
        this.fields = ControlUnit.getControlInstructions(3);
        this.write = fields.get(0);
        this.read = fields.get(1);
        this.branch = fields.get(2);

        this.address = exmemRegister.getBinary(16,23);
        this.result = exmemRegister.getBinary(8,16);

    }

    /**
     *
     */
    private void write(){
        // TODO Add above to the mem/wb register

        if(write){
            //Stack.writeBinaryAtIndex(temp,Integer.parseInt(address,2));
        }else if(read){
            // this.result = Stack.getBinary(Integer.parseInt(address),
            // Integer.parseInt(address) + 8);
            //
        }
        memwbRegister.append(result);
        memwbRegister.append(address);
        memwbRegister.append(exmemRegister.getBinary(24,25));

    }


    /**
     *
     */
    public void execute(){
        if(ControlUnit.getGoAhead(3)) {
            read();
            write();
        }
    }

}
