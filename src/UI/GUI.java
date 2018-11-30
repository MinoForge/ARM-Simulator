package UI;

import Simulation.Controller;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GUI extends Application {

    private Stage theStage;
    private Scene theScene;
    private File currentFile;

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.theStage = primaryStage;
        this.theStage.setTitle("ARM & Pipeline");




//        this.currentFile = null; //TODO get file from user
//        StringBuilder temp = new StringBuilder();
//        try {
//            Scanner fileIn = new Scanner(currentFile);
//            while(fileIn.hasNextLine()) {
//                temp.append(fileIn.nextLine());
//            }
//        } catch(FileNotFoundException fnfe) {
//            System.out.println("PLEASE FIX YOUR FILING");
//        }
//        String fileContents = temp.toString();


//        Controller controller = new Controller(currentFile); //TODO
        //TODO Make window nice

        Parent top = makeTop();
        Parent mid = makeMid("This is where the file contents go\nThis isn't a file though.");
        Parent bot = makeBot();

        SplitPane midBot = new SplitPane(mid, bot);
        midBot.setOrientation(Orientation.VERTICAL);
        midBot.setDividerPositions(.8);
        VBox mainWindow = new VBox(top, midBot);

        theScene = new Scene(mainWindow, WIDTH, HEIGHT);
        theStage.setScene(theScene);
        theStage.show();








    }


    private HBox makeTop() {
        HBox buttonPane = new HBox();
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(new Button("New File"));
        buttons.add(new Button("Open File"));
        buttons.add(new Button("Save File"));
        buttons.add(new Button("Step Back"));
        buttons.add(new Button("Stop Run"));
        buttons.add(new Button("Pause Run"));
        buttons.add(new Button("Start/Continue"));
        buttons.add(new Button("Step Forward"));
        buttons.add(new Button("Cycles/Sec"));
        buttons.add(new Button("0x"));
        buttonPane.getChildren().addAll(buttons);
        return buttonPane;
    }

    private SplitPane makeMid(String fileContents) {

        TextArea fileText = new TextArea(fileContents);

        fileText.setEditable(false);
        ScrollPane filePane = new ScrollPane(fileText);
        filePane.setFitToWidth(true);
        filePane.setFitToHeight(true);
//        VBox registers = new VBox();

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
//        registers.getChildren().add(regTable);
//        regTable.setItems(obsRegList);

        regTable.setFixedCellSize(25);
        regTable.prefHeightProperty().bind(regTable.fixedCellSizeProperty().multiply(Bindings.size(
                regTable.getItems()).add(1.01)));
        regTable.minHeightProperty().bind(regTable.prefHeightProperty());
        regTable.maxHeightProperty().bind(regTable.prefHeightProperty());

        ScrollPane regPane = new ScrollPane(regTable);
        regPane.setFitToWidth(true);
        regPane.setFitToHeight(true);

        SplitPane midPane = new SplitPane(filePane, regPane);
        midPane.setDividerPositions(.5);
        SplitPane.setResizableWithParent(filePane, true);
        SplitPane.setResizableWithParent(regPane, true);
        fileText.setMinWidth(midPane.getDividerPositions()[0]);

        return midPane;
    }

    private TabPane makeBot() {

        TextArea simText = new TextArea("This will be messages from the simulator.");
        simText.setMinWidth(WIDTH);
//        simText.
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
