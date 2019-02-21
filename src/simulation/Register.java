package simulation;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
    private int length;

    private int index;

    /**
     * Constructor which creates a Register with a specified number of bytes.
     * @param numBytes The size of the Register, in bytes.
     */
    public Register(int numBytes) { //TODO Make a static register index so
        // that cannot make duplicates.
//        this.regNum = new SimpleIntegerProperty();
//        this.trueVal = new SimpleLongProperty();
//        this.binVal = new SimpleStringProperty();
////        this.octVal = new SimpleStringProperty();
//        this.decVal = new SimpleStringProperty();
//        this.hexVal = new SimpleStringProperty();
        this.length = numBytes;
        this.bytes = new byte[numBytes];
        this.index = 0;
        zeroOut();
    }

    private void zeroOut() {
        for (int i = 0; i < length; i++) {
            bytes[i] = 0;
        }
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


    //TESTED WORKING 2/12
    public boolean append(String binary) {
        int result = writeBinaryAtIndex(index, binary);
        if(result == -1) {
            return false;
        } else {
            index = result;
        }
        return true;
    }

    //TESTED WORKING 2/12
    //DOES NOT RESPECT INDEX FIELD. Use for specific byte writing.
    public int writeBinaryAtIndex(int startByteIndex, String binary) {
        if(startByteIndex < 0)  { //index
            return -1;
        }
        if(binary.length() % BYTE_SIZE != 0) { //Will not fit byte-array
            return -1; //TODO Make this an exception instead?
        }
        if(!binary.matches("[01]+")) { //Contains non-binary elements
            return -1;
        }
        int bytesCnt = binary.length() / BYTE_SIZE; //Number of bytes to write
        if(bytesCnt > length - startByteIndex) { //Will overflow register
            return -1;
        }

        ByteBuffer buffer = ByteBuffer.allocate(bytesCnt); //allocate bytes
        buffer.order(Controller.BYTE_ORDER);

        int i;
        for(i = startByteIndex; i < bytesCnt + startByteIndex; i++) {
            int strIndex = BYTE_SIZE * (i - startByteIndex); //indices of binary string to parse
//            buffer.putInt()
            buffer.put((byte)Integer.parseInt(binary.substring(strIndex, strIndex +
                    BYTE_SIZE), 2));
            bytes[i] = buffer.get(i-startByteIndex);

        }

//        bytes = buffer.array();

        return i;
    }

    public int writeBinary(String binary) {
        return writeBinaryAtIndex(0, binary);
    }

    //TESTED WORKING 2/11
    public byte[] getBytes(int start, int pastEnd) {
        if(pastEnd > length) {
            return null;
        }
        if(start < 0) {
            return null;
        }
        int bytesCnt = pastEnd - start;
        byte[] result = new byte[bytesCnt];
        for(int i = 0; i < bytesCnt; i++) {
            result[i] = bytes[start + i];
        }
        return result;
    }

    //TESTED WORKING 2/11
    public String getBinary(int start, int pastEnd) {
        if(pastEnd > length) {
            return "Can't index past end of register";
        }
        if(start < 0) {
            return "Can't index before start of register";
        }


        int bytesCnt = pastEnd - start;
        ByteBuffer buffer = ByteBuffer.allocate(bytesCnt);
        buffer.order(Controller.BYTE_ORDER);
        buffer.put(getBytes(start, pastEnd));
        buffer.flip();
//        byte[] arr = getBytes(start, pastEnd);
        String result = "";
        for(int i = 0; i < bytesCnt; i++) { //Look, it's not magic but the explanation is long.
            result += Integer.toBinaryString((buffer.get(i) & 0xFF) + 0x100).substring(1);
        } //See https://stackoverflow.com/a/17496691

        return result;
    }

    //TESTED WORKING 2/11
    public String getBinary() {
//        System.out.println(this.length);
        return getBinary(0, this.length);
    }

    //GENERAL TESTING
    public static void main(String... args) {
        Controller control = new Controller("file", true);
        Register reg = new Register(5);
        String test = "110010000000001100100111";
        System.out.println(test.length());
        reg.append(test);
        System.out.println(reg.getBinary(1, 3));
        System.out.println(reg.getBinary());
        System.out.println(reg.index);
        String str = test.substring(8,24);
        System.out.println(str.length());
        System.out.println(str);
        reg.append(str);
        System.out.println(reg.getBinary());
//        System.out.println(Integer.toBinaryString((reg & 0xFF) + 0x100).substring(1));
        System.out.println(reg.writeBinaryAtIndex(3, "0011001111001100"));
        System.out.println(reg.getBinary());

    }

    private int testFunctions() {
        //TODO coverage
        return 0;
    }

    @Override
    public String toString() {
        return getBinary();
    }
}
