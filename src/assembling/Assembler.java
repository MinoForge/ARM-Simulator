package assembling;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import assembling.parsing.antlr.LEGGramLexer;
import assembling.parsing.antlr.LEGGramParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    private ArrayList<String> dataLabels;
    private ArrayList<String> dataBins;
    private int[] lineAddresses;



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
        String[] tempInsts = this.fileString.split("\n");
        ArrayList<String> realInsts = new ArrayList<>();
        ArrayList<String> data      = new ArrayList<>();
        realInstAdder(tempInsts, realInsts, data); //alters realInsts and data
        System.out.println("realInsts from Assembler:\n" + realInsts.toString());
        System.out.println("data lines from Assembler:\n" + data.toString());
        lineAddresses = dataSection(data); //data array modified here
        for(int i = 0; i < lineAddresses.length; i++) {
            //Turn relative addresses (into data) into absolute memory addresses
            lineAddresses[i] = lineAddresses[i] + realInsts.size() * 4;
            System.out.println(lineAddresses[i]);
        }


        this.labelMap = new HashMap<>();
        makeLabels();
        System.out.println("the hashmap: " + labelMap.toString());




        //Setting up for making binary instructions

        this.opCodes = new HashMap<>();
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
        this.opCodes.put("lsl",  "10011010110");
        this.opCodes.put("lsr",  "10011010110");
        this.opCodes.put("svc",  "11010100000");

    }

    public int[] dataSection(ArrayList<String> temp){
        this.dataLabels = new ArrayList<>();
//        this.dataBins = new ArrayList<>();
        ArrayList<String> data = new ArrayList<>(temp);
//        for(int i = 2; temp.get(i).equals(".text"); i++){
//            data.add(temp.get(i));
//        }
        ArrayList<String> wordAlignedData = new ArrayList<>();
        int wordMod = 0;
        int totalDataBytes = 0;
        String binary = "";
        int[] addressIntoData = new int[data.size()];
        for(int i = 0; i < data.size(); i++){
            System.out.println("Start of line: " + totalDataBytes);
            String line = data.get(i);
            dataLabels.add(line.substring(0, line.indexOf(" ")));
            String contents = line.substring(line.indexOf(" ") +1);
            String type = contents.substring(0, contents.indexOf(" "));
            contents = contents.substring(contents.indexOf(" ") + 1);
            int lineIndex = 0;

            System.out.println("Type: " + type);
            System.out.println("Contents: " + contents);
            if(type.matches("\\.byte")) {
                System.out.println("Matched Byte");
                String[] temp1 = contents.split(" ");
                addressIntoData[i] = totalDataBytes;
                while(lineIndex < temp1.length) {

                    if(temp1[lineIndex].matches
                            ("[A-Fa-f0-9][A-Fa-f0-9]?")){
                        binary = binary + correctBits(Integer.toBinaryString(Integer
                                .parseInt(temp1[lineIndex], 16)),8,8);
                        totalDataBytes++;
                        wordMod++;
                        lineIndex++;
                        if(wordMod == 4) {
                            wordAlignedData.add(binary);
                            wordMod = 0;
                            binary = "";
                        }
                    }

                }
            } else if(type.matches("\\.asci.")) {
                contents = contents.substring(1, contents.length());
                char[] temp2 = contents.toCharArray();
                addressIntoData[i] = totalDataBytes;
                while(lineIndex < temp2.length) {
                    binary = binary + correctBits(Integer.toBinaryString(
                            (int)(temp2[lineIndex])),8,8);
                    totalDataBytes++;
                    wordMod++;
                    lineIndex++;
                    if(wordMod == 4) {
                        wordAlignedData.add(binary);
                        wordMod = 0;
                        binary = "";
                    }
                }
                if(type.equals(".asciz")) {
                    binary = binary + "00000000";
                }
            }

        }
        while(binary.length() > 0 && binary.length() < 4) {
            binary = binary + "00000000";
        }
        if(binary.length() != 0) {
            wordAlignedData.add(binary);
        }
        this.dataBins = wordAlignedData;
        return addressIntoData;
    }

    public void makeLabels(){
        for(int i = 0; i < instructionArray.length; i++){
            if(instructionArray[i].contains(":")){
                System.out.println("I got here in the make labels if " +
                        "statement");
                String[] temp = instructionArray[i].split(":");
                System.out.println(temp[0] + " and " + temp[1]);
                temp[0].replace(":","");
                labelMap.put(temp[0], i);

                instructionArray[i] = instructionArray[i].replace(temp[0]+
                        ":" ,"").trim();
                System.out.println(instructionArray[i]);
            }
        }
        for(int i = 0; i < dataLabels.size(); i++) {
            labelMap.put(dataLabels.get(i), lineAddresses[i]);
        }
        System.out.println("Labelmap after data added: " + labelMap);
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

    public void realInstAdder(String[] tempInsts, ArrayList<String>
            realInsts, ArrayList<String> data){
        boolean isData = false;
        for(String s: tempInsts) {
            if (!(s.equals("ENTRY") || s.equals("END") ||
                    s.equals("") || s.equals(".text") || s.equals(".data")) &&
                    !isData) {
                realInsts.add(s);
                System.out.println(s);
            }else if(s.equals(".data")){
                isData = true;
            }else if(s.equals(".text")){
                isData = false;
            }
            if(isData && !(s.equals(".data") || s.equals(".text") ||
                    s.equals("END") || s.equals(""))){
                data.add(s);
            }

        }
        this.instructionArray = realInsts.toArray(new String[0]);

        this.errorMsg = "";
        for(int i = 0; i < instructionArray.length; i++){
            instructionArray[i] = instructionArray[i].trim();
        }
        for(String s : instructionArray){
            System.out.println(s);
        }


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
                    instructionArray[i].matches("/+.*") || instructionArray[i].equals("")) {
                System.out.println("Matched: " + instructionArray[i] + "as special. Not adding to" +
                        " instruction list.");
                //Do nothing touch
            } else {
                curInstruction = instructionArray[i].trim();
                String[] tempArray = curInstruction.split(" ");
                instructions.add(findBin(tempArray, i));
            }
        }
        for(int i = 0; i < dataBins.size(); i++) {
            instructions.add(dataBins.get(i));
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
        String temp = (instruction[0].toLowerCase()).trim();

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
            if(check.matches("100.")) {
                //|| check.matches(".1.0"))????
                format = 'i';
            }
            else if(check.matches(".101")){
                format = 'r';
            }
            // need to finish for r types
            else if(check.matches("110.")){
                format = 'd';
            }
            // system calls
            else if(check.matches("1010")){
                format = 's';
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
                if(temp.equals("LSL")){
                    instBin = instBin + "001000";
                }else if(temp.equals("LSR")){
                    instBin = instBin + "001001";
                }else {
                    instBin = instBin + "000000";
                }

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
                String tmp ="";
                int memLocation;
                if(instruction[1].matches("[A-Za-z]+")){
                    System.out.println(instruction[1]);
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
                int num;
                if(instruction[2].matches("[A-Za-z]+")){
                    num = labelMap.get(instruction[2]) - currentLine;
                }else {
                    num = Integer.parseInt(instruction[2].replace("#", ""));
                }
                immediate = Integer.toBinaryString(num);
                immediate = correctBits(immediate, 19,19);

                instBin = instBin + immediate;

                reg1 = Integer.toBinaryString(Integer.parseInt(instruction[1]
                        .replaceAll("[a-zA-Z]", "")));
                reg1 = correctBits(reg1, 5,5);

                instBin = instBin + reg1;
                break;

            case('d'):
                // TODO:
                // much trickier to pull apart as the syntax for these
                // instructions are much different from the others
                instruction[2] = instruction[2].replace("[","");
                //instruction[3] = instruction[3].replaceAll("#\\]","");
                instruction[3] = instruction[3].replace("#","");
                instruction[3] = instruction[3].replace("]","");

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
            case('s'):
                instruction[1] = instruction[1].replace("#", "");
                num = Integer.parseInt(instruction[1]);
                immediate = Integer.toBinaryString(num);
                immediate = correctBits(immediate, 16, 16);

                instBin = instBin + immediate + "00001"; //Must be 00001 for
                                                         //syscalls


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
            if (!(s.equals("ENTRY") || s.equals("END") || s.matches("/+.*") ||
                    s.equals(""))) {
                justInstructions.add(s);
            }
        }
        return justInstructions;
    }
}
