package simulation;

import java.util.ArrayList;

/**
 * Created by Caleb on 1/30/2019.
 *
 *
 */
public class ControlUnit {

    //TODO By diagrams in book, ControlUnit is not initialized until Decode. However it doesn't
    //TODO really affect anything? Shouldn't matter.

    /** Singleton */
    static private ControlUnit unit;

    /** List of Boolean arrays for the flags. */
    private ArrayList<ArrayList<Boolean>> values;

    private ArrayList<String> instructions;

    private ArrayList<Boolean> flags;

    public static final boolean ASSERT   = true;
    public static final boolean DEASSERT = false;

    private static final int STAGE_BINARY_LOADED = 1; //0-indexed stage where binary is loaded in.
    private static final int STAGE_INST_LOADED   = 0; //0-indexed stage where instruction loaded in.

    /** Variable to keep track of pipeline halt length. */
    private int stopTimer;

    /** Variable to track which stage is currently halted. */
    private int haltedStage;

    /** Number of pipeline stages. */
    private static final int NUM_STAGES = 5;

    /** Private constructor. Initializes all fields with no information, and no stoppage. */
    private ControlUnit() {
        this.values = new ArrayList<>(NUM_STAGES);
//        this.runBoolean = new ArrayList<>(NUM_STAGES);
        for(int i = 0; i < NUM_STAGES; i++) {
            this.values.add(new ArrayList<>());
//            this.runBoolean.add(false);
        }
        this.stopTimer = 0;
        this.haltedStage = -1;

        this.flags = new ArrayList<>();
    }

    /** Private static method to initialize the ControlUnit. */
    static private void makeUnit() {
        unit = new ControlUnit();
    }

    /**
     * Gets the flags specific for the requesting pipeline stage.
     * @param stageNum The index of the requesting stage.
     * @return The list of flags for the requesting stage.
     */
    static public ArrayList<Boolean> getControlFlags(int stageNum) {
        if(unit == null) {
            makeUnit();
        }
        unit.push(stageNum);
        return unit.values.get(stageNum);
    }

    static public String getInstruction(int stageNum){
        return unit.instructions.get(stageNum);
    }

    /**
     * Checks for stoppage in the line for the requesting stage.
     * @param stageNum The index of the requesting stage.
     * @return true if stage is free to execute. false if a stoppage affects stage.
     */
    static public boolean getGoAhead(int stageNum) {
        if(unit == null) {
            makeUnit();
        }
        if(0 < unit.stopTimer) { //stopTimer positive
            if(stageNum <= unit.haltedStage) { //Current stage is at or before the halted stage
                if (unit.haltedStage == stageNum) { //Current Stage is the one halted
                    unit.stopTimer--; //Decrement Timer
                }
                return false; //Do not run Pipeline Stage.
            }

        } else {
            unit.haltedStage = -1;
        }

        return true; //No stoppage, run stage.
    }

    static public void newInstructionBin(int instBin){
        if(unit == null){
            makeUnit();
        }

        String temp = Integer.toBinaryString(instBin);
        char format = '\0';
        String check = temp.substring(2,7);
        if(check.matches("_100_")){
            format = 'i';
            unit.flags.set(0,ASSERT);   //Reg2Loc
            unit.flags.set(1,DEASSERT); //ALUOp1
            unit.flags.set(2,DEASSERT); //ALUOp2
            unit.flags.set(3,ASSERT);   //ALUSrc
            unit.flags.set(4,DEASSERT); //Branch
            unit.flags.set(5,DEASSERT); //MemRead
            unit.flags.set(6,DEASSERT); //MemWrite
            unit.flags.set(7,ASSERT);   //RegWrite
            unit.flags.set(8,DEASSERT); //Mem2Reg


        }
        else if(check.matches("101_")){
            format = 'b';
            unit.flags.set(0,ASSERT);   //Reg2Loc
            unit.flags.set(1,DEASSERT); //ALUOp1
            unit.flags.set(2,DEASSERT); //ALUOp2
            unit.flags.set(3,DEASSERT); //ALUSrc
            unit.flags.set(4,DEASSERT); //Branch
            unit.flags.set(5,DEASSERT); //MemRead
            unit.flags.set(6,DEASSERT); //MemWrite
            unit.flags.set(7,ASSERT);   //RegWrite
            unit.flags.set(8,DEASSERT); //Mem2Reg

        }
        else if(check.matches("_101")){
            format = 'r';
            unit.flags.set(0,DEASSERT); //Reg2Loc
            unit.flags.set(1,DEASSERT); //ALUOp1
            unit.flags.set(2,DEASSERT); //ALUOp2
            unit.flags.set(3,DEASSERT); //ALUSrc
            unit.flags.set(4,DEASSERT); //Branch
            unit.flags.set(5,DEASSERT); //MemRead
            unit.flags.set(6,DEASSERT); //MemWrite
            unit.flags.set(7,ASSERT);   //RegWrite
            unit.flags.set(8,DEASSERT); //Mem2Reg

        }
        else if(check.matches("_1_0")){
            format = 'd';
            unit.flags.set(0,ASSERT);   //Reg2Loc
            unit.flags.set(1,DEASSERT); //ALUOp1
            unit.flags.set(2,DEASSERT); //ALUOp2
            unit.flags.set(3,ASSERT);   //ALUSrc
            unit.flags.set(4,DEASSERT); //Branch
            unit.dFlagger();

        }
        unit.values.set(STAGE_BINARY_LOADED,unit.flags);
        unit.push(STAGE_BINARY_LOADED);
    }

