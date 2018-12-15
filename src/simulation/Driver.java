package simulation;

import parsing.antlr.LEGGramLexer;
import parsing.antlr.LEGGramParser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.Scanner;

/**
 * The main class to run to start up the controller and begin running a file through the simulator.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 30, 2018
 *
 */
public class Driver implements ANTLRErrorListener{

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
            System.out.println("File not Found.");
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
    public static void main(String... args){ //TODO add functionality
        String arg = "TestFile.txt";
        if(args.length == 1) //{
            arg = args[0];
//        } else {
//            System.out.println("Wrong number of arguments.");
//            usage();
//            System.exit(1);
//        }
        Driver drive = new Driver(arg);
        drive.parseInput();


    }

    /**
     * Method to parse the String set in fileString.
     * @return true if no problems were encountered in parsing. False otherwise.
     */
    public boolean parseInput() {
        boolean passesParse = true;
        LEGGramLexer lexer = new LEGGramLexer(CharStreams.fromString(this.fileString));
        LEGGramParser parser = new LEGGramParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(this);

        parser.prog();

        System.err.println(this.errorMsg);
        if(!this.errorMsg.equals("")) {
            passesParse = false;
        }

        return passesParse;
    }

    //Begin ErrorListener implementation.
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException re) {


        String sourceName = recognizer.getInputStream().getSourceName();
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
        }

//        System.err.println(sourceName+"line "+line+":"+charPositionInLine+" "+msg);
        errorMsg = errorMsg + "\n" + sourceName+"line "+line+":"+charPositionInLine+" "+msg;
    }


    public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
        System.out.println("reportAmbiguity not handled.");
    }

    @Override
    public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
        System.out.println("reportAttemptingFullContext not handled.");
    }

    @Override
    public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
        System.out.println("reportContextSensitivity not handled.");
    }

    //End ErrorListener implementation.

    public static void usage() {
        System.err.println("java Driver <filename>");
    }
}
