package parsing.antlr;

// Generated from LEGGram.g4 by antlr 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

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
		null, null, "'SUB '", "'MOV '", "'AND '", "'OR '", null, "', '", null, 
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
			setState(22); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(22);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ADD:
				case SUB:
				case MOV:
				case AND:
				case OR:
					{
					setState(20);
					inst();
					}
					break;
				case WS:
					{
					setState(21);
					match(WS);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(24); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << MOV) | (1L << AND) | (1L << OR) | (1L << WS))) != 0) );
			setState(26);
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
			setState(28);
			match(DATA);
			setState(29);
			match(WS);
			setState(30);
			match(DIRECTIVE);
			setState(31);
			match(LCWORD);
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				{
				setState(32);
				match(STRING);
				}
				break;
			case INT:
				{
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(33);
					match(INT);
					}
					}
					setState(36); 
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
			setState(40);
			match(REGISTER);
			setState(41);
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
			setState(43);
			match(HASH);
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEG) {
				{
				setState(44);
				match(NEG);
				}
			}

			setState(47);
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
			setState(49);
			match(EQUALS);
			setState(50);
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
			setState(52);
			match(WORD);
			setState(53);
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
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
				{
				setState(55);
				match(ADD);
				setState(56);
				reg();
				setState(57);
				match(SEPARATOR);
				setState(58);
				reg();
				setState(59);
				match(SEPARATOR);
				setState(62);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(60);
					reg();
					}
					break;
				case HASH:
					{
					setState(61);
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
				setState(64);
				match(SUB);
				setState(65);
				reg();
				setState(66);
				match(SEPARATOR);
				setState(67);
				reg();
				setState(68);
				match(SEPARATOR);
				setState(71);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(69);
					reg();
					}
					break;
				case HASH:
					{
					setState(70);
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
				setState(73);
				match(MOV);
				setState(74);
				reg();
				setState(75);
				match(SEPARATOR);
				setState(76);
				reg();
				}
				break;
			case AND:
				{
				setState(78);
				match(AND);
				setState(79);
				reg();
				setState(80);
				match(SEPARATOR);
				setState(81);
				reg();
				setState(82);
				match(SEPARATOR);
				setState(83);
				reg();
				}
				break;
			case OR:
				{
				setState(85);
				match(OR);
				setState(86);
				reg();
				setState(87);
				match(SEPARATOR);
				setState(88);
				reg();
				setState(89);
				match(SEPARATOR);
				setState(90);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26a\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\3\3\3"+
		"\3\3\6\3\31\n\3\r\3\16\3\32\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\6\4%\n\4\r"+
		"\4\16\4&\5\4)\n\4\3\5\3\5\3\5\3\6\3\6\5\6\60\n\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tA\n\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\tJ\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\5\t_\n\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2c\2\22"+
		"\3\2\2\2\4\25\3\2\2\2\6\36\3\2\2\2\b*\3\2\2\2\n-\3\2\2\2\f\63\3\2\2\2"+
		"\16\66\3\2\2\2\20^\3\2\2\2\22\23\5\4\3\2\23\24\5\6\4\2\24\3\3\2\2\2\25"+
		"\30\7\20\2\2\26\31\5\20\t\2\27\31\7\n\2\2\30\26\3\2\2\2\30\27\3\2\2\2"+
		"\31\32\3\2\2\2\32\30\3\2\2\2\32\33\3\2\2\2\33\34\3\2\2\2\34\35\7\21\2"+
		"\2\35\5\3\2\2\2\36\37\7\25\2\2\37 \7\n\2\2 !\7\24\2\2!(\7\23\2\2\")\7"+
		"\26\2\2#%\7\b\2\2$#\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\')\3\2\2\2"+
		"(\"\3\2\2\2($\3\2\2\2)\7\3\2\2\2*+\7\f\2\2+,\7\b\2\2,\t\3\2\2\2-/\7\16"+
		"\2\2.\60\7\13\2\2/.\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61\62\7\b\2\2\62"+
		"\13\3\2\2\2\63\64\7\17\2\2\64\65\7\22\2\2\65\r\3\2\2\2\66\67\7\22\2\2"+
		"\678\7\r\2\28\17\3\2\2\29:\7\3\2\2:;\5\b\5\2;<\7\t\2\2<=\5\b\5\2=@\7\t"+
		"\2\2>A\5\b\5\2?A\5\n\6\2@>\3\2\2\2@?\3\2\2\2A_\3\2\2\2BC\7\4\2\2CD\5\b"+
		"\5\2DE\7\t\2\2EF\5\b\5\2FI\7\t\2\2GJ\5\b\5\2HJ\5\n\6\2IG\3\2\2\2IH\3\2"+
		"\2\2J_\3\2\2\2KL\7\5\2\2LM\5\b\5\2MN\7\t\2\2NO\5\b\5\2O_\3\2\2\2PQ\7\6"+
		"\2\2QR\5\b\5\2RS\7\t\2\2ST\5\b\5\2TU\7\t\2\2UV\5\b\5\2V_\3\2\2\2WX\7\7"+
		"\2\2XY\5\b\5\2YZ\7\t\2\2Z[\5\b\5\2[\\\7\t\2\2\\]\5\b\5\2]_\3\2\2\2^9\3"+
		"\2\2\2^B\3\2\2\2^K\3\2\2\2^P\3\2\2\2^W\3\2\2\2_\21\3\2\2\2\n\30\32&(/"+
		"@I^";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}