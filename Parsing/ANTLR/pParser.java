// Generated from p.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class pParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ADD=1, SUB=2, MOV=3, AND=4, OR=5, INT=6, SEPARATOR=7, WS=8, NEG=9, REGISTER=10, 
		HASH=11;
	public static final int
		RULE_prog = 0, RULE_reg = 1, RULE_imm = 2, RULE_inst = 3;
	public static final String[] ruleNames = {
		"prog", "reg", "imm", "inst"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'ADD '", "'SUB '", "'MOV '", "'AND '", "'OR '", null, "', '", null, 
		"'-'", "'r'", "'#'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ADD", "SUB", "MOV", "AND", "OR", "INT", "SEPARATOR", "WS", "NEG", 
		"REGISTER", "HASH"
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
	public String getGrammarFileName() { return "p.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public pParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public List<InstContext> inst() {
			return getRuleContexts(InstContext.class);
		}
		public InstContext inst(int i) {
			return getRuleContext(InstContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(pParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(pParser.WS, i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pListener ) ((pListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pListener ) ((pListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(10);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ADD:
				case SUB:
				case MOV:
				case AND:
				case OR:
					{
					setState(8);
					inst();
					}
					break;
				case WS:
					{
					setState(9);
					match(WS);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(12); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << SUB) | (1L << MOV) | (1L << AND) | (1L << OR) | (1L << WS))) != 0) );
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
		public TerminalNode REGISTER() { return getToken(pParser.REGISTER, 0); }
		public TerminalNode INT() { return getToken(pParser.INT, 0); }
		public RegContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pListener ) ((pListener)listener).enterReg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pListener ) ((pListener)listener).exitReg(this);
		}
	}

	public final RegContext reg() throws RecognitionException {
		RegContext _localctx = new RegContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_reg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(14);
			match(REGISTER);
			setState(15);
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
		public TerminalNode HASH() { return getToken(pParser.HASH, 0); }
		public TerminalNode INT() { return getToken(pParser.INT, 0); }
		public TerminalNode NEG() { return getToken(pParser.NEG, 0); }
		public ImmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pListener ) ((pListener)listener).enterImm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pListener ) ((pListener)listener).exitImm(this);
		}
	}

	public final ImmContext imm() throws RecognitionException {
		ImmContext _localctx = new ImmContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_imm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(17);
			match(HASH);
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEG) {
				{
				setState(18);
				match(NEG);
				}
			}

			setState(21);
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

	public static class InstContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(pParser.ADD, 0); }
		public List<RegContext> reg() {
			return getRuleContexts(RegContext.class);
		}
		public RegContext reg(int i) {
			return getRuleContext(RegContext.class,i);
		}
		public List<TerminalNode> SEPARATOR() { return getTokens(pParser.SEPARATOR); }
		public TerminalNode SEPARATOR(int i) {
			return getToken(pParser.SEPARATOR, i);
		}
		public TerminalNode SUB() { return getToken(pParser.SUB, 0); }
		public TerminalNode MOV() { return getToken(pParser.MOV, 0); }
		public TerminalNode AND() { return getToken(pParser.AND, 0); }
		public TerminalNode OR() { return getToken(pParser.OR, 0); }
		public ImmContext imm() {
			return getRuleContext(ImmContext.class,0);
		}
		public InstContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inst; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof pListener ) ((pListener)listener).enterInst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof pListener ) ((pListener)listener).exitInst(this);
		}
	}

	public final InstContext inst() throws RecognitionException {
		InstContext _localctx = new InstContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_inst);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
				{
				setState(23);
				match(ADD);
				setState(24);
				reg();
				setState(25);
				match(SEPARATOR);
				setState(26);
				reg();
				setState(27);
				match(SEPARATOR);
				setState(30);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(28);
					reg();
					}
					break;
				case HASH:
					{
					setState(29);
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
				setState(32);
				match(SUB);
				setState(33);
				reg();
				setState(34);
				match(SEPARATOR);
				setState(35);
				reg();
				setState(36);
				match(SEPARATOR);
				setState(39);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case REGISTER:
					{
					setState(37);
					reg();
					}
					break;
				case HASH:
					{
					setState(38);
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
				setState(41);
				match(MOV);
				setState(42);
				reg();
				setState(43);
				match(SEPARATOR);
				setState(44);
				reg();
				}
				break;
			case AND:
				{
				setState(46);
				match(AND);
				setState(47);
				reg();
				setState(48);
				match(SEPARATOR);
				setState(49);
				reg();
				setState(50);
				match(SEPARATOR);
				setState(51);
				reg();
				}
				break;
			case OR:
				{
				setState(53);
				match(OR);
				setState(54);
				reg();
				setState(55);
				match(SEPARATOR);
				setState(56);
				reg();
				setState(57);
				match(SEPARATOR);
				setState(58);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\rA\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\6\2\r\n\2\r\2\16\2\16\3\3\3\3\3\3\3\4\3\4\5"+
		"\4\26\n\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5!\n\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5*\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5?\n\5\3\5\2\2\6\2\4\6\b\2\2\2E\2\f\3\2"+
		"\2\2\4\20\3\2\2\2\6\23\3\2\2\2\b>\3\2\2\2\n\r\5\b\5\2\13\r\7\n\2\2\f\n"+
		"\3\2\2\2\f\13\3\2\2\2\r\16\3\2\2\2\16\f\3\2\2\2\16\17\3\2\2\2\17\3\3\2"+
		"\2\2\20\21\7\f\2\2\21\22\7\b\2\2\22\5\3\2\2\2\23\25\7\r\2\2\24\26\7\13"+
		"\2\2\25\24\3\2\2\2\25\26\3\2\2\2\26\27\3\2\2\2\27\30\7\b\2\2\30\7\3\2"+
		"\2\2\31\32\7\3\2\2\32\33\5\4\3\2\33\34\7\t\2\2\34\35\5\4\3\2\35 \7\t\2"+
		"\2\36!\5\4\3\2\37!\5\6\4\2 \36\3\2\2\2 \37\3\2\2\2!?\3\2\2\2\"#\7\4\2"+
		"\2#$\5\4\3\2$%\7\t\2\2%&\5\4\3\2&)\7\t\2\2\'*\5\4\3\2(*\5\6\4\2)\'\3\2"+
		"\2\2)(\3\2\2\2*?\3\2\2\2+,\7\5\2\2,-\5\4\3\2-.\7\t\2\2./\5\4\3\2/?\3\2"+
		"\2\2\60\61\7\6\2\2\61\62\5\4\3\2\62\63\7\t\2\2\63\64\5\4\3\2\64\65\7\t"+
		"\2\2\65\66\5\4\3\2\66?\3\2\2\2\678\7\7\2\289\5\4\3\29:\7\t\2\2:;\5\4\3"+
		"\2;<\7\t\2\2<=\5\4\3\2=?\3\2\2\2>\31\3\2\2\2>\"\3\2\2\2>+\3\2\2\2>\60"+
		"\3\2\2\2>\67\3\2\2\2?\t\3\2\2\2\b\f\16\25 )>";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}