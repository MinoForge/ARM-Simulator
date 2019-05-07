package simulation;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Interface {

    private static Interface instance;

    private static TextArea[] areas;
    private static PrintStream[] streams = new PrintStream[3];

    private Interface() {
        areas = new TextArea[3];
        areas[0] = null; //Do not use, but could in the future.
        areas[1] = new TextArea(); //Simulator Out

        areas[1].setOnKeyPressed(key -> {
            if(key.getCode() == KeyCode.ENTER) {
                areas[1].notify();
            }
        });

        areas[2] = new TextArea(); //Simulator Err

        streams = new PrintStream[3];
        attachStreams();
    }

    private void attachStreams() {
        streams[0] = new PrintStream(new OutputStream() {
            public void write(int b) throws IOException {
                instance.appendText(areas[0], String.valueOf((char) b));
            }
        }, true);

        streams[1] = new PrintStream(new OutputStream() {
            public void write(int b) throws IOException {
                instance.appendText(areas[1], String.valueOf((char) b));
            }
        }, true);

        streams[2] = new PrintStream(new OutputStream() {
            public void write(int b) throws IOException {
                instance.appendText(areas[2], String.valueOf((char) b));
            }
        }, true);
    }

    private void appendText(TextArea area, String str) {
        Platform.runLater(() -> area.appendText(str));
    }

    public static TextArea[] getAreas() {
        if(instance == null) {
            instance = new Interface();
        }

        return areas;
    }

    public static PrintStream[] getStreams() {
        if(instance == null) {
            instance = new Interface();
        }
        return streams;
    }







}
