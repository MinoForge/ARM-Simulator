package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import simulation.Controller;
import simulation.registers.Register;
import simulation.registers.RegisterFile;

import java.io.*;
import java.util.ArrayList;


/**
 * Class which will eventually be the main GUI class.  For now, it is only a mockup.
 *
 * @author Peter Gardner
 * @author Caleb Dinehart
 * @version November 30, 2018
 */
public class GUI extends Application {

    /** Main window. */
    private Stage theStage;

    /** The Scene which goes in theStage */
    private Scene theScene;

    /** The file being read. */
    private String file;

    private Controller control;

    /** The width of the window. */
    private static final int WIDTH = 1200;

    /** The height of the window. */
    private static final int HEIGHT = 800;

    private TextArea simOut;

    private TextArea simErr;

    private TabPane outPane;


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

        control = new Controller(file, true);


        //Make sections of GUI.
        Parent top = makeTop();
        Parent mid = makeMid(control.getFile(file), control.getRegFile());
        Parent bot = makeBot();



        //Attach sections together.
        SplitPane midBot = new SplitPane(mid, bot);
        midBot.setOrientation(Orientation.VERTICAL);
        midBot.setDividerPositions(.8);
        VBox mainWindow = new VBox(top, midBot);

        //Make scene and put into stage.
        theScene = new Scene(mainWindow, WIDTH, HEIGHT);
        theStage.setScene(theScene);
        theStage.show();

        test();
    }

    public void test() {
        control.setTestRegs();
//        control.cycleToEnd();
//        Scanner scanIn = new Scanner(System.in);
//        while(!scanIn.nextLine().equals("q")) {
//            control.cycle();
//        }
    }


    /**
     * Method to make the middle of the window, including the file text and registers.
     * @param fileContents The contents of the File to be displayed.
     * @return The pane containing the text, and a table of registers.
     */
    private SplitPane makeMid(String fileContents, RegisterFile regFile) {
        //Text Pane and resizing code.
        TextArea fileText = new TextArea(fileContents);
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

        ObservableList<Register> obsRegList = FXCollections.observableArrayList(regFile.getRegisters());
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

    private void appendText(TextArea out, String str) {
        Platform.runLater(() -> out.appendText(str));
    }

    /**
     * Method to create the TabPane which will have output messages from the simulator,
     * and I/O to the 'chip' itself.
     * @return TabPane containing one tab bound to simulator messages, and one tab bound to the I/O
     * of the 'chip'.
     */
    private TabPane makeBot() {

        this.simErr = new TextArea("Welcome to ARM & Simulator!\nError messages will show here");
        simErr.setMinWidth(WIDTH);
        simErr.setEditable(false);
        OutputStream err = new OutputStream() {
            public void write(int b) throws IOException {
                appendText(simErr, String.valueOf((char) b));
            }
        };

        //Intellij is weird. Uncomment for hilarity with previous line of code.
//        OutputStream er = (int b) -> {
//            appendText(simErr, String.valueOf((char) b));
//        };
        System.setErr(new PrintStream(err, true));

        ScrollPane simScroll = new ScrollPane(simErr);
        simScroll.setFitToWidth(true);
        simScroll.setFitToHeight(true);


        Tab tabOne = new Tab("Simulator Messages", simScroll);

        this.simOut = new TextArea("Simulator I/O");
        simOut.setEditable(true);
        simOut.setMinWidth(WIDTH);
        OutputStream out = new OutputStream() {
            public void write(int b) throws IOException {
                appendText(simOut, String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));
//        InputStream

        ScrollPane sysScroll = new ScrollPane(simOut);
        sysScroll.setFitToWidth(true);
        sysScroll.setFitToHeight(true);
        //TODO attach sysText to 'System' output/input
        Tab tabTwo = new Tab("System I/O", sysScroll);


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

    public TabPane getOutPane() {
        return outPane;
    }


}
