package simulation.pipeline;

/**
 * An abstract class to define a few key methods which must be available in any pipeline Segment.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public abstract class PipelineSegment {

    private void write() {}

    private void read() {}

    /**
     * Method to make sure that the binary representation of the registers
     * have the correct number bits. It concatenates zeros to the front of
     * the binary string and returns the corrected string.
     * @param reg the string to be checked/altered
     * @param num the number of bits needed
     * @return the corrected string
     */
    public static String correctBits(String reg, int num) { //TODO needs testing and expansion
        StringBuilder str = new StringBuilder(reg);

        while (str.length() < num) {
            str.insert(0, '0');
//            str.insert(0, str.charAt(0));
        }

        while(str.length() > num) {
            str.deleteCharAt(0);
        }
        return str.toString();
    }

//    public static String correctedCorrectBits(String binary, int currentMaxSize, int newSize) {
//        StringBuilder str = new StringBuilder(binary);
//
//        while (str.length() < newSize && str.length() == currentMaxSize) {
////            str.insert(0, '0');
//            str.insert(0, str.charAt(0));
//        }
//
//        while(str.length() > num) {
//            str.deleteCharAt(0);
//        }
//        return str.toString();
//    }

    abstract public void execute();

    public String interpretPipeReg() {
        return "This PipelineSegment does not support this operation.";
    }

    public static void main(String... args) {
        long longer = 5L;
        int num = 5;
        byte shorter = (byte)5;
        String pos = Integer.toBinaryString(num);
        String neg = Integer.toBinaryString(-num);
        System.out.println(pos);
        System.out.println(neg);
        String corrected;
        String result = "";

        //Test 1: Extend Pos
        corrected = correctBits(pos, 64);
        System.out.println(corrected);
        result += corrected.equals(Long.toBinaryString(longer)) ? "Test passed.\n" : "Test failed.\n";
        //Test 2: Shrink Pos
        corrected = correctBits(pos, 8);
        System.out.println(corrected);
        result += corrected.equals("00000101") ? "Test passed.\n" : "Test failed.\n";
        //Test 3: Extend Neg
        corrected = correctBits(neg, 64);
        System.out.println(corrected);
        result += corrected.equals(Long.toBinaryString(-longer)) ? "Test passed.\n" : "Test failed.\n";
        //Test 4: Shrink Neg
        corrected = correctBits(neg, 8);
        System.out.println(corrected);
        result += corrected.equals(Byte.toString((byte)-shorter)) ? "Test passed.\n" : "Test failed.\n";
        System.out.println(result);

    }
}
