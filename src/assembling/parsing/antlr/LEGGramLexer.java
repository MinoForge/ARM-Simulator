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
		ADD=1, SUB=2, MOV=3, AND=4, ORR=5, MUL=6, UDIV=7, LDUR=8, STUR=9, B=10, 
		BL=11, CBZ=12, CBNZ=13, MADD=14, LSL=15, LSR=16, UBFM=17, SVC=18, LDR=19, 
		BR=20, BLR=21, INT=22, SEPARATOR=23, WS=24, NEG=25, REGISTER=26, COLON=27, 
		HASH=28, EQUALS=29, ENTRY=30, END=31, WORD=32, LCWORD=33, DIRECTIVE=34, 
		DATA=35, TEXT=36, STRING=37, LBRACK=38, RBRACK=39, COMSLASH=40;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ADD", "SUB", "MOV", "AND", "ORR", "MUL", "UDIV", "LDUR", "STUR", "B", 
		"BL", "CBZ", "CBNZ", "MADD", "LSL", "LSR", "UBFM", "SVC", "LDR", "BR", 
		"BLR", "INT", "SEPARATOR", "WS", "NEG", "REGISTER", "COLON", "HASH", "EQUALS", 
		"ENTRY", "END", "WORD", "LCWORD", "DIRECTIVE", "DATA", "TEXT", "STRING", 
		"LBRACK", "RBRACK", "COMSLASH"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u0103\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\6\27\u00c0\n\27\r\27"+
		"\16\27\u00c1\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3"+
		"\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\6!\u00de"+
		"\n!\r!\16!\u00df\3\"\6\"\u00e3\n\"\r\"\16\"\u00e4\3#\3#\3$\3$\3$\3$\3"+
		"$\3$\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3(\3(\3)\3)\6)\u0100\n)"+
		"\r)\16)\u0101\2\2*\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r"+
		"\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33"+
		"\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*\3\2\30\4\2CCcc\4\2FFff\3"+
		"\2\"\"\4\2UUuu\4\2WWww\4\2DDdd\4\2OOoo\4\2QQqq\4\2XXxx\4\2PPpp\4\2TTt"+
		"t\4\2NNnn\4\2KKkk\4\2VVvv\4\2EEee\4\2\\\\||\4\2HHhh\3\2\62;\5\2\13\f\17"+
		"\17\"\"\6\2//C\\aac|\3\2c|\4\2\61\61^^\2\u0106\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2"+
		"\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2"+
		"\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\3S\3\2\2\2\5X\3\2\2\2\7]\3\2\2\2\tb"+
		"\3\2\2\2\13g\3\2\2\2\rl\3\2\2\2\17q\3\2\2\2\21w\3\2\2\2\23}\3\2\2\2\25"+
		"\u0083\3\2\2\2\27\u0086\3\2\2\2\31\u008a\3\2\2\2\33\u008f\3\2\2\2\35\u0095"+
		"\3\2\2\2\37\u009b\3\2\2\2!\u00a0\3\2\2\2#\u00a5\3\2\2\2%\u00ab\3\2\2\2"+
		"\'\u00b0\3\2\2\2)\u00b5\3\2\2\2+\u00b9\3\2\2\2-\u00bf\3\2\2\2/\u00c3\3"+
		"\2\2\2\61\u00c6\3\2\2\2\63\u00c8\3\2\2\2\65\u00ca\3\2\2\2\67\u00cc\3\2"+
		"\2\29\u00ce\3\2\2\2;\u00d0\3\2\2\2=\u00d2\3\2\2\2?\u00d8\3\2\2\2A\u00dd"+
		"\3\2\2\2C\u00e2\3\2\2\2E\u00e6\3\2\2\2G\u00e8\3\2\2\2I\u00ee\3\2\2\2K"+
		"\u00f4\3\2\2\2M\u00f9\3\2\2\2O\u00fb\3\2\2\2Q\u00fd\3\2\2\2ST\t\2\2\2"+
		"TU\t\3\2\2UV\t\3\2\2VW\t\4\2\2W\4\3\2\2\2XY\t\5\2\2YZ\t\6\2\2Z[\t\7\2"+
		"\2[\\\t\4\2\2\\\6\3\2\2\2]^\t\b\2\2^_\t\t\2\2_`\t\n\2\2`a\t\4\2\2a\b\3"+
		"\2\2\2bc\t\2\2\2cd\t\13\2\2de\t\3\2\2ef\t\4\2\2f\n\3\2\2\2gh\t\t\2\2h"+
		"i\t\f\2\2ij\t\f\2\2jk\t\4\2\2k\f\3\2\2\2lm\t\b\2\2mn\t\6\2\2no\t\r\2\2"+
		"op\t\4\2\2p\16\3\2\2\2qr\t\6\2\2rs\t\3\2\2st\t\16\2\2tu\t\n\2\2uv\t\4"+
		"\2\2v\20\3\2\2\2wx\t\r\2\2xy\t\3\2\2yz\t\6\2\2z{\t\f\2\2{|\t\4\2\2|\22"+
		"\3\2\2\2}~\t\5\2\2~\177\t\17\2\2\177\u0080\t\6\2\2\u0080\u0081\t\f\2\2"+
		"\u0081\u0082\t\4\2\2\u0082\24\3\2\2\2\u0083\u0084\t\7\2\2\u0084\u0085"+
		"\t\4\2\2\u0085\26\3\2\2\2\u0086\u0087\t\7\2\2\u0087\u0088\t\r\2\2\u0088"+
		"\u0089\t\4\2\2\u0089\30\3\2\2\2\u008a\u008b\t\20\2\2\u008b\u008c\t\7\2"+
		"\2\u008c\u008d\t\21\2\2\u008d\u008e\t\4\2\2\u008e\32\3\2\2\2\u008f\u0090"+
		"\t\20\2\2\u0090\u0091\t\7\2\2\u0091\u0092\t\13\2\2\u0092\u0093\t\21\2"+
		"\2\u0093\u0094\t\4\2\2\u0094\34\3\2\2\2\u0095\u0096\t\b\2\2\u0096\u0097"+
		"\t\2\2\2\u0097\u0098\t\3\2\2\u0098\u0099\t\3\2\2\u0099\u009a\t\4\2\2\u009a"+
		"\36\3\2\2\2\u009b\u009c\t\r\2\2\u009c\u009d\t\5\2\2\u009d\u009e\t\r\2"+
		"\2\u009e\u009f\t\4\2\2\u009f \3\2\2\2\u00a0\u00a1\t\r\2\2\u00a1\u00a2"+
		"\t\5\2\2\u00a2\u00a3\t\f\2\2\u00a3\u00a4\t\4\2\2\u00a4\"\3\2\2\2\u00a5"+
		"\u00a6\t\6\2\2\u00a6\u00a7\t\7\2\2\u00a7\u00a8\t\22\2\2\u00a8\u00a9\t"+
		"\b\2\2\u00a9\u00aa\t\4\2\2\u00aa$\3\2\2\2\u00ab\u00ac\t\5\2\2\u00ac\u00ad"+
		"\t\n\2\2\u00ad\u00ae\t\20\2\2\u00ae\u00af\t\4\2\2\u00af&\3\2\2\2\u00b0"+
		"\u00b1\t\r\2\2\u00b1\u00b2\t\3\2\2\u00b2\u00b3\t\f\2\2\u00b3\u00b4\t\4"+
		"\2\2\u00b4(\3\2\2\2\u00b5\u00b6\t\7\2\2\u00b6\u00b7\t\f\2\2\u00b7\u00b8"+
		"\t\4\2\2\u00b8*\3\2\2\2\u00b9\u00ba\t\7\2\2\u00ba\u00bb\t\r\2\2\u00bb"+
		"\u00bc\t\f\2\2\u00bc\u00bd\t\4\2\2\u00bd,\3\2\2\2\u00be\u00c0\t\23\2\2"+
		"\u00bf\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2"+
		"\3\2\2\2\u00c2.\3\2\2\2\u00c3\u00c4\7.\2\2\u00c4\u00c5\7\"\2\2\u00c5\60"+
		"\3\2\2\2\u00c6\u00c7\t\24\2\2\u00c7\62\3\2\2\2\u00c8\u00c9\7/\2\2\u00c9"+
		"\64\3\2\2\2\u00ca\u00cb\7t\2\2\u00cb\66\3\2\2\2\u00cc\u00cd\7<\2\2\u00cd"+
		"8\3\2\2\2\u00ce\u00cf\7%\2\2\u00cf:\3\2\2\2\u00d0\u00d1\7?\2\2\u00d1<"+
		"\3\2\2\2\u00d2\u00d3\7G\2\2\u00d3\u00d4\7P\2\2\u00d4\u00d5\7V\2\2\u00d5"+
		"\u00d6\7T\2\2\u00d6\u00d7\7[\2\2\u00d7>\3\2\2\2\u00d8\u00d9\7G\2\2\u00d9"+
		"\u00da\7P\2\2\u00da\u00db\7F\2\2\u00db@\3\2\2\2\u00dc\u00de\t\25\2\2\u00dd"+
		"\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2"+
		"\2\2\u00e0B\3\2\2\2\u00e1\u00e3\t\26\2\2\u00e2\u00e1\3\2\2\2\u00e3\u00e4"+
		"\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5D\3\2\2\2\u00e6"+
		"\u00e7\7\60\2\2\u00e7F\3\2\2\2\u00e8\u00e9\7\60\2\2\u00e9\u00ea\7f\2\2"+
		"\u00ea\u00eb\7c\2\2\u00eb\u00ec\7v\2\2\u00ec\u00ed\7c\2\2\u00edH\3\2\2"+
		"\2\u00ee\u00ef\7\60\2\2\u00ef\u00f0\7v\2\2\u00f0\u00f1\7g\2\2\u00f1\u00f2"+
		"\7z\2\2\u00f2\u00f3\7v\2\2\u00f3J\3\2\2\2\u00f4\u00f5\7$\2\2\u00f5\u00f6"+
		"\7\60\2\2\u00f6\u00f7\7,\2\2\u00f7\u00f8\7$\2\2\u00f8L\3\2\2\2\u00f9\u00fa"+
		"\7]\2\2\u00faN\3\2\2\2\u00fb\u00fc\7_\2\2\u00fcP\3\2\2\2\u00fd\u00ff\t"+
		"\27\2\2\u00fe\u0100\t\27\2\2\u00ff\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101"+
		"\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102R\3\2\2\2\7\2\u00c1\u00df\u00e4"+
		"\u0101\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}