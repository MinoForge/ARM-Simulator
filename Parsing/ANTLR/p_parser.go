// Code generated from p.g4 by ANTLR 4.7.1. DO NOT EDIT.

package parser // p

import (
	"fmt"
	"reflect"
	"strconv"

	"github.com/antlr/antlr4/runtime/Go/antlr"
)

// Suppress unused import errors
var _ = fmt.Printf
var _ = reflect.Copy
var _ = strconv.Itoa

var parserATN = []uint16{
	3, 24715, 42794, 33075, 47597, 16764, 15335, 30598, 22884, 3, 9, 50, 4,
	2, 9, 2, 4, 3, 9, 3, 4, 4, 9, 4, 4, 5, 9, 5, 3, 2, 3, 2, 6, 2, 13, 10,
	2, 13, 2, 14, 2, 14, 3, 3, 3, 3, 3, 3, 3, 4, 3, 4, 5, 4, 22, 10, 4, 3,
	4, 3, 4, 3, 5, 3, 5, 3, 5, 3, 5, 5, 5, 30, 10, 5, 3, 5, 3, 5, 3, 5, 5,
	5, 35, 10, 5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 5, 3, 5, 3,
	5, 3, 5, 5, 5, 48, 10, 5, 3, 5, 2, 2, 6, 2, 4, 6, 8, 2, 2, 2, 51, 2, 12,
	3, 2, 2, 2, 4, 16, 3, 2, 2, 2, 6, 19, 3, 2, 2, 2, 8, 47, 3, 2, 2, 2, 10,
	13, 5, 8, 5, 2, 11, 13, 7, 6, 2, 2, 12, 10, 3, 2, 2, 2, 12, 11, 3, 2, 2,
	2, 13, 14, 3, 2, 2, 2, 14, 12, 3, 2, 2, 2, 14, 15, 3, 2, 2, 2, 15, 3, 3,
	2, 2, 2, 16, 17, 7, 8, 2, 2, 17, 18, 7, 4, 2, 2, 18, 5, 3, 2, 2, 2, 19,
	21, 7, 9, 2, 2, 20, 22, 7, 7, 2, 2, 21, 20, 3, 2, 2, 2, 21, 22, 3, 2, 2,
	2, 22, 23, 3, 2, 2, 2, 23, 24, 7, 4, 2, 2, 24, 7, 3, 2, 2, 2, 25, 26, 7,
	3, 2, 2, 26, 27, 7, 6, 2, 2, 27, 29, 5, 4, 3, 2, 28, 30, 7, 5, 2, 2, 29,
	28, 3, 2, 2, 2, 29, 30, 3, 2, 2, 2, 30, 31, 3, 2, 2, 2, 31, 32, 7, 6, 2,
	2, 32, 34, 5, 4, 3, 2, 33, 35, 7, 5, 2, 2, 34, 33, 3, 2, 2, 2, 34, 35,
	3, 2, 2, 2, 35, 36, 3, 2, 2, 2, 36, 37, 7, 6, 2, 2, 37, 38, 5, 4, 3, 2,
	38, 48, 3, 2, 2, 2, 39, 40, 7, 3, 2, 2, 40, 41, 5, 4, 3, 2, 41, 42, 7,
	5, 2, 2, 42, 43, 5, 4, 3, 2, 43, 44, 7, 5, 2, 2, 44, 45, 7, 9, 2, 2, 45,
	46, 7, 4, 2, 2, 46, 48, 3, 2, 2, 2, 47, 25, 3, 2, 2, 2, 47, 39, 3, 2, 2,
	2, 48, 9, 3, 2, 2, 2, 8, 12, 14, 21, 29, 34, 47,
}
var deserializer = antlr.NewATNDeserializer(nil)
var deserializedATN = deserializer.DeserializeFromUInt16(parserATN)

var literalNames = []string{
	"", "'ADD'", "", "','", "", "'-'", "'r'", "'#'",
}
var symbolicNames = []string{
	"", "ADD", "INT", "SEPARATOR", "NEWLINE", "NEG", "REGISTER", "HASH",
}

var ruleNames = []string{
	"prog", "reg", "imm", "inst",
}
var decisionToDFA = make([]*antlr.DFA, len(deserializedATN.DecisionToState))

