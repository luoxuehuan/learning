// Generated from DSLSQL.g4 by ANTLR 4.7.1

package streaming.dsl.parser;

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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, COMMENT=36, STRING=37, IDENTIFIER=38, 
		BACKQUOTED_IDENTIFIER=39, SIMPLE_COMMENT=40, BRACKETED_EMPTY_COMMENT=41, 
		BRACKETED_COMMENT=42, WS=43, UNRECOGNIZED=44;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
		"T__33", "T__34", "COMMENT", "STRING", "IDENTIFIER", "BACKQUOTED_IDENTIFIER", 
		"DIGIT", "LETTER", "SIMPLE_COMMENT", "BRACKETED_EMPTY_COMMENT", "BRACKETED_COMMENT", 
		"WS", "UNRECOGNIZED"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'load'", "'LOAD'", "'.'", "'options'", "'as'", "'save'", "'SAVE'", 
		"'partitionBy'", "'select'", "'SELECT'", "';'", "'insert'", "'INSERT'", 
		"'create'", "'CREATE'", "'set'", "'SET'", "'='", "'connect'", "'CONNECT'", 
		"'('", "')'", "'where'", "'source'", "'SOURCE'", "'train'", "'TRAIN'", 
		"'register'", "'REGISTER'", "','", "'overwrite'", "'append'", "'errorIfExists'", 
		"'ignore'", "'and'", "'COMMENT'", null, null, null, null, "'/**/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"COMMENT", "STRING", "IDENTIFIER", "BACKQUOTED_IDENTIFIER", "SIMPLE_COMMENT", 
		"BRACKETED_EMPTY_COMMENT", "BRACKETED_COMMENT", "WS", "UNRECOGNIZED"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2.\u0198\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3"+
		"%\3%\3&\3&\3&\3&\7&\u0141\n&\f&\16&\u0144\13&\3&\3&\3&\3&\3&\7&\u014b"+
		"\n&\f&\16&\u014e\13&\3&\5&\u0151\n&\3\'\3\'\3\'\6\'\u0156\n\'\r\'\16\'"+
		"\u0157\3(\3(\3(\3(\7(\u015e\n(\f(\16(\u0161\13(\3(\3(\3)\3)\3*\3*\3+\3"+
		"+\3+\3+\7+\u016d\n+\f+\16+\u0170\13+\3+\5+\u0173\n+\3+\5+\u0176\n+\3+"+
		"\3+\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\7-\u0186\n-\f-\16-\u0189\13-\3"+
		"-\3-\3-\3-\3-\3.\6.\u0191\n.\r.\16.\u0192\3.\3.\3/\3/\3\u0187\2\60\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q\2S\2U*W+Y,[-].\3\2\n\4\2))^^\4\2$$^^\3\2bb\3\2\62"+
		";\4\2C\\c|\4\2\f\f\17\17\3\2--\5\2\13\f\17\17\"\"\2\u01a4\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2["+
		"\3\2\2\2\2]\3\2\2\2\3_\3\2\2\2\5d\3\2\2\2\7i\3\2\2\2\tk\3\2\2\2\13s\3"+
		"\2\2\2\rv\3\2\2\2\17{\3\2\2\2\21\u0080\3\2\2\2\23\u008c\3\2\2\2\25\u0093"+
		"\3\2\2\2\27\u009a\3\2\2\2\31\u009c\3\2\2\2\33\u00a3\3\2\2\2\35\u00aa\3"+
		"\2\2\2\37\u00b1\3\2\2\2!\u00b8\3\2\2\2#\u00bc\3\2\2\2%\u00c0\3\2\2\2\'"+
		"\u00c2\3\2\2\2)\u00ca\3\2\2\2+\u00d2\3\2\2\2-\u00d4\3\2\2\2/\u00d6\3\2"+
		"\2\2\61\u00dc\3\2\2\2\63\u00e3\3\2\2\2\65\u00ea\3\2\2\2\67\u00f0\3\2\2"+
		"\29\u00f6\3\2\2\2;\u00ff\3\2\2\2=\u0108\3\2\2\2?\u010a\3\2\2\2A\u0114"+
		"\3\2\2\2C\u011b\3\2\2\2E\u0129\3\2\2\2G\u0130\3\2\2\2I\u0134\3\2\2\2K"+
		"\u0150\3\2\2\2M\u0155\3\2\2\2O\u0159\3\2\2\2Q\u0164\3\2\2\2S\u0166\3\2"+
		"\2\2U\u0168\3\2\2\2W\u0179\3\2\2\2Y\u0180\3\2\2\2[\u0190\3\2\2\2]\u0196"+
		"\3\2\2\2_`\7n\2\2`a\7q\2\2ab\7c\2\2bc\7f\2\2c\4\3\2\2\2de\7N\2\2ef\7Q"+
		"\2\2fg\7C\2\2gh\7F\2\2h\6\3\2\2\2ij\7\60\2\2j\b\3\2\2\2kl\7q\2\2lm\7r"+
		"\2\2mn\7v\2\2no\7k\2\2op\7q\2\2pq\7p\2\2qr\7u\2\2r\n\3\2\2\2st\7c\2\2"+
		"tu\7u\2\2u\f\3\2\2\2vw\7u\2\2wx\7c\2\2xy\7x\2\2yz\7g\2\2z\16\3\2\2\2{"+
		"|\7U\2\2|}\7C\2\2}~\7X\2\2~\177\7G\2\2\177\20\3\2\2\2\u0080\u0081\7r\2"+
		"\2\u0081\u0082\7c\2\2\u0082\u0083\7t\2\2\u0083\u0084\7v\2\2\u0084\u0085"+
		"\7k\2\2\u0085\u0086\7v\2\2\u0086\u0087\7k\2\2\u0087\u0088\7q\2\2\u0088"+
		"\u0089\7p\2\2\u0089\u008a\7D\2\2\u008a\u008b\7{\2\2\u008b\22\3\2\2\2\u008c"+
		"\u008d\7u\2\2\u008d\u008e\7g\2\2\u008e\u008f\7n\2\2\u008f\u0090\7g\2\2"+
		"\u0090\u0091\7e\2\2\u0091\u0092\7v\2\2\u0092\24\3\2\2\2\u0093\u0094\7"+
		"U\2\2\u0094\u0095\7G\2\2\u0095\u0096\7N\2\2\u0096\u0097\7G\2\2\u0097\u0098"+
		"\7E\2\2\u0098\u0099\7V\2\2\u0099\26\3\2\2\2\u009a\u009b\7=\2\2\u009b\30"+
		"\3\2\2\2\u009c\u009d\7k\2\2\u009d\u009e\7p\2\2\u009e\u009f\7u\2\2\u009f"+
		"\u00a0\7g\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7v\2\2\u00a2\32\3\2\2\2\u00a3"+
		"\u00a4\7K\2\2\u00a4\u00a5\7P\2\2\u00a5\u00a6\7U\2\2\u00a6\u00a7\7G\2\2"+
		"\u00a7\u00a8\7T\2\2\u00a8\u00a9\7V\2\2\u00a9\34\3\2\2\2\u00aa\u00ab\7"+
		"e\2\2\u00ab\u00ac\7t\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7c\2\2\u00ae\u00af"+
		"\7v\2\2\u00af\u00b0\7g\2\2\u00b0\36\3\2\2\2\u00b1\u00b2\7E\2\2\u00b2\u00b3"+
		"\7T\2\2\u00b3\u00b4\7G\2\2\u00b4\u00b5\7C\2\2\u00b5\u00b6\7V\2\2\u00b6"+
		"\u00b7\7G\2\2\u00b7 \3\2\2\2\u00b8\u00b9\7u\2\2\u00b9\u00ba\7g\2\2\u00ba"+
		"\u00bb\7v\2\2\u00bb\"\3\2\2\2\u00bc\u00bd\7U\2\2\u00bd\u00be\7G\2\2\u00be"+
		"\u00bf\7V\2\2\u00bf$\3\2\2\2\u00c0\u00c1\7?\2\2\u00c1&\3\2\2\2\u00c2\u00c3"+
		"\7e\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5\7p\2\2\u00c5\u00c6\7p\2\2\u00c6"+
		"\u00c7\7g\2\2\u00c7\u00c8\7e\2\2\u00c8\u00c9\7v\2\2\u00c9(\3\2\2\2\u00ca"+
		"\u00cb\7E\2\2\u00cb\u00cc\7Q\2\2\u00cc\u00cd\7P\2\2\u00cd\u00ce\7P\2\2"+
		"\u00ce\u00cf\7G\2\2\u00cf\u00d0\7E\2\2\u00d0\u00d1\7V\2\2\u00d1*\3\2\2"+
		"\2\u00d2\u00d3\7*\2\2\u00d3,\3\2\2\2\u00d4\u00d5\7+\2\2\u00d5.\3\2\2\2"+
		"\u00d6\u00d7\7y\2\2\u00d7\u00d8\7j\2\2\u00d8\u00d9\7g\2\2\u00d9\u00da"+
		"\7t\2\2\u00da\u00db\7g\2\2\u00db\60\3\2\2\2\u00dc\u00dd\7u\2\2\u00dd\u00de"+
		"\7q\2\2\u00de\u00df\7w\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7e\2\2\u00e1"+
		"\u00e2\7g\2\2\u00e2\62\3\2\2\2\u00e3\u00e4\7U\2\2\u00e4\u00e5\7Q\2\2\u00e5"+
		"\u00e6\7W\2\2\u00e6\u00e7\7T\2\2\u00e7\u00e8\7E\2\2\u00e8\u00e9\7G\2\2"+
		"\u00e9\64\3\2\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\7"+
		"c\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef\7p\2\2\u00ef\66\3\2\2\2\u00f0\u00f1"+
		"\7V\2\2\u00f1\u00f2\7T\2\2\u00f2\u00f3\7C\2\2\u00f3\u00f4\7K\2\2\u00f4"+
		"\u00f5\7P\2\2\u00f58\3\2\2\2\u00f6\u00f7\7t\2\2\u00f7\u00f8\7g\2\2\u00f8"+
		"\u00f9\7i\2\2\u00f9\u00fa\7k\2\2\u00fa\u00fb\7u\2\2\u00fb\u00fc\7v\2\2"+
		"\u00fc\u00fd\7g\2\2\u00fd\u00fe\7t\2\2\u00fe:\3\2\2\2\u00ff\u0100\7T\2"+
		"\2\u0100\u0101\7G\2\2\u0101\u0102\7I\2\2\u0102\u0103\7K\2\2\u0103\u0104"+
		"\7U\2\2\u0104\u0105\7V\2\2\u0105\u0106\7G\2\2\u0106\u0107\7T\2\2\u0107"+
		"<\3\2\2\2\u0108\u0109\7.\2\2\u0109>\3\2\2\2\u010a\u010b\7q\2\2\u010b\u010c"+
		"\7x\2\2\u010c\u010d\7g\2\2\u010d\u010e\7t\2\2\u010e\u010f\7y\2\2\u010f"+
		"\u0110\7t\2\2\u0110\u0111\7k\2\2\u0111\u0112\7v\2\2\u0112\u0113\7g\2\2"+
		"\u0113@\3\2\2\2\u0114\u0115\7c\2\2\u0115\u0116\7r\2\2\u0116\u0117\7r\2"+
		"\2\u0117\u0118\7g\2\2\u0118\u0119\7p\2\2\u0119\u011a\7f\2\2\u011aB\3\2"+
		"\2\2\u011b\u011c\7g\2\2\u011c\u011d\7t\2\2\u011d\u011e\7t\2\2\u011e\u011f"+
		"\7q\2\2\u011f\u0120\7t\2\2\u0120\u0121\7K\2\2\u0121\u0122\7h\2\2\u0122"+
		"\u0123\7G\2\2\u0123\u0124\7z\2\2\u0124\u0125\7k\2\2\u0125\u0126\7u\2\2"+
		"\u0126\u0127\7v\2\2\u0127\u0128\7u\2\2\u0128D\3\2\2\2\u0129\u012a\7k\2"+
		"\2\u012a\u012b\7i\2\2\u012b\u012c\7p\2\2\u012c\u012d\7q\2\2\u012d\u012e"+
		"\7t\2\2\u012e\u012f\7g\2\2\u012fF\3\2\2\2\u0130\u0131\7c\2\2\u0131\u0132"+
		"\7p\2\2\u0132\u0133\7f\2\2\u0133H\3\2\2\2\u0134\u0135\7E\2\2\u0135\u0136"+
		"\7Q\2\2\u0136\u0137\7O\2\2\u0137\u0138\7O\2\2\u0138\u0139\7G\2\2\u0139"+
		"\u013a\7P\2\2\u013a\u013b\7V\2\2\u013bJ\3\2\2\2\u013c\u0142\7)\2\2\u013d"+
		"\u0141\n\2\2\2\u013e\u013f\7^\2\2\u013f\u0141\13\2\2\2\u0140\u013d\3\2"+
		"\2\2\u0140\u013e\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142"+
		"\u0143\3\2\2\2\u0143\u0145\3\2\2\2\u0144\u0142\3\2\2\2\u0145\u0151\7)"+
		"\2\2\u0146\u014c\7$\2\2\u0147\u014b\n\3\2\2\u0148\u0149\7^\2\2\u0149\u014b"+
		"\13\2\2\2\u014a\u0147\3\2\2\2\u014a\u0148\3\2\2\2\u014b\u014e\3\2\2\2"+
		"\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d\u014f\3\2\2\2\u014e\u014c"+
		"\3\2\2\2\u014f\u0151\7$\2\2\u0150\u013c\3\2\2\2\u0150\u0146\3\2\2\2\u0151"+
		"L\3\2\2\2\u0152\u0156\5S*\2\u0153\u0156\5Q)\2\u0154\u0156\7a\2\2\u0155"+
		"\u0152\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0154\3\2\2\2\u0156\u0157\3\2"+
		"\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158N\3\2\2\2\u0159\u015f"+
		"\7b\2\2\u015a\u015e\n\4\2\2\u015b\u015c\7b\2\2\u015c\u015e\7b\2\2\u015d"+
		"\u015a\3\2\2\2\u015d\u015b\3\2\2\2\u015e\u0161\3\2\2\2\u015f\u015d\3\2"+
		"\2\2\u015f\u0160\3\2\2\2\u0160\u0162\3\2\2\2\u0161\u015f\3\2\2\2\u0162"+
		"\u0163\7b\2\2\u0163P\3\2\2\2\u0164\u0165\t\5\2\2\u0165R\3\2\2\2\u0166"+
		"\u0167\t\6\2\2\u0167T\3\2\2\2\u0168\u0169\7/\2\2\u0169\u016a\7/\2\2\u016a"+
		"\u016e\3\2\2\2\u016b\u016d\n\7\2\2\u016c\u016b\3\2\2\2\u016d\u0170\3\2"+
		"\2\2\u016e\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0172\3\2\2\2\u0170"+
		"\u016e\3\2\2\2\u0171\u0173\7\17\2\2\u0172\u0171\3\2\2\2\u0172\u0173\3"+
		"\2\2\2\u0173\u0175\3\2\2\2\u0174\u0176\7\f\2\2\u0175\u0174\3\2\2\2\u0175"+
		"\u0176\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0178\b+\2\2\u0178V\3\2\2\2\u0179"+
		"\u017a\7\61\2\2\u017a\u017b\7,\2\2\u017b\u017c\7,\2\2\u017c\u017d\7\61"+
		"\2\2\u017d\u017e\3\2\2\2\u017e\u017f\b,\2\2\u017fX\3\2\2\2\u0180\u0181"+
		"\7\61\2\2\u0181\u0182\7,\2\2\u0182\u0183\3\2\2\2\u0183\u0187\n\b\2\2\u0184"+
		"\u0186\13\2\2\2\u0185\u0184\3\2\2\2\u0186\u0189\3\2\2\2\u0187\u0188\3"+
		"\2\2\2\u0187\u0185\3\2\2\2\u0188\u018a\3\2\2\2\u0189\u0187\3\2\2\2\u018a"+
		"\u018b\7,\2\2\u018b\u018c\7\61\2\2\u018c\u018d\3\2\2\2\u018d\u018e\b-"+
		"\2\2\u018eZ\3\2\2\2\u018f\u0191\t\t\2\2\u0190\u018f\3\2\2\2\u0191\u0192"+
		"\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0194\3\2\2\2\u0194"+
		"\u0195\b.\2\2\u0195\\\3\2\2\2\u0196\u0197\13\2\2\2\u0197^\3\2\2\2\21\2"+
		"\u0140\u0142\u014a\u014c\u0150\u0155\u0157\u015d\u015f\u016e\u0172\u0175"+
		"\u0187\u0192\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}