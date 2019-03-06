package simulation;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.beans.property.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
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

    public static final short DEFAULT_BYTE_SIZE = 8;



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
    //DOES NOT RESPECT INDEX FIELD. Use for specific byte writing.
    /**
     * Method to write a binary string to a register starting at a specific byte, regardless of
     * contents or indices. Does not respect or update index for appending.
     * @param startByteIndex The byte index at which to start writing.
     * @param binary The binary string of the bytes to be written.
     * @return -1 if the string will not fit in available space or index out of bounds, else returns
     * the index after the final byte written.
     */
    public int writeBinaryAtIndex(int startByteIndex, String binary) {
        if(startByteIndex < 0)  { //index
            return -1;
        }
        if(binary.length() % DEFAULT_BYTE_SIZE != 0) { //Will not fit byte-array
            return -1; //TODO Make this an exception instead?
        }
        if(!binary.matches("[01]+")) { //Contains non-binary elements
            return -1;
        }
        int bytesCnt = binary.length() / DEFAULT_BYTE_SIZE; //Number of bytes to write
        if(bytesCnt > length - startByteIndex) { //Will overflow register
            return -1;
        }

        ByteBuffer buffer = ByteBuffer.allocate(bytesCnt); //allocate bytes
        buffer.order(Controller.BYTE_ORDER);

        int i;
        for(i = startByteIndex; i < bytesCnt + startByteIndex; i++) {
            int strIndex = DEFAULT_BYTE_SIZE * (i - startByteIndex); //indices of binary string to parse
//            buffer.putInt()
            buffer.put((byte)Integer.parseInt(binary.substring(strIndex, strIndex +
                    DEFAULT_BYTE_SIZE), 2));
            bytes[i] = buffer.get(i-startByteIndex);

        }

//        bytes = buffer.array();

        return i;
    }

    //TESTED WORKING 2/12
    /**
     * A special method for registers which will append bytes to the end of previously appended
     * information. Current byte where next append with begin is tracked in index field.
     * @param binary The binary string of the bytes to be added.
     * @return -1 if write failed, else the new index at which a new append will begin.
     */
    public int append(String binary) {
        int result = writeBinaryAtIndex(index, binary);
        if(result == -1) {
            return -1;
        } else {
            index = result;
        }
        return index;
    }

    /**
     * Method which overwrites the current contents of the register, starting at byte 0.
     * Resets and updates index.
     * @param binary The binary string of the bytes being written.
     * @return -1 if write failed, else the new append index.
     */
    public int writeBinary(String binary) {
        index = 0;
        return append(binary);
    }


    //TESTED WORKING 2/11
    /**
     * Method to get a sequence of bytes from the register as an array.
     * @param start The first byte in the sequence.
     * @param pastEnd The index after the last byte in the sequence.
     * @return null if indices are out of bounds, else an array of the bytes requested.
     */
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

    public byte[] getBytes() {
        return getBytes(0, length);
    }


    //TESTED WORKING 2/11
    /**
     * Creates a binary string from a sequence of bytes in the register.
     * @param start The first index in the sequence.
     * @param pastEnd The index after the last byte in the sequence.
     * @return null if indices are out of range, else a binary string of the bytes between start
     * and end.
     */
    public String getBinary(int start, int pastEnd) {
        if(pastEnd > length || pastEnd < 0) {
            return null;
        }
        if(start < 0 || start >= length) {
            return null;
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

    /**
     * More general method which returns a string representing the binary of the entire register.
     * @return The binary string of the register.
     */
    public String getBinary() {
//        System.out.println(this.length);
        return getBinary(0, this.length);
    }

    //Tested working 3/4
    public Integer getInt(int startByte) {
        if(startByte < 0 || startByte > length - Integer.BYTES) {
            return null;
        }

        int word = ByteBuffer.wrap(Arrays.copyOfRange(bytes, startByte, startByte + Integer.BYTES)).getInt();
        return word;
    }

    //GENERAL TESTING
    public static void main(String... args) {
        Controller control = new Controller("file", true);
        Register reg = new Register(4);
        reg.testFunctions();
//        String test = "110010000000001100100111";
//        System.out.println(test.length());
//        reg.append(test);
//        System.out.println(reg.getBinary(1, 3));
//        System.out.println(reg.getBinary());
//        System.out.println(reg.index);
//        String str = test.substring(8,24);
//        System.out.println(str.length());
//        System.out.println(str);
//        reg.append(str);
//        System.out.println(reg.getBinary());
////        System.out.println(Integer.toBinaryString((reg & 0xFF) + 0x100).substring(1));
//        System.out.println(reg.writeBinaryAtIndex(3, "0011001111001100"));
//        System.out.println(reg.getBinary());

    }

    private int testFunctions() {
        // INIT TEST
        String test = "11110000110000110000001100000000";
        System.out.println(test);
        System.out.println(test.length());
        append(test);
        //END INIT

        System.out.println(Integer.parseUnsignedInt(test, 2));
        System.out.println(getInt(0));
        return 0;
    }

    @Override
    public String toString() {
        return getBinary();
    }
}
