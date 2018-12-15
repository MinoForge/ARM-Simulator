# ARM and Pipeline
2018-2019 WCU Capstone ARM Simulator

Authors: Caleb Dinehart & Peter Gardner


<b>TO COMPILE:</b>

    compile.sh
    //This would probably fail on Windows.
    //Please compile on Agora before moving off for GUI testing.


<b>TO RUN:</b>

Pipeline Demo:

    java src.simulation.pipeline.PipelineTester
    //Known bug where some of the prints in decode do not come from the instruction
    //being run.
    
Parsing Demo:

    java src.simulation.Driver <TestFile.txt>
    //Note, a test file has been included, but you may choose your own.
    
    
GUI Demo:

    java src.ui.GUI
    //Runs in JavaFX, does not run in Agora.