func init() {
	for index, ds := range deserializedATN.DecisionToState {
		decisionToDFA[index] = antlr.NewDFA(ds, index)
	}
}

type pParser struct {
	*antlr.BaseParser
}

func NewpParser(input antlr.TokenStream) *pParser {
	this := new(pParser)

	this.BaseParser = antlr.NewBaseParser(input)

	this.Interpreter = antlr.NewParserATNSimulator(this, deserializedATN, decisionToDFA, antlr.NewPredictionContextCache())
	this.RuleNames = ruleNames
	this.LiteralNames = literalNames
	this.SymbolicNames = symbolicNames
	this.GrammarFileName = "p.g4"

	return this
}

// pParser tokens.
const (
	pParserEOF       = antlr.TokenEOF
	pParserADD       = 1
	pParserINT       = 2
	pParserSEPARATOR = 3
	pParserNEWLINE   = 4
	pParserNEG       = 5
	pParserREGISTER  = 6
	pParserHASH      = 7
)

// pParser rules.
const (
	pParserRULE_prog = 0
	pParserRULE_reg  = 1
	pParserRULE_imm  = 2
	pParserRULE_inst = 3
)

// IProgContext is an interface to support dynamic dispatch.
type IProgContext interface {
	antlr.ParserRuleContext

	// GetParser returns the parser.
	GetParser() antlr.Parser

	// IsProgContext differentiates from other interfaces.
	IsProgContext()
}

type ProgContext struct {
	*antlr.BaseParserRuleContext
	parser antlr.Parser
}

func NewEmptyProgContext() *ProgContext {
	var p = new(ProgContext)
	p.BaseParserRuleContext = antlr.NewBaseParserRuleContext(nil, -1)
	p.RuleIndex = pParserRULE_prog
	return p
}

func (*ProgContext) IsProgContext() {}

func NewProgContext(parser antlr.Parser, parent antlr.ParserRuleContext, invokingState int) *ProgContext {
	var p = new(ProgContext)

	p.BaseParserRuleContext = antlr.NewBaseParserRuleContext(parent, invokingState)

	p.parser = parser
	p.RuleIndex = pParserRULE_prog

	return p
}

func (s *ProgContext) GetParser() antlr.Parser { return s.parser }

func (s *ProgContext) AllInst() []IInstContext {
	var ts = s.GetTypedRuleContexts(reflect.TypeOf((*IInstContext)(nil)).Elem())
	var tst = make([]IInstContext, len(ts))

	for i, t := range ts {
		if t != nil {
			tst[i] = t.(IInstContext)
		}
	}

	return tst
}

func (s *ProgContext) Inst(i int) IInstContext {
	var t = s.GetTypedRuleContext(reflect.TypeOf((*IInstContext)(nil)).Elem(), i)

	if t == nil {
		return nil
	}

	return t.(IInstContext)
}

func (s *ProgContext) AllNEWLINE() []antlr.TerminalNode {
	return s.GetTokens(pParserNEWLINE)
}

func (s *ProgContext) NEWLINE(i int) antlr.TerminalNode {
	return s.GetToken(pParserNEWLINE, i)
}

func (s *ProgContext) GetRuleContext() antlr.RuleContext {
	return s
}

func (s *ProgContext) ToStringTree(ruleNames []string, recog antlr.Recognizer) string {
	return antlr.TreesStringTree(s, ruleNames, recog)
}

func (s *ProgContext) EnterRule(listener antlr.ParseTreeListener) {
	if listenerT, ok := listener.(pListener); ok {
		listenerT.EnterProg(s)
	}
}

func (s *ProgContext) ExitRule(listener antlr.ParseTreeListener) {
	if listenerT, ok := listener.(pListener); ok {
		listenerT.ExitProg(s)
	}
}

