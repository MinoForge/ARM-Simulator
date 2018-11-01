grammar p;

//language=Go;
//

/* Tokens */
ADD    : 'ADD ';
SUB    : 'SUB ';
MOV    : 'MOV ';

AND    : 'AND ';
OR     : 'OR ';


INT    : [0-9]+;
SEPARATOR     : ', ';
WS    :[ \n\t\r];
NEG : '-';
REGISTER : 'r';
HASH : '#';
ENTRY: 'ENTRY';
END: 'END';

/* Rules */

prog   : ENTRY (inst | WS)+ END
       ;

reg    : (REGISTER INT);

imm    : (HASH NEG ? INT);

inst   : (ADD reg SEPARATOR reg SEPARATOR (reg | imm)
       |  SUB reg SEPARATOR reg SEPARATOR (reg | imm)
       |  MOV reg SEPARATOR reg 
       |  AND reg SEPARATOR reg SEPARATOR reg
       |  OR reg SEPARATOR reg SEPARATOR reg )
       ;


