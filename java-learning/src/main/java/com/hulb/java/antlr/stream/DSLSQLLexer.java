// Generated from DSLSQL.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DSLSQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, COMMENT=17, 
		STRING=18, IDENTIFIER=19, BACKQUOTED_IDENTIFIER=20, SIMPLE_COMMENT=21, 
		BRACKETED_EMPTY_COMMENT=22, BRACKETED_COMMENT=23, WS=24, UNRECOGNIZED=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "COMMENT", 
		"STRING", "IDENTIFIER", "BACKQUOTED_IDENTIFIER", "DIGIT", "LETTER", "SIMPLE_COMMENT", 
		"BRACKETED_EMPTY_COMMENT", "BRACKETED_COMMENT", "WS", "UNRECOGNIZED"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'option'", "'select'", "'SELECT'", "'create'", "'CREATE'", 
		"'source'", "'SOURCE'", "'sink'", "'SINK'", "'stream'", "'STREAM'", "','", 
		"'='", "';'", "'COMMENT'", null, null, null, null, "'/**/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "COMMENT", "STRING", "IDENTIFIER", "BACKQUOTED_IDENTIFIER", 
		"SIMPLE_COMMENT", "BRACKETED_EMPTY_COMMENT", "BRACKETED_COMMENT", "WS", 
		"UNRECOGNIZED"
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


	public DSLSQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DSLSQL.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00f0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\7\23\u0099\n\23\f\23\16\23\u009c\13\23\3"+
		"\23\3\23\3\23\3\23\3\23\7\23\u00a3\n\23\f\23\16\23\u00a6\13\23\3\23\5"+
		"\23\u00a9\n\23\3\24\3\24\3\24\6\24\u00ae\n\24\r\24\16\24\u00af\3\25\3"+
		"\25\3\25\3\25\7\25\u00b6\n\25\f\25\16\25\u00b9\13\25\3\25\3\25\3\26\3"+
		"\26\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u00c5\n\30\f\30\16\30\u00c8\13"+
		"\30\3\30\5\30\u00cb\n\30\3\30\5\30\u00ce\n\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\7\32\u00de\n\32\f\32\16"+
		"\32\u00e1\13\32\3\32\3\32\3\32\3\32\3\32\3\33\6\33\u00e9\n\33\r\33\16"+
		"\33\u00ea\3\33\3\33\3\34\3\34\3\u00df\2\35\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\2-\2/\27\61\30\63\31\65\32\67\33\3\2\n\4\2))^^\4\2$$^^\3\2bb\3\2\62;"+
		"\4\2C\\c|\4\2\f\f\17\17\3\2--\5\2\13\f\17\17\"\"\2\u00fc\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2"+
		"\2\2\2\67\3\2\2\2\39\3\2\2\2\5;\3\2\2\2\7=\3\2\2\2\tD\3\2\2\2\13K\3\2"+
		"\2\2\rR\3\2\2\2\17Y\3\2\2\2\21`\3\2\2\2\23g\3\2\2\2\25n\3\2\2\2\27s\3"+
		"\2\2\2\31x\3\2\2\2\33\177\3\2\2\2\35\u0086\3\2\2\2\37\u0088\3\2\2\2!\u008a"+
		"\3\2\2\2#\u008c\3\2\2\2%\u00a8\3\2\2\2\'\u00ad\3\2\2\2)\u00b1\3\2\2\2"+
		"+\u00bc\3\2\2\2-\u00be\3\2\2\2/\u00c0\3\2\2\2\61\u00d1\3\2\2\2\63\u00d8"+
		"\3\2\2\2\65\u00e8\3\2\2\2\67\u00ee\3\2\2\29:\7*\2\2:\4\3\2\2\2;<\7+\2"+
		"\2<\6\3\2\2\2=>\7q\2\2>?\7r\2\2?@\7v\2\2@A\7k\2\2AB\7q\2\2BC\7p\2\2C\b"+
		"\3\2\2\2DE\7u\2\2EF\7g\2\2FG\7n\2\2GH\7g\2\2HI\7e\2\2IJ\7v\2\2J\n\3\2"+
		"\2\2KL\7U\2\2LM\7G\2\2MN\7N\2\2NO\7G\2\2OP\7E\2\2PQ\7V\2\2Q\f\3\2\2\2"+
		"RS\7e\2\2ST\7t\2\2TU\7g\2\2UV\7c\2\2VW\7v\2\2WX\7g\2\2X\16\3\2\2\2YZ\7"+
		"E\2\2Z[\7T\2\2[\\\7G\2\2\\]\7C\2\2]^\7V\2\2^_\7G\2\2_\20\3\2\2\2`a\7u"+
		"\2\2ab\7q\2\2bc\7w\2\2cd\7t\2\2de\7e\2\2ef\7g\2\2f\22\3\2\2\2gh\7U\2\2"+
		"hi\7Q\2\2ij\7W\2\2jk\7T\2\2kl\7E\2\2lm\7G\2\2m\24\3\2\2\2no\7u\2\2op\7"+
		"k\2\2pq\7p\2\2qr\7m\2\2r\26\3\2\2\2st\7U\2\2tu\7K\2\2uv\7P\2\2vw\7M\2"+
		"\2w\30\3\2\2\2xy\7u\2\2yz\7v\2\2z{\7t\2\2{|\7g\2\2|}\7c\2\2}~\7o\2\2~"+
		"\32\3\2\2\2\177\u0080\7U\2\2\u0080\u0081\7V\2\2\u0081\u0082\7T\2\2\u0082"+
		"\u0083\7G\2\2\u0083\u0084\7C\2\2\u0084\u0085\7O\2\2\u0085\34\3\2\2\2\u0086"+
		"\u0087\7.\2\2\u0087\36\3\2\2\2\u0088\u0089\7?\2\2\u0089 \3\2\2\2\u008a"+
		"\u008b\7=\2\2\u008b\"\3\2\2\2\u008c\u008d\7E\2\2\u008d\u008e\7Q\2\2\u008e"+
		"\u008f\7O\2\2\u008f\u0090\7O\2\2\u0090\u0091\7G\2\2\u0091\u0092\7P\2\2"+
		"\u0092\u0093\7V\2\2\u0093$\3\2\2\2\u0094\u009a\7)\2\2\u0095\u0099\n\2"+
		"\2\2\u0096\u0097\7^\2\2\u0097\u0099\13\2\2\2\u0098\u0095\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u00a9\7)\2\2\u009e"+
		"\u00a4\7$\2\2\u009f\u00a3\n\3\2\2\u00a0\u00a1\7^\2\2\u00a1\u00a3\13\2"+
		"\2\2\u00a2\u009f\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4"+
		"\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2"+
		"\2\2\u00a7\u00a9\7$\2\2\u00a8\u0094\3\2\2\2\u00a8\u009e\3\2\2\2\u00a9"+
		"&\3\2\2\2\u00aa\u00ae\5-\27\2\u00ab\u00ae\5+\26\2\u00ac\u00ae\7a\2\2\u00ad"+
		"\u00aa\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2"+
		"\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0(\3\2\2\2\u00b1\u00b7"+
		"\7b\2\2\u00b2\u00b6\n\4\2\2\u00b3\u00b4\7b\2\2\u00b4\u00b6\7b\2\2\u00b5"+
		"\u00b2\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2"+
		"\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba"+
		"\u00bb\7b\2\2\u00bb*\3\2\2\2\u00bc\u00bd\t\5\2\2\u00bd,\3\2\2\2\u00be"+
		"\u00bf\t\6\2\2\u00bf.\3\2\2\2\u00c0\u00c1\7/\2\2\u00c1\u00c2\7/\2\2\u00c2"+
		"\u00c6\3\2\2\2\u00c3\u00c5\n\7\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2"+
		"\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00cb\7\17\2\2\u00ca\u00c9\3\2\2\2\u00ca\u00cb\3"+
		"\2\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00ce\7\f\2\2\u00cd\u00cc\3\2\2\2\u00cd"+
		"\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\b\30\2\2\u00d0\60\3\2\2"+
		"\2\u00d1\u00d2\7\61\2\2\u00d2\u00d3\7,\2\2\u00d3\u00d4\7,\2\2\u00d4\u00d5"+
		"\7\61\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\b\31\2\2\u00d7\62\3\2\2\2\u00d8"+
		"\u00d9\7\61\2\2\u00d9\u00da\7,\2\2\u00da\u00db\3\2\2\2\u00db\u00df\n\b"+
		"\2\2\u00dc\u00de\13\2\2\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00df\3\2"+
		"\2\2\u00e2\u00e3\7,\2\2\u00e3\u00e4\7\61\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e6\b\32\2\2\u00e6\64\3\2\2\2\u00e7\u00e9\t\t\2\2\u00e8\u00e7\3\2\2"+
		"\2\u00e9\u00ea\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec"+
		"\3\2\2\2\u00ec\u00ed\b\33\2\2\u00ed\66\3\2\2\2\u00ee\u00ef\13\2\2\2\u00ef"+
		"8\3\2\2\2\21\2\u0098\u009a\u00a2\u00a4\u00a8\u00ad\u00af\u00b5\u00b7\u00c6"+
		"\u00ca\u00cd\u00df\u00ea\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}