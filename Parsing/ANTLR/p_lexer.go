// Code generated from p.g4 by ANTLR 4.7.1. DO NOT EDIT.

package parser

import (
	"fmt"
	"unicode"

	"github.com/antlr/antlr4/runtime/Go/antlr"
)

// Suppress unused import error
var _ = fmt.Printf
var _ = unicode.IsLetter

var serializedLexerAtn = []uint16{
	3, 24715, 42794, 33075, 47597, 16764, 15335, 30598, 22884, 2, 9, 36, 8,
	1, 4, 2, 9, 2, 4, 3, 9, 3, 4, 4, 9, 4, 4, 5, 9, 5, 4, 6, 9, 6, 4, 7, 9,
	7, 4, 8, 9, 8, 3, 2, 3, 2, 3, 2, 3, 2, 3, 3, 6, 3, 23, 10, 3, 13, 3, 14,
	3, 24, 3, 4, 3, 4, 3, 5, 3, 5, 3, 6, 3, 6, 3, 7, 3, 7, 3, 8, 3, 8, 2, 2,
	9, 3, 3, 5, 4, 7, 5, 9, 6, 11, 7, 13, 8, 15, 9, 3, 2, 4, 3, 2, 50, 59,
	5, 2, 11, 12, 15, 15, 34, 34, 2, 36, 2, 3, 3, 2, 2, 2, 2, 5, 3, 2, 2, 2,
	2, 7, 3, 2, 2, 2, 2, 9, 3, 2, 2, 2, 2, 11, 3, 2, 2, 2, 2, 13, 3, 2, 2,
	2, 2, 15, 3, 2, 2, 2, 3, 17, 3, 2, 2, 2, 5, 22, 3, 2, 2, 2, 7, 26, 3, 2,
	2, 2, 9, 28, 3, 2, 2, 2, 11, 30, 3, 2, 2, 2, 13, 32, 3, 2, 2, 2, 15, 34,
	3, 2, 2, 2, 17, 18, 7, 67, 2, 2, 18, 19, 7, 70, 2, 2, 19, 20, 7, 70, 2,
	2, 20, 4, 3, 2, 2, 2, 21, 23, 9, 2, 2, 2, 22, 21, 3, 2, 2, 2, 23, 24, 3,
	2, 2, 2, 24, 22, 3, 2, 2, 2, 24, 25, 3, 2, 2, 2, 25, 6, 3, 2, 2, 2, 26,
	27, 7, 46, 2, 2, 27, 8, 3, 2, 2, 2, 28, 29, 9, 3, 2, 2, 29, 10, 3, 2, 2,
	2, 30, 31, 7, 47, 2, 2, 31, 12, 3, 2, 2, 2, 32, 33, 7, 116, 2, 2, 33, 14,
	3, 2, 2, 2, 34, 35, 7, 37, 2, 2, 35, 16, 3, 2, 2, 2, 4, 2, 24, 2,
}

var lexerDeserializer = antlr.NewATNDeserializer(nil)
var lexerAtn = lexerDeserializer.DeserializeFromUInt16(serializedLexerAtn)

var lexerChannelNames = []string{
	"DEFAULT_TOKEN_CHANNEL", "HIDDEN",
}

var lexerModeNames = []string{
	"DEFAULT_MODE",
}

var lexerLiteralNames = []string{
	"", "'ADD'", "", "','", "", "'-'", "'r'", "'#'",
}

var lexerSymbolicNames = []string{
	"", "ADD", "INT", "SEPARATOR", "NEWLINE", "NEG", "REGISTER", "HASH",
}

var lexerRuleNames = []string{
	"ADD", "INT", "SEPARATOR", "NEWLINE", "NEG", "REGISTER", "HASH",
}

type pLexer struct {
	*antlr.BaseLexer
	channelNames []string
	modeNames    []string
	// TODO: EOF string
}

var lexerDecisionToDFA = make([]*antlr.DFA, len(lexerAtn.DecisionToState))

func init() {
	for index, ds := range lexerAtn.DecisionToState {
		lexerDecisionToDFA[index] = antlr.NewDFA(ds, index)
	}
}

func NewpLexer(input antlr.CharStream) *pLexer {

	l := new(pLexer)

	l.BaseLexer = antlr.NewBaseLexer(input)
	l.Interpreter = antlr.NewLexerATNSimulator(l, lexerAtn, lexerDecisionToDFA, antlr.NewPredictionContextCache())

	l.channelNames = lexerChannelNames
	l.modeNames = lexerModeNames
	l.RuleNames = lexerRuleNames
	l.LiteralNames = lexerLiteralNames
	l.SymbolicNames = lexerSymbolicNames
	l.GrammarFileName = "p.g4"
	// TODO: l.EOF = antlr.TokenEOF

	return l
}

// pLexer tokens.
const (
	pLexerADD       = 1
	pLexerINT       = 2
	pLexerSEPARATOR = 3
	pLexerNEWLINE   = 4
	pLexerNEG       = 5
	pLexerREGISTER  = 6
	pLexerHASH      = 7
)
