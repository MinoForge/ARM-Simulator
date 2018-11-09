package Simulation.Pipeline;

public abstract class PipelineSegment {

    private void write() {};

    private void read() {}

    abstract public void execute();
}