func (p *pParser) Prog() (localctx IProgContext) {
	localctx = NewProgContext(p, p.GetParserRuleContext(), p.GetState())
	p.EnterRule(localctx, 0, pParserRULE_prog)
	var _la int

	defer func() {
		p.ExitRule()
	}()

	defer func() {
		if err := recover(); err != nil {
			if v, ok := err.(antlr.RecognitionException); ok {
				localctx.SetException(v)
				p.GetErrorHandler().ReportError(p, v)
				p.GetErrorHandler().Recover(p, v)
			} else {
				panic(err)
			}
		}
	}()

	p.EnterOuterAlt(localctx, 1)
	p.SetState(10)
	p.GetErrorHandler().Sync(p)
	_la = p.GetTokenStream().LA(1)

	for ok := true; ok; ok = _la == pParserADD || _la == pParserNEWLINE {
		p.SetState(10)
		p.GetErrorHandler().Sync(p)

		switch p.GetTokenStream().LA(1) {
		case pParserADD:
			{
				p.SetState(8)
				p.Inst()
			}

		case pParserNEWLINE:
			{
				p.SetState(9)
				p.Match(pParserNEWLINE)
			}

		default:
			panic(antlr.NewNoViableAltException(p, nil, nil, nil, nil, nil))
		}

		p.SetState(12)
		p.GetErrorHandler().Sync(p)
		_la = p.GetTokenStream().LA(1)
	}

	return localctx
}

// IRegContext is an interface to support dynamic dispatch.
type IRegContext interface {
	antlr.ParserRuleContext

	// GetParser returns the parser.
	GetParser() antlr.Parser

	// IsRegContext differentiates from other interfaces.
	IsRegContext()
}

type RegContext struct {
	*antlr.BaseParserRuleContext
	parser antlr.Parser
}

func NewEmptyRegContext() *RegContext {
	var p = new(RegContext)
	p.BaseParserRuleContext = antlr.NewBaseParserRuleContext(nil, -1)
	p.RuleIndex = pParserRULE_reg
	return p
}

func (*RegContext) IsRegContext() {}

func NewRegContext(parser antlr.Parser, parent antlr.ParserRuleContext, invokingState int) *RegContext {
	var p = new(RegContext)

	p.BaseParserRuleContext = antlr.NewBaseParserRuleContext(parent, invokingState)

	p.parser = parser
	p.RuleIndex = pParserRULE_reg

	return p
}

func (s *RegContext) GetParser() antlr.Parser { return s.parser }

func (s *RegContext) REGISTER() antlr.TerminalNode {
	return s.GetToken(pParserREGISTER, 0)
}

func (s *RegContext) INT() antlr.TerminalNode {
	return s.GetToken(pParserINT, 0)
}

func (s *RegContext) GetRuleContext() antlr.RuleContext {
	return s
}

func (s *RegContext) ToStringTree(ruleNames []string, recog antlr.Recognizer) string {
	return antlr.TreesStringTree(s, ruleNames, recog)
}

func (s *RegContext) EnterRule(listener antlr.ParseTreeListener) {
	if listenerT, ok := listener.(pListener); ok {
		listenerT.EnterReg(s)
	}
}

func (s *RegContext) ExitRule(listener antlr.ParseTreeListener) {
	if listenerT, ok := listener.(pListener); ok {
		listenerT.ExitReg(s)
	}
}

func (p *pParser) Reg() (localctx IRegContext) {
	localctx = NewRegContext(p, p.GetParserRuleContext(), p.GetState())
	p.EnterRule(localctx, 2, pParserRULE_reg)

	defer func() {
		p.ExitRule()
	}()

	defer func() {
		if err := recover(); err != nil {
			if v, ok := err.(antlr.RecognitionException); ok {
				localctx.SetException(v)
				p.GetErrorHandler().ReportError(p, v)
				p.GetErrorHandler().Recover(p, v)
			} else {
				panic(err)
			}
		}
	}()

	p.EnterOuterAlt(localctx, 1)
	{
		p.SetState(14)
		p.Match(pParserREGISTER)
	}
	{
		p.SetState(15)
		p.Match(pParserINT)
	}

	return localctx
}

// IImmContext is an interface to support dynamic dispatch.
type IImmContext interface {
	antlr.ParserRuleContext

	// GetParser returns the parser.
	GetParser() antlr.Parser

	// IsImmContext differentiates from other interfaces.
	IsImmContext()
}

type ImmContext struct {
	*antlr.BaseParserRuleContext
	parser antlr.Parser
}

