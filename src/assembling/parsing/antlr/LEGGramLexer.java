// Generated from LEGGram.g4 by ANTLR 4.7.1

    package assembling.parsing.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LEGGramLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ADD=1, SUB=2, MOV=3, AND=4, ORR=5, MUL=6, DIV=7, LDUR=8, STUR=9, B=10, 
		BL=11, CBZ=12, CBNZ=13, MADD=14, LSL=15, LSR=16, UBFM=17, SVC=18, INT=19, 
		SEPARATOR=20, WS=21, NEG=22, REGISTER=23, COLON=24, HASH=25, EQUALS=26, 
		ENTRY=27, END=28, WORD=29, LCWORD=30, DIRECTIVE=31, DATA=32, STRING=33, 
		LBRACK=34, RBRACK=35, COMSLASH=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ADD", "SUB", "MOV", "AND", "ORR", "MUL", "DIV", "LDUR", "STUR", "B", 
		"BL", "CBZ", "CBNZ", "MADD", "LSL", "LSR", "UBFM", "SVC", "INT", "SEPARATOR", 
		"WS", "NEG", "REGISTER", "COLON", "HASH", "EQUALS", "ENTRY", "END", "WORD", 
		"LCWORD", "DIRECTIVE", "DATA", "STRING", "LBRACK", "RBRACK", "COMSLASH"
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
		"COMSLASH"
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


	public LEGGramLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LEGGram.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2&\u00e6\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\6\24\u00a9\n\24\r\24\16\24\u00aa\3\25\3\25\3\25\3"+
		"\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\6\36\u00c7\n\36\r\36\16\36"+
		"\u00c8\3\37\6\37\u00cc\n\37\r\37\16\37\u00cd\3 \3 \3!\3!\3!\3!\3!\3!\3"+
		"\"\3\"\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\6%\u00e3\n%\r%\16%\u00e4\2\2&\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&\3\2\30\4\2CCcc\4\2FFff\3\2\"\"\4\2UUuu\4\2WWww\4\2DDdd\4\2"+
		"OOoo\4\2QQqq\4\2XXxx\4\2PPpp\4\2TTtt\4\2NNnn\4\2KKkk\4\2VVvv\4\2EEee\4"+
		"\2\\\\||\4\2HHhh\3\2\62;\5\2\13\f\17\17\"\"\5\2//C\\c|\3\2c|\4\2\61\61"+
		"^^\2\u00e9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\3K\3\2\2\2\5P\3\2\2\2\7U\3\2\2\2\tZ\3\2\2\2\13"+
		"_\3\2\2\2\rd\3\2\2\2\17i\3\2\2\2\21n\3\2\2\2\23t\3\2\2\2\25z\3\2\2\2\27"+
		"}\3\2\2\2\31\u0081\3\2\2\2\33\u0086\3\2\2\2\35\u008c\3\2\2\2\37\u0092"+
		"\3\2\2\2!\u0097\3\2\2\2#\u009c\3\2\2\2%\u00a2\3\2\2\2\'\u00a8\3\2\2\2"+
		")\u00ac\3\2\2\2+\u00af\3\2\2\2-\u00b1\3\2\2\2/\u00b3\3\2\2\2\61\u00b5"+
		"\3\2\2\2\63\u00b7\3\2\2\2\65\u00b9\3\2\2\2\67\u00bb\3\2\2\29\u00c1\3\2"+
		"\2\2;\u00c6\3\2\2\2=\u00cb\3\2\2\2?\u00cf\3\2\2\2A\u00d1\3\2\2\2C\u00d7"+
		"\3\2\2\2E\u00dc\3\2\2\2G\u00de\3\2\2\2I\u00e0\3\2\2\2KL\t\2\2\2LM\t\3"+
		"\2\2MN\t\3\2\2NO\t\4\2\2O\4\3\2\2\2PQ\t\5\2\2QR\t\6\2\2RS\t\7\2\2ST\t"+
		"\4\2\2T\6\3\2\2\2UV\t\b\2\2VW\t\t\2\2WX\t\n\2\2XY\t\4\2\2Y\b\3\2\2\2Z"+
		"[\t\2\2\2[\\\t\13\2\2\\]\t\3\2\2]^\t\4\2\2^\n\3\2\2\2_`\t\t\2\2`a\t\f"+
		"\2\2ab\t\f\2\2bc\t\4\2\2c\f\3\2\2\2de\t\b\2\2ef\t\6\2\2fg\t\r\2\2gh\t"+
		"\4\2\2h\16\3\2\2\2ij\t\3\2\2jk\t\16\2\2kl\t\n\2\2lm\t\4\2\2m\20\3\2\2"+
		"\2no\t\r\2\2op\t\3\2\2pq\t\6\2\2qr\t\f\2\2rs\t\4\2\2s\22\3\2\2\2tu\t\5"+
		"\2\2uv\t\17\2\2vw\t\6\2\2wx\t\f\2\2xy\t\4\2\2y\24\3\2\2\2z{\t\7\2\2{|"+
		"\t\4\2\2|\26\3\2\2\2}~\t\7\2\2~\177\t\r\2\2\177\u0080\t\4\2\2\u0080\30"+
		"\3\2\2\2\u0081\u0082\t\20\2\2\u0082\u0083\t\7\2\2\u0083\u0084\t\21\2\2"+
		"\u0084\u0085\t\4\2\2\u0085\32\3\2\2\2\u0086\u0087\t\20\2\2\u0087\u0088"+
		"\t\7\2\2\u0088\u0089\t\13\2\2\u0089\u008a\t\21\2\2\u008a\u008b\t\4\2\2"+
		"\u008b\34\3\2\2\2\u008c\u008d\t\b\2\2\u008d\u008e\t\2\2\2\u008e\u008f"+
		"\t\3\2\2\u008f\u0090\t\3\2\2\u0090\u0091\t\4\2\2\u0091\36\3\2\2\2\u0092"+
		"\u0093\t\r\2\2\u0093\u0094\t\5\2\2\u0094\u0095\t\r\2\2\u0095\u0096\t\4"+
		"\2\2\u0096 \3\2\2\2\u0097\u0098\t\r\2\2\u0098\u0099\t\5\2\2\u0099\u009a"+
		"\t\f\2\2\u009a\u009b\t\4\2\2\u009b\"\3\2\2\2\u009c\u009d\t\6\2\2\u009d"+
		"\u009e\t\7\2\2\u009e\u009f\t\22\2\2\u009f\u00a0\t\b\2\2\u00a0\u00a1\t"+
		"\4\2\2\u00a1$\3\2\2\2\u00a2\u00a3\t\5\2\2\u00a3\u00a4\t\n\2\2\u00a4\u00a5"+
		"\t\20\2\2\u00a5\u00a6\t\4\2\2\u00a6&\3\2\2\2\u00a7\u00a9\t\23\2\2\u00a8"+
		"\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2"+
		"\2\2\u00ab(\3\2\2\2\u00ac\u00ad\7.\2\2\u00ad\u00ae\7\"\2\2\u00ae*\3\2"+
		"\2\2\u00af\u00b0\t\24\2\2\u00b0,\3\2\2\2\u00b1\u00b2\7/\2\2\u00b2.\3\2"+
		"\2\2\u00b3\u00b4\7t\2\2\u00b4\60\3\2\2\2\u00b5\u00b6\7<\2\2\u00b6\62\3"+
		"\2\2\2\u00b7\u00b8\7%\2\2\u00b8\64\3\2\2\2\u00b9\u00ba\7?\2\2\u00ba\66"+
		"\3\2\2\2\u00bb\u00bc\7G\2\2\u00bc\u00bd\7P\2\2\u00bd\u00be\7V\2\2\u00be"+
		"\u00bf\7T\2\2\u00bf\u00c0\7[\2\2\u00c08\3\2\2\2\u00c1\u00c2\7G\2\2\u00c2"+
		"\u00c3\7P\2\2\u00c3\u00c4\7F\2\2\u00c4:\3\2\2\2\u00c5\u00c7\t\25\2\2\u00c6"+
		"\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9<\3\2\2\2\u00ca\u00cc\t\26\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd"+
		"\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce>\3\2\2\2\u00cf"+
		"\u00d0\7\60\2\2\u00d0@\3\2\2\2\u00d1\u00d2\7\60\2\2\u00d2\u00d3\7f\2\2"+
		"\u00d3\u00d4\7c\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7c\2\2\u00d6B\3\2\2"+
		"\2\u00d7\u00d8\7$\2\2\u00d8\u00d9\7\60\2\2\u00d9\u00da\7,\2\2\u00da\u00db"+
		"\7$\2\2\u00dbD\3\2\2\2\u00dc\u00dd\7]\2\2\u00ddF\3\2\2\2\u00de\u00df\7"+
		"_\2\2\u00dfH\3\2\2\2\u00e0\u00e2\t\27\2\2\u00e1\u00e3\t\27\2\2\u00e2\u00e1"+
		"\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"J\3\2\2\2\7\2\u00aa\u00c8\u00cd\u00e4\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}