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

    abstract public void execute();

    public String interpretPipeReg() {
        return "This PipelineSegment does not support this operation.";
    }
}
