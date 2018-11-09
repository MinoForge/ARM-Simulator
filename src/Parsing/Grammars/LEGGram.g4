grammar LEGGram;

/* Tokens */
ADD    : 'ADD ';
SUB    : 'SUB ';
MOV    : 'MOV ';
AND    : 'AND ';
OR     : 'OR ';

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

prog    : ENTRY (label | inst | WS)+ END
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


