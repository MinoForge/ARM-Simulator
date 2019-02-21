package simulation.pipeline;
import simulation.ControlUnit;
import simulation.Register;
import java.util.ArrayList;

/**
 * A class to model the Write-back segment of the ARM pipeline.
 * NOT IMPLEMENTED YET.
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Writer extends PipelineSegment{
    
    private Register memwbRegister;
    private ArrayList<Boolean> fields; // holds the info from the control unit  
    private boolean regWrite; // tells write if we are writing to a register
    private boolean memToReg; // tells write if we are writing info from memory
    private String  aluResult;// the result from the alu in Execute
    private String  memData;  // data recieved from memory
    private String  rdField;  // the destination register number
    private Register[] regs;  // the array of registers


   /**
    * Contructor for Write, takes the memwb pipeline register, and the 
    * register array from the simulator.
    */
    public Writer(Register memwb, Register[] regs) {
        this.memwbRegister = memwb;
        this.regWrite = false;
        this.memToReg = false;
        this.aluResult= "";
        this.memData  = "";
        this.fields   = null;
        this.regs = regs;
    }

    /**
     * Method that recieves the flags from the control unit and the data 
     * from the memwb register.
     */
    private void read(){
        fields = ControlUnit.getControlInstructions(4);
        regWrite = fields.get(0);
        memToReg = fields.get(1);

        memData = memwbRegister.getBinary(0,8);
        aluResult = memwbRegister.getBinary(8,16);
        rdField = memwbRegister.getBinary(16,17);
    }

    /**
     * Method that checks the flags recieved from the control unit
     * and determines whether we are writing data from memory 
     * or data from the alu result into the destination register.
     */
    private void write(){
        //if regWrite is true then we are writing to a register, 
        //else we do nothing
        if(regWrite){
            //if memToReg is true then we are writing data from memory to
            //the destination register, else write the alu result.
            if(memToReg){
                regs[Integer.parseInt(rdField,2)].writeBinary(memData);
            }else{
                regs[Integer.parseInt(rdField,2)].writeBinary(aluResult);
            }
        }
    }

    /**
     * Method first checks to see if the control unit will let this stage 
     * run, then if it does run, runs read() and execute().
     */
    public void execute(){
        if(ControlUnit.getGoAhead(4)){
            read();
            write();
        }
    }

}
