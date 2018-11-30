package Simulation;

import Parsing.ANTLR.LEGGramLexer;
import Parsing.ANTLR.LEGGramParser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
//import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.BitSet;
import java.util.Scanner;

/**
 * The main class to run to start up the controller and begin running a file through the simulator.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Driver implements ANTLRErrorListener{

    private File file;
    private String fileString;
    private String errorMsg;

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


    public static void main(String... args){
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

    public boolean parseInput() {
        //TODO ANTLR runs and parses the file
        boolean passesParse = true;
//        System.out.println(fileString);
        LEGGramLexer lexer = new LEGGramLexer(CharStreams.fromString(this.fileString));
        LEGGramParser parser = new LEGGramParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(this);

        parser.prog(); //TODO Not done.

        System.err.println(this.errorMsg);


        return passesParse;
    }


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

    public static void usage() {
        System.err.println("java Driver <filename>");
    }
}
