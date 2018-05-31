// Generated from DSLSQL.g4 by ANTLR 4.7.1

package streaming.dsl.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DSLSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, COMMENT=37, STRING=38, 
		IDENTIFIER=39, BACKQUOTED_IDENTIFIER=40, SIMPLE_COMMENT=41, BRACKETED_EMPTY_COMMENT=42, 
		BRACKETED_COMMENT=43, WS=44, UNRECOGNIZED=45;
	public static final int
		RULE_statement = 0, RULE_sql = 1, RULE_colTypeList = 2, RULE_optionList = 3, 
		RULE_colType = 4, RULE_dataType = 5, RULE_overwrite = 6, RULE_append = 7, 
		RULE_errorIfExists = 8, RULE_ignore = 9, RULE_booleanExpression = 10, 
		RULE_expression = 11, RULE_ender = 12, RULE_format = 13, RULE_path = 14, 
		RULE_setValue = 15, RULE_setKey = 16, RULE_db = 17, RULE_tableName = 18, 
		RULE_functionName = 19, RULE_col = 20, RULE_qualifiedName = 21, RULE_identifier = 22, 
		RULE_strictIdentifier = 23, RULE_quotedIdentifier = 24;
	public static final String[] ruleNames = {
		"statement", "sql", "colTypeList", "optionList", "colType", "dataType", 
		"overwrite", "append", "errorIfExists", "ignore", "booleanExpression", 
		"expression", "ender", "format", "path", "setValue", "setKey", "db", "tableName", 
		"functionName", "col", "qualifiedName", "identifier", "strictIdentifier", 
		"quotedIdentifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'load'", "'LOAD'", "'.'", "'options'", "'as'", "'save'", "'SAVE'", 
		"'partitionBy'", "'select'", "'SELECT'", "';'", "'insert'", "'INSERT'", 
		"'create source stream'", "'CREATE SOURCE STREAM'", "'('", "')'", "'option'", 
		"'set'", "'SET'", "'='", "'connect'", "'CONNECT'", "'where'", "'source'", 
		"'SOURCE'", "'train'", "'TRAIN'", "'register'", "'REGISTER'", "','", "'overwrite'", 
		"'append'", "'errorIfExists'", "'ignore'", "'and'", "'COMMENT'", null, 
		null, null, null, "'/**/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "COMMENT", "STRING", "IDENTIFIER", "BACKQUOTED_IDENTIFIER", "SIMPLE_COMMENT", 
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

	@Override
	public String getGrammarFileName() { return "DSLSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DSLSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StatementContext extends ParserRuleContext {
		public List<SqlContext> sql() {
			return getRuleContexts(SqlContext.class);
		}
		public SqlContext sql(int i) {
			return getRuleContext(SqlContext.class,i);
		}
		public List<EnderContext> ender() {
			return getRuleContexts(EnderContext.class);
		}
		public EnderContext ender(int i) {
			return getRuleContext(EnderContext.class,i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__5) | (1L << T__6) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__18) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << SIMPLE_COMMENT))) != 0)) {
				{
				{
				setState(50);
				sql();
				setState(51);
				ender();
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class SqlContext extends ParserRuleContext {
		public FormatContext format() {
			return getRuleContext(FormatContext.class,0);
		}
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<BooleanExpressionContext> booleanExpression() {
			return getRuleContexts(BooleanExpressionContext.class);
		}
		public BooleanExpressionContext booleanExpression(int i) {
			return getRuleContext(BooleanExpressionContext.class,i);
		}
		public List<OverwriteContext> overwrite() {
			return getRuleContexts(OverwriteContext.class);
		}
		public OverwriteContext overwrite(int i) {
			return getRuleContext(OverwriteContext.class,i);
		}
		public List<AppendContext> append() {
			return getRuleContexts(AppendContext.class);
		}
		public AppendContext append(int i) {
			return getRuleContext(AppendContext.class,i);
		}
		public List<ErrorIfExistsContext> errorIfExists() {
			return getRuleContexts(ErrorIfExistsContext.class);
		}
		public ErrorIfExistsContext errorIfExists(int i) {
			return getRuleContext(ErrorIfExistsContext.class,i);
		}
		public List<IgnoreContext> ignore() {
			return getRuleContexts(IgnoreContext.class);
		}
		public IgnoreContext ignore(int i) {
			return getRuleContext(IgnoreContext.class,i);
		}
		public ColContext col() {
			return getRuleContext(ColContext.class,0);
		}
		public ColTypeListContext colTypeList() {
			return getRuleContext(ColTypeListContext.class,0);
		}
		public OptionListContext optionList() {
			return getRuleContext(OptionListContext.class,0);
		}
		public SetKeyContext setKey() {
			return getRuleContext(SetKeyContext.class,0);
		}
		public SetValueContext setValue() {
			return getRuleContext(SetValueContext.class,0);
		}
		public DbContext db() {
			return getRuleContext(DbContext.class,0);
		}
		public FunctionNameContext functionName() {
			return getRuleContext(FunctionNameContext.class,0);
		}
		public TerminalNode SIMPLE_COMMENT() { return getToken(DSLSQLParser.SIMPLE_COMMENT, 0); }
		public SqlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterSql(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitSql(this);
		}
	}

	public final SqlContext sql() throws RecognitionException {
		SqlContext _localctx = new SqlContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sql);
		int _la;
		try {
			int _alt;
			setState(214);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				_la = _input.LA(1);
				if ( !(_la==T__0 || _la==T__1) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(59);
				format();
				setState(60);
				match(T__2);
				setState(61);
				path();
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(62);
					match(T__3);
					}
				}

				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==BACKQUOTED_IDENTIFIER) {
					{
					setState(65);
					expression();
					}
				}

				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__35) {
					{
					{
					setState(68);
					booleanExpression();
					}
					}
					setState(73);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(74);
				match(T__4);
				setState(75);
				tableName();
				}
				break;
			case T__5:
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				_la = _input.LA(1);
				if ( !(_la==T__5 || _la==T__6) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34))) != 0)) {
					{
					setState(82);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__31:
						{
						setState(78);
						overwrite();
						}
						break;
					case T__32:
						{
						setState(79);
						append();
						}
						break;
					case T__33:
						{
						setState(80);
						errorIfExists();
						}
						break;
					case T__34:
						{
						setState(81);
						ignore();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(87);
				tableName();
				setState(88);
				match(T__4);
				setState(89);
				format();
				setState(90);
				match(T__2);
				setState(91);
				path();
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(92);
					match(T__3);
					}
				}

				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==BACKQUOTED_IDENTIFIER) {
					{
					setState(95);
					expression();
					}
				}

				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__35) {
					{
					{
					setState(98);
					booleanExpression();
					}
					}
					setState(103);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(104);
					match(T__7);
					setState(105);
					col();
					}
				}

				}
				break;
			case T__8:
			case T__9:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				_la = _input.LA(1);
				if ( !(_la==T__8 || _la==T__9) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(112);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(109);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==T__10) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(114);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				setState(115);
				match(T__4);
				setState(116);
				tableName();
				}
				break;
			case T__11:
			case T__12:
				enterOuterAlt(_localctx, 4);
				{
				setState(117);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==T__12) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << COMMENT) | (1L << STRING) | (1L << IDENTIFIER) | (1L << BACKQUOTED_IDENTIFIER) | (1L << SIMPLE_COMMENT) | (1L << BRACKETED_EMPTY_COMMENT) | (1L << BRACKETED_COMMENT) | (1L << WS) | (1L << UNRECOGNIZED))) != 0)) {
					{
					{
					setState(118);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==T__10) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(123);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__13:
			case T__14:
				enterOuterAlt(_localctx, 5);
				{
				setState(124);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(125);
				tableName();
				setState(130);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(126);
					match(T__15);
					setState(127);
					colTypeList();
					setState(128);
					match(T__16);
					}
					break;
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__17) {
					{
					setState(132);
					match(T__17);
					}
				}

				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(135);
					match(T__15);
					setState(136);
					optionList();
					setState(137);
					match(T__16);
					}
				}

				}
				break;
			case T__18:
			case T__19:
				enterOuterAlt(_localctx, 6);
				{
				setState(141);
				_la = _input.LA(1);
				if ( !(_la==T__18 || _la==T__19) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(142);
				setKey();
				setState(143);
				match(T__20);
				setState(144);
				setValue();
				}
				break;
			case T__21:
			case T__22:
				enterOuterAlt(_localctx, 7);
				{
				setState(146);
				_la = _input.LA(1);
				if ( !(_la==T__21 || _la==T__22) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(147);
				format();
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__15) {
					{
					setState(148);
					match(T__15);
					setState(149);
					colTypeList();
					setState(150);
					match(T__16);
					}
				}

				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(154);
					match(T__23);
					}
				}

				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==BACKQUOTED_IDENTIFIER) {
					{
					setState(157);
					expression();
					}
				}

				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__35) {
					{
					{
					setState(160);
					booleanExpression();
					}
					}
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(166);
					match(T__4);
					setState(167);
					db();
					}
				}

				}
				break;
			case T__24:
			case T__25:
				enterOuterAlt(_localctx, 8);
				{
				setState(170);
				_la = _input.LA(1);
				if ( !(_la==T__24 || _la==T__25) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(171);
				format();
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(172);
					match(T__23);
					}
				}

				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==BACKQUOTED_IDENTIFIER) {
					{
					setState(175);
					expression();
					}
				}

				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__35) {
					{
					{
					setState(178);
					booleanExpression();
					}
					}
					setState(183);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(184);
					match(T__4);
					setState(185);
					db();
					}
				}

				}
				break;
			case T__26:
			case T__27:
				enterOuterAlt(_localctx, 9);
				{
				setState(188);
				_la = _input.LA(1);
				if ( !(_la==T__26 || _la==T__27) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(189);
				tableName();
				setState(190);
				match(T__4);
				setState(191);
				format();
				setState(192);
				match(T__2);
				setState(193);
				path();
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(194);
					match(T__23);
					}
				}

				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==BACKQUOTED_IDENTIFIER) {
					{
					setState(197);
					expression();
					}
				}

				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__35) {
					{
					{
					setState(200);
					booleanExpression();
					}
					}
					setState(205);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__28:
			case T__29:
				enterOuterAlt(_localctx, 10);
				{
				setState(206);
				_la = _input.LA(1);
				if ( !(_la==T__28 || _la==T__29) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(207);
				format();
				setState(208);
				match(T__2);
				setState(209);
				path();
				setState(210);
				match(T__4);
				setState(211);
				functionName();
				}
				break;
			case SIMPLE_COMMENT:
				enterOuterAlt(_localctx, 11);
				{
				setState(213);
				match(SIMPLE_COMMENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ColTypeListContext extends ParserRuleContext {
		public List<ColTypeContext> colType() {
			return getRuleContexts(ColTypeContext.class);
		}
		public ColTypeContext colType(int i) {
			return getRuleContext(ColTypeContext.class,i);
		}
		public ColTypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colTypeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterColTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitColTypeList(this);
		}
	}

	public final ColTypeListContext colTypeList() throws RecognitionException {
		ColTypeListContext _localctx = new ColTypeListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_colTypeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			colType();
			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(217);
				match(T__30);
				setState(218);
				colType();
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class OptionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OptionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterOptionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitOptionList(this);
		}
	}

	public final OptionListContext optionList() throws RecognitionException {
		OptionListContext _localctx = new OptionListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_optionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			expression();
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__30) {
				{
				{
				setState(225);
				match(T__30);
				setState(226);
				expression();
				}
				}
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ColTypeContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode COMMENT() { return getToken(DSLSQLParser.COMMENT, 0); }
		public TerminalNode STRING() { return getToken(DSLSQLParser.STRING, 0); }
		public ColTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_colType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterColType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitColType(this);
		}
	}

	public final ColTypeContext colType() throws RecognitionException {
		ColTypeContext _localctx = new ColTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_colType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			identifier();
			setState(233);
			dataType();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMENT) {
				{
				setState(234);
				match(COMMENT);
				setState(235);
				match(STRING);
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

	public static class DataTypeContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitDataType(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_dataType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			identifier();
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

	public static class OverwriteContext extends ParserRuleContext {
		public OverwriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_overwrite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterOverwrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitOverwrite(this);
		}
	}

	public final OverwriteContext overwrite() throws RecognitionException {
		OverwriteContext _localctx = new OverwriteContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_overwrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(T__31);
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

	public static class AppendContext extends ParserRuleContext {
		public AppendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_append; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterAppend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitAppend(this);
		}
	}

	public final AppendContext append() throws RecognitionException {
		AppendContext _localctx = new AppendContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_append);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(T__32);
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

	public static class ErrorIfExistsContext extends ParserRuleContext {
		public ErrorIfExistsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_errorIfExists; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterErrorIfExists(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitErrorIfExists(this);
		}
	}

	public final ErrorIfExistsContext errorIfExists() throws RecognitionException {
		ErrorIfExistsContext _localctx = new ErrorIfExistsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_errorIfExists);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(T__33);
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

	public static class IgnoreContext extends ParserRuleContext {
		public IgnoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ignore; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterIgnore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitIgnore(this);
		}
	}

	public final IgnoreContext ignore() throws RecognitionException {
		IgnoreContext _localctx = new IgnoreContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ignore);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(T__34);
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

	public static class BooleanExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BooleanExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterBooleanExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitBooleanExpression(this);
		}
	}

	public final BooleanExpressionContext booleanExpression() throws RecognitionException {
		BooleanExpressionContext _localctx = new BooleanExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_booleanExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			match(T__35);
			setState(249);
			expression();
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

	public static class ExpressionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode STRING() { return getToken(DSLSQLParser.STRING, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			identifier();
			setState(252);
			match(T__20);
			setState(253);
			match(STRING);
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

	public static class EnderContext extends ParserRuleContext {
		public EnderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ender; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterEnder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitEnder(this);
		}
	}

	public final EnderContext ender() throws RecognitionException {
		EnderContext _localctx = new EnderContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ender);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(T__10);
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

	public static class FormatContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FormatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_format; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterFormat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitFormat(this);
		}
	}

	public final FormatContext format() throws RecognitionException {
		FormatContext _localctx = new FormatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_format);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			identifier();
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

	public static class PathContext extends ParserRuleContext {
		public QuotedIdentifierContext quotedIdentifier() {
			return getRuleContext(QuotedIdentifierContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitPath(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_path);
		try {
			setState(261);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				quotedIdentifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				identifier();
				}
				break;
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

	public static class SetValueContext extends ParserRuleContext {
		public QuotedIdentifierContext quotedIdentifier() {
			return getRuleContext(QuotedIdentifierContext.class,0);
		}
		public TerminalNode STRING() { return getToken(DSLSQLParser.STRING, 0); }
		public SetValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterSetValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitSetValue(this);
		}
	}

	public final SetValueContext setValue() throws RecognitionException {
		SetValueContext _localctx = new SetValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_setValue);
		try {
			setState(265);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BACKQUOTED_IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(263);
				quotedIdentifier();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(264);
				match(STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class SetKeyContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public SetKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterSetKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitSetKey(this);
		}
	}

	public final SetKeyContext setKey() throws RecognitionException {
		SetKeyContext _localctx = new SetKeyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_setKey);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			identifier();
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

	public static class DbContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_db; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterDb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitDb(this);
		}
	}

	public final DbContext db() throws RecognitionException {
		DbContext _localctx = new DbContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_db);
		try {
			setState(271);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				qualifiedName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				identifier();
				}
				break;
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

	public static class TableNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitTableName(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			identifier();
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

	public static class FunctionNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitFunctionName(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_functionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			identifier();
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

	public static class ColContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ColContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_col; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterCol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitCol(this);
		}
	}

	public final ColContext col() throws RecognitionException {
		ColContext _localctx = new ColContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_col);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			identifier();
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

	public static class QualifiedNameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitQualifiedName(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_qualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			identifier();
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(280);
				match(T__2);
				setState(281);
				identifier();
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class IdentifierContext extends ParserRuleContext {
		public StrictIdentifierContext strictIdentifier() {
			return getRuleContext(StrictIdentifierContext.class,0);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			strictIdentifier();
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

	public static class StrictIdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(DSLSQLParser.IDENTIFIER, 0); }
		public QuotedIdentifierContext quotedIdentifier() {
			return getRuleContext(QuotedIdentifierContext.class,0);
		}
		public StrictIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strictIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterStrictIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitStrictIdentifier(this);
		}
	}

	public final StrictIdentifierContext strictIdentifier() throws RecognitionException {
		StrictIdentifierContext _localctx = new StrictIdentifierContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_strictIdentifier);
		try {
			setState(291);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(289);
				match(IDENTIFIER);
				}
				break;
			case BACKQUOTED_IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				quotedIdentifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class QuotedIdentifierContext extends ParserRuleContext {
		public TerminalNode BACKQUOTED_IDENTIFIER() { return getToken(DSLSQLParser.BACKQUOTED_IDENTIFIER, 0); }
		public QuotedIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quotedIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).enterQuotedIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DSLSQLListener ) ((DSLSQLListener)listener).exitQuotedIdentifier(this);
		}
	}

	public final QuotedIdentifierContext quotedIdentifier() throws RecognitionException {
		QuotedIdentifierContext _localctx = new QuotedIdentifierContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_quotedIdentifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(BACKQUOTED_IDENTIFIER);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3/\u012a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\7\28\n\2\f\2\16\2;\13\2\3\3\3\3\3\3\3\3\3\3\5\3"+
		"B\n\3\3\3\5\3E\n\3\3\3\7\3H\n\3\f\3\16\3K\13\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\7\3U\n\3\f\3\16\3X\13\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3`\n\3\3\3"+
		"\5\3c\n\3\3\3\7\3f\n\3\f\3\16\3i\13\3\3\3\3\3\5\3m\n\3\3\3\3\3\7\3q\n"+
		"\3\f\3\16\3t\13\3\3\3\3\3\3\3\3\3\7\3z\n\3\f\3\16\3}\13\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3\u0085\n\3\3\3\5\3\u0088\n\3\3\3\3\3\3\3\3\3\5\3\u008e"+
		"\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\u009b\n\3\3\3\5\3"+
		"\u009e\n\3\3\3\5\3\u00a1\n\3\3\3\7\3\u00a4\n\3\f\3\16\3\u00a7\13\3\3\3"+
		"\3\3\5\3\u00ab\n\3\3\3\3\3\3\3\5\3\u00b0\n\3\3\3\5\3\u00b3\n\3\3\3\7\3"+
		"\u00b6\n\3\f\3\16\3\u00b9\13\3\3\3\3\3\5\3\u00bd\n\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\5\3\u00c6\n\3\3\3\5\3\u00c9\n\3\3\3\7\3\u00cc\n\3\f\3\16\3"+
		"\u00cf\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\u00d9\n\3\3\4\3\4\3\4"+
		"\7\4\u00de\n\4\f\4\16\4\u00e1\13\4\3\5\3\5\3\5\7\5\u00e6\n\5\f\5\16\5"+
		"\u00e9\13\5\3\6\3\6\3\6\3\6\5\6\u00ef\n\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\5\20\u0108\n\20\3\21\3\21\5\21\u010c\n\21\3\22\3\22\3\23\3\23\5\23"+
		"\u0112\n\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\27\7\27\u011d\n"+
		"\27\f\27\16\27\u0120\13\27\3\30\3\30\3\31\3\31\5\31\u0126\n\31\3\32\3"+
		"\32\3\32\2\2\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\2\r\3\2\3\4\3\2\b\t\3\2\13\f\3\2\r\r\3\2\16\17\3\2\20\21\3\2\25\26\3"+
		"\2\30\31\3\2\33\34\3\2\35\36\3\2\37 \2\u013f\29\3\2\2\2\4\u00d8\3\2\2"+
		"\2\6\u00da\3\2\2\2\b\u00e2\3\2\2\2\n\u00ea\3\2\2\2\f\u00f0\3\2\2\2\16"+
		"\u00f2\3\2\2\2\20\u00f4\3\2\2\2\22\u00f6\3\2\2\2\24\u00f8\3\2\2\2\26\u00fa"+
		"\3\2\2\2\30\u00fd\3\2\2\2\32\u0101\3\2\2\2\34\u0103\3\2\2\2\36\u0107\3"+
		"\2\2\2 \u010b\3\2\2\2\"\u010d\3\2\2\2$\u0111\3\2\2\2&\u0113\3\2\2\2(\u0115"+
		"\3\2\2\2*\u0117\3\2\2\2,\u0119\3\2\2\2.\u0121\3\2\2\2\60\u0125\3\2\2\2"+
		"\62\u0127\3\2\2\2\64\65\5\4\3\2\65\66\5\32\16\2\668\3\2\2\2\67\64\3\2"+
		"\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\3\3\2\2\2;9\3\2\2\2<=\t\2\2\2=>"+
		"\5\34\17\2>?\7\5\2\2?A\5\36\20\2@B\7\6\2\2A@\3\2\2\2AB\3\2\2\2BD\3\2\2"+
		"\2CE\5\30\r\2DC\3\2\2\2DE\3\2\2\2EI\3\2\2\2FH\5\26\f\2GF\3\2\2\2HK\3\2"+
		"\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\7\7\2\2MN\5&\24\2N\u00d9"+
		"\3\2\2\2OV\t\3\2\2PU\5\16\b\2QU\5\20\t\2RU\5\22\n\2SU\5\24\13\2TP\3\2"+
		"\2\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2"+
		"\2\2XV\3\2\2\2YZ\5&\24\2Z[\7\7\2\2[\\\5\34\17\2\\]\7\5\2\2]_\5\36\20\2"+
		"^`\7\6\2\2_^\3\2\2\2_`\3\2\2\2`b\3\2\2\2ac\5\30\r\2ba\3\2\2\2bc\3\2\2"+
		"\2cg\3\2\2\2df\5\26\f\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hl\3\2"+
		"\2\2ig\3\2\2\2jk\7\n\2\2km\5*\26\2lj\3\2\2\2lm\3\2\2\2m\u00d9\3\2\2\2"+
		"nr\t\4\2\2oq\n\5\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2"+
		"tr\3\2\2\2uv\7\7\2\2v\u00d9\5&\24\2w{\t\6\2\2xz\n\5\2\2yx\3\2\2\2z}\3"+
		"\2\2\2{y\3\2\2\2{|\3\2\2\2|\u00d9\3\2\2\2}{\3\2\2\2~\177\t\7\2\2\177\u0084"+
		"\5&\24\2\u0080\u0081\7\22\2\2\u0081\u0082\5\6\4\2\u0082\u0083\7\23\2\2"+
		"\u0083\u0085\3\2\2\2\u0084\u0080\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087"+
		"\3\2\2\2\u0086\u0088\7\24\2\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2"+
		"\u0088\u008d\3\2\2\2\u0089\u008a\7\22\2\2\u008a\u008b\5\b\5\2\u008b\u008c"+
		"\7\23\2\2\u008c\u008e\3\2\2\2\u008d\u0089\3\2\2\2\u008d\u008e\3\2\2\2"+
		"\u008e\u00d9\3\2\2\2\u008f\u0090\t\b\2\2\u0090\u0091\5\"\22\2\u0091\u0092"+
		"\7\27\2\2\u0092\u0093\5 \21\2\u0093\u00d9\3\2\2\2\u0094\u0095\t\t\2\2"+
		"\u0095\u009a\5\34\17\2\u0096\u0097\7\22\2\2\u0097\u0098\5\6\4\2\u0098"+
		"\u0099\7\23\2\2\u0099\u009b\3\2\2\2\u009a\u0096\3\2\2\2\u009a\u009b\3"+
		"\2\2\2\u009b\u009d\3\2\2\2\u009c\u009e\7\32\2\2\u009d\u009c\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u00a0\3\2\2\2\u009f\u00a1\5\30\r\2\u00a0\u009f\3"+
		"\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a5\3\2\2\2\u00a2\u00a4\5\26\f\2\u00a3"+
		"\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\u00aa\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\7\7\2\2\u00a9"+
		"\u00ab\5$\23\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00d9\3\2"+
		"\2\2\u00ac\u00ad\t\n\2\2\u00ad\u00af\5\34\17\2\u00ae\u00b0\7\32\2\2\u00af"+
		"\u00ae\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00b3\5\30"+
		"\r\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b7\3\2\2\2\u00b4"+
		"\u00b6\5\26\f\2\u00b5\u00b4\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3"+
		"\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00bc\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba"+
		"\u00bb\7\7\2\2\u00bb\u00bd\5$\23\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd\u00d9\3\2\2\2\u00be\u00bf\t\13\2\2\u00bf\u00c0\5&\24\2\u00c0"+
		"\u00c1\7\7\2\2\u00c1\u00c2\5\34\17\2\u00c2\u00c3\7\5\2\2\u00c3\u00c5\5"+
		"\36\20\2\u00c4\u00c6\7\32\2\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2"+
		"\u00c6\u00c8\3\2\2\2\u00c7\u00c9\5\30\r\2\u00c8\u00c7\3\2\2\2\u00c8\u00c9"+
		"\3\2\2\2\u00c9\u00cd\3\2\2\2\u00ca\u00cc\5\26\f\2\u00cb\u00ca\3\2\2\2"+
		"\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d9"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\t\f\2\2\u00d1\u00d2\5\34\17\2"+
		"\u00d2\u00d3\7\5\2\2\u00d3\u00d4\5\36\20\2\u00d4\u00d5\7\7\2\2\u00d5\u00d6"+
		"\5(\25\2\u00d6\u00d9\3\2\2\2\u00d7\u00d9\7+\2\2\u00d8<\3\2\2\2\u00d8O"+
		"\3\2\2\2\u00d8n\3\2\2\2\u00d8w\3\2\2\2\u00d8~\3\2\2\2\u00d8\u008f\3\2"+
		"\2\2\u00d8\u0094\3\2\2\2\u00d8\u00ac\3\2\2\2\u00d8\u00be\3\2\2\2\u00d8"+
		"\u00d0\3\2\2\2\u00d8\u00d7\3\2\2\2\u00d9\5\3\2\2\2\u00da\u00df\5\n\6\2"+
		"\u00db\u00dc\7!\2\2\u00dc\u00de\5\n\6\2\u00dd\u00db\3\2\2\2\u00de\u00e1"+
		"\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\7\3\2\2\2\u00e1"+
		"\u00df\3\2\2\2\u00e2\u00e7\5\30\r\2\u00e3\u00e4\7!\2\2\u00e4\u00e6\5\30"+
		"\r\2\u00e5\u00e3\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7"+
		"\u00e8\3\2\2\2\u00e8\t\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00eb\5.\30\2"+
		"\u00eb\u00ee\5\f\7\2\u00ec\u00ed\7\'\2\2\u00ed\u00ef\7(\2\2\u00ee\u00ec"+
		"\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\13\3\2\2\2\u00f0\u00f1\5.\30\2\u00f1"+
		"\r\3\2\2\2\u00f2\u00f3\7\"\2\2\u00f3\17\3\2\2\2\u00f4\u00f5\7#\2\2\u00f5"+
		"\21\3\2\2\2\u00f6\u00f7\7$\2\2\u00f7\23\3\2\2\2\u00f8\u00f9\7%\2\2\u00f9"+
		"\25\3\2\2\2\u00fa\u00fb\7&\2\2\u00fb\u00fc\5\30\r\2\u00fc\27\3\2\2\2\u00fd"+
		"\u00fe\5.\30\2\u00fe\u00ff\7\27\2\2\u00ff\u0100\7(\2\2\u0100\31\3\2\2"+
		"\2\u0101\u0102\7\r\2\2\u0102\33\3\2\2\2\u0103\u0104\5.\30\2\u0104\35\3"+
		"\2\2\2\u0105\u0108\5\62\32\2\u0106\u0108\5.\30\2\u0107\u0105\3\2\2\2\u0107"+
		"\u0106\3\2\2\2\u0108\37\3\2\2\2\u0109\u010c\5\62\32\2\u010a\u010c\7(\2"+
		"\2\u010b\u0109\3\2\2\2\u010b\u010a\3\2\2\2\u010c!\3\2\2\2\u010d\u010e"+
		"\5.\30\2\u010e#\3\2\2\2\u010f\u0112\5,\27\2\u0110\u0112\5.\30\2\u0111"+
		"\u010f\3\2\2\2\u0111\u0110\3\2\2\2\u0112%\3\2\2\2\u0113\u0114\5.\30\2"+
		"\u0114\'\3\2\2\2\u0115\u0116\5.\30\2\u0116)\3\2\2\2\u0117\u0118\5.\30"+
		"\2\u0118+\3\2\2\2\u0119\u011e\5.\30\2\u011a\u011b\7\5\2\2\u011b\u011d"+
		"\5.\30\2\u011c\u011a\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e"+
		"\u011f\3\2\2\2\u011f-\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0122\5\60\31"+
		"\2\u0122/\3\2\2\2\u0123\u0126\7)\2\2\u0124\u0126\5\62\32\2\u0125\u0123"+
		"\3\2\2\2\u0125\u0124\3\2\2\2\u0126\61\3\2\2\2\u0127\u0128\7*\2\2\u0128"+
		"\63\3\2\2\2&9ADITV_bglr{\u0084\u0087\u008d\u009a\u009d\u00a0\u00a5\u00aa"+
		"\u00af\u00b2\u00b7\u00bc\u00c5\u00c8\u00cd\u00d8\u00df\u00e7\u00ee\u0107"+
		"\u010b\u0111\u011e\u0125";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}