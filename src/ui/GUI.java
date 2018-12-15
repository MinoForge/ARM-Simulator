package ui;

import javafx.application.Application;
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

import java.io.File;
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
    private File currentFile;

    /** The width of the window. */
    private static final int WIDTH = 1200;

    /** The height of the window. */
    private static final int HEIGHT = 800;


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

        //Make sections of GUI.
        Parent top = makeTop();
        Parent mid = makeMid("This is where the file contents go\n" +
                "This isn't a file though.");
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
    }


    /**
     * Method to make the strip of Buttons to go along the top of the window.
     * @return The strip of Buttons.
     */
    private HBox makeTop() {
        HBox buttonPane = new HBox();
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(new Button("New File"));
        buttons.add(new Button("Open File"));
        buttons.add(new Button("Save File"));
        buttons.add(new Button("Step Back"));
        buttons.add(new Button("Stop Run"));
        buttons.add(new Button("Pause Run"));
        buttons.add(new Button("Assembly, Assemble!"));
        buttons.add(new Button("Start/Continue"));
        buttons.add(new Button("Step Forward"));
        buttons.add(new Button("Cycles/Sec"));
//        buttons.add(new Button("0x"));
        buttonPane.getChildren().addAll(buttons);
        return buttonPane;
    }


    /**
     * Method to make the middle of the window, including the file text and registers.
     * @param fileContents The contents of the File to be displayed.
     * @return The pane containing the text, and a table of registers.
     */
    private SplitPane makeMid(String fileContents) {
        //Text Pane and resizing code.
        TextArea fileText = new TextArea(fileContents);
        fileText.setEditable(false);
        ScrollPane filePane = new ScrollPane(fileText);
        filePane.setFitToWidth(true);
        filePane.setFitToHeight(true);

        //Register Table Pane and resizing code.
        TableView<Register> regTable = makeRegTable();
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
    private TableView<Register> makeRegTable() {
        ArrayList<Register> regList = new ArrayList<>(32);
        for(int i = 0; i < 32; i++) {
            regList.add(new Register(i));
        }

        ObservableList<Register> obsRegList = FXCollections.observableArrayList(regList);
        TableView<Register> regTable = new TableView<>(obsRegList);

        TableColumn<Register, String> colOne = new TableColumn<>("Reg #");
        colOne.setCellValueFactory(new PropertyValueFactory<>("regNum"));
        TableColumn<Register, String> colTwo = new TableColumn<>("Binary");
        colTwo.setCellValueFactory(new PropertyValueFactory<>("binVal"));
        TableColumn<Register, String> colThree = new TableColumn<>("Decimal");
        colThree.setCellValueFactory(new PropertyValueFactory<>("decVal"));
        TableColumn<Register, String> colFour = new TableColumn<>("Hex");
        colFour.setCellValueFactory(new PropertyValueFactory<>("hexVal"));
        TableColumn<Register, String> colFive = new TableColumn<>("Bytes");
        colFive.setCellValueFactory(new PropertyValueFactory<>("bytes"));

        regTable.getColumns().setAll(colOne, colTwo, colThree, colFour, colFive);

        regTable.setFixedCellSize(25);
        regTable.prefHeightProperty().bind(regTable.fixedCellSizeProperty().multiply(Bindings.size(
                regTable.getItems()).add(1.01)));
        regTable.minHeightProperty().bind(regTable.prefHeightProperty());
        regTable.maxHeightProperty().bind(regTable.prefHeightProperty());

        return regTable;
    }

    /**
     * Method to create the TabPane which will have output messages from the simulator,
     * and I/O to the 'chip' itself.
     * @return TabPane containing one tab bound to simulator messages, and one tab bound to the I/O
     * of the 'chip'.
     */
    private TabPane makeBot() {

        TextArea simText = new TextArea("This will be messages from the simulator.");
        simText.setMinWidth(WIDTH);
        simText.setEditable(false);
        ScrollPane simScroll = new ScrollPane(simText);
        simScroll.setFitToWidth(true);
        simScroll.setFitToHeight(true);
        //TODO attach simText to Simulator Message Output.
        Tab tabOne = new Tab("Simulator Messages", simScroll);

        TextArea sysText = new TextArea("This will be system output from the 'chip'.");
//        sysText.setEditable(false);
        sysText.setMinWidth(WIDTH);
        ScrollPane sysScroll = new ScrollPane(sysText);
        sysScroll.setFitToWidth(true);
        sysScroll.setFitToHeight(true);
        //TODO attach sysText to 'System' output/input
        Tab tabTwo = new Tab("System I/O", sysScroll);


        TabPane outPane = new TabPane(tabOne, tabTwo);
        tabOne.setClosable(false);
        tabTwo.setClosable(false);

        return outPane;
    }

}
