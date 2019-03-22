package assembling.parsing.antlr;

// Generated from LEGGram.g4 by antlr 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LEGGramLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ADD=1, SUB=2, MOV=3, AND=4, OR=5, INT=6, SEPARATOR=7, WS=8, NEG=9, REGISTER=10, 
		COLON=11, HASH=12, EQUALS=13, ENTRY=14, END=15, WORD=16, LCWORD=17, DIRECTIVE=18, 
		DATA=19, STRING=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"ADD", "SUB", "MOV", "AND", "OR", "INT", "SEPARATOR", "WS", "NEG", "REGISTER", 
		"COLON", "HASH", "EQUALS", "ENTRY", "END", "WORD", "LCWORD", "DIRECTIVE", 
		"DATA", "STRING"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26x\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\6\7E\n\7"+
		"\r\7\16\7F\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\6\21c\n\21"+
		"\r\21\16\21d\3\22\6\22h\n\22\r\22\16\22i\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26\3"+
		"\2\t\4\2CCcc\4\2FFff\3\2\"\"\3\2\62;\5\2\13\f\17\17\"\"\4\2C\\c|\3\2c"+
		"|\2z\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r"+
		"\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2"+
		"\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2"+
		"#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5\60\3\2\2\2\7"+
		"\65\3\2\2\2\t:\3\2\2\2\13?\3\2\2\2\rD\3\2\2\2\17H\3\2\2\2\21K\3\2\2\2"+
		"\23M\3\2\2\2\25O\3\2\2\2\27Q\3\2\2\2\31S\3\2\2\2\33U\3\2\2\2\35W\3\2\2"+
		"\2\37]\3\2\2\2!b\3\2\2\2#g\3\2\2\2%k\3\2\2\2\'m\3\2\2\2)s\3\2\2\2+,\t"+
		"\2\2\2,-\t\3\2\2-.\t\3\2\2./\t\4\2\2/\4\3\2\2\2\60\61\7U\2\2\61\62\7W"+
		"\2\2\62\63\7D\2\2\63\64\7\"\2\2\64\6\3\2\2\2\65\66\7O\2\2\66\67\7Q\2\2"+
		"\678\7X\2\289\7\"\2\29\b\3\2\2\2:;\7C\2\2;<\7P\2\2<=\7F\2\2=>\7\"\2\2"+
		">\n\3\2\2\2?@\7Q\2\2@A\7T\2\2AB\7\"\2\2B\f\3\2\2\2CE\t\5\2\2DC\3\2\2\2"+
		"EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\16\3\2\2\2HI\7.\2\2IJ\7\"\2\2J\20\3\2"+
		"\2\2KL\t\6\2\2L\22\3\2\2\2MN\7/\2\2N\24\3\2\2\2OP\7t\2\2P\26\3\2\2\2Q"+
		"R\7<\2\2R\30\3\2\2\2ST\7%\2\2T\32\3\2\2\2UV\7?\2\2V\34\3\2\2\2WX\7G\2"+
		"\2XY\7P\2\2YZ\7V\2\2Z[\7T\2\2[\\\7[\2\2\\\36\3\2\2\2]^\7G\2\2^_\7P\2\2"+
		"_`\7F\2\2` \3\2\2\2ac\t\7\2\2ba\3\2\2\2cd\3\2\2\2db\3\2\2\2de\3\2\2\2"+
		"e\"\3\2\2\2fh\t\b\2\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2j$\3\2\2"+
		"\2kl\7\60\2\2l&\3\2\2\2mn\7\60\2\2no\7f\2\2op\7c\2\2pq\7v\2\2qr\7c\2\2"+
		"r(\3\2\2\2st\7$\2\2tu\7\60\2\2uv\7,\2\2vw\7$\2\2w*\3\2\2\2\6\2Fdi\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}