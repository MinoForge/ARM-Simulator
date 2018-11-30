package Simulation;

import Parsing.ANTLR.LEGGramLexer;
import Parsing.ANTLR.LEGGramParser;
/*
import org.antlr.runtime.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
*/
import java.io.File;
import java.io.IOException;


import java.io.File;
import java.util.Scanner;

/**
 * The main class to run to start up the controller and begin running a file through the simulator.
 *
 * @author Caleb Dinehart
 * @author Peter Gardner
 * @version November 8, 2018
 *
 */
public class Driver {

    public static void main(String... args){
        String input = "";

        //TODO scanner stuff for input file

        parseInput(input);

        if(parseFile()) {
            Controller controller = new Controller(input);
        } else{
            System.exit(1);
        }


    }

    public static void parseInput(String input) {
        //TODO ANTLR runs and parses the file
    }

    public static boolean parseFile(){ //(/*String fileName*/){
        /*
            try {
            LEGGramLexer lexer = new LEGGramLexer(CharStreams.fromFileName(file));
            LEGGramParser parser = new LEGGramParser(new CommonTokenStream(lexer));
            parser.getSerializedATN(); //TODO Not done.
        } catch(IOException ioe) {

        }
        */
        return true;
    }


    public static void usage() {
        System.err.println("java Driver <filename>");
    }
}
