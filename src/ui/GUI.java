package ui;

import assembling.Assembler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import simulation.Controller;
import simulation.Interface;
import simulation.registers.Register;
import simulation.registers.RegisterFile;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;


/**
 * Class which will eventually be the main GUI class.  For now, it is only a mockup.
 *
 * @author Peter Gardner
 * @author Caleb Dinehart
 * @version May 10, 2019
 */
public class GUI extends Application {

    /** Main window. */
    private Stage theStage;

    /** The Scene which goes in theStage */
    private Scene theScene;

    /** The file being read. */
    private String file;

    private Controller control;
    private Assembler assembler;

    /** The width of the window. */
    private static final int WIDTH = 1200;

    /** The height of the window. */
    private static final int HEIGHT = 800;

    private TextArea simOut;

    private TextArea simErr;

    private TabPane outPane;

    private Semaphore go;
    private Semaphore cycleCPU;
    private Semaphore cycleToEnd;
    private Semaphore cycleInstruction;


    /** Main. */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The JavaFX main start method.
     * @param primaryStage The window we are building our GUI in.
     */
    @Override
    public void start(Stage primaryStage) {
        this.theStage = primaryStage;
        this.theStage.setTitle("ARM & pipeline");

        file = "TestFile.txt";
//
//        Process proc = Runtime.getRuntime().exec
        this.go = new Semaphore(0);
        this.cycleCPU = new Semaphore(0);
        this.cycleInstruction = new Semaphore(0);
        this.cycleToEnd = new Semaphore(0);


        assembler = new Assembler(file);
        control = new Controller(file, true, assembler,
                go, cycleCPU, cycleInstruction, cycleToEnd);



        //Make sections of GUI.
        Parent top = makeTop();
        Parent mid = makeMid(control.getFile(file), control.getRegFile());
        Parent bot = makeBot();



        //Attach sections together.
        SplitPane midBot = new SplitPane(mid, bot);
        midBot.setOrientation(Orientation.VERTICAL);
        midBot.setDividerPositions(.2); //Debug Code
//        midBot.setDividerPositions(.8); //Real code
        VBox mainWindow = new VBox(top, midBot);

        //Make scene and put into stage.
        theScene = new Scene(mainWindow, WIDTH, HEIGHT);
        theStage.setScene(theScene);
        theStage.show();

    }


    /**
     * Method to make the middle of the window, including the file text and registers.
     * @param fileContents The contents of the File to be displayed.
     * @return The pane containing the text, and a table of registers.
     */
    private SplitPane makeMid(String fileContents, RegisterFile regFile) {
        //Text Pane and resizing code.
        String[] fileArray = fileContents.split("\n");
        StringBuilder displayFile = new StringBuilder();
        for(int i = 0; i < fileArray.length; i++) {
            int j = i == 0 ? 1 : i;
//            System.out.println(j);
            displayFile.append(i);
            String str = String.format("%" + (8-(int)Math.log10(j)) + "s", "");
            displayFile.append(str);
            displayFile.append(fileArray[i]);
            displayFile.append("\n");
        }

        TextArea fileText = new TextArea(displayFile.toString());
        fileText.setStyle("-fx-font-family: 'monospaced';");
        fileText.setEditable(false);
        ScrollPane filePane = new ScrollPane(fileText);
        filePane.setFitToWidth(true);
        filePane.setFitToHeight(true);

        //Register Table Pane and resizing code.
        TableView<Register> regTable = makeRegTable(regFile);
        ScrollPane regPane = new ScrollPane(regTable);
        regPane.setFitToWidth(true);
        regPane.setFitToHeight(true);

        //Creation of SplitPane and more resizing properties.
        SplitPane midPane = new SplitPane(filePane, regPane);
        midPane.setDividerPositions(.5);
        SplitPane.setResizableWithParent(filePane, true);
        SplitPane.setResizableWithParent(regPane, true);
        fileText.setMinWidth(midPane.getDividerPositions()[0]);

        return midPane;
    }

