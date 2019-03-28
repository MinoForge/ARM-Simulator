# ARM and Pipeline
2018-2019 WCU Capstone ARM Simulator

Authors: Caleb Dinehart & Peter Gardner


<b>TO COMPILE:</b>

    compile.sh
    //This would probably fail on Windows.
    //Please compile on Agora before moving off for GUI testing.


<b>TO RUN: make sure you are in /src/ before running</b>

Pipeline Demo:

    java simulation.pipeline.PipelineTester
    //Known bug where some of the prints in decode do not come from the instruction
    //being run.
    
assembling.parsing Demo:

    java simulation.Driver <TestFile.txt>
    //Note, a test filePath has been included, but you may choose your own.
    
    
GUI Demo:

    java ui.GUI
    //Runs in JavaFX, does not run in Agora.
