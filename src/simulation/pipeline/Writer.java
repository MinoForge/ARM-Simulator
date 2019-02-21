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
    private ArrayList<Boolean> fields;
    private boolean regWrite;
    private boolean memToReg;
    private String  aluResult;
    private String  memData;
    private String  rdField;
    private Register[] regs;


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
     *
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
     *
     */
    private void write(){
        if(regWrite){
            if(memToReg){
                regs[Integer.parseInt(rdField,2)].writeBinary(memData);
            }else{
                regs[Integer.parseInt(rdField,2)].writeBinary(aluResult);
            }
        }
    }

    /**
     *
     */
    public void execute(){
        if(ControlUnit.getGoAhead(4)){
            read();
            write();
        }
    }

}
