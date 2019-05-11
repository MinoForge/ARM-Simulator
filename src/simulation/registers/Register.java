package simulation.registers;

import javafx.beans.property.*;
import javafx.util.converter.BigIntegerStringConverter;
import simulation.Controller;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Class to represent a Register. This will eventually be refactored into the main Simulator
 * to make life easier, but for now is only used in the mockup GUI.
 *
 * @author Peter Gardner
 * @author Caleb Dinehart
 * @version May 10, 2019
 */
public class Register {

    public static final short DEFAULT_BYTE_SIZE = 8;



    /** The identifier for the register */
    private IntegerProperty regNum;

    /** The actual value of the Register. Will need to be a byte array in the end, probably */
    private StringProperty trueVal;

    /** The value of the register, in a binary String. */
    private StringProperty binVal;

//    /** The value of the register, in an octal String. */
//    private StringProperty octVal;

    /** The value of the register, in a decimal String. */
    private LongProperty decVal;

    /** The value of the register, in a hexadecimal String. */
    private StringProperty hexVal;

    /** Currently unused, will be used as the actual value stored in the Register. */
    private byte[] bytes;

    /** Length of the register, in bytes. Unused.*/
    private int length;

    /** The current index of the next open byte. Only reliable if using append(). */
    private int index;

    /** Whether this register may be written to */
    private boolean locked;

    /** A counter which tracks how many registers have been created. */
    private static Integer regCounter;

    /**
     * Constructor which creates a Register with a specified number of bytes.
     * @param numBytes The size of the Register, in bytes.
     */
    public Register(int numBytes) { //TODO Make a static register index so
        // that cannot make duplicates.
        if(regCounter == null) {
            regCounter = 0;
        }
        this.regNum = new SimpleIntegerProperty(regCounter++);

        this.binVal = new SimpleStringProperty();
        this.decVal = new SimpleLongProperty();
        this.hexVal = new SimpleStringProperty();

        this.length = numBytes;
        this.bytes = new byte[numBytes];
        this.index = 0;
        this.locked = false;
        zeroOut();
    }

    /**
     * Puts zeroes into all bytes of the register.
     */
    public void zeroOut() {
        if(!locked) {
            for (int i = 0; i < length; i++) {
                bytes[i] = 0;
            }
            index = 0;
        }
        setVals(getBinary());
    }

    /**
     * @return true if the register is locked, false if not.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Helper method to change the state of this register's lock.
     * @param val the value which the lock will be set to.
     */
    private void setLocked(boolean val) {
        this.locked = val;
    }

    /** Locks the register against being written to. */
    public static void lock(Register reg) {
        reg.setLocked(true);
    }

    /** Unlocks the register to be written to. */
    public static void unlock(Register reg) {
        reg.setLocked(false);
    }

    /** Gets the actual integer representing the register number. */
    public int getRegNum() {
        return regNum.get();
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
    public Long getDecVal() {
        return decVal.get();
    }


    /**
     * Sets the value of the Register to a new value given.
     * @param newVal The new value to be written to the Register.
     */
    public void setVals(String newVal) {
        binVal.set(newVal);
        BigInteger val = new BigInteger(newVal, 2);
        decVal.set(val.longValue());
        hexVal.set(val.toString(16));
//        System.err.println(bin + " :: " + dec + " :: " + hex);
    }


    /** Gets the IntegerProperty for regNum. */
    public IntegerProperty regNumProperty() {
        return regNum;
    }

    /** Gets the StringProperty for binVal. */
    public StringProperty binValProperty() {
        return binVal;
    }

    /** Gets the StringProperty for decVal. */
    public LongProperty decValProperty() {
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
//        System.err.println(startByteIndex);
//        System.err.println(binary);

        if(locked) {
            System.err.println("Locked");
            return -1;
        }
        if(startByteIndex < 0)  { //index
            System.err.println("Negative Index not allowed");
            return -1;
        }
        if(binary.length() % DEFAULT_BYTE_SIZE != 0) { //Will not fit byte-array
            System.err.println(binary.length() + "::" + regCounter);
            System.err.println("Byte array doesn't align");
            return -1; //TODO Make this an exception instead?
        }
        if(!binary.matches("[01]+")) { //Contains non-binary elements
            System.err.println("Contains non-binary elements.");
            return -1;
        }
        int bytesCnt = binary.length() / DEFAULT_BYTE_SIZE; //Number of bytes to write
        if(bytesCnt > length - startByteIndex) { //Will overflow register
            System.err.println("Overflowing register.");
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
        setVals(getBinary());
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
//        System.err.println(this.length);
        return getBinary(0, this.length);
    }

    //Tested working 3/4
    /** Returns a 4-byte sequence starting at a given byte of the register. */
    public Integer getInt(int startByte) {
        if(startByte < 0 || startByte > length - Integer.BYTES) {
            return null;
        }

        int word = ByteBuffer.wrap(Arrays.copyOfRange(bytes, startByte, startByte + Integer.BYTES)).getInt();
        return word;
    }

    /** Returns an 8-byte sequence starting at a given byte of the register. */
    public Long getLong(int startByte) {
        if(startByte < 0 || startByte > length - Long.BYTES) {
            return null;
        }

        long word = ByteBuffer.wrap(Arrays.copyOfRange(bytes, startByte, startByte + Long.BYTES)).getLong();
        return word;
    }

    public int indexOf(int startIndex, byte b) {
        for(int i = startIndex; i < length; i++) {
            if(bytes[i] == b) {
                return i;
            }
        }
        return -1;
    }

    //GENERAL TESTING
    public static void main(String... args) {
//        Controller control = new Controller("file", true);
        Register reg = new Register(4);
        reg.testFunctions();
//        String test = "110010000000001100100111";
//        System.err.println(test.length());
//        reg.append(test);
//        System.err.println(reg.getBinary(1, 3));
//        System.err.println(reg.getBinary());
//        System.err.println(reg.index);
//        String str = test.substring(8,24);
//        System.err.println(str.length());
//        System.err.println(str);
//        reg.append(str);
//        System.err.println(reg.getBinary());
////        System.err.println(Integer.toBinaryString((reg & 0xFF) + 0x100).substring(1));
//        System.err.println(reg.writeBinaryAtIndex(3, "0011001111001100"));
//        System.err.println(reg.getBinary());

    }

    private int testFunctions() {
        // INIT TEST
        String test = "11110000110000110000001100000000";
        System.err.println(test);
        System.err.println(test.length());
        append(test);
        //END INIT

        System.err.println(Integer.parseUnsignedInt(test, 2));
        System.err.println(getInt(0));
        return 0;
    }

    @Override
    public String toString() {
        return getBinary();
    }
}
