package simulation;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.Semaphore;


/**
 * Class to handle output to gui, as well as catching proper input into gui.
 *
 * @author Peter Gardner
 * @version May 10, 2019
 */
public class Interface {

    private static Interface instance;

    private TextArea[] areas;
    private PrintStream[] streams = new PrintStream[3];

    private String currentInput;
    private int beforeInputLength;
    private Semaphore input;

    private Interface() {
        currentInput = "";
        beforeInputLength = 0;
        input = new Semaphore(0);

        areas = new TextArea[3];
        areas[0] = null; //Do not use, but could in the future.
        areas[1] = new TextArea(); //Simulator Out

        areas[1].setOnKeyPressed(key -> {
            if(key.getCode() == KeyCode.ENTER) {
                input.release();
                try {
                    if (input.availablePermits() == 1) {
                        input.acquire();
                    }
                } catch(InterruptedException ie) {
                    //
                }
            }
        });

        areas[1].setOnMouseClicked(event -> beforeInputLength = areas[1].getCaretPosition());

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

        return instance.areas;
    }

    public static PrintStream[] getStreams() {
        if(instance == null) {
            instance = new Interface();
        }
        return instance.streams;
    }

    public static String getNewInput() {
        if(instance == null) {
            instance = new Interface();
        }
        instance.input.release();
        try {
            instance.input.acquire(2);
        } catch(InterruptedException ie) {

        }
        instance.currentInput = instance.areas[1].getText().substring(instance.beforeInputLength).trim();
        System.err.println(instance.currentInput);

        return instance.currentInput;
    }







}
