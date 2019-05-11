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
UDIV	: [Uu][Dd][Ii][Vv][ ];
LDUR	: [Ll][Dd][Uu][Rr][ ];
STUR	: [Ss][Tt][Uu][Rr][ ];
B		: [Bb][ ];
BL		: [Bb][Ll][ ];
CBZ		: [Cc][Bb][Zz][ ];
CBNZ	: [Cc][Bb][Nn][Zz][ ];
MADD	: [Mm][Aa][Dd][Dd][ ];
LSL		: [Ll][Ss][Ll][ ];
LSR		: [Ll][Ss][Rr][ ];
SVC	    : [Ss][Vv][Cc][ ];
LDR     : [Ll][Dd][Rr][ ];
BR      : [Bb][Rr][ ];
BLR     : [Bb][Ll][Rr][ ];
//Unimplemented
UBFM	: [Uu][Bb][Ff][Mm][ ];

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
WORD          : [A-Za-z\-_]+;
LCWORD        : [a-z]+;
DIRECTIVE     : '.';
DATA          : '.data';
TEXT          : '.text';
STRING        : '".*"';
LBRACK        : '[';
RBRACK        : ']';
//COMSLASH      : '/';
//End Symbols

COMSLASH       : [\\/][\\/]+;

/* Rules */

//Total File
filePath    : data? prog data?;

//Consumes all instructions
prog    : ENTRY WS+ TEXT (label | inst | WS)+ END
        ;

//Not implemented yet
data    : (DATA WS DIRECTIVE LCWORD (STRING | INT+) );

//Consumes registers and their number
reg     : (REGISTER INT);

//Consumes immediate values
imm     : (HASH NEG ? INT);

//Not implemented yet
memcall : (EQUALS WORD);

//Consumes a word ending colon.
label   : (WORD COLON);

comment : (COMSLASH .*?);

//Consumes all instructions. More added as more implemented.
inst    : (ADD reg SEPARATOR reg SEPARATOR (reg | imm)
        |  SUB reg SEPARATOR reg SEPARATOR (reg | imm)
        |  MOV reg SEPARATOR reg
        |  AND reg SEPARATOR reg SEPARATOR reg
        |  ORR reg SEPARATOR reg SEPARATOR reg
        |  MUL reg SEPARATOR reg SEPARATOR reg
        |  MADD reg SEPARATOR reg SEPARATOR reg SEPARATOR reg
        |  UDIV reg SEPARATOR reg SEPARATOR reg
        |  BL WORD | imm
        |  B WORD | imm
        |  BR reg //TODO add to assembler
        |  BLR reg //TODO add to assembler
        |  CBZ reg SEPARATOR (WORD | imm)
        |  CBNZ reg SEPARATOR (WORD | imm)
        |  LDUR reg SEPARATOR LBRACK reg SEPARATOR imm RBRACK
        |  STUR reg SEPARATOR LBRACK reg SEPARATOR imm RBRACK
        |  LSL reg SEPARATOR reg SEPARATOR reg
        |  LSR reg SEPARATOR reg SEPARATOR reg
        |  LDR reg SEPARATOR WORD
        |  UBFM reg SEPARATOR reg SEPARATOR imm SEPARATOR imm
        |  SVC imm
        )
        ;


