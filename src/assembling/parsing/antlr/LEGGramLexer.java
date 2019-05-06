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
		ENTRY=27, END=28, WORD=29, LCWORD=30, DIRECTIVE=31, DATA=32, TEXT=33, 
		STRING=34, LBRACK=35, RBRACK=36, COMSLASH=37;
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
		"LCWORD", "DIRECTIVE", "DATA", "TEXT", "STRING", "LBRACK", "RBRACK", "COMSLASH"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "', '", null, "'-'", "'r'", 
		"':'", "'#'", "'='", "'ENTRY'", "'END'", null, null, "'.'", "'.data'", 
		"'.text'", "'\".*\"'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "ADD", "SUB", "MOV", "AND", "ORR", "MUL", "DIV", "LDUR", "STUR", 
		"B", "BL", "CBZ", "CBNZ", "MADD", "LSL", "LSR", "UBFM", "SVC", "INT", 
		"SEPARATOR", "WS", "NEG", "REGISTER", "COLON", "HASH", "EQUALS", "ENTRY", 
		"END", "WORD", "LCWORD", "DIRECTIVE", "DATA", "TEXT", "STRING", "LBRACK", 
		"RBRACK", "COMSLASH"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\'\u00ee\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\6\24\u00ab\n\24\r\24\16\24\u00ac\3\25\3\25\3"+
		"\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\6\36\u00c9\n\36\r\36"+
		"\16\36\u00ca\3\37\6\37\u00ce\n\37\r\37\16\37\u00cf\3 \3 \3!\3!\3!\3!\3"+
		"!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3%\3%\3&\3&\6&\u00eb"+
		"\n&\r&\16&\u00ec\2\2\'\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27"+
		"\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'\3\2\30\4\2CCcc\4\2FFff\3\2\"\""+
		"\4\2UUuu\4\2WWww\4\2DDdd\4\2OOoo\4\2QQqq\4\2XXxx\4\2PPpp\4\2TTtt\4\2N"+
		"Nnn\4\2KKkk\4\2VVvv\4\2EEee\4\2\\\\||\4\2HHhh\3\2\62;\5\2\13\f\17\17\""+
		"\"\5\2//C\\c|\3\2c|\4\2\61\61^^\2\u00f1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2"+
		"\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35"+
		"\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2"+
		"\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2"+
		"A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\3M\3"+
		"\2\2\2\5R\3\2\2\2\7W\3\2\2\2\t\\\3\2\2\2\13a\3\2\2\2\rf\3\2\2\2\17k\3"+
		"\2\2\2\21p\3\2\2\2\23v\3\2\2\2\25|\3\2\2\2\27\177\3\2\2\2\31\u0083\3\2"+
		"\2\2\33\u0088\3\2\2\2\35\u008e\3\2\2\2\37\u0094\3\2\2\2!\u0099\3\2\2\2"+
		"#\u009e\3\2\2\2%\u00a4\3\2\2\2\'\u00aa\3\2\2\2)\u00ae\3\2\2\2+\u00b1\3"+
		"\2\2\2-\u00b3\3\2\2\2/\u00b5\3\2\2\2\61\u00b7\3\2\2\2\63\u00b9\3\2\2\2"+
		"\65\u00bb\3\2\2\2\67\u00bd\3\2\2\29\u00c3\3\2\2\2;\u00c8\3\2\2\2=\u00cd"+
		"\3\2\2\2?\u00d1\3\2\2\2A\u00d3\3\2\2\2C\u00d9\3\2\2\2E\u00df\3\2\2\2G"+
		"\u00e4\3\2\2\2I\u00e6\3\2\2\2K\u00e8\3\2\2\2MN\t\2\2\2NO\t\3\2\2OP\t\3"+
		"\2\2PQ\t\4\2\2Q\4\3\2\2\2RS\t\5\2\2ST\t\6\2\2TU\t\7\2\2UV\t\4\2\2V\6\3"+
		"\2\2\2WX\t\b\2\2XY\t\t\2\2YZ\t\n\2\2Z[\t\4\2\2[\b\3\2\2\2\\]\t\2\2\2]"+
		"^\t\13\2\2^_\t\3\2\2_`\t\4\2\2`\n\3\2\2\2ab\t\t\2\2bc\t\f\2\2cd\t\f\2"+
		"\2de\t\4\2\2e\f\3\2\2\2fg\t\b\2\2gh\t\6\2\2hi\t\r\2\2ij\t\4\2\2j\16\3"+
		"\2\2\2kl\t\3\2\2lm\t\16\2\2mn\t\n\2\2no\t\4\2\2o\20\3\2\2\2pq\t\r\2\2"+
		"qr\t\3\2\2rs\t\6\2\2st\t\f\2\2tu\t\4\2\2u\22\3\2\2\2vw\t\5\2\2wx\t\17"+
		"\2\2xy\t\6\2\2yz\t\f\2\2z{\t\4\2\2{\24\3\2\2\2|}\t\7\2\2}~\t\4\2\2~\26"+
		"\3\2\2\2\177\u0080\t\7\2\2\u0080\u0081\t\r\2\2\u0081\u0082\t\4\2\2\u0082"+
		"\30\3\2\2\2\u0083\u0084\t\20\2\2\u0084\u0085\t\7\2\2\u0085\u0086\t\21"+
		"\2\2\u0086\u0087\t\4\2\2\u0087\32\3\2\2\2\u0088\u0089\t\20\2\2\u0089\u008a"+
		"\t\7\2\2\u008a\u008b\t\13\2\2\u008b\u008c\t\21\2\2\u008c\u008d\t\4\2\2"+
		"\u008d\34\3\2\2\2\u008e\u008f\t\b\2\2\u008f\u0090\t\2\2\2\u0090\u0091"+
		"\t\3\2\2\u0091\u0092\t\3\2\2\u0092\u0093\t\4\2\2\u0093\36\3\2\2\2\u0094"+
		"\u0095\t\r\2\2\u0095\u0096\t\5\2\2\u0096\u0097\t\r\2\2\u0097\u0098\t\4"+
		"\2\2\u0098 \3\2\2\2\u0099\u009a\t\r\2\2\u009a\u009b\t\5\2\2\u009b\u009c"+
		"\t\f\2\2\u009c\u009d\t\4\2\2\u009d\"\3\2\2\2\u009e\u009f\t\6\2\2\u009f"+
		"\u00a0\t\7\2\2\u00a0\u00a1\t\22\2\2\u00a1\u00a2\t\b\2\2\u00a2\u00a3\t"+
		"\4\2\2\u00a3$\3\2\2\2\u00a4\u00a5\t\5\2\2\u00a5\u00a6\t\n\2\2\u00a6\u00a7"+
		"\t\20\2\2\u00a7\u00a8\t\4\2\2\u00a8&\3\2\2\2\u00a9\u00ab\t\23\2\2\u00aa"+
		"\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2"+
		"\2\2\u00ad(\3\2\2\2\u00ae\u00af\7.\2\2\u00af\u00b0\7\"\2\2\u00b0*\3\2"+
		"\2\2\u00b1\u00b2\t\24\2\2\u00b2,\3\2\2\2\u00b3\u00b4\7/\2\2\u00b4.\3\2"+
		"\2\2\u00b5\u00b6\7t\2\2\u00b6\60\3\2\2\2\u00b7\u00b8\7<\2\2\u00b8\62\3"+
		"\2\2\2\u00b9\u00ba\7%\2\2\u00ba\64\3\2\2\2\u00bb\u00bc\7?\2\2\u00bc\66"+
		"\3\2\2\2\u00bd\u00be\7G\2\2\u00be\u00bf\7P\2\2\u00bf\u00c0\7V\2\2\u00c0"+
		"\u00c1\7T\2\2\u00c1\u00c2\7[\2\2\u00c28\3\2\2\2\u00c3\u00c4\7G\2\2\u00c4"+
		"\u00c5\7P\2\2\u00c5\u00c6\7F\2\2\u00c6:\3\2\2\2\u00c7\u00c9\t\25\2\2\u00c8"+
		"\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2"+
		"\2\2\u00cb<\3\2\2\2\u00cc\u00ce\t\26\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0>\3\2\2\2\u00d1"+
		"\u00d2\7\60\2\2\u00d2@\3\2\2\2\u00d3\u00d4\7\60\2\2\u00d4\u00d5\7f\2\2"+
		"\u00d5\u00d6\7c\2\2\u00d6\u00d7\7v\2\2\u00d7\u00d8\7c\2\2\u00d8B\3\2\2"+
		"\2\u00d9\u00da\7\60\2\2\u00da\u00db\7v\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd"+
		"\7z\2\2\u00dd\u00de\7v\2\2\u00deD\3\2\2\2\u00df\u00e0\7$\2\2\u00e0\u00e1"+
		"\7\60\2\2\u00e1\u00e2\7,\2\2\u00e2\u00e3\7$\2\2\u00e3F\3\2\2\2\u00e4\u00e5"+
		"\7]\2\2\u00e5H\3\2\2\2\u00e6\u00e7\7_\2\2\u00e7J\3\2\2\2\u00e8\u00ea\t"+
		"\27\2\2\u00e9\u00eb\t\27\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00edL\3\2\2\2\7\2\u00ac\u00ca\u00cf"+
		"\u00ec\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}