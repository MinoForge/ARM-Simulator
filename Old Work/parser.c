/**
 * parser.c - recursive descent parser for Bob, our language.
 *            Most of the functions in this file model a Non-terminal in the
 *            grammar listed below.
 * @author Peter Gardner
 * @version April 4, 2017
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <math.h>
#include "tokenizer.h"
#include "parser.h"



/*
 * <bexpr> ::= <expr> ;
 * <expr> ::=  <term> <ttail>
 * <ttail> ::=  <add_sub_tok> <term> <ttail> | e
 * <term> ::=  <stmt> <stail>
 * <stail> ::=  <mult_div_tok> <stmt> <stail> | e
 * <stmt> ::=  <factor> <ftail>
 * <ftail> ::=  <compare_tok> <factor> <ftail> | e
 * <factor> ::=  <expp> ^ <factor> | <expp>
 * <expp> ::=  ( <expr> ) | <num>
 * <add_sub_tok> ::=  + | -
 * <mul_div_tok> ::=  * | /
 * <compare_tok> ::=  < | > | <= | >= | ! = | ==
 * <num> ::=  {0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9}+
 */

/**
 * Function which does the following piece of the parse tree: <bexpr> ->
 * <expr> ;  .
 *
 * @param token The first token in the statement.
 * @return The final total, or ERROR
 */
int bexpr(char *token) {
    int subtotal = expr(token);
    if(subtotal == ERROR) {
        return subtotal;
    } else if(*token == ';') {
        get_token(token);
    } else {
        *token = ';';
        subtotal = ERROR;
    }
    return subtotal;
}

/**
 * Function which does the following piece of the parse tree: <expr> ->
 * <term> <ttail>   .
 *
 * @param token The first token in the statement.
 * @return The final total, or ERROR.
 */
int expr(char *token) {
    int subtotal = term(token);
    if (subtotal == ERROR)
        return subtotal;
    else
        return ttail(token, subtotal);
}

/**
 * Function which does the following piece of the parse tree: <term> ->
 * <stmt> <stail>   .
 *
 * @param token The current token in the statement.
 * @return The current total of the calculation, or ERROR.
 */
int term(char *token) {
    int subtotal = stmt(token);
    if (subtotal == ERROR){
        return subtotal;
    } else {
        return stail(token, subtotal);
    }
}

/**
 * Function which does the following piece of the parse tree: <ttail> ->
 * <add_sub_tok> <term> <ttail> | e   .
 *
 * @param token The current token in the statement.
 * @param subtotal The current total of the calculation, before ttail call.
 * @return The current total of the calculation, or ERROR.
 */
int ttail(char *token, int subtotal) {
    int term_value;

    if (!strncmp(token, "+", 1)) {
        add_sub_tok(token);
        term_value = term(token);

        if (term_value == ERROR) {
            return term_value;
        } else {
            return ttail(token, (subtotal + term_value));
        }
    } else if(!strncmp(token, "-", 1)) {
        add_sub_tok(token);
        term_value = term(token);

        if (term_value == ERROR) {
            return term_value;
        } else {
            return ttail(token, (subtotal - term_value));
        }
    } else {
        return subtotal;
    }
}

/**
 * Function which does the following piece of the parse tree: <stmt> ->
 * <factor> <ftail>   .
 *
 * @param token The current token in the statement.
 * @return The current total of the calculation, or ERROR.
 */
int stmt(char *token){
    int subtotal = factor(token);
    if (subtotal == ERROR){
        return subtotal;
    }else{
        return ftail(token, subtotal);
    }
}

/**
 * Function which does the following piece of the parse tree: <stail> ->
 * <mult_div_tok> <stmt> <stail> | e   .
 *
 * @param token The current token in the statement.
 * @param subtotal The current total of the calculation, before stail call.
 * @return The current total of the calculation, or ERROR.
 */
int stail(char *token, int subtotal) {
    int stmt_value;

    if (!strncmp(token, "*", 1)) {
        mul_div_tok(token);
        stmt_value = stmt(token);

        if (stmt_value == ERROR) {
            return stmt_value;
        } else {
            return stail(token, (subtotal * stmt_value));
        }
    }
    else if(!strncmp(token, "/", 1)) {
        add_sub_tok(token);
        stmt_value = stmt(token);

        if (stmt_value == ERROR) {
            return stmt_value;
        } else {
            return stail(token, (subtotal / stmt_value));
        }
    } else {
        return subtotal;
    }
}

