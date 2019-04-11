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

//TODO DOES NOT SUPPORT IMMEDIATES IN ADD
public class Assembler implements ANTLRErrorListener {

    /** The file to be read from. */
    private File file;

    /** The contents of the file. */
    private String fileString;

    /** String containing all errors which the parser encounters. */
    private String errorMsg;


    private String[] instructionArray;

    /** InstructionName -> Binary opCode */
    private HashMap<String,String> opCodes;

    /**
     * Constructor.
     * @param fileName The file being assembled.
     */
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
        this.opCodes.put("add",  "10001011000");
        this.opCodes.put("and",  "10001010000");
        this.opCodes.put("andi", "1001001000");
        this.opCodes.put("addi", "1001000100");
        this.opCodes.put("orr",  "10101010000");
        this.opCodes.put("orri", "1011001000");
        this.opCodes.put("sub",  "11001011000");
        this.opCodes.put("subi", "1101000100");
        this.opCodes.put("ldur", "11111000010");
        this.opCodes.put("stur", "11111000000");
        this.opCodes.put("cbz",  "10110100");
        this.opCodes.put("cbnz", "10110101");
        this.opCodes.put("b",    "000101");
        this.opCodes.put("bl",   "100101");
        this.opCodes.put("mul",  "10011011000");
    }


    /**
     * Parses the given file.
     * @return true if no syntax errors, false otherwise.
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


    //TODO this method needs help. If anything is in the file other than Entry, End, and instructions
    //TODO this will not function as intended. May require significant changes elsewhere as well.
    /**
     * Assembles the file into an ArrayList containing the binary strings.
     * @return an ArrayList containing the translated instructions.
     */
    public ArrayList<String> makeBinaryList(){
        ArrayList<String> instructions = new ArrayList<>();
        /*
         * Taking the list of instructions from the file and putting them
         * into an array
         */


        String curInstruction;
        for(int i = 0; i < instructionArray.length; i++) {
//            System.out.println(instructionArray[i]);
            if (instructionArray[i].equals("ENTRY") || instructionArray[i].equals("END") ||
                    instructionArray[i].matches("/+.*")) {
                System.out.println("Matched: " + instructionArray[i] + "as special. Not adding to" +
                        " instruction list.");
                //Do nothing touch
            } else {
                curInstruction = instructionArray[i];
                String[] tempArray = curInstruction.split(" ");
                instructions.add(findBin(tempArray));
            }
        }
        return instructions;
    }


    /**
     * Turns a single instruction, split into pieces, into binary.
     * @param instruction the instruction being translated, split into tokens.
     * @return translated binary string
     */
    public String findBin(String[] instruction){
        String instBin = "";
        for (int j  = 0; j < instruction.length; j++){
            instruction[j] = instruction[j].replace(",", "");
        }
        String temp = instruction[0].toLowerCase();

        if(instruction.length < 4) {
            instBin = instBin + opCodes.get(temp);
        }else{
            if(instruction[3].matches("[#][0-9]+")) {
                instBin = instBin + opCodes.get(temp + "i");
            }else{
                instBin = instBin + opCodes.get(temp);
            }
        }
        char format = '\0';
        String check  = "";
        if(instBin.length() > 8){
            check = instBin.substring(3,7);
            if(check.matches("100.") || check.matches(".1.0")){
                format = 'i';
            }
            else if(check.matches(".101")){
                format = 'r';
            }
            else if(check.matches("TODO")){
                format = 'd';
            }
        }else{
            if(instBin.length() < 8){
                format = 'b';
            }else{
                format = 'c';
            }
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
                // instBin = instBin + opCodes.get(temp);

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
                String tmp = instruction[1].replace("#", "");
                int memLocation = Integer.parseInt(tmp);
                String memBin = Integer.toBinaryString(memLocation);
                memBin = correctBits(memBin, 26);
                instBin = instBin + memBin;

                break;
            case('c'):
                //TODO: Need label logic
                int num = Integer.parseInt(instruction[1].replace
                        ("[a-zA-Z]", ""));
                immediate = Integer.toBinaryString(num);
                immediate = correctBits(immediate, 19);

                instBin = instBin + immediate;

                reg1 = Integer.toBinaryString(Integer.parseInt(instruction[2]
                        .replaceAll("[a-zA-Z]", "")));
                reg1 = correctBits(reg1, 5);

                instBin = instBin + reg1;
                break;

            case('d'):



                break;
        }

        return instBin;
    }

    /**
     * Getter for all instructions, in assembly language, from the file.
     * @return an array of all instructions, represented as assembly code.
     */
    public ArrayList<String> getInstructionList() {
        ArrayList<String> justInstructions = new ArrayList<>();
        for(String s : instructionArray){
            if (!(s.equals("ENTRY") || s.equals("END") || s.matches("/+.*"))) {
                System.out.println();
                justInstructions.add(s);
            }
        }
        return justInstructions;
//        String[] temp = new String[justInstructions.size()];
//        for(int i = 0; i < temp.length; i++){
//            temp[i] = justInstructions.get(i);
//        }
//        return temp;
    }
}
