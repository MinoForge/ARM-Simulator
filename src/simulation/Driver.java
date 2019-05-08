package simulation;

import assembling.Assembler;
import ui.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main class to run to start up the controller and begin running a file through the simulator.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 30, 2018
 *
 */
public class Driver {

    /** The file to be read from. */
    private File file;

    /** The contents of the file. */
    private String fileString;

    /** String containing all errors which the parser encounters. */
    private String errorMsg;

    /**
     * Constructor which takes a fileName.
     * @param fileName the name of the File.
     */
    public Driver(String fileName) {
        Scanner fileIn = null;
        try {
            this.file = new File(fileName);
            fileIn = new Scanner(file);
        } catch(FileNotFoundException fnfe) {
            System.err.println("File not Found.");
            usage();
            System.exit(2);
        }
        StringBuilder tempStr = new StringBuilder();
        while(fileIn.hasNextLine()) {
            tempStr.append(fileIn.nextLine());
            tempStr.append("\n");
        }
        this.fileString = tempStr.toString();

        this.errorMsg = "";
    }

    /**
     * Main method to start up the Simulator.
     * @param args Takes a fileName.
     */
    public static void main(String... args) { //TODO add functionality
        String arg = "TestFile.txt";
        if (args.length == 1) //{
            arg = args[0];
//        } else {
//            System.err.println("Wrong number of arguments.");
//            usage();
//            System.exit(1);
//        }
//        Assembler assembler = new Assembler(arg);
//        Controller control = new Controller(arg, true, assembler);
//        control.assemble();
//        control.setTestRegs();
//        control.start();

//        GUI gui = new GUI();
//        GUI.main(new String[0]);
//        try {
//            Process proc = Runtime.getRuntime().exec("java GUI");
//        } catch (IOException ioe) {
//            System.err.println(ioe.getStackTrace());
//        }





//        control.cycleToEnd();
    }

    public static void usage() {
        System.err.println("java Driver <filename>");
    }
}
