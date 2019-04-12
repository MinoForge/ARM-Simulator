package simulation.pipeline;
import simulation.control.ControlUnit;
import simulation.registers.Register;
import simulation.registers.RegisterFile;

import java.util.ArrayList;

/**
 * A class to model the Write-back segment of the ARM pipeline.
 * NOT IMPLEMENTED YET.
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Writeback extends PipelineSegment{
    
    private Register memwbRegister;
    private ArrayList<Boolean> fields; // holds the info from the control unit  
    private boolean regWrite; // tells write if we are writing to a register
    private boolean memToReg; // tells write if we are writing info from memory
    private String  aluResult;// the result from the alu in Execute
    private String  memData;  // data recieved from memory
    private String  rdField;  // the destination register number
    private RegisterFile regFile;  // the array of registers


   /**
    * Contructor for Write, takes the memwb pipeline register, and the 
    * register array from the simulator.
    */
    public Writeback(Register memwb, RegisterFile regFile) {
        this.memwbRegister = memwb;
        this.regWrite = false;
        this.memToReg = false;
        this.aluResult= "";
        this.memData  = "";
        this.fields   = null;
        this.regFile = regFile;
    }

    /**
     * Method that recieves the flags from the control unit and the data 
     * from the memwb register.
     */
    private void read(){
        System.out.println("STARTING write NOW");


        memData = memwbRegister.getBinary(0,8);
        System.out.println("This is the data from main mem: " + memData);

        aluResult = memwbRegister.getBinary(8,16);
        System.out.println("This is the data direct from Execute: " + aluResult);

        rdField = memwbRegister.getBinary(16,17);
        System.out.println("This is the address of the write-back register: " + rdField);
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
            int regToWrite = Integer.parseInt(rdField,2);
            if(memToReg){
                System.out.println("Writing from memory");

                regFile.getRegister(regToWrite).writeBinary(memData);

            }else{
                System.out.println("Writing from ALU output");
                regFile.getRegister(regToWrite).writeBinary(aluResult);
            }
            regFile.freeRegister(regToWrite);
            System.out.println("Result being written to reg" + Integer.parseInt(rdField,2) + ": " + regFile.getRegister(regToWrite));
        } else {
            System.out.println("No write back.");
        }
    }

    /**
     * Method first checks to see if the control unit will let this stage 
     * run, then if it does run, runs read() and execute().
     */
    public void execute(){
        if(ControlUnit.getGoAhead(4)){
            read();
//            System.out.println(ControlUnit.getState(4));
            fields = ControlUnit.getControlFlags(4);

            regWrite = fields.get(7);
            memToReg = fields.get(8);
            write();
        }
    }

}
