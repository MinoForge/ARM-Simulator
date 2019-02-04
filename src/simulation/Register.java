package simulation;

import javafx.beans.property.*;

import java.util.Random;

/**
 * Class to represent a Register. This will eventually be refactored into the main Simulator
 * to make life easier, but for now is only used in the mockup GUI.
 *
 * @author Peter Gardner
 * @author Caleb Dinehart
 * @version November 30, 2018
 */
//TODO Like everthing. Move packages, make used throughout. Write stuff.
public class Register {
    /** The identifier for the register */
    private IntegerProperty regNum;

    /** The actual value of the Register. Will need to be a byte array in the end, probably */
    private LongProperty trueVal;

    /** The value of the register, in a binary String. */
    private StringProperty binVal;

//    /** The value of the register, in an octal String. */
//    private StringProperty octVal;

    /** The value of the register, in a decimal String. */
    private StringProperty decVal;

    /** The value of the register, in a hexadecimal String. */
    private StringProperty hexVal;

    /** Currently unused, will be used as the actual value stored in the Register. */
    private byte[] bytes;

    /** Length of the register, in bytes. Unused.*/
    private short length;

    /**
     * Constructor which creates a Register with that identifier number.
     * @param regNum The number of the Register.
     */
    public Register(Integer regNum) { //TODO Make a static register index so that cannot make duplicates.
        this.regNum = new SimpleIntegerProperty(regNum);
        this.trueVal = new SimpleLongProperty();
        this.binVal = new SimpleStringProperty();
//        this.octVal = new SimpleStringProperty();
        this.decVal = new SimpleStringProperty();
        this.hexVal = new SimpleStringProperty();
        this.length = 8;

        Random random = new Random();
        this.setVals((long)random.nextInt(200));
//        this.setVals();
    }

    /** Gets the actual Integer for the regNum. */
    public Integer getRegNum() {
        return regNum.getValue();
    }

    /** Gets the actual String for the hexVal. */
    public String getHexVal() {
        return hexVal.get();
    }

    /** Gets the actual String for the binVal. */
    public String getBinVal() {
        return binVal.get();
    }

    /** Gets the actual String for the decVal. */
    public String getDecVal() {
        return decVal.get();
    }

    /** Gets the actual Long for the trueVal. */
    public Long getTrueVal() {
        return trueVal.getValue();
    }

    /**
     * Sets the value of the Register to a new value given.
     * @param newVal The new value to be written to the Register.
     */
    public void setVals(Long newVal) {
        trueVal.set(newVal);
        String bin = Long.toString(newVal, 2);
        String dec = Long.toString(newVal, 10);
        String hex = Long.toString(newVal, 16);
//        System.out.println(bin + " :: " + dec + " :: " + hex);
        binVal.set(bin);
        decVal.set(dec);
        hexVal.set(hex);
    }

    /** Gets the IntegerProperty for regNum. */
    public IntegerProperty regNumProperty() {
        return regNum;
    }

    /** Gets the LongProperty for trueVal. */
    public LongProperty trueValProperty() {
        return trueVal;
    }

    /** Gets the StringProperty for binVal. */
    public StringProperty binValProperty() {
        return binVal;
    }

    /** Gets the StringProperty for decVal. */
    public StringProperty decValProperty() {
        return decVal;
    }

    /** Gets the StringProperty for hexVal. */
    public StringProperty hexValProperty() {
        return hexVal;
    }
}
