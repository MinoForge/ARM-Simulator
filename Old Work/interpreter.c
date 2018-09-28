/**
 * interpreter.c - A language interpreter for a (very) simple language. This
 *                 language is named Bob.
 *
 * @author Peter Gardner
 * @version April 4, 2017
 */

#include <stdio.h>
#include <string.h>
#include "tokenizer.h"
#include "parser.h"


/** Usage error message */
char usage[USAGE_CHARS] = {"./interpreter <input_file> <output_file>"};

/**
 * Main function of our interpreter. Does simple math based on the 'Bob'
 * language.
 * @param argc Number of arguments provided
 * @param argv input_file output_file
 * @return 0 if no file or user errors were encountered. !0 otherwise.
 */
int main(int argc,char *argv[]){
    FILE *in_file;
    FILE *out_file;
    char input[LINE];
    char token[TSIZE];
    char current_line[LINE];
    extern char *line;

    if(argc != 3) {
        printf("\nWrong number of arguments. Try again.\n%s\n", usage);
        return 1;
    }
    in_file = fopen(argv[1], "r");
    if(in_file == NULL) {
        printf("\nBad input filename. Does %s exist?\n%s\n", argv[1], usage);
        return 2;
    }
    out_file = fopen(argv[2], "w");
    if(out_file == NULL) {
        printf("\nBad output filename. Cannot open %s for writing\n%s\n", \
                argv[2], usage);
        return 3;
    }

    while(fgets(input, LINE, in_file) != NULL){
        line = input;
        strcpy(current_line, input);
        if(*line != '\n') {
            get_token(token);
            int result = 0;
            if(*token != '\0') {
                result = bexpr(token);
                if (result == ERROR) {
                    if (is_sym(*token) != TRUE) {
                        fprintf(out_file, "%s===> '%s'\nLexical Error: not a "
                                "lexeme\n\n", current_line, token);
                    } else {
                        fprintf(out_file, "%s===> '%s' expected\n"
                                "Syntax Error\n\n", current_line, token);
                    }
                } else {
                    fprintf(out_file, "%sSyntax OK\nValue is %d\n\n",
                            current_line,
                            result);
                }
            }
        }
    }
    return 0;
}