package simulation;

import java.util.ArrayList;

/**
 * Created by Caleb on 1/30/2019.
 */
public class ControlUnit {

    static private ControlUnit unit;

    private ArrayList<ArrayList<Boolean>> values;
//    private ArrayList<Boolean> runBoolean;
    private int stopTimer;
    private int haltedStage;

    private static final int NUM_STAGES = 5;

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

    static private void makeUnit() {
        unit = new ControlUnit();
    }

    static public ArrayList<Boolean> getControlInstructions(int num) {
        if(unit == null) {
            makeUnit();
        }
        return unit.values.get(num);
    }

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

    static public void newInstruction(String inst) {
        if(unit == null) {
            makeUnit();
        }
        unit.push(0);
        //TODO Process instruction and set flags
    }

    private void push(int stageNum) {
        if(stageNum != NUM_STAGES) {
            values.set(stageNum + 1, values.get(stageNum));
        }
    }

    static public boolean flushPipeControl(int stageToStart, int stageToEnd) {
        if(unit == null) {
            makeUnit();
        }

        if(stageToStart < 0 || stageToEnd < 0 ||
                stageToEnd > NUM_STAGES || stageToStart > NUM_STAGES) {

            System.err.println("Halting Stage indices out of range: " +
                    stageToStart + ":" + stageToEnd);
            return false;
        }
        for(int i = stageToStart; i <= stageToEnd; i++) {
            unit.values.set(i, new ArrayList<>());
        }
        return true;
    }

    static public boolean flushPipeControl(int stageToStart) {
        return flushPipeControl(stageToStart, stageToStart);
    }

    static public void haltPipe(int stageToHalt, int haltTimer) {
        if(unit == null) {
            makeUnit();
        }
        unit.haltedStage = stageToHalt;
        unit.stopTimer = haltTimer;
    }



    public static void main(String... args) {

    }







}
