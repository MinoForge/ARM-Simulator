package assembling;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import assembling.parsing.antlr.LEGGramLexer;
import assembling.parsing.antlr.LEGGramParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Scanner;

import static simulation.pipeline.PipelineSegment.correctBits;


public class Assembler implements ANTLRErrorListener {

    //TODO Take file, return binary.
    //TODO Cry a lot.
    //TODO A lot.

    /** The file to be read from. */
    private File file;

    /** The contents of the file. */
    private String fileString;

    /** String containing all errors which the parser encounters. */
    private String errorMsg;


    private String[] instructionArray;

    /** InstructionName -> Binary opCode */
    private HashMap<String,String> opCodes;


    public Assembler(String fileName){
        // File input;
        Scanner fileIn = null;
        try {
            this.file = new File(fileName);
            fileIn = new Scanner(file);
        } catch(FileNotFoundException fnfe) {
            System.out.println("File not Found.");
            System.exit(2);
        }
        StringBuilder tempStr = new StringBuilder();
        while(fileIn.hasNextLine()) {
        tempStr.append(fileIn.nextLine());
        tempStr.append("\n");
        }
        this.fileString = tempStr.toString();
        this.instructionArray = this.fileString.split("\n");
        this.errorMsg = "";


        //Setting up for making binary instructions

        this.opCodes = new HashMap<>();
        //TODO Might make Enumerations out of these
        this.opCodes.put("add", "10001011000");
        this.opCodes.put("and", "10001010000");
        this.opCodes.put("addi","1001000100");
    }


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

    public static void usage()
    {
        System.err.println("java Driver <filename>");
    }

    public ArrayList<String> makeBinaryList(){
        ArrayList<String> instructions = new ArrayList<>();
        /*
         * Taking the list of instructions from the file and putting them
         * into an array
         */

        String curInstruction;
        for(int i = 0; i < instructionArray.length; i++) {
            if (instructionArray[i].equals("ENTRY") || instructionArray[i].equals("END")) {
                //Do nothing touch
            } else {
                curInstruction = instructionArray[i];
                String[] tempArray = curInstruction.split(" ");
                instructions.add(findBin(tempArray));
            }
        }


        return instructions;
    }

    public String findBin(String[] instruction){
        String instBin = "";
        for (int j  = 0; j < instruction.length; j++){
            instruction[j] = instruction[j].replace(",", "");
        }
        String temp = instruction[0].toLowerCase();
        instBin = instBin + opCodes.get(temp);

        char format = '\0';
        String check = instBin.substring(3,7);
        if(check.matches("100.") || check.matches(".1.0")){
            format = 'i';
        }
        else if(check.matches("101.")){
            format = 'b';
        }
        else if(check.matches(".101")){
            format = 'r';
        }

        String reg1,reg2,reg3;
        switch(format){
            case('r'):
                //grabbing inst command
                // Adding the correct opcode to the instBin string

                //TODO remove the magic num
                int[] registerNumbers = new int[3];

                for(int i = 0; i < instruction.length - 1; i++){
                    instruction[i+1] = instruction[i+1].replaceAll("[a-zA-Z]", "");

                    registerNumbers[i] = Integer.parseInt(instruction[i + 1]);
                }

                reg1 = Integer.toBinaryString(registerNumbers[2]);
                reg2 = Integer.toBinaryString(registerNumbers[1]);
                reg3 = Integer.toBinaryString(registerNumbers[0]);

                reg1 = correctBits(reg1,5);
                reg2 = correctBits(reg2,5);
                reg3 = correctBits(reg3,5);

                // adding rm register binary
                instBin = instBin + reg1;

                // will need to check if a shamt is present but for now
                // assuming there isn't
                instBin = instBin + "000000";
                //shamt = "000000";


                // adding rn register binary

                instBin = instBin + reg2;

                // adding rd register binary

                instBin = instBin + reg3;


                break;
            //Currently unfinished for i types
            case('i'):
                //grabbing  inst command
                //temp = instruction[0].toLowerCase();
                // adding opcode to instruction binary
                //instBin = instBin + opCodes.get(temp);

                //pulling the registers from the instruction
                reg1 = Integer.toBinaryString(Integer.parseInt(instruction[1]
                        .replaceAll("[a-zA-Z]", "")));
                reg2 = Integer.toBinaryString(Integer.parseInt(instruction[2]
                        .replaceAll("[a-zA-Z]", "")));
                reg1 = correctBits(reg1,5);
                reg2 = correctBits(reg2,5);

                //pulling the immediate value from the instruction
                String immediate =  instruction[3].replace("#","");
                String immediateBin = Integer.toBinaryString(Integer.parseInt
                        (immediate));
                immediateBin = correctBits(immediateBin, 12);

                instBin = instBin + immediateBin + reg2 + reg1;


                break;
            // Not started on b types
            case('b'):
                //grabbing instruction command
                //temp = instruction[0].toLowerCase();
                //adding opcode
                //instBin = instBin + opCodes.get(temp);

                String memLocation;

                break;

        }

        return instBin;
    }

    public String[] getInstructionArray() {
        ArrayList<String> justInstructions = new ArrayList<>();
        for(String s : instructionArray){
            if (!(s.equals("ENTRY") || s.equals("END"))) {
                justInstructions.add(s);
            }
        }
        String[] temp = new String[justInstructions.size()];
        for(int i = 0; i < temp.length; i++){
            temp[i] = justInstructions.get(i);
        }
        return temp;
    }
}
