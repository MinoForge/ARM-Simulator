package simulation;

import java.util.ArrayList;

/**
 * Created by Caleb on 1/30/2019.
 */
public class ControlUnit {

    static private ControlUnit unit;

    private ArrayList<ArrayList<Boolean>> values;
    private ArrayList<Boolean> runBoolean;

    private static final int NUM_STAGES = 5;

    private ControlUnit() {
        this.values = new ArrayList<>(NUM_STAGES);
        this.runBoolean = new ArrayList<>(NUM_STAGES);
        for(int i = 0; i < NUM_STAGES; i++) {
            this.values.add(new ArrayList<>());
            this.runBoolean.add(false);
        }

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

    static public boolean getGoAhead(int num) {
        if(unit == null) {
            makeUnit();
        }
        return unit.runBoolean.get(num);
    }

    static void newInstruction(String inst) {
        if(unit == null) {
            makeUnit();
        }
        unit.push();
        //TODO Process instruction and set flags
    }

    private void push() {
        if(unit == null) {
            makeUnit();
        }
        for(int i = 1; i < NUM_STAGES; i++) {

        }
    }

//    static public void flushPipe(int stageToStart, int stageToStart) {
//
//    }

    static public void haltPipe(int stageToHalt, int haltTimer) {

    }







}