func NewEmptyImmContext() *ImmContext {
	var p = new(ImmContext)
	p.BaseParserRuleContext = antlr.NewBaseParserRuleContext(nil, -1)
	p.RuleIndex = pParserRULE_imm
	return p
}

func (*ImmContext) IsImmContext() {}

func NewImmContext(parser antlr.Parser, parent antlr.ParserRuleContext, invokingState int) *ImmContext {
	var p = new(ImmContext)

	p.BaseParserRuleContext = antlr.NewBaseParserRuleContext(parent, invokingState)

	p.parser = parser
	p.RuleIndex = pParserRULE_imm

	return p
}

func (s *ImmContext) GetParser() antlr.Parser { return s.parser }

func (s *ImmContext) HASH() antlr.TerminalNode {
	return s.GetToken(pParserHASH, 0)
}

func (s *ImmContext) INT() antlr.TerminalNode {
	return s.GetToken(pParserINT, 0)
}

func (s *ImmContext) NEG() antlr.TerminalNode {
	return s.GetToken(pParserNEG, 0)
}

func (s *ImmContext) GetRuleContext() antlr.RuleContext {
	return s
}

func (s *ImmContext) ToStringTree(ruleNames []string, recog antlr.Recognizer) string {
	return antlr.TreesStringTree(s, ruleNames, recog)
}

func (s *ImmContext) EnterRule(listener antlr.ParseTreeListener) {
	if listenerT, ok := listener.(pListener); ok {
		listenerT.EnterImm(s)
	}
}

func (s *ImmContext) ExitRule(listener antlr.ParseTreeListener) {
	if listenerT, ok := listener.(pListener); ok {
		listenerT.ExitImm(s)
	}
}

func (p *pParser) Imm() (localctx IImmContext) {
	localctx = NewImmContext(p, p.GetParserRuleContext(), p.GetState())
	p.EnterRule(localctx, 4, pParserRULE_imm)
	var _la int

	defer func() {
		p.ExitRule()
	}()

	defer func() {
		if err := recover(); err != nil {
			if v, ok := err.(antlr.RecognitionException); ok {
				localctx.SetException(v)
				p.GetErrorHandler().ReportError(p, v)
				p.GetErrorHandler().Recover(p, v)
			} else {
				panic(err)
			}
		}
	}()

	p.EnterOuterAlt(localctx, 1)
	{
		p.SetState(17)
		p.Match(pParserHASH)
	}
	p.SetState(19)
	p.GetErrorHandler().Sync(p)
	_la = p.GetTokenStream().LA(1)

	if _la == pParserNEG {
		{
			p.SetState(18)
			p.Match(pParserNEG)
		}

	}
	{
		p.SetState(21)
		p.Match(pParserINT)
	}

	return localctx
}

// IInstContext is an interface to support dynamic dispatch.
type IInstContext interface {
	antlr.ParserRuleContext

	// GetParser returns the parser.
	GetParser() antlr.Parser

	// IsInstContext differentiates from other interfaces.
	IsInstContext()
}

type InstContext struct {
	*antlr.BaseParserRuleContext
	parser antlr.Parser
}

func NewEmptyInstContext() *InstContext {
	var p = new(InstContext)
	p.BaseParserRuleContext = antlr.NewBaseParserRuleContext(nil, -1)
	p.RuleIndex = pParserRULE_inst
	return p
}

func (*InstContext) IsInstContext() {}

func NewInstContext(parser antlr.Parser, parent antlr.ParserRuleContext, invokingState int) *InstContext {
	var p = new(InstContext)

	p.BaseParserRuleContext = antlr.NewBaseParserRuleContext(parent, invokingState)

	p.parser = parser
	p.RuleIndex = pParserRULE_inst

	return p
}

func (s *InstContext) GetParser() antlr.Parser { return s.parser }

func (s *InstContext) ADD() antlr.TerminalNode {
	return s.GetToken(pParserADD, 0)
}

func (s *InstContext) AllNEWLINE() []antlr.TerminalNode {
	return s.GetTokens(pParserNEWLINE)
}

func (s *InstContext) NEWLINE(i int) antlr.TerminalNode {
	return s.GetToken(pParserNEWLINE, i)
}

