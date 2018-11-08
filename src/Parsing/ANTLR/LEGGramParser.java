package Parsing.ANTLR;

// Generated from LEGGram.g4 by ANTLR 4.7.1
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
		ADD=1, SUB=2, MOV=3, AND=4, OR=5, INT=6, SEPARATOR=7, WS=8, NEG=9, REGISTER=10, 
		COLON=11, HASH=12, EQUALS=13, ENTRY=14, END=15, WORD=16, LCWORD=17, DIRECTIVE=18, 
		DATA=19, STRING=20;
	public static final int
		RULE_file = 0, RULE_prog = 1, RULE_data = 2, RULE_reg = 3, RULE_imm = 4, 
		RULE_memcall = 5, RULE_label = 6, RULE_inst = 7;
	public static final String[] ruleNames = {
		"file", "prog", "data", "reg", "imm", "memcall", "label", "inst"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'ADD '", "'SUB '", "'MOV '", "'AND '", "'OR '", null, "', '", null, 
		"'-'", "'r'", "':'", "'#'", "'='", "'ENTRY'", "'END'", null, null, "'.'", 
		"'.data'", "'\".*\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ADD", "SUB", "MOV", "AND", "OR", "INT", "SEPARATOR", "WS", "NEG", 
		"REGISTER", "COLON", "HASH", "EQUALS", "ENTRY", "END", "WORD", "LCWORD", 
		"DIRECTIVE", "DATA", "STRING"
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
	public static class FileContext extends ParserRuleContext {
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public DataContext data() {
			return getRuleContext(DataContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LEGGramListener ) ((LEGGramListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			prog();
			setState(17);
			data();
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
			setState(19);
			match(ENTRY);
			setState(23); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(23);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WORD:
					{
					setState(20);
					label();
					}
					break;
				case ADD:
				case SUB:
				case MOV:
				case AND:
				case OR:
					{
					setState(21);
					inst();
					}
					break;
				case WS:
					{
					setState(22);
					match(WS);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(25); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << MOV) | (1L << AND) | (1L << OR) | (1L << WS) | (1L << WORD))) != 0) );
			setState(27);
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
			setState(29);
			match(DATA);
			setState(30);
			match(WS);
			setState(31);
			match(DIRECTIVE);
			setState(32);
			match(LCWORD);
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(33);
				match(STRING);
				}
				break;
			case INT:
				{
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(34);
					match(INT);
					}
					}
					setState(37); 
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
			setState(41);
			match(REGISTER);
			setState(42);
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
			setState(44);
			match(HASH);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEG) {
				{
				setState(45);
				match(NEG);
				}
			}

			setState(48);
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
			setState(50);
			match(EQUALS);
			setState(51);
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
			setState(53);
			match(WORD);
			setState(54);
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
		public TerminalNode OR() { return getToken(LEGGramParser.OR, 0); }
		public ImmContext imm() {
			return getRuleContext(ImmContext.class,0);
		}
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
		enterRule(_localctx, 14, RULE_inst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
				{
				setState(56);
				match(ADD);
				setState(57);
				reg();
				setState(58);
				match(SEPARATOR);
				setState(59);
				reg();
				setState(60);
				match(SEPARATOR);
				setState(63);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(61);
					reg();
					}
					break;
				case HASH:
					{
					setState(62);
					imm();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case SUB:
				{
				setState(65);
				match(SUB);
				setState(66);
				reg();
				setState(67);
				match(SEPARATOR);
				setState(68);
				reg();
				setState(69);
				match(SEPARATOR);
				setState(72);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(70);
					reg();
					}
					break;
				case HASH:
					{
					setState(71);
					imm();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case MOV:
				{
				setState(74);
				match(MOV);
				setState(75);
				reg();
				setState(76);
				match(SEPARATOR);
				setState(77);
				reg();
				}
				break;
			case AND:
				{
				setState(79);
				match(AND);
				setState(80);
				reg();
				setState(81);
				match(SEPARATOR);
				setState(82);
				reg();
				setState(83);
				match(SEPARATOR);
				setState(84);
				reg();
				}
				break;
			case OR:
				{
				setState(86);
				match(OR);
				setState(87);
				reg();
				setState(88);
				match(SEPARATOR);
				setState(89);
				reg();
				setState(90);
				match(SEPARATOR);
				setState(91);
				reg();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26b\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\3\3\6\3\32\n\3\r\3\16\3\33\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\6\4&\n"+
		"\4\r\4\16\4\'\5\4*\n\4\3\5\3\5\3\5\3\6\3\6\5\6\61\n\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tB\n\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\tK\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t`\n\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2"+
		"\2e\2\22\3\2\2\2\4\25\3\2\2\2\6\37\3\2\2\2\b+\3\2\2\2\n.\3\2\2\2\f\64"+
		"\3\2\2\2\16\67\3\2\2\2\20_\3\2\2\2\22\23\5\4\3\2\23\24\5\6\4\2\24\3\3"+
		"\2\2\2\25\31\7\20\2\2\26\32\5\16\b\2\27\32\5\20\t\2\30\32\7\n\2\2\31\26"+
		"\3\2\2\2\31\27\3\2\2\2\31\30\3\2\2\2\32\33\3\2\2\2\33\31\3\2\2\2\33\34"+
		"\3\2\2\2\34\35\3\2\2\2\35\36\7\21\2\2\36\5\3\2\2\2\37 \7\25\2\2 !\7\n"+
		"\2\2!\"\7\24\2\2\")\7\23\2\2#*\7\26\2\2$&\7\b\2\2%$\3\2\2\2&\'\3\2\2\2"+
		"\'%\3\2\2\2\'(\3\2\2\2(*\3\2\2\2)#\3\2\2\2)%\3\2\2\2*\7\3\2\2\2+,\7\f"+
		"\2\2,-\7\b\2\2-\t\3\2\2\2.\60\7\16\2\2/\61\7\13\2\2\60/\3\2\2\2\60\61"+
		"\3\2\2\2\61\62\3\2\2\2\62\63\7\b\2\2\63\13\3\2\2\2\64\65\7\17\2\2\65\66"+
		"\7\22\2\2\66\r\3\2\2\2\678\7\22\2\289\7\r\2\29\17\3\2\2\2:;\7\3\2\2;<"+
		"\5\b\5\2<=\7\t\2\2=>\5\b\5\2>A\7\t\2\2?B\5\b\5\2@B\5\n\6\2A?\3\2\2\2A"+
		"@\3\2\2\2B`\3\2\2\2CD\7\4\2\2DE\5\b\5\2EF\7\t\2\2FG\5\b\5\2GJ\7\t\2\2"+
		"HK\5\b\5\2IK\5\n\6\2JH\3\2\2\2JI\3\2\2\2K`\3\2\2\2LM\7\5\2\2MN\5\b\5\2"+
		"NO\7\t\2\2OP\5\b\5\2P`\3\2\2\2QR\7\6\2\2RS\5\b\5\2ST\7\t\2\2TU\5\b\5\2"+
		"UV\7\t\2\2VW\5\b\5\2W`\3\2\2\2XY\7\7\2\2YZ\5\b\5\2Z[\7\t\2\2[\\\5\b\5"+
		"\2\\]\7\t\2\2]^\5\b\5\2^`\3\2\2\2_:\3\2\2\2_C\3\2\2\2_L\3\2\2\2_Q\3\2"+
		"\2\2_X\3\2\2\2`\21\3\2\2\2\n\31\33\')\60AJ_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}