package ui;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import simulation.Controller;

import java.util.ArrayList;

public class ButtonStrip {

    /** The Controller and the GUI, to allow modifying their states with the Buttons. */
    private Controller control;
    private GUI gui;

    /** All the buttons. */
    private Button newButton;
    private Button openButton;
    private Button saveButton;
    private Button stepBackButton;
    private Button stopRunButton;
    private Button pauseRunButton;
    private Button assembleButton;
    private Button runProgramButton;
    private Button runInstructionButton;
    private Button runCycleButton;
    private Button cyclesPerSecButton;
    private Button resetRegButton;

    /** A list of all the buttons. */
    private ArrayList<Button> buttons;

    /** Whether this is the first time that an instruction is being run since assembly. */
    private boolean firstRun;

    /** Constructor which creates the strip.
     *  @param control The Controller which this strip will manipulate.
     *  @param gui The GUI which this strip will manipulate.
     */
    public ButtonStrip(Controller control, GUI gui) {
        this.control = control;
        this.gui = gui;

        newButton = makeNewButton();
        openButton = makeOpenButton();
        saveButton = makeSaveButton();
        stepBackButton = makeStepBackButton();
        stopRunButton = makeStopButton();
        pauseRunButton = makePauseButton();
        assembleButton = makeAssembleButton();
        runProgramButton = makeRunButton();
        runInstructionButton = makeInstructButton();
        runCycleButton = makeStepButton();
        cyclesPerSecButton = makeCyclesPerSecButton();
        resetRegButton = makeResetButton();

        buttons = initButtons();

        firstRun = true;
    }

    /**
     * Adds all buttons to a list and returns it.
     * @return The list of buttons.
     */
    private ArrayList<Button> initButtons() {
        ArrayList<Button> buttons = new ArrayList<>(11); //Magic number of buttons.
        buttons.add(newButton);
        buttons.add(openButton);
        buttons.add(saveButton);
        buttons.add(stepBackButton);
        buttons.add(stopRunButton);
        buttons.add(pauseRunButton);
        buttons.add(assembleButton);
        buttons.add(runProgramButton);
        buttons.add(runInstructionButton);
        buttons.add(runCycleButton);
        buttons.add(cyclesPerSecButton);
        buttons.add(resetRegButton);

        return buttons;
    }

    /** Gets the list of buttons. */
    public ArrayList<Button> getButtons() {
        return buttons;
    }

    /** The button for newFile. */
    private Button makeNewButton() {
        Button button = new Button("New File");
        button.setOnMouseReleased(event -> {}); //TODO new
        button.setDisable(true);
        return button;
    }

    /** The button for openFile. */
    private Button makeOpenButton() {
        Button button = new Button("Open File");
        button.setOnMouseReleased(event -> new FileChooser());
        return button;
    }

    /** The button for saveFile. */
    private Button makeSaveButton() {
        Button button = new Button("Save File");
        button.setOnMouseReleased(event -> {}); //TODO save
        button.setDisable(true);
        return button;
    }

    /** The button to step the simulator back a step. Not implemented. */
    private Button makeStepBackButton() {
        Button button = new Button("Step Back");
        button.setOnMouseReleased(event -> {}); //TODO step back
        button.setDisable(true);
        return button;
    }

    /** The button to pause the simulator in the middle of an instruction. */
    private Button makePauseButton() {
        Button button = new Button("Pause Run");
        button.setOnMouseReleased(event -> {
            try {
                gui.getGo().acquire(2);
                gui.getGo().release();
            } catch(InterruptedException ie) {
                //
            }
            button.setDisable(true);
//            runProgramButton.setDisable(false);
            stepBackButton.setDisable(false);
        });
        button.setDisable(true);
        return button;
    }

    /** The button to assemble the open file to machine code and prep the simulator for run. */
    private Button makeAssembleButton() {
        Button button = new Button("Assemble!");
        button.setOnMouseReleased(event -> {
            firstRun = true;
            control.assemble();
            runProgramButton.setDisable(false);
            runCycleButton.setDisable(false);
            runInstructionButton.setDisable(false);
            gui.getOutPane().getSelectionModel().select(1);
            gui.getGo().drainPermits();
            gui.getCycleCPU().drainPermits();
            gui.getCycleInstruction().drainPermits();
            gui.getCycleToEnd().drainPermits();
            gui.getGo().release();
            Thread thread = new Thread(control);
            thread.setDaemon(true);
            thread.start();
        });
        return button;
    }

    /** The button to step the simulator forward a single cpu cycle. */
    private Button makeStepButton() {
        Button button = new Button("Cycle CPU");
        button.setOnMouseReleased(event -> {
            if(firstRun) {
                control.reset();
                firstRun = false;
            }
            gui.getCycleCPU().release();
            gui.getGo().release();
//            try {
//                Thread.sleep(10);
////                System.out.println("Reacquired Go to GUI thread");
////                System.out.flush();
////                gui.getGo().acquire();
//            } catch(InterruptedException ie) {
//                //Shouldn't be an issue
//            }
//            if(!control.cycle()) {
//                System.out.println("Reached end of instructions.");
//                button.setDisable(true);
//                runProgramButton.setDisable(true);
//                runInstructionButton.setDisable(true);
//            }
        });
        button.setDisable(true);
        return button;
    }

    /** The button to run the simulator until it finishes. */
    private Button makeRunButton() {
        Button button = new Button("Start/Continue");
        button.setOnMouseReleased(event -> {
            button.setDisable(true);
            stopRunButton.setDisable(false);
            pauseRunButton.setDisable(false);
            runInstructionButton.setDisable(true);
            runCycleButton.setDisable(true);
            if(firstRun) {
                control.reset();
                firstRun = false;
            }
            gui.getCycleToEnd().release();
            gui.getGo().release();
        });
        button.setDisable(true);
        return button;
    }

    /** The button to run the simulator forward a single instruction */
    private Button makeInstructButton() {
        Button button = new Button("Step Forward");
        button.setOnMouseReleased(event -> {
            if(firstRun) {
                control.reset();
                firstRun = false;
            }
            gui.getCycleInstruction().release();
            gui.getGo().release();
        });
        button.setDisable(true);
        return button;
    }

    /** The button to stop the simulator where it is. */
    private Button makeStopButton() {
        Button button = new Button("Stop Run");
        button.setOnMouseReleased(event -> {
            try {
                gui.getGo().acquire(2);
                gui.getGo().release();
            } catch(InterruptedException ie) {
                //
            }
            button.setDisable(true);
            runProgramButton.setDisable(false);
            assembleButton.setDisable(false);
        });
        button.setDisable(true);
        return button;
    }

    /** The button to alter the number of cycles per second. Unimplemented */
    private Button makeCyclesPerSecButton() {
        Button button = new Button("Cycles/Sec");
        button.setOnMouseReleased(event -> {});
        button.setDisable(true);
        return button;
    }

    /** The button to reset the registers to their initial values. */
    private Button makeResetButton() {
        Button button = new Button("Reset Simulator");
        button.setOnMouseReleased(event -> control.reset());
        return button;
    }

}
