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
		ADD=1, SUB=2, MOV=3, AND=4, ORR=5, MUL=6, UDIV=7, LDUR=8, STUR=9, B=10, 
		BL=11, CBZ=12, CBNZ=13, MADD=14, LSL=15, LSR=16, UBFM=17, SVC=18, LDR=19, 
		BR=20, BLR=21, INT=22, SEPARATOR=23, WS=24, NEG=25, REGISTER=26, COLON=27, 
		HASH=28, EQUALS=29, ENTRY=30, END=31, WORD=32, LCWORD=33, DIRECTIVE=34, 
		DATA=35, TEXT=36, STRING=37, LBRACK=38, RBRACK=39, COMSLASH=40;
	public static final int
		RULE_filePath = 0, RULE_prog = 1, RULE_data = 2, RULE_reg = 3, RULE_imm = 4, 
		RULE_memcall = 5, RULE_label = 6, RULE_comment = 7, RULE_inst = 8;
	public static final String[] ruleNames = {
		"filePath", "prog", "data", "reg", "imm", "memcall", "label", "comment", 
		"inst"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "', '", 
		null, "'-'", "'r'", "':'", "'#'", "'='", "'ENTRY'", "'END'", null, null, 
		"'.'", "'.data'", "'.text'", "'\".*\"'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ADD", "SUB", "MOV", "AND", "ORR", "MUL", "UDIV", "LDUR", "STUR", 
		"B", "BL", "CBZ", "CBNZ", "MADD", "LSL", "LSR", "UBFM", "SVC", "LDR", 
		"BR", "BLR", "INT", "SEPARATOR", "WS", "NEG", "REGISTER", "COLON", "HASH", 
		"EQUALS", "ENTRY", "END", "WORD", "LCWORD", "DIRECTIVE", "DATA", "TEXT", 
		"STRING", "LBRACK", "RBRACK", "COMSLASH"
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
		public TerminalNode TEXT() { return getToken(LEGGramParser.TEXT, 0); }
		public TerminalNode END() { return getToken(LEGGramParser.END, 0); }
		public List<TerminalNode> WS() { return getTokens(LEGGramParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(LEGGramParser.WS, i);
		}
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
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				match(WS);
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(31);
			match(TEXT);
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(35);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WORD:
					{
					setState(32);
					label();
					}
					break;
				case ADD:
				case SUB:
				case MOV:
				case AND:
				case ORR:
				case MUL:
				case UDIV:
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
				case LDR:
				case BR:
				case BLR:
				case HASH:
					{
					setState(33);
					inst();
					}
					break;
				case WS:
					{
					setState(34);
					match(WS);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << MOV) | (1L << AND) | (1L << ORR) | (1L << MUL) | (1L << UDIV) | (1L << LDUR) | (1L << STUR) | (1L << B) | (1L << BL) | (1L << CBZ) | (1L << CBNZ) | (1L << MADD) | (1L << LSL) | (1L << LSR) | (1L << UBFM) | (1L << SVC) | (1L << LDR) | (1L << BR) | (1L << BLR) | (1L << WS) | (1L << HASH) | (1L << WORD))) != 0) );
			setState(39);
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
			setState(41);
			match(DATA);
			setState(42);
			match(WS);
			setState(43);
			match(DIRECTIVE);
			setState(44);
			match(LCWORD);
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(45);
				match(STRING);
				}
				break;
			case INT:
				{
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(46);
					match(INT);
					}
					}
					setState(49); 
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
			setState(53);
			match(REGISTER);
			setState(54);
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
			setState(56);
			match(HASH);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEG) {
				{
				setState(57);
				match(NEG);
				}
			}

			setState(60);
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
			setState(62);
			match(EQUALS);
			setState(63);
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
			setState(65);
			match(WORD);
			setState(66);
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
		public TerminalNode COMSLASH() { return getToken(LEGGramParser.COMSLASH, 0); }
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
			setState(68);
			match(COMSLASH);
			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(69);
					matchWildcard();
					}
					} 
				}
				setState(74);
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
		public TerminalNode UDIV() { return getToken(LEGGramParser.UDIV, 0); }
		public TerminalNode BL() { return getToken(LEGGramParser.BL, 0); }
		public TerminalNode WORD() { return getToken(LEGGramParser.WORD, 0); }
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
		public TerminalNode LDR() { return getToken(LEGGramParser.LDR, 0); }
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
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(75);
				match(ADD);
				setState(76);
				reg();
				setState(77);
				match(SEPARATOR);
				setState(78);
				reg();
				setState(79);
				match(SEPARATOR);
				setState(82);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(80);
					reg();
					}
					break;
				case HASH:
					{
					setState(81);
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
				setState(84);
				match(SUB);
				setState(85);
				reg();
				setState(86);
				match(SEPARATOR);
				setState(87);
				reg();
				setState(88);
				match(SEPARATOR);
				setState(91);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(89);
					reg();
					}
					break;
				case HASH:
					{
					setState(90);
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
				setState(93);
				match(MOV);
				setState(94);
				reg();
				setState(95);
				match(SEPARATOR);
				setState(96);
				reg();
				}
				break;
			case 4:
				{
				setState(98);
				match(AND);
				setState(99);
				reg();
				setState(100);
				match(SEPARATOR);
				setState(101);
				reg();
				setState(102);
				match(SEPARATOR);
				setState(103);
				reg();
				}
				break;
			case 5:
				{
				setState(105);
				match(ORR);
				setState(106);
				reg();
				setState(107);
				match(SEPARATOR);
				setState(108);
				reg();
				setState(109);
				match(SEPARATOR);
				setState(110);
				reg();
				}
				break;
			case 6:
				{
				setState(112);
				match(MUL);
				setState(113);
				reg();
				setState(114);
				match(SEPARATOR);
				setState(115);
				reg();
				setState(116);
				match(SEPARATOR);
				setState(117);
				reg();
				}
				break;
			case 7:
				{
				setState(119);
				match(MADD);
				setState(120);
				reg();
				setState(121);
				match(SEPARATOR);
				setState(122);
				reg();
				setState(123);
				match(SEPARATOR);
				setState(124);
				reg();
				setState(125);
				match(SEPARATOR);
				setState(126);
				reg();
				}
				break;
			case 8:
				{
				setState(128);
				match(UDIV);
				setState(129);
				reg();
				setState(130);
				match(SEPARATOR);
				setState(131);
				reg();
				setState(132);
				match(SEPARATOR);
				setState(133);
				reg();
				}
				break;
			case 9:
				{
				setState(135);
				match(BL);
				setState(136);
				match(WORD);
				}
				break;
			case 10:
				{
				setState(137);
				imm();
				}
				break;
			case 11:
				{
				setState(138);
				match(B);
				setState(139);
				match(WORD);
				}
				break;
			case 12:
				{
				setState(140);
				imm();
				}
				break;
			case 13:
				{
				setState(141);
				match(BR);
				setState(142);
				reg();
				}
				break;
			case 14:
				{
				setState(143);
				match(BLR);
				setState(144);
				reg();
				}
				break;
			case 15:
				{
				setState(145);
				match(CBZ);
				setState(146);
				reg();
				setState(147);
				match(SEPARATOR);
				setState(150);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WORD:
					{
					setState(148);
					match(WORD);
					}
					break;
				case HASH:
					{
					setState(149);
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
				setState(152);
				match(CBNZ);
				setState(153);
				reg();
				setState(154);
				match(SEPARATOR);
				setState(157);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WORD:
					{
					setState(155);
					match(WORD);
					}
					break;
				case HASH:
					{
					setState(156);
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
				setState(159);
				match(LDUR);
				setState(160);
				reg();
				setState(161);
				match(SEPARATOR);
				setState(162);
				match(LBRACK);
				setState(163);
				reg();
				setState(164);
				match(SEPARATOR);
				setState(165);
				imm();
				setState(166);
				match(RBRACK);
				}
				break;
			case 18:
				{
				setState(168);
				match(STUR);
				setState(169);
				reg();
				setState(170);
				match(SEPARATOR);
				setState(171);
				match(LBRACK);
				setState(172);
				reg();
				setState(173);
				match(SEPARATOR);
				setState(174);
				imm();
				setState(175);
				match(RBRACK);
				}
				break;
			case 19:
				{
				setState(177);
				match(LSL);
				setState(178);
				reg();
				setState(179);
				match(SEPARATOR);
				setState(180);
				reg();
				setState(181);
				match(SEPARATOR);
				setState(182);
				reg();
				}
				break;
			case 20:
				{
				setState(184);
				match(LSR);
				setState(185);
				reg();
				setState(186);
				match(SEPARATOR);
				setState(187);
				reg();
				setState(188);
				match(SEPARATOR);
				setState(189);
				reg();
				}
				break;
			case 21:
				{
				setState(191);
				match(LDR);
				setState(192);
				reg();
				setState(193);
				match(SEPARATOR);
				setState(194);
				match(WORD);
				}
				break;
			case 22:
				{
				setState(196);
				match(UBFM);
				setState(197);
				reg();
				setState(198);
				match(SEPARATOR);
				setState(199);
				reg();
				setState(200);
				match(SEPARATOR);
				setState(201);
				imm();
				setState(202);
				match(SEPARATOR);
				setState(203);
				imm();
				}
				break;
			case 23:
				{
				setState(205);
				match(SVC);
				setState(206);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u00d4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\5\2"+
		"\26\n\2\3\2\3\2\5\2\32\n\2\3\3\3\3\6\3\36\n\3\r\3\16\3\37\3\3\3\3\3\3"+
		"\3\3\6\3&\n\3\r\3\16\3\'\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\6\4\62\n\4\r"+
		"\4\16\4\63\5\4\66\n\4\3\5\3\5\3\5\3\6\3\6\5\6=\n\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\t\3\t\7\tI\n\t\f\t\16\tL\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\5\nU\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n^\n\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n"+
		"\u0099\n\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a0\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00d2\n\n\3\n\3J\2\13\2\4\6\b\n\f\16\20"+
		"\22\2\2\2\u00ee\2\25\3\2\2\2\4\33\3\2\2\2\6+\3\2\2\2\b\67\3\2\2\2\n:\3"+
		"\2\2\2\f@\3\2\2\2\16C\3\2\2\2\20F\3\2\2\2\22\u00d1\3\2\2\2\24\26\5\6\4"+
		"\2\25\24\3\2\2\2\25\26\3\2\2\2\26\27\3\2\2\2\27\31\5\4\3\2\30\32\5\6\4"+
		"\2\31\30\3\2\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33\35\7 \2\2\34\36\7\32\2"+
		"\2\35\34\3\2\2\2\36\37\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 !\3\2\2\2!%\7"+
		"&\2\2\"&\5\16\b\2#&\5\22\n\2$&\7\32\2\2%\"\3\2\2\2%#\3\2\2\2%$\3\2\2\2"+
		"&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\7!\2\2*\5\3\2\2\2+,\7%\2"+
		"\2,-\7\32\2\2-.\7$\2\2.\65\7#\2\2/\66\7\'\2\2\60\62\7\30\2\2\61\60\3\2"+
		"\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65/\3\2\2"+
		"\2\65\61\3\2\2\2\66\7\3\2\2\2\678\7\34\2\289\7\30\2\29\t\3\2\2\2:<\7\36"+
		"\2\2;=\7\33\2\2<;\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\7\30\2\2?\13\3\2\2\2@"+
		"A\7\37\2\2AB\7\"\2\2B\r\3\2\2\2CD\7\"\2\2DE\7\35\2\2E\17\3\2\2\2FJ\7*"+
		"\2\2GI\13\2\2\2HG\3\2\2\2IL\3\2\2\2JK\3\2\2\2JH\3\2\2\2K\21\3\2\2\2LJ"+
		"\3\2\2\2MN\7\3\2\2NO\5\b\5\2OP\7\31\2\2PQ\5\b\5\2QT\7\31\2\2RU\5\b\5\2"+
		"SU\5\n\6\2TR\3\2\2\2TS\3\2\2\2U\u00d2\3\2\2\2VW\7\4\2\2WX\5\b\5\2XY\7"+
		"\31\2\2YZ\5\b\5\2Z]\7\31\2\2[^\5\b\5\2\\^\5\n\6\2][\3\2\2\2]\\\3\2\2\2"+
		"^\u00d2\3\2\2\2_`\7\5\2\2`a\5\b\5\2ab\7\31\2\2bc\5\b\5\2c\u00d2\3\2\2"+
		"\2de\7\6\2\2ef\5\b\5\2fg\7\31\2\2gh\5\b\5\2hi\7\31\2\2ij\5\b\5\2j\u00d2"+
		"\3\2\2\2kl\7\7\2\2lm\5\b\5\2mn\7\31\2\2no\5\b\5\2op\7\31\2\2pq\5\b\5\2"+
		"q\u00d2\3\2\2\2rs\7\b\2\2st\5\b\5\2tu\7\31\2\2uv\5\b\5\2vw\7\31\2\2wx"+
		"\5\b\5\2x\u00d2\3\2\2\2yz\7\20\2\2z{\5\b\5\2{|\7\31\2\2|}\5\b\5\2}~\7"+
		"\31\2\2~\177\5\b\5\2\177\u0080\7\31\2\2\u0080\u0081\5\b\5\2\u0081\u00d2"+
		"\3\2\2\2\u0082\u0083\7\t\2\2\u0083\u0084\5\b\5\2\u0084\u0085\7\31\2\2"+
		"\u0085\u0086\5\b\5\2\u0086\u0087\7\31\2\2\u0087\u0088\5\b\5\2\u0088\u00d2"+
		"\3\2\2\2\u0089\u008a\7\r\2\2\u008a\u00d2\7\"\2\2\u008b\u00d2\5\n\6\2\u008c"+
		"\u008d\7\f\2\2\u008d\u00d2\7\"\2\2\u008e\u00d2\5\n\6\2\u008f\u0090\7\26"+
		"\2\2\u0090\u00d2\5\b\5\2\u0091\u0092\7\27\2\2\u0092\u00d2\5\b\5\2\u0093"+
		"\u0094\7\16\2\2\u0094\u0095\5\b\5\2\u0095\u0098\7\31\2\2\u0096\u0099\7"+
		"\"\2\2\u0097\u0099\5\n\6\2\u0098\u0096\3\2\2\2\u0098\u0097\3\2\2\2\u0099"+
		"\u00d2\3\2\2\2\u009a\u009b\7\17\2\2\u009b\u009c\5\b\5\2\u009c\u009f\7"+
		"\31\2\2\u009d\u00a0\7\"\2\2\u009e\u00a0\5\n\6\2\u009f\u009d\3\2\2\2\u009f"+
		"\u009e\3\2\2\2\u00a0\u00d2\3\2\2\2\u00a1\u00a2\7\n\2\2\u00a2\u00a3\5\b"+
		"\5\2\u00a3\u00a4\7\31\2\2\u00a4\u00a5\7(\2\2\u00a5\u00a6\5\b\5\2\u00a6"+
		"\u00a7\7\31\2\2\u00a7\u00a8\5\n\6\2\u00a8\u00a9\7)\2\2\u00a9\u00d2\3\2"+
		"\2\2\u00aa\u00ab\7\13\2\2\u00ab\u00ac\5\b\5\2\u00ac\u00ad\7\31\2\2\u00ad"+
		"\u00ae\7(\2\2\u00ae\u00af\5\b\5\2\u00af\u00b0\7\31\2\2\u00b0\u00b1\5\n"+
		"\6\2\u00b1\u00b2\7)\2\2\u00b2\u00d2\3\2\2\2\u00b3\u00b4\7\21\2\2\u00b4"+
		"\u00b5\5\b\5\2\u00b5\u00b6\7\31\2\2\u00b6\u00b7\5\b\5\2\u00b7\u00b8\7"+
		"\31\2\2\u00b8\u00b9\5\b\5\2\u00b9\u00d2\3\2\2\2\u00ba\u00bb\7\22\2\2\u00bb"+
		"\u00bc\5\b\5\2\u00bc\u00bd\7\31\2\2\u00bd\u00be\5\b\5\2\u00be\u00bf\7"+
		"\31\2\2\u00bf\u00c0\5\b\5\2\u00c0\u00d2\3\2\2\2\u00c1\u00c2\7\25\2\2\u00c2"+
		"\u00c3\5\b\5\2\u00c3\u00c4\7\31\2\2\u00c4\u00c5\7\"\2\2\u00c5\u00d2\3"+
		"\2\2\2\u00c6\u00c7\7\23\2\2\u00c7\u00c8\5\b\5\2\u00c8\u00c9\7\31\2\2\u00c9"+
		"\u00ca\5\b\5\2\u00ca\u00cb\7\31\2\2\u00cb\u00cc\5\n\6\2\u00cc\u00cd\7"+
		"\31\2\2\u00cd\u00ce\5\n\6\2\u00ce\u00d2\3\2\2\2\u00cf\u00d0\7\24\2\2\u00d0"+
		"\u00d2\5\n\6\2\u00d1M\3\2\2\2\u00d1V\3\2\2\2\u00d1_\3\2\2\2\u00d1d\3\2"+
		"\2\2\u00d1k\3\2\2\2\u00d1r\3\2\2\2\u00d1y\3\2\2\2\u00d1\u0082\3\2\2\2"+
		"\u00d1\u0089\3\2\2\2\u00d1\u008b\3\2\2\2\u00d1\u008c\3\2\2\2\u00d1\u008e"+
		"\3\2\2\2\u00d1\u008f\3\2\2\2\u00d1\u0091\3\2\2\2\u00d1\u0093\3\2\2\2\u00d1"+
		"\u009a\3\2\2\2\u00d1\u00a1\3\2\2\2\u00d1\u00aa\3\2\2\2\u00d1\u00b3\3\2"+
		"\2\2\u00d1\u00ba\3\2\2\2\u00d1\u00c1\3\2\2\2\u00d1\u00c6\3\2\2\2\u00d1"+
		"\u00cf\3\2\2\2\u00d2\23\3\2\2\2\20\25\31\37%\'\63\65<JT]\u0098\u009f\u00d1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}