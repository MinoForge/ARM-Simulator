grammar LEGGram;

@header {
    package assembling.parsing.antlr;
}

/* Tokens */

//Instructions
ADD     : [Aa][Dd][Dd][ ];
SUB     : [Ss][Uu][Bb][ ];
MOV     : [Mm][Oo][Vv][ ];
AND     : [Aa][Nn][Dd][ ];
ORR      : [Oo][Rr][Rr][ ];
//Begin new instructions
MUL		: [Mm][Uu][Ll][ ];
DIV		: [Dd][Ii][Vv][ ];
BL		: [Bb][Ll][ ];
LDUR	: [Ll][Dd][Uu][Rr][ ];
STUR	: [Ss][Tt][Uu][Rr][ ];
B		: [Bb][ ];
CBZ		: [Cc][Bb][Zz][ ];
CBNZ	: [Cc][Bb][Nn][Zz][ ];
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
LBRACK        : '[';
RBRACK        : ']';
//End Symbols

/* Rules */

//Total File
filePath    : data? prog data?;
//TODO re-add labels to prog?? Was ambiguous.

//Consumes all instructions
prog    : ENTRY (label | inst | WS)+ END
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
        |  ORR reg SEPARATOR reg SEPARATOR reg
        |  MUL reg SEPARATOR reg SEPARATOR reg
        |  DIV reg SEPARATOR reg SEPARATOR reg
        |  B (label | imm)
        |  BL (label | imm)
        |  CBZ reg SEPARATOR imm
        |  CBNZ reg SEPARATOR imm
        |  LDUR reg SEPARATOR LBRACK reg SEPARATOR imm RBRACK
        |  STUR reg SEPARATOR LBRACK reg SEPARATOR imm RBRACK
        )
        ;


