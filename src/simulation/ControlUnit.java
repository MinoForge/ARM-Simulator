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

    }

    /** Private static method to initialize the ControlUnit. */
    static private void makeUnit() {
        unit = new ControlUnit();
    }

    /**
     * Gets the flags specific for the requesting pipeline stage.
     * @param num The index of the requesting stage.
     * @return The list of flags for the requesting stage.
     */
    static public ArrayList<Boolean> getControlInstructions(int num) {
        if(unit == null) {
            makeUnit();
        }
        unit.push(num);

        return unit.values.get(num);
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

    /**
     * Takes a binary instruction and puts flags into the first array and second array.
     * @param instBin Either the full instruction binary or just the first 11 bits.
     */
    static public void newInstruction(int instBin) {
        if(unit == null) {
            makeUnit();
        }


        ArrayList<Boolean> flags = new ArrayList<>();
        for(int i = 0; i < 11; i++) {

        }
        unit.values.set(0, flags);

        unit.push(0);
    }

//    private static boolean



    /**
     * Private method to push the boolean array from a particular stage to the next.
     * @param stageNum The stage whose flags are being passed to the next.
     */
    private void push(int stageNum) {
        if(stageNum != NUM_STAGES) {
            values.set(stageNum + 1, values.get(stageNum)); //TODO This probably clones unfortunately
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
