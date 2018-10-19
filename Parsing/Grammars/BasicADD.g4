grammar p;

//language=Go;
//

/* Tokens */
ADD    : 'ADD';
INT    : [0-9]+;
SEPARATOR     : ',';
NEWLINE    :[ \n\t\r];
NEG : '-';
REGISTER : 'r';
HASH : '#';

/* Rules */

prog   : (inst | NEWLINE)+
       ;

reg    : (REGISTER INT);

imm    : (HASH NEG ? INT);

inst   : ((ADD NEWLINE reg SEPARATOR ?NEWLINE reg SEPARATOR ?NEWLINE reg)
       | (ADD reg SEPARATOR reg SEPARATOR HASH INT))
       ;