/**
 * Function which does the following piece of the parse tree: <factor> ->
 * <expp> ^ <factor> | <expp>   .
 *
 * @param token The current token in the statement.
 * @return The current total of the calculation, or ERROR.
 */
int factor(char *token) {
    int subtotal = expp(token);
    if(subtotal == ERROR){
        return subtotal;
    }else if (*token == '^'){
        expon_tok(token);
        int factor_value = factor(token);
        if(factor_value == ERROR) {
            return factor_value;
        } else {
            return pow(subtotal,factor_value);
        }
    }else{
        return subtotal;
    }
}

/**
 * Function which does the following piece of the parse tree: <ftail> ->
 * <compare_tok> <factor> <ftail> | e   .
 *
 * @param token The current token in the statement.
 * @param subtotal The current total of the calculation, before stail call.
 * @return The current total of the calculation, or ERROR.
 */
int ftail(char *token, int subtotal) {
    int factor_value;

    if (!strncmp(token, "<", 1)) {
        compare_tok(token);
        factor_value = factor(token);

        if (factor_value == ERROR) {
            return factor_value;
        } else {
            return ftail(token, (subtotal < factor_value));
        }
    } else if(!strncmp(token, ">", 1)) {
        compare_tok(token);
        factor_value = factor(token);

        if (factor_value == ERROR) {
            return factor_value;
        } else {
            return ftail(token, (subtotal > factor_value));
        }
    } else if(!strncmp(token, "<=", 1)) {
        compare_tok(token);
        factor_value = factor(token);

        if (factor_value == ERROR) {
            return factor_value;
        } else {
            return ftail(token, (subtotal <= factor_value));
        }
    } else if(!strncmp(token, ">=", 1)) {
        compare_tok(token);
        factor_value = factor(token);

        if (factor_value == ERROR) {
            return factor_value;
        } else {
            return ftail(token, (subtotal >= factor_value));
        }
    }else if(!strncmp(token,"!=", 1)) {
        compare_tok(token);
        factor_value = factor(token);

        if (factor_value == ERROR) {
            return factor_value;
        } else {
            return ftail(token, (subtotal != factor_value));
        }
    } else if(!strncmp(token,"==", 1)) {
        compare_tok(token);
        factor_value = factor(token);

        if (factor_value == ERROR) {
            return factor_value;
        } else {
            return ftail(token, (subtotal == factor_value));
        }
    } else {
        return subtotal;
    }
}

/**
 * Function which does the following piece of the parse tree: <expp> ->
 * (<expr>) | <num>   .
 *
 * @param token The current token in the statement.
 * @return The current total of the calculation, or ERROR.
 */
int expp(char *token) {
    int subtotal;

    if(is_num(token) == TRUE) {
        subtotal = num(token);
    } else if(*token == '(') {
        get_token(token);
        int expr_value = expr(token);
        if(expr_value == ERROR) {
            return expr_value;
        } else if(*token == ')') {
            get_token(token);
            subtotal = expr_value;
        } else {
            *token = ')';
            subtotal = ERROR;
        }
    }else{
        subtotal = ERROR;
    }
    return subtotal;
}

/**
 * Consumes +|- tokens
 *
 * @param token The current token in the statement.
 */
void add_sub_tok(char *token){
    get_token(token);
}

/**
 * Consumes *|/ tokens
 *
 * @param token The current token in the statement.
 */
 void mul_div_tok(char *token){
    get_token(token);
}

/**
 * Consumes <|>|>=|<=|==|!= tokens
 *
 * @param token The current token in the statement.
 */
void compare_tok(char *token) {
    get_token(token);
}

/**
 * Consumes ^ tokens
 *
 * @param token The current token in the statement.
 */
void expon_tok(char *token){
    get_token(token);
}

/**
 * Consumes whole int literals.
 * @param token The current token in the statement.
 * @return The value of the entire int literal token.
 */
int num(char *token) {
    int subtotal = atoi(token);
    get_token(token);
    return subtotal;
}

/**
 * Simple function which checks whether a character is a number.
 * @param token Pointer to the character being checked.
 * @return TRUE if the character is a digit, FALSE otherwise.
 */
int is_num(char *token) {
    if(isdigit(*token)) {
        return TRUE;
    } else {
        return FALSE;
    }
}