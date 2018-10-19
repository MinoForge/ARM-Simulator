// Code generated from p.g4 by ANTLR 4.7.1. DO NOT EDIT.

package parser // p

import "github.com/antlr/antlr4/runtime/Go/antlr"

// BasepListener is a complete listener for a parse tree produced by pParser.
type BasepListener struct{}

var _ pListener = &BasepListener{}

// VisitTerminal is called when a terminal node is visited.
func (s *BasepListener) VisitTerminal(node antlr.TerminalNode) {}

// VisitErrorNode is called when an error node is visited.
func (s *BasepListener) VisitErrorNode(node antlr.ErrorNode) {}

// EnterEveryRule is called when any rule is entered.
func (s *BasepListener) EnterEveryRule(ctx antlr.ParserRuleContext) {}

// ExitEveryRule is called when any rule is exited.
func (s *BasepListener) ExitEveryRule(ctx antlr.ParserRuleContext) {}

// EnterProg is called when production prog is entered.
func (s *BasepListener) EnterProg(ctx *ProgContext) {}

// ExitProg is called when production prog is exited.
func (s *BasepListener) ExitProg(ctx *ProgContext) {}

// EnterReg is called when production reg is entered.
func (s *BasepListener) EnterReg(ctx *RegContext) {}

// ExitReg is called when production reg is exited.
func (s *BasepListener) ExitReg(ctx *RegContext) {}

// EnterImm is called when production imm is entered.
func (s *BasepListener) EnterImm(ctx *ImmContext) {}

// ExitImm is called when production imm is exited.
func (s *BasepListener) ExitImm(ctx *ImmContext) {}

// EnterInst is called when production inst is entered.
func (s *BasepListener) EnterInst(ctx *InstContext) {}

// ExitInst is called when production inst is exited.
func (s *BasepListener) ExitInst(ctx *InstContext) {}
