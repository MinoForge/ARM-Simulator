/**
 * tokenizer.c - A simple token recognizer for the 'Bob' language
 *
 * NOTE: The terms 'token' and 'lexeme' are used interchangeably in this
 *       program.
 *
 * @author Peter Gardner
 * @version April 4, 2017
 */

#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "tokenizer.h"


/** Pointer to input line */
char *line;

/**
 * Copies the next token in the file to the token_ptr.
 * @param token_ptr The previous token. Will be overwritten.
 */
void get_token(char *token_ptr) {
    char current_token[TSIZE] = {0};
    int i = 0;
    while(isspace(*line)) {
        line++;
    }
    if(isdigit(*line)) {
        while(isdigit(*line)){
            current_token[i] = *line;
            line++;
            i++;
        }
    }else if(is_sym(*line) == TRUE) {
        if(*line == '<' || *line == '>' || *line == '=' || *line == '!'){
            char temp = *line;
            line++;
            current_token[0] = temp;
            if(*line == '='){
                current_token[1] = *line;
                line++;
            }
        }else{
            current_token[0] = *line;
            line++;
        }
    }else{
        while(!isspace(*line) && !(is_sym(*line)  || isdigit(*line))){
            current_token[i] = *line;
            i++;
            line++;
        }
    }
    strncpy(token_ptr,current_token,TSIZE);
}

/**
 * Checks if a character is a valid 'symbol'(not including numbers) in our
 * language.
 * @param ch the character being passed in and checked
 * @return TRUE if ch is in the language, FALSE otherwise.
 */
int is_sym(char ch){
    int result;
    if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '('
       || ch == ')' || ch == '^' || ch == '<' || ch == '>' || ch == '!'
       || ch == '=' || ch == ';') {
        result = TRUE;
    } else {
        result = FALSE;
    }

    return result;
}