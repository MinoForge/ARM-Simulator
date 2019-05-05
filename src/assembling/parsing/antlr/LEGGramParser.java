// Generated from LEGGram.g4 by ANTLR 4.7.1

    package assembling.parsing.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LEGGramParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ADD=1, SUB=2, MOV=3, AND=4, ORR=5, MUL=6, DIV=7, LDUR=8, STUR=9, B=10, 
		BL=11, CBZ=12, CBNZ=13, MADD=14, LSL=15, LSR=16, UBFM=17, SVC=18, INT=19, 
		SEPARATOR=20, WS=21, NEG=22, REGISTER=23, COLON=24, HASH=25, EQUALS=26, 
		ENTRY=27, END=28, WORD=29, LCWORD=30, DIRECTIVE=31, DATA=32, STRING=33, 
		LBRACK=34, RBRACK=35, COMSLASH=36, BR=37, BLR=38;
	public static final int
		RULE_filePath = 0, RULE_prog = 1, RULE_data = 2, RULE_reg = 3, RULE_imm = 4, 
		RULE_memcall = 5, RULE_label = 6, RULE_comment = 7, RULE_inst = 8;
	public static final String[] ruleNames = {
		"filePath", "prog", "data", "reg", "imm", "memcall", "label", "comment", 
		"inst"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "', '", null, "'-'", "'r'", 
		"':'", "'#'", "'='", "'ENTRY'", "'END'", null, null, "'.'", "'.data'", 
		"'\".*\"'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ADD", "SUB", "MOV", "AND", "ORR", "MUL", "DIV", "LDUR", "STUR", 
		"B", "BL", "CBZ", "CBNZ", "MADD", "LSL", "LSR", "UBFM", "SVC", "INT", 
		"SEPARATOR", "WS", "NEG", "REGISTER", "COLON", "HASH", "EQUALS", "ENTRY", 
		"END", "WORD", "LCWORD", "DIRECTIVE", "DATA", "STRING", "LBRACK", "RBRACK", 
		"COMSLASH", "BR", "BLR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "LEGGram.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LEGGramParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FilePathContext extends ParserRuleContext {
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public List<DataContext> data() {
			return getRuleContexts(DataContext.class);
		}
		public DataContext data(int i) {
			return getRuleContext(DataContext.class,i);
		}
		public FilePathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filePath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterFilePath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitFilePath(this);
		}
	}

	public final FilePathContext filePath() throws RecognitionException {
		FilePathContext _localctx = new FilePathContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_filePath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DATA) {
				{
				setState(18);
				data();
				}
			}

			setState(21);
			prog();
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DATA) {
				{
				setState(22);
				data();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgContext extends ParserRuleContext {
		public TerminalNode ENTRY() { return getToken(LEGGramParser.ENTRY, 0); }
		public TerminalNode END() { return getToken(LEGGramParser.END, 0); }
		public List<LabelContext> label() {
			return getRuleContexts(LabelContext.class);
		}
		public LabelContext label(int i) {
			return getRuleContext(LabelContext.class,i);
		}
		public List<InstContext> inst() {
			return getRuleContexts(InstContext.class);
		}
		public InstContext inst(int i) {
			return getRuleContext(InstContext.class,i);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(LEGGramParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(LEGGramParser.WS, i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(ENTRY);
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(30);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WORD:
					{
					setState(26);
					label();
					}
					break;
				case ADD:
				case SUB:
				case MOV:
				case AND:
				case ORR:
				case MUL:
				case DIV:
				case LDUR:
				case STUR:
				case B:
				case BL:
				case CBZ:
				case CBNZ:
				case MADD:
				case LSL:
				case LSR:
				case UBFM:
				case SVC:
				case HASH:
				case BR:
				case BLR:
					{
					setState(27);
					inst();
					}
					break;
				case COMSLASH:
					{
					setState(28);
					comment();
					}
					break;
				case WS:
					{
					setState(29);
					match(WS);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << MOV) | (1L << AND) | (1L << ORR) | (1L << MUL) | (1L << DIV) | (1L << LDUR) | (1L << STUR) | (1L << B) | (1L << BL) | (1L << CBZ) | (1L << CBNZ) | (1L << MADD) | (1L << LSL) | (1L << LSR) | (1L << UBFM) | (1L << SVC) | (1L << WS) | (1L << HASH) | (1L << WORD) | (1L << COMSLASH) | (1L << BR) | (1L << BLR))) != 0) );
			setState(34);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataContext extends ParserRuleContext {
		public TerminalNode DATA() { return getToken(LEGGramParser.DATA, 0); }
		public TerminalNode WS() { return getToken(LEGGramParser.WS, 0); }
		public TerminalNode DIRECTIVE() { return getToken(LEGGramParser.DIRECTIVE, 0); }
		public TerminalNode LCWORD() { return getToken(LEGGramParser.LCWORD, 0); }
		public TerminalNode STRING() { return getToken(LEGGramParser.STRING, 0); }
		public List<TerminalNode> INT() { return getTokens(LEGGramParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(LEGGramParser.INT, i);
		}
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitData(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_data);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(36);
			match(DATA);
			setState(37);
			match(WS);
			setState(38);
			match(DIRECTIVE);
			setState(39);
			match(LCWORD);
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(40);
				match(STRING);
				}
				break;
			case INT:
				{
				setState(42); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(41);
					match(INT);
					}
					}
					setState(44); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==INT );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegContext extends ParserRuleContext {
		public TerminalNode REGISTER() { return getToken(LEGGramParser.REGISTER, 0); }
		public TerminalNode INT() { return getToken(LEGGramParser.INT, 0); }
		public RegContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterReg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitReg(this);
		}
	}

	public final RegContext reg() throws RecognitionException {
		RegContext _localctx = new RegContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_reg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(48);
			match(REGISTER);
			setState(49);
			match(INT);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImmContext extends ParserRuleContext {
		public TerminalNode HASH() { return getToken(LEGGramParser.HASH, 0); }
		public TerminalNode INT() { return getToken(LEGGramParser.INT, 0); }
		public TerminalNode NEG() { return getToken(LEGGramParser.NEG, 0); }
		public ImmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterImm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitImm(this);
		}
	}

	public final ImmContext imm() throws RecognitionException {
		ImmContext _localctx = new ImmContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_imm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(51);
			match(HASH);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEG) {
				{
				setState(52);
				match(NEG);
				}
			}

			setState(55);
			match(INT);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemcallContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(LEGGramParser.EQUALS, 0); }
		public TerminalNode WORD() { return getToken(LEGGramParser.WORD, 0); }
		public MemcallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memcall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterMemcall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitMemcall(this);
		}
	}

	public final MemcallContext memcall() throws RecognitionException {
		MemcallContext _localctx = new MemcallContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_memcall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(57);
			match(EQUALS);
			setState(58);
			match(WORD);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(LEGGramParser.WORD, 0); }
		public TerminalNode COLON() { return getToken(LEGGramParser.COLON, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(60);
			match(WORD);
			setState(61);
			match(COLON);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public List<TerminalNode> COMSLASH() { return getTokens(LEGGramParser.COMSLASH); }
		public TerminalNode COMSLASH(int i) {
			return getToken(LEGGramParser.COMSLASH, i);
		}
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_comment);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(64); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(63);
					match(COMSLASH);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(66); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(68);
					matchWildcard();
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(LEGGramParser.ADD, 0); }
		public List<RegContext> reg() {
			return getRuleContexts(RegContext.class);
		}
		public RegContext reg(int i) {
			return getRuleContext(RegContext.class,i);
		}
		public List<TerminalNode> SEPARATOR() { return getTokens(LEGGramParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(LEGGramParser.SEPARATOR, i);
		}
		public TerminalNode SUB() { return getToken(LEGGramParser.SUB, 0); }
		public TerminalNode MOV() { return getToken(LEGGramParser.MOV, 0); }
		public TerminalNode AND() { return getToken(LEGGramParser.AND, 0); }
		public TerminalNode ORR() { return getToken(LEGGramParser.ORR, 0); }
		public TerminalNode MUL() { return getToken(LEGGramParser.MUL, 0); }
		public TerminalNode MADD() { return getToken(LEGGramParser.MADD, 0); }
		public TerminalNode DIV() { return getToken(LEGGramParser.DIV, 0); }
		public TerminalNode BL() { return getToken(LEGGramParser.BL, 0); }
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public List<ImmContext> imm() {
			return getRuleContexts(ImmContext.class);
		}
		public ImmContext imm(int i) {
			return getRuleContext(ImmContext.class,i);
		}
		public TerminalNode B() { return getToken(LEGGramParser.B, 0); }
		public TerminalNode BR() { return getToken(LEGGramParser.BR, 0); }
		public TerminalNode BLR() { return getToken(LEGGramParser.BLR, 0); }
		public TerminalNode CBZ() { return getToken(LEGGramParser.CBZ, 0); }
		public TerminalNode CBNZ() { return getToken(LEGGramParser.CBNZ, 0); }
		public TerminalNode LDUR() { return getToken(LEGGramParser.LDUR, 0); }
		public TerminalNode LBRACK() { return getToken(LEGGramParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(LEGGramParser.RBRACK, 0); }
		public TerminalNode STUR() { return getToken(LEGGramParser.STUR, 0); }
		public TerminalNode LSL() { return getToken(LEGGramParser.LSL, 0); }
		public TerminalNode LSR() { return getToken(LEGGramParser.LSR, 0); }
		public TerminalNode UBFM() { return getToken(LEGGramParser.UBFM, 0); }
		public TerminalNode SVC() { return getToken(LEGGramParser.SVC, 0); }
		public InstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitInst(this);
		}
	}

	public final InstContext inst() throws RecognitionException {
		InstContext _localctx = new InstContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_inst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(74);
				match(ADD);
				setState(75);
				reg();
				setState(76);
				match(SEPARATOR);
				setState(77);
				reg();
				setState(78);
				match(SEPARATOR);
				setState(81);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(79);
					reg();
					}
					break;
				case HASH:
					{
					setState(80);
					imm();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				{
				setState(83);
				match(SUB);
				setState(84);
				reg();
				setState(85);
				match(SEPARATOR);
				setState(86);
				reg();
				setState(87);
				match(SEPARATOR);
				setState(90);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(88);
					reg();
					}
					break;
				case HASH:
					{
					setState(89);
					imm();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				{
				setState(92);
				match(MOV);
				setState(93);
				reg();
				setState(94);
				match(SEPARATOR);
				setState(95);
				reg();
				}
				break;
			case 4:
				{
				setState(97);
				match(AND);
				setState(98);
				reg();
				setState(99);
				match(SEPARATOR);
				setState(100);
				reg();
				setState(101);
				match(SEPARATOR);
				setState(102);
				reg();
				}
				break;
			case 5:
				{
				setState(104);
				match(ORR);
				setState(105);
				reg();
				setState(106);
				match(SEPARATOR);
				setState(107);
				reg();
				setState(108);
				match(SEPARATOR);
				setState(109);
				reg();
				}
				break;
			case 6:
				{
				setState(111);
				match(MUL);
				setState(112);
				reg();
				setState(113);
				match(SEPARATOR);
				setState(114);
				reg();
				setState(115);
				match(SEPARATOR);
				setState(116);
				reg();
				}
				break;
			case 7:
				{
				setState(118);
				match(MADD);
				setState(119);
				reg();
				setState(120);
				match(SEPARATOR);
				setState(121);
				reg();
				setState(122);
				match(SEPARATOR);
				setState(123);
				reg();
				setState(124);
				match(SEPARATOR);
				setState(125);
				reg();
				}
				break;
			case 8:
				{
				setState(127);
				match(DIV);
				setState(128);
				reg();
				setState(129);
				match(SEPARATOR);
				setState(130);
				reg();
				setState(131);
				match(SEPARATOR);
				setState(132);
				reg();
				}
				break;
			case 9:
				{
				setState(134);
				match(BL);
				setState(135);
				label();
				}
				break;
			case 10:
				{
				setState(136);
				imm();
				}
				break;
			case 11:
				{
				setState(137);
				match(B);
				setState(138);
				label();
				}
				break;
			case 12:
				{
				setState(139);
				imm();
				}
				break;
			case 13:
				{
				setState(140);
				match(BR);
				setState(141);
				reg();
				}
				break;
			case 14:
				{
				setState(142);
				match(BLR);
				setState(143);
				reg();
				}
				break;
			case 15:
				{
				setState(144);
				match(CBZ);
				setState(145);
				reg();
				setState(146);
				match(SEPARATOR);
				setState(149);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WORD:
					{
					setState(147);
					label();
					}
					break;
				case HASH:
					{
					setState(148);
					imm();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 16:
				{
				setState(151);
				match(CBNZ);
				setState(152);
				reg();
				setState(153);
				match(SEPARATOR);
				setState(156);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WORD:
					{
					setState(154);
					label();
					}
					break;
				case HASH:
					{
					setState(155);
					imm();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 17:
				{
				setState(158);
				match(LDUR);
				setState(159);
				reg();
				setState(160);
				match(SEPARATOR);
				setState(161);
				match(LBRACK);
				setState(162);
				reg();
				setState(163);
				match(SEPARATOR);
				setState(164);
				imm();
				setState(165);
				match(RBRACK);
				}
				break;
			case 18:
				{
				setState(167);
				match(STUR);
				setState(168);
				reg();
				setState(169);
				match(SEPARATOR);
				setState(170);
				match(LBRACK);
				setState(171);
				reg();
				setState(172);
				match(SEPARATOR);
				setState(173);
				imm();
				setState(174);
				match(RBRACK);
				}
				break;
			case 19:
				{
				setState(176);
				match(LSL);
				setState(177);
				reg();
				setState(178);
				match(SEPARATOR);
				setState(179);
				reg();
				setState(180);
				match(SEPARATOR);
				setState(181);
				reg();
				}
				break;
			case 20:
				{
				setState(183);
				match(LSR);
				setState(184);
				reg();
				setState(185);
				match(SEPARATOR);
				setState(186);
				reg();
				setState(187);
				match(SEPARATOR);
				setState(188);
				reg();
				}
				break;
			case 21:
				{
				setState(190);
				match(UBFM);
				setState(191);
				reg();
				setState(192);
				match(SEPARATOR);
				setState(193);
				reg();
				setState(194);
				match(SEPARATOR);
				setState(195);
				imm();
				setState(196);
				match(SEPARATOR);
				setState(197);
				imm();
				}
				break;
			case 22:
				{
				setState(199);
				match(SVC);
				setState(200);
				imm();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3(\u00ce\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\5\2"+
		"\26\n\2\3\2\3\2\5\2\32\n\2\3\3\3\3\3\3\3\3\3\3\6\3!\n\3\r\3\16\3\"\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\6\4-\n\4\r\4\16\4.\5\4\61\n\4\3\5\3\5\3\5"+
		"\3\6\3\6\5\68\n\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\6\tC\n\t\r\t\16"+
		"\tD\3\t\7\tH\n\t\f\t\16\tK\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\nT\n\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n]\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0098\n\n\3"+
		"\n\3\n\3\n\3\n\3\n\5\n\u009f\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00cc"+
		"\n\n\3\n\3I\2\13\2\4\6\b\n\f\16\20\22\2\2\2\u00e8\2\25\3\2\2\2\4\33\3"+
		"\2\2\2\6&\3\2\2\2\b\62\3\2\2\2\n\65\3\2\2\2\f;\3\2\2\2\16>\3\2\2\2\20"+
		"B\3\2\2\2\22\u00cb\3\2\2\2\24\26\5\6\4\2\25\24\3\2\2\2\25\26\3\2\2\2\26"+
		"\27\3\2\2\2\27\31\5\4\3\2\30\32\5\6\4\2\31\30\3\2\2\2\31\32\3\2\2\2\32"+
		"\3\3\2\2\2\33 \7\35\2\2\34!\5\16\b\2\35!\5\22\n\2\36!\5\20\t\2\37!\7\27"+
		"\2\2 \34\3\2\2\2 \35\3\2\2\2 \36\3\2\2\2 \37\3\2\2\2!\"\3\2\2\2\" \3\2"+
		"\2\2\"#\3\2\2\2#$\3\2\2\2$%\7\36\2\2%\5\3\2\2\2&\'\7\"\2\2\'(\7\27\2\2"+
		"()\7!\2\2)\60\7 \2\2*\61\7#\2\2+-\7\25\2\2,+\3\2\2\2-.\3\2\2\2.,\3\2\2"+
		"\2./\3\2\2\2/\61\3\2\2\2\60*\3\2\2\2\60,\3\2\2\2\61\7\3\2\2\2\62\63\7"+
		"\31\2\2\63\64\7\25\2\2\64\t\3\2\2\2\65\67\7\33\2\2\668\7\30\2\2\67\66"+
		"\3\2\2\2\678\3\2\2\289\3\2\2\29:\7\25\2\2:\13\3\2\2\2;<\7\34\2\2<=\7\37"+
		"\2\2=\r\3\2\2\2>?\7\37\2\2?@\7\32\2\2@\17\3\2\2\2AC\7&\2\2BA\3\2\2\2C"+
		"D\3\2\2\2DB\3\2\2\2DE\3\2\2\2EI\3\2\2\2FH\13\2\2\2GF\3\2\2\2HK\3\2\2\2"+
		"IJ\3\2\2\2IG\3\2\2\2J\21\3\2\2\2KI\3\2\2\2LM\7\3\2\2MN\5\b\5\2NO\7\26"+
		"\2\2OP\5\b\5\2PS\7\26\2\2QT\5\b\5\2RT\5\n\6\2SQ\3\2\2\2SR\3\2\2\2T\u00cc"+
		"\3\2\2\2UV\7\4\2\2VW\5\b\5\2WX\7\26\2\2XY\5\b\5\2Y\\\7\26\2\2Z]\5\b\5"+
		"\2[]\5\n\6\2\\Z\3\2\2\2\\[\3\2\2\2]\u00cc\3\2\2\2^_\7\5\2\2_`\5\b\5\2"+
		"`a\7\26\2\2ab\5\b\5\2b\u00cc\3\2\2\2cd\7\6\2\2de\5\b\5\2ef\7\26\2\2fg"+
		"\5\b\5\2gh\7\26\2\2hi\5\b\5\2i\u00cc\3\2\2\2jk\7\7\2\2kl\5\b\5\2lm\7\26"+
		"\2\2mn\5\b\5\2no\7\26\2\2op\5\b\5\2p\u00cc\3\2\2\2qr\7\b\2\2rs\5\b\5\2"+
		"st\7\26\2\2tu\5\b\5\2uv\7\26\2\2vw\5\b\5\2w\u00cc\3\2\2\2xy\7\20\2\2y"+
		"z\5\b\5\2z{\7\26\2\2{|\5\b\5\2|}\7\26\2\2}~\5\b\5\2~\177\7\26\2\2\177"+
		"\u0080\5\b\5\2\u0080\u00cc\3\2\2\2\u0081\u0082\7\t\2\2\u0082\u0083\5\b"+
		"\5\2\u0083\u0084\7\26\2\2\u0084\u0085\5\b\5\2\u0085\u0086\7\26\2\2\u0086"+
		"\u0087\5\b\5\2\u0087\u00cc\3\2\2\2\u0088\u0089\7\r\2\2\u0089\u00cc\5\16"+
		"\b\2\u008a\u00cc\5\n\6\2\u008b\u008c\7\f\2\2\u008c\u00cc\5\16\b\2\u008d"+
		"\u00cc\5\n\6\2\u008e\u008f\7\'\2\2\u008f\u00cc\5\b\5\2\u0090\u0091\7("+
		"\2\2\u0091\u00cc\5\b\5\2\u0092\u0093\7\16\2\2\u0093\u0094\5\b\5\2\u0094"+
		"\u0097\7\26\2\2\u0095\u0098\5\16\b\2\u0096\u0098\5\n\6\2\u0097\u0095\3"+
		"\2\2\2\u0097\u0096\3\2\2\2\u0098\u00cc\3\2\2\2\u0099\u009a\7\17\2\2\u009a"+
		"\u009b\5\b\5\2\u009b\u009e\7\26\2\2\u009c\u009f\5\16\b\2\u009d\u009f\5"+
		"\n\6\2\u009e\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f\u00cc\3\2\2\2\u00a0"+
		"\u00a1\7\n\2\2\u00a1\u00a2\5\b\5\2\u00a2\u00a3\7\26\2\2\u00a3\u00a4\7"+
		"$\2\2\u00a4\u00a5\5\b\5\2\u00a5\u00a6\7\26\2\2\u00a6\u00a7\5\n\6\2\u00a7"+
		"\u00a8\7%\2\2\u00a8\u00cc\3\2\2\2\u00a9\u00aa\7\13\2\2\u00aa\u00ab\5\b"+
		"\5\2\u00ab\u00ac\7\26\2\2\u00ac\u00ad\7$\2\2\u00ad\u00ae\5\b\5\2\u00ae"+
		"\u00af\7\26\2\2\u00af\u00b0\5\n\6\2\u00b0\u00b1\7%\2\2\u00b1\u00cc\3\2"+
		"\2\2\u00b2\u00b3\7\21\2\2\u00b3\u00b4\5\b\5\2\u00b4\u00b5\7\26\2\2\u00b5"+
		"\u00b6\5\b\5\2\u00b6\u00b7\7\26\2\2\u00b7\u00b8\5\b\5\2\u00b8\u00cc\3"+
		"\2\2\2\u00b9\u00ba\7\22\2\2\u00ba\u00bb\5\b\5\2\u00bb\u00bc\7\26\2\2\u00bc"+
		"\u00bd\5\b\5\2\u00bd\u00be\7\26\2\2\u00be\u00bf\5\b\5\2\u00bf\u00cc\3"+
		"\2\2\2\u00c0\u00c1\7\23\2\2\u00c1\u00c2\5\b\5\2\u00c2\u00c3\7\26\2\2\u00c3"+
		"\u00c4\5\b\5\2\u00c4\u00c5\7\26\2\2\u00c5\u00c6\5\n\6\2\u00c6\u00c7\7"+
		"\26\2\2\u00c7\u00c8\5\n\6\2\u00c8\u00cc\3\2\2\2\u00c9\u00ca\7\24\2\2\u00ca"+
		"\u00cc\5\n\6\2\u00cbL\3\2\2\2\u00cbU\3\2\2\2\u00cb^\3\2\2\2\u00cbc\3\2"+
		"\2\2\u00cbj\3\2\2\2\u00cbq\3\2\2\2\u00cbx\3\2\2\2\u00cb\u0081\3\2\2\2"+
		"\u00cb\u0088\3\2\2\2\u00cb\u008a\3\2\2\2\u00cb\u008b\3\2\2\2\u00cb\u008d"+
		"\3\2\2\2\u00cb\u008e\3\2\2\2\u00cb\u0090\3\2\2\2\u00cb\u0092\3\2\2\2\u00cb"+
		"\u0099\3\2\2\2\u00cb\u00a0\3\2\2\2\u00cb\u00a9\3\2\2\2\u00cb\u00b2\3\2"+
		"\2\2\u00cb\u00b9\3\2\2\2\u00cb\u00c0\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cc"+
		"\23\3\2\2\2\20\25\31 \".\60\67DIS\\\u0097\u009e\u00cb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}