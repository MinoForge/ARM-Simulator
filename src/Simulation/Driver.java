package Simulation;

import Parsing.ANTLR.LEGGramLexer;
import Parsing.ANTLR.LEGGramParser;
import org.antlr.runtime.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;


public class Driver {

    public static void main(String... args){
        //TODO scanner stuff for input file
        String input = "";
        parseFile(input);




    }

    public static void parseFile(String file) {
        try {
            LEGGramLexer lexer = new LEGGramLexer(CharStreams.fromFileName(file));
            LEGGramParser parser = new LEGGramParser(new CommonTokenStream(lexer));
            parser.getSerializedATN();
        } catch(IOException ioe) {

        }
    }


    public static void usage() {
        System.err.println("java Driver <filename>");
    }
}