    private void dFlagger(){
        if(instructions.get(0).matches("s.*")) { //TODO if might need to restrict to first few chars (label issues?)
            unit.flags.set(5, DEASSERT); //MemRead
            unit.flags.set(6, ASSERT);   //MemWrite
            unit.flags.set(7, DEASSERT); //RegWrite
            unit.flags.set(8, DEASSERT); //Mem2Reg
        }else {
            unit.flags.set(5, ASSERT);   //MemRead
            unit.flags.set(6, DEASSERT); //MemWrite
            unit.flags.set(7, ASSERT);   //RegWrite
            unit.flags.set(8, ASSERT);   //Mem2Reg

        }

    }

    /**
     * Takes a binary instruction and puts flags into the first array and second array.
     * @param inst Either the full instruction binary or just the first 11 bits.
     */
    static public void newInstruction(String inst) {
            if(unit == null) {
                makeUnit();
            }

        unit.instructions.set(STAGE_INST_LOADED, inst);

        unit.push(STAGE_INST_LOADED);
    }

//    private static boolean



    /**
     * Private method to push the boolean array from a particular stage to the next.
     * @param stageNum The stage whose flags are being passed to the next.
     */
    private void push(int stageNum) {
        if(stageNum != NUM_STAGES) {
            values.set(stageNum + 1, values.get(stageNum)); //TODO This probably clones unfortunately
            instructions.set(stageNum + 1, instructions.get(stageNum));
        }

    }

    /**
     * Flushes flags for the stages specified. Used for hazard control.
     * @param stageToStart Stage where clearing starts.
     * @param stageToEnd Stage where clearing ends (inclusive).
     * @return false with bad stage indices. true otherwise
     */
    static public boolean flushPipeControl(int stageToStart, int stageToEnd) {
        if(unit == null) {
            makeUnit();
        }

        if(stageToStart < 0 || stageToEnd < 0 ||
                stageToEnd >= NUM_STAGES || stageToStart >= NUM_STAGES) {

            System.err.println("Halting Stage indices out of range: " +
                    stageToStart + ":" + stageToEnd);
            return false;
        }
        for(int i = stageToStart; i <= stageToEnd; i++) {
            unit.values.set(i, new ArrayList<>());
        }
        return true;
    }

    /**
     * Flushes flags for a single stage.
     * @param stageToStart Stage to flush.
     * @return false on a bad stage index. true otherwise
     */
    static public boolean flushPipeControl(int stageToStart) {
        return flushPipeControl(stageToStart, stageToStart);
    }

    /**
     * Halts all Pipeline stages before a specified stage, for a specified number of runs.
     * @param stageToHalt The latest stage which must halt.
     * @param haltTimer The number of runs for which the stage will not execute.
     */
    static public void haltPipe(int stageToHalt, int haltTimer) {
        if(unit == null) {
            makeUnit();
        }
        unit.haltedStage = stageToHalt;
        unit.stopTimer = haltTimer;
    }


    /** Testing. */
    public static void main(String... args) {

    }







}
