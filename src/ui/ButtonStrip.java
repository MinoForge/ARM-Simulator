package ui;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import simulation.Controller;

import java.util.ArrayList;

public class ButtonStrip {

    private Controller control;
    private GUI gui;

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

    private ArrayList<Button> buttons;



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
        resetRegButton = makeResetRegButton();

        buttons = initButtons();
    }

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

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    private Button makeNewButton() {
        Button button = new Button("New File");
        button.setOnMouseReleased(event -> {}); //TODO new
        button.setDisable(true);
        return button;
    }

    private Button makeOpenButton() {
        Button button = new Button("Open File");
        button.setOnMouseReleased(event -> new FileChooser());
        return button;
    }

    private Button makeSaveButton() {
        Button button = new Button("Save File");
        button.setOnMouseReleased(event -> {}); //TODO save
        button.setDisable(true);
        return button;
    }

    private Button makeStepBackButton() {
        Button button = new Button("Step Back");
        button.setOnMouseReleased(event -> {});
        button.setDisable(true);
        return button;
    }

    private Button makePauseButton() {
        Button button = new Button("Pause Run");
        button.setOnMouseReleased(event -> {
            control.setHalt(true);
            button.setDisable(true);
            runProgramButton.setDisable(false);
            stepBackButton.setDisable(false);
        });
        button.setDisable(true);
        return button;
    }

    private Button makeAssembleButton() {
        Button button = new Button("Assemble!");
        button.setOnMouseReleased(event -> {
            control.setHalt(false);
            control.assemble();
            runProgramButton.setDisable(false);
            runCycleButton.setDisable(false);
            runInstructionButton.setDisable(false);
            gui.getOutPane().getSelectionModel().select(1);
        });
        return button;
    }

    private Button makeStepButton() {
        Button button = new Button("Cycle CPU");
        button.setOnMouseReleased(event -> {
            if(Controller.PC == 0) {
                control.start();
                control.resetReg();
            }
            if(!control.cycle()) {
                System.out.println("Reached end of instructions.");
                button.setDisable(true);
                runProgramButton.setDisable(true);
                runInstructionButton.setDisable(true);
            }
        });
        button.setDisable(true);
        return button;
    }

    private Button makeRunButton() {
        Button button = new Button("Start/Continue");
        button.setOnMouseReleased(event -> {
            button.setDisable(true);
            stopRunButton.setDisable(false);
            pauseRunButton.setDisable(false);
            runInstructionButton.setDisable(true);
            runCycleButton.setDisable(true);
            if(Controller.PC == 0) {
                control.start();
                control.resetReg();
            }
            control.cycleToEnd();
            System.out.println("Reached end of instructions.");
            button.setDisable(true);
            pauseRunButton.setDisable(true);
            stopRunButton.setDisable(true);
            control.stop();
            System.out.println(Controller.PC);
        });
        button.setDisable(true);
        return button;
    }

    private Button makeInstructButton() {
        Button button = new Button("Step Forward");
        button.setOnMouseReleased(event -> {
            if(Controller.PC == 0) {
                control.start();
                control.resetReg();
            }
            if(!control.doInstruction()) {
                System.out.println("Reached end of instructions.");
                button.setDisable(true);
                runProgramButton.setDisable(true);
                runCycleButton.setDisable(true);
                control.stop();
            }
        });
        button.setDisable(true);
        return button;
    }

    private Button makeStopButton() {
        Button button = new Button("Stop Run");
        button.setOnMouseReleased(event -> {
            control.stop();
            button.setDisable(true);
            runProgramButton.setDisable(false);
            assembleButton.setDisable(false);
        });
        button.setDisable(true);
        return button;
    }

    private Button makeCyclesPerSecButton() {
        Button button = new Button("Cycles/Sec");
        button.setOnMouseReleased(event -> {});
        button.setDisable(true);
        return button;
    }

    private Button makeResetRegButton() {
        Button button = new Button("Reset Registers");
        button.setOnMouseReleased(event -> control.resetReg());
        return button;
    }

}