func (s *InstContext) AllReg() []IRegContext {
	var ts = s.GetTypedRuleContexts(reflect.TypeOf((*IRegContext)(nil)).Elem())
	var tst = make([]IRegContext, len(ts))

	for i, t := range ts {
		if t != nil {
			tst[i] = t.(IRegContext)
		}
	}

	return tst
}

func (s *InstContext) Reg(i int) IRegContext {
	var t = s.GetTypedRuleContext(reflect.TypeOf((*IRegContext)(nil)).Elem(), i)

	if t == nil {
		return nil
	}

	return t.(IRegContext)
}

func (s *InstContext) AllSEPARATOR() []antlr.TerminalNode {
	return s.GetTokens(pParserSEPARATOR)
}

func (s *InstContext) SEPARATOR(i int) antlr.TerminalNode {
	return s.GetToken(pParserSEPARATOR, i)
}

func (s *InstContext) HASH() antlr.TerminalNode {
	return s.GetToken(pParserHASH, 0)
}

func (s *InstContext) INT() antlr.TerminalNode {
	return s.GetToken(pParserINT, 0)
}

func (s *InstContext) GetRuleContext() antlr.RuleContext {
	return s
}

func (s *InstContext) ToStringTree(ruleNames []string, recog antlr.Recognizer) string {
	return antlr.TreesStringTree(s, ruleNames, recog)
}

func (s *InstContext) EnterRule(listener antlr.ParseTreeListener) {
	if listenerT, ok := listener.(pListener); ok {
		listenerT.EnterInst(s)
	}
}

func (s *InstContext) ExitRule(listener antlr.ParseTreeListener) {
	if listenerT, ok := listener.(pListener); ok {
		listenerT.ExitInst(s)
	}
}

func (p *pParser) Inst() (localctx IInstContext) {
	localctx = NewInstContext(p, p.GetParserRuleContext(), p.GetState())
	p.EnterRule(localctx, 6, pParserRULE_inst)
	var _la int

	defer func() {
		p.ExitRule()
	}()

	defer func() {
		if err := recover(); err != nil {
			if v, ok := err.(antlr.RecognitionException); ok {
				localctx.SetException(v)
				p.GetErrorHandler().ReportError(p, v)
				p.GetErrorHandler().Recover(p, v)
			} else {
				panic(err)
			}
		}
	}()

	p.EnterOuterAlt(localctx, 1)
	p.SetState(45)
	p.GetErrorHandler().Sync(p)
	switch p.GetInterpreter().AdaptivePredict(p.GetTokenStream(), 5, p.GetParserRuleContext()) {
	case 1:
		{
			p.SetState(23)
			p.Match(pParserADD)
		}
		{
			p.SetState(24)
			p.Match(pParserNEWLINE)
		}
		{
			p.SetState(25)
			p.Reg()
		}
		p.SetState(27)
		p.GetErrorHandler().Sync(p)
		_la = p.GetTokenStream().LA(1)

		if _la == pParserSEPARATOR {
			{
				p.SetState(26)
				p.Match(pParserSEPARATOR)
			}

		}
		{
			p.SetState(29)
			p.Match(pParserNEWLINE)
		}
		{
			p.SetState(30)
			p.Reg()
		}
		p.SetState(32)
		p.GetErrorHandler().Sync(p)
		_la = p.GetTokenStream().LA(1)

		if _la == pParserSEPARATOR {
			{
				p.SetState(31)
				p.Match(pParserSEPARATOR)
			}

		}
		{
			p.SetState(34)
			p.Match(pParserNEWLINE)
		}
		{
			p.SetState(35)
			p.Reg()
		}

	case 2:
		{
			p.SetState(37)
			p.Match(pParserADD)
		}
		{
			p.SetState(38)
			p.Reg()
		}
		{
			p.SetState(39)
			p.Match(pParserSEPARATOR)
		}
		{
			p.SetState(40)
			p.Reg()
		}
		{
			p.SetState(41)
			p.Match(pParserSEPARATOR)
		}
		{
			p.SetState(42)
			p.Match(pParserHASH)
		}
		{
			p.SetState(43)
			p.Match(pParserINT)
		}

	}

	return localctx
}
