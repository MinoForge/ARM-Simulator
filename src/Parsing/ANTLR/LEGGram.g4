grammar LEGGram;

/* Tokens */
ADD    : [Aa][Dd][Dd][ ];
SUB    : [Ss][Uu][Bb][ ];
MOV    : [Mm][Oo][Vv][ ];
AND    : [Aa][Nn][Dd][ ];
OR     : [Oo][Rr][ ];

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


/* Rules */

file    : prog  data;
//TODO re-add labels to prog?? Was ambiguous.
prog    : ENTRY (inst | WS)+ END
        ;

data    : (DATA WS DIRECTIVE LCWORD (STRING | INT+));

reg     : (REGISTER INT);

imm     : (HASH NEG ? INT);

memcall : (EQUALS WORD);

label   : (WORD COLON);

inst    : (ADD reg SEPARATOR reg SEPARATOR (reg | imm)
        |  SUB reg SEPARATOR reg SEPARATOR (reg | imm)
        |  MOV reg SEPARATOR reg
        |  AND reg SEPARATOR reg SEPARATOR reg
        |  OR reg SEPARATOR reg SEPARATOR reg )
        ;