    /**
     * Helper method to create the Table for the Registers. For mockup, creates default registers.
     * @return The Table of all Registers and their current values.
     */
    private TableView<Register> makeRegTable(RegisterFile regFile) {

        ObservableList<Register> obsRegList = FXCollections.observableList(regFile.getRegisters(),
                (Register r) -> new Observable[]{r.regNumProperty(), r.binValProperty(),
                        r.decValProperty(), r.hexValProperty()});
        TableView<Register> regTable = new TableView<>(obsRegList);


        TableColumn<Register, String> colOne = new TableColumn<>("Reg #");
        colOne.setCellValueFactory(new PropertyValueFactory<>("regNum"));
        TableColumn<Register, String> colTwo = new TableColumn<>("Binary");
        colTwo.setCellValueFactory(new PropertyValueFactory<>("binVal"));
        TableColumn<Register, String> colThree = new TableColumn<>("Decimal");
        colThree.setCellValueFactory(new PropertyValueFactory<>("decVal"));
        TableColumn<Register, String> colFour = new TableColumn<>("Hex");
        colFour.setCellValueFactory(new PropertyValueFactory<>("hexVal"));
//        TableColumn<Register, String> colFive = new TableColumn<>("Bytes");
//        colFive.setCellValueFactory(new PropertyValueFactory<>("bytes"));

        regTable.getColumns().setAll(colOne, colTwo, colThree, colFour);
//        regTable.getColumns().setAll(colOne, colTwo, colThree, colFour, colFive);

        regTable.setFixedCellSize(25);
        regTable.prefHeightProperty().bind(regTable.fixedCellSizeProperty().multiply(Bindings.size(
                regTable.getItems()).add(1.01)));
        regTable.minHeightProperty().bind(regTable.prefHeightProperty());
        regTable.maxHeightProperty().bind(regTable.prefHeightProperty());
        regTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        regTable.getColumns().get(0).setMaxWidth(70);
        regTable.getColumns().get(1).setMinWidth(300);
        regTable.getColumns().get(1).setMaxWidth(600);
        regTable.getColumns().get(2).setMaxWidth(120);
        regTable.getColumns().get(3).setMaxWidth(60);//TODO Fix gui

        return regTable;
    }


    /**
     * Method to create the TabPane which will have output messages from the simulator,
     * and I/O to the 'chip' itself.
     * @return TabPane containing one tab bound to simulator messages, and one tab bound to the I/O
     * of the 'chip'.
     */
    private TabPane makeBot() {

        TextArea[] areas = Interface.getAreas();
        PrintStream[] streams = Interface.getStreams();

        this.simErr = areas[2];
        simErr.setMinWidth(WIDTH);
        simErr.setEditable(false);

        //Intellij is weird. Uncomment for hilarity with previous line of code.
//        OutputStream er = (int b) -> {
//            appendText(simErr, String.valueOf((char) b));
//        };
//        System.setErr(streams[2]);
        System.err.println("Welcome to ARM & Simulator!\nError messages will show here");

        ScrollPane simScroll = new ScrollPane(simErr);
        simScroll.setFitToWidth(true);
        simScroll.setFitToHeight(true);


        Tab tabOne = new Tab("Simulator Messages", simScroll);

        this.simOut = areas[1];
        simOut.setEditable(true);
        simOut.setMinWidth(WIDTH);
        System.setOut(streams[1]);
        System.out.println("Simulator I/O\n");
//        InputStream

        ScrollPane sysScroll = new ScrollPane(simOut);
        sysScroll.setFitToWidth(true);
        sysScroll.setFitToHeight(true);
        Tab tabTwo = new Tab("System I/O\n", sysScroll);


        this.outPane = new TabPane(tabOne, tabTwo);
        tabOne.setClosable(false);
        tabTwo.setClosable(false);

        return outPane;
    }

    /**
     * Method to make the strip of Buttons to go along the top of the window.
     * @return The strip of Buttons.
     */
    private HBox makeTop() {
        HBox buttonPane = new HBox();

        ButtonStrip bStrip = new ButtonStrip(control, this);
        ArrayList<Button> buttons = bStrip.getButtons();

        buttonPane.getChildren().addAll(buttons);
        return buttonPane;
    }

    TabPane getOutPane() {
        return outPane;
    }




    public Semaphore getGo() {
        return go;
    }

    public Semaphore getCycleCPU() {
        return cycleCPU;
    }

    public Semaphore getCycleToEnd() {
        return cycleToEnd;
    }

    public Semaphore getCycleInstruction() {
        return cycleInstruction;
    }
}
