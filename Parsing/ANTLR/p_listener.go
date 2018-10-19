// Code generated from p.g4 by ANTLR 4.7.1. DO NOT EDIT.

package parser // p

import "github.com/antlr/antlr4/runtime/Go/antlr"

// pListener is a complete listener for a parse tree produced by pParser.
type pListener interface {
	antlr.ParseTreeListener

	// EnterProg is called when entering the prog production.
	EnterProg(c *ProgContext)

	// EnterReg is called when entering the reg production.
	EnterReg(c *RegContext)

	// EnterImm is called when entering the imm production.
	EnterImm(c *ImmContext)

	// EnterInst is called when entering the inst production.
	EnterInst(c *InstContext)

	// ExitProg is called when exiting the prog production.
	ExitProg(c *ProgContext)

	// ExitReg is called when exiting the reg production.
	ExitReg(c *RegContext)

	// ExitImm is called when exiting the imm production.
	ExitImm(c *ImmContext)

	// ExitInst is called when exiting the inst production.
	ExitInst(c *InstContext)
}
