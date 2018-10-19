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
		ADD=1, INT=2, SEPARATOR=3, NEWLINE=4, NEG=5, REGISTER=6, HASH=7;
	public static final int
		RULE_prog = 0, RULE_reg = 1, RULE_imm = 2, RULE_inst = 3;
	public static final String[] ruleNames = {
		"prog", "reg", "imm", "inst"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'ADD'", null, "','", null, "'-'", "'r'", "'#'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ADD", "INT", "SEPARATOR", "NEWLINE", "NEG", "REGISTER", "HASH"
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
		public List<TerminalNode> NEWLINE() { return getTokens(pParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(pParser.NEWLINE, i);
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
					{
					setState(8);
					inst();
					}
					break;
				case NEWLINE:
					{
					setState(9);
					match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(12); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ADD || _la==NEWLINE );
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
		public List<TerminalNode> NEWLINE() { return getTokens(pParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(pParser.NEWLINE, i);
		}
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
		public TerminalNode HASH() { return getToken(pParser.HASH, 0); }
		public TerminalNode INT() { return getToken(pParser.INT, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				{
				setState(23);
				match(ADD);
				setState(24);
				match(NEWLINE);
				setState(25);
				reg();
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEPARATOR) {
					{
					setState(26);
					match(SEPARATOR);
					}
				}

				setState(29);
				match(NEWLINE);
				setState(30);
				reg();
				setState(32);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEPARATOR) {
					{
					setState(31);
					match(SEPARATOR);
					}
				}

				setState(34);
				match(NEWLINE);
				setState(35);
				reg();
				}
				}
				break;
			case 2:
				{
				{
				setState(37);
				match(ADD);
				setState(38);
				reg();
				setState(39);
				match(SEPARATOR);
				setState(40);
				reg();
				setState(41);
				match(SEPARATOR);
				setState(42);
				match(HASH);
				setState(43);
				match(INT);
				}
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\t\62\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\6\2\r\n\2\r\2\16\2\16\3\3\3\3\3\3\3\4\3\4"+
		"\5\4\26\n\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5\36\n\5\3\5\3\5\3\5\5\5#\n\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\60\n\5\3\5\2\2\6\2\4\6"+
		"\b\2\2\2\63\2\f\3\2\2\2\4\20\3\2\2\2\6\23\3\2\2\2\b/\3\2\2\2\n\r\5\b\5"+
		"\2\13\r\7\6\2\2\f\n\3\2\2\2\f\13\3\2\2\2\r\16\3\2\2\2\16\f\3\2\2\2\16"+
		"\17\3\2\2\2\17\3\3\2\2\2\20\21\7\b\2\2\21\22\7\4\2\2\22\5\3\2\2\2\23\25"+
		"\7\t\2\2\24\26\7\7\2\2\25\24\3\2\2\2\25\26\3\2\2\2\26\27\3\2\2\2\27\30"+
		"\7\4\2\2\30\7\3\2\2\2\31\32\7\3\2\2\32\33\7\6\2\2\33\35\5\4\3\2\34\36"+
		"\7\5\2\2\35\34\3\2\2\2\35\36\3\2\2\2\36\37\3\2\2\2\37 \7\6\2\2 \"\5\4"+
		"\3\2!#\7\5\2\2\"!\3\2\2\2\"#\3\2\2\2#$\3\2\2\2$%\7\6\2\2%&\5\4\3\2&\60"+
		"\3\2\2\2\'(\7\3\2\2()\5\4\3\2)*\7\5\2\2*+\5\4\3\2+,\7\5\2\2,-\7\t\2\2"+
		"-.\7\4\2\2.\60\3\2\2\2/\31\3\2\2\2/\'\3\2\2\2\60\t\3\2\2\2\b\f\16\25\35"+
		"\"/";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}