package Simulation.Pipeline;

/**
 * An abstract class to define a few key methods which must be available in any Pipeline Segment.
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
}
