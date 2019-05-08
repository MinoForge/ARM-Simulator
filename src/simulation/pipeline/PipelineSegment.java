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

//    /**
//     * Method to make sure that the binary representation of the registers
//     * have the correct number bits. It concatenates zeros to the front of
//     * the binary string and returns the corrected string.
//     * @param reg the string to be checked/altered
//     * @param num the number of bits needed
//     * @return the corrected string
//     */
//    public static String correctBits(String reg, int num) { //TODO needs testing and expansion
//        StringBuilder str = new StringBuilder(reg);
//
//        while (str.length() < num) {
//            str.insert(0, '0');
////            str.insert(0, str.charAt(0));
//        }
//
//        while(str.length() > num) {
//            str.deleteCharAt(0);
//        }
//        return str.toString();
//    }

    /**
     * Method to make sure that the binary representation of the registers
     * have the correct number bits. It concatenates zeros to the front of
     * the binary string and returns the corrected string.
     * @param binary the string to be checked/altered
     * @param newSize Number of bits needed.
     * @param currentMaxSize Number of bits currently inhabiting
     * @return the corrected string
     */
    public static String correctBits(String binary, int newSize, int currentMaxSize) {
        StringBuilder str = new StringBuilder(binary);

        if(str.length() == currentMaxSize) { //Negative number
            if(str.length() < newSize) { //We're sign-extending
                while (str.length() < newSize) { //So insert 1s
                    str.insert(0, '1');
                }
            } else { //We're sign-shrinking
                while (str.length() > newSize) { //So delete from the front
                    str.deleteCharAt(0);
                }
                str.deleteCharAt(0); //Delete one more bit
                str.insert(0, '1'); //Then insert a 1 to retain negativity
            }

        } else { //Positive number
            while (str.length() < newSize) { //We're sign-extending
                str.insert(0, '0'); //So insert 0s
            }
            while (str.length() > newSize) { //We're sign-shrinking
                str.deleteCharAt(0); //So delete from the front
            }
        }

        return str.toString();
    }

    abstract public void execute();

    public String interpretPipeReg() {
        return "This PipelineSegment does not support this operation.";
    }

    public static void main(String... args) {
        long longer = Integer.MAX_VALUE;
        int num = Integer.MAX_VALUE;
        byte shorter = (byte)Integer.MAX_VALUE;
        String pos = Integer.toBinaryString(num);
        String neg = Integer.toBinaryString(-num);
        System.err.println("Positive value: " + pos);
        System.err.println("Negative value: " + neg);
        String corrected;
        String result = "";

        //Test 1: Extend Pos
        corrected = correctBits(pos, 32, 64);
        System.err.println(corrected);
        result += corrected.equals("0000000000000000000000000000000000000000000000000000000000000101") ? "Test passed.\n" : "Test failed.\n";
        //Test 2: Shrink Pos
        corrected = correctBits(pos, 32, 8);
        System.err.println(corrected);
        result += corrected.equals("00000101") ? "Test passed.\n" : "Test failed.\n";
        //Test 3: Extend Neg
        corrected = correctBits(neg, 32, 64);
        System.err.println(corrected);
        result += corrected.equals(Long.toBinaryString(-longer)) ? "Test passed.\n" : "Test failed.\n";
        //Test 4: Shrink Neg
        corrected = correctBits(neg, 32, 8);
        System.err.println(corrected);
        result += corrected.equals(Byte.toString((byte)-shorter)) ? "Test passed.\n" : "Test failed.\n";
        System.err.println(result);

    }
}
