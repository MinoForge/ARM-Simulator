# ARM and Pipeline
2018-2019 WCU Capstone ARM Simulator

Authors: Caleb Dinehart & Peter Gardner
May 10, 2019


<b>TO COMPILE:</b>

    compile.sh
    //This would probably fail on Windows.
    //Please compile on Agora before moving off for GUI testing.


<b>TO RUN: make sure you are in /src/ before running</b>


TO RUN:
    java ui.GUI
        Must change the actual file named "TestFile.txt" in the source folder
        we have provided a few test files that test simple Branching, Loading
        and Storing, System Calls, and A combination of all of them.

KNOWN BUGS:
    When Branching:
        If there are multiple branch instructions in the pipeline at the same
        time, they will not jump properly.

    Stack pointer points to the index of the our virtual memory not true memory
        it should be pointing to 0x4000000

    For input to be read from the keyboard, you must click at the end of the
    prompt before entering the number. Only numbers are supported at this time.

    Comments are not supported.

    There is an error message that pops up about byte alignment should be
    ignored.

NOTES ON SYSTEM ERROR:
    Right now we have a lot of testing prints being output to system error,
    we print out what is happening in each pipeline stage and there
    registers, what virtual memory holds right now, and what labels are set
    in the current file.

NOTES:
    List of Instructions:
        ADD   :   <register>, <register>, <register> or #number
            Addition
        SUB   :   <register>, <register>, <register> or #number
            Subtraction
        AND   :   <register>, <register>, <register> or #number
            logical and
        ORR   :   <register>, <register>, <register> or #number
            logical or
        LSL   :   <register>, <register>, <register>
            Left shifting
        LSR   :   <register>, <register>, <register>
            Right shifting
        LDUR  :   <register>, [<register>, #number]
            Load a double word
        STUR  :   <register>, [<register>, #number]
            Store a double word
        B     :   label
            Branch to the label
        BL    :   label
            Branch the label, the return address is placed into r30
        CBZ   :   <register>, label
            If the contents of the register is zero, branch to the label
        CBNZ  :   <register>, label
            IF the contents are not zero, branch to the label
        LDR   :   <register>, label
            Load the address the label points to into the register
        SVC   :   #0
            reg 8 must have 63 or 64 to run the system calls
            63 - reading input, puts input into register 0
            64 - printing whats at the address stored in register 0
        MOV   :  <register>, <register>
            Move contents of one register to another
        MUL   :  <register>, <register>, <register>
            multiplication
        UDIV  :  <register>, <register>, <register>
            division
        BR    :  <register>
            branch to the contents in the register
        BLR   :  <register>
            branch link register
