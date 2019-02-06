package simulation;

import javafx.beans.property.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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

    private static final short BYTE_SIZE = 8;



//    /** The identifier for the register */
//    private IntegerProperty regNum;

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
     * Constructor which creates a Register with a specified number of bytes.
     * @param numBytes The size of the Register, in bytes.
     */
    public Register(int numBytes) { //TODO Make a static register index so that cannot make duplicates.
//        this.regNum = new SimpleIntegerProperty();
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

//    /** Gets the IntegerProperty for regNum. */
//    public IntegerProperty regNumProperty() {
//        return regNum;
//    }

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


    public boolean append(String binary) {
        if(binary.length() % BYTE_SIZE != 0) {
            return false; //TODO Make this an exception instead?
        }
        if(binary.contains("^[0-1]")) {
            return false;
        }
        short bytes = (short)(binary.length() / BYTE_SIZE);
        ByteBuffer buffer = ByteBuffer.allocate(binary.length() / BYTE_SIZE);
        buffer.order(Controller.BYTE_ORDER);
        for(int i = 0;)
    }

    public String getBinary(int start, int pastEnd) {

    }




}
