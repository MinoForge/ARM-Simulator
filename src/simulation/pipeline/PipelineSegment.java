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
    public static String correctBits(String reg, int num) {
        StringBuilder str = new StringBuilder(reg);

        while (str.length() < num) {
            str.insert(0, '0');
        }

        while(str.length() > num) {
//            if(reg.charAt(0) == '0') {
                str.deleteCharAt(0);
//            }
        }
        return str.toString();
    }

    abstract public void execute();

    public String interpretPipeReg() {
        return "This PipelineSegment does not support this operation.";
    }
}
