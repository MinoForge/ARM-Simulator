grammar LEGGram;

@header {
    package assembling.parsing.antlr;
}

/* Tokens */

//Instructions
ADD    : [Aa][Dd][Dd][ ];
SUB    : [Ss][Uu][Bb][ ];
MOV    : [Mm][Oo][Vv][ ];
AND    : [Aa][Nn][Dd][ ];
OR     : [Oo][Rr][ ];
//End Instructions

//Important Symbols
INT           : [0-9]+;
SEPARATOR     : ', ';
WS            :[ \n\t\r];
NEG           : '-';
REGISTER      : 'r';
COLON         : ':';
HASH          : '#';
EQUALS        : '=';
ENTRY         : 'ENTRY';
END           : 'END';
WORD          : [A-Za-z]+;
LCWORD        : [a-z]+;
DIRECTIVE     : '.';
DATA          : '.data';
STRING        : '".*"';
//End Symbols

/* Rules */

//Total File
filePath    : prog  data;
//TODO re-add labels to prog?? Was ambiguous.

//Consumes all instructions
prog    : ENTRY (inst | WS)+ END
        ;

//Not implemented yet
data    : (DATA WS DIRECTIVE LCWORD (STRING | INT+));

//Consumes registers and their number
reg     : (REGISTER INT);

//Consumes immediate values
imm     : (HASH NEG ? INT);

//Not implemented yet
memcall : (EQUALS WORD);

//De-implemented. See TODO above
label   : (WORD COLON);

//Consumes all instructions. More added as more implemented.
inst    : (ADD reg SEPARATOR reg SEPARATOR (reg | imm)
        |  SUB reg SEPARATOR reg SEPARATOR (reg | imm)
        |  MOV reg SEPARATOR reg
        |  AND reg SEPARATOR reg SEPARATOR reg
        |  OR reg SEPARATOR reg SEPARATOR reg )
        ;


