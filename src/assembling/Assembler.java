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

    /** The file to be read from. */
    private File file;

    /** The contents of the file. */
    private String fileString;

    /** String containing all errors which the parser encounters. */
    private String errorMsg;


    private String[] instructionArray;

    /** InstructionName -> Binary opCode */
    private HashMap<String,String> opCodes;

    /** Label -> location */
    private HashMap<String, Integer> labelMap;


    /**
     * Constructor.
     * @param fileName The file being assembled.
     */
    public Assembler(String fileName){
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

        this.labelMap = new HashMap<>();

        makeLabels();

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

    public void makeLabels(){
        for(int i = 0; i < instructionArray.length; i++){
            if(instructionArray[i].matches(".:.")){
                String[] temp = instructionArray[i].split(":");
                temp[1].replace(":","");
                labelMap.put(temp[1], i);
            }
        }
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
                instructions.add(findBin(tempArray, i));
            }
        }
        return instructions;
    }


    /**
     * Turns a single instruction, split into pieces, into binary.
     * @param instruction the instruction being translated, split into tokens.
     * @return translated binary string
     */
    public String findBin(String[] instruction, int currentLine){
        String instBin = "";
        for (int j  = 0; j < instruction.length; j++){
            instruction[j] = instruction[j].replace(",", "");
        }
        String temp = instruction[0].toLowerCase();

        // This check is to determine how many arguments are in the instruction
        // if true then the instruction could be either an i or r type
        if(instruction.length < 4) {
            instBin = instBin + opCodes.get(temp);
        }else{
            // check to see if the last argument is an immediate
            if(instruction[3].matches("[#][0-9]+")) {
                // in the hash map the i types are mapped to their r type
                // equivalents but with i added to the end, so the
                // immediate add is mapped to "addi"
                instBin = instBin + opCodes.get(temp + "i");
            }else{
                //just the r type instruction
                instBin = instBin + opCodes.get(temp);
            }
        }
        char format = '\0';
        String check  = "";
        // Checks to see what format the instruction is
        if(instBin.length() > 8){
            check = instBin.substring(3,7);
            if(check.matches("100.") || check.matches(".1.0")){
                format = 'i';
            }
            else if(check.matches(".101")){
                format = 'r';
            }
            // need to finish for r types
            else if(check.matches("110.")){
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

                reg1 = correctBits(reg1,5, 5);
                reg2 = correctBits(reg2,5, 5);
                reg3 = correctBits(reg3,5, 5);

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
                reg1 = correctBits(reg1,5,5);
                reg2 = correctBits(reg2,5,5);

                //pulling the immediate value from the instruction
                String immediate =  instruction[3].replace("#","");
                String immediateBin = Integer.toBinaryString(Integer.parseInt
                        (immediate));
                immediateBin = correctBits(immediateBin, 12,12);

                instBin = instBin + immediateBin + reg2 + reg1;


                break;

            case('b'):
                //TODO: label logic needed
                // Not finished need label logic for now this is just immediates
                String tmp ="";
                int memLocation;
                if(instruction[1].matches("[A-Za-z]+")){
                    int location = labelMap.get(instruction[1]);
                    memLocation = (location - currentLine);

                } else {
                    tmp = instruction[1].replace("#", "");
                    memLocation = Integer.parseInt(tmp);
                }

                String memBin = Integer.toBinaryString(memLocation);
                memBin = correctBits(memBin, 26,26);
                //finished the instruction binary
                instBin = instBin + memBin;
                break;

            case('c'):

                //TODO: Need label logic
                // current logic is for an immediate given to the
                int num;
                if(instruction[2].matches("[A-Za_z]+")){
                    num = labelMap.get(instruction[2]) - currentLine;
                }else {
                    num = Integer.parseInt(instruction[1].replace("#", ""));
                }
                immediate = Integer.toBinaryString(num);
                immediate = correctBits(immediate, 19,19);

                instBin = instBin + immediate;

                reg1 = Integer.toBinaryString(Integer.parseInt(instruction[2]
                        .replaceAll("[a-zA-Z]", "")));
                reg1 = correctBits(reg1, 5,5);

                instBin = instBin + reg1;
                break;

            case('d'):
                // TODO:
                // much trickier to pull apart as the syntax for these
                // instructions are much different from the others
                for(int i = 2; i < instruction.length; i++){
                    instruction[i] = instruction[i].replace("[","");
                }
                instruction[3] = instruction[3].replace("#","");

                reg1 = Integer.toBinaryString(Integer.parseInt(instruction[1]
                        .replaceAll("[a-zA-Z]", "")));
                reg2 = Integer.toBinaryString(Integer.parseInt(instruction[2]
                        .replaceAll("[a-zA-Z]", "")));
                reg1 = correctBits(reg1,5,5);
                reg2 = correctBits(reg2,5,5);

                num = Integer.parseInt(instruction[3]);
                immediate = Integer.toBinaryString(num);
                immediate = correctBits(immediate, 9, 9);

                instBin = instBin + immediate + "00" + reg2 + reg1;


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
                justInstructions.add(s);
            }
        }
        return justInstructions;
    }
}
