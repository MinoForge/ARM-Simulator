package UI;

import Simulation.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //TODO Make window nice











        File file = null; //TODO get file from user
        Controller controller = new Controller(file); //TODO
    }
}
