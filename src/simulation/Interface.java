package simulation;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.Semaphore;

public class Interface implements Runnable {

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
            }
        });

        areas[2] = new TextArea(); //Simulator Err

        streams = new PrintStream[3];
        attachStreams();
    }

    public void run() {
        while(!Thread.interrupted()) {
            try { //COMMENT THIS AND EXECUTE PLOX
                input.acquire(2);
                System.out.flush();
                areas[1].appendText("\n");
                currentInput = areas[1].getText().substring(beforeInputLength);
                currentInput = currentInput.substring(currentInput.indexOf(">>>") + 3);
                currentInput = currentInput.substring(0, currentInput.indexOf("\n")).trim();
                input.release(3);
                input.acquire(4);

            } catch (InterruptedException ie) {
                //
            }
        }
    }

    public static Semaphore getInput() {
        if(instance == null) {
            instance = new Interface();
        }

        return instance.input;
    }

    private void attachStreams() {
        streams[0] = new PrintStream(new OutputStream() {
            public void write(int b) throws IOException {
                instance.appendText(areas[0], String.valueOf((char) b));
            }
        }, true);

        streams[1] = new PrintStream(new OutputStream() {
            public synchronized void write(int b) throws IOException {
                instance.appendText(areas[1], String.valueOf((char) b));
            }
            @Override
            public synchronized void flush() throws IOException {
                super.flush();
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

        return instance.currentInput;
    }

    public static void setBeforeInputLength(int len) {
        if(instance == null) {
            instance = new Interface();
        }

        instance.beforeInputLength = len;
    }

    public static Interface theInterface() {
        if(instance == null) {
            instance = new Interface();
        }

        return instance;
    }







}
