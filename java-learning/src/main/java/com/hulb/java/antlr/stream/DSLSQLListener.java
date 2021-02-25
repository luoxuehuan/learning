// Generated from DSLSQL.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DSLSQLParser}.
 */
public interface DSLSQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(DSLSQLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(DSLSQLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#sql}.
	 * @param ctx the parse tree
	 */
	void enterSql(DSLSQLParser.SqlContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#sql}.
	 * @param ctx the parse tree
	 */
	void exitSql(DSLSQLParser.SqlContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#create}.
	 * @param ctx the parse tree
	 */
	void enterCreate(DSLSQLParser.CreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#create}.
	 * @param ctx the parse tree
	 */
	void exitCreate(DSLSQLParser.CreateContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(DSLSQLParser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(DSLSQLParser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#stream}.
	 * @param ctx the parse tree
	 */
	void enterStream(DSLSQLParser.StreamContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#stream}.
	 * @param ctx the parse tree
	 */
	void exitStream(DSLSQLParser.StreamContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#other}.
	 * @param ctx the parse tree
	 */
	void enterOther(DSLSQLParser.OtherContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#other}.
	 * @param ctx the parse tree
	 */
	void exitOther(DSLSQLParser.OtherContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#colTypeList}.
	 * @param ctx the parse tree
	 */
	void enterColTypeList(DSLSQLParser.ColTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#colTypeList}.
	 * @param ctx the parse tree
	 */
	void exitColTypeList(DSLSQLParser.ColTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#optionList}.
	 * @param ctx the parse tree
	 */
	void enterOptionList(DSLSQLParser.OptionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#optionList}.
	 * @param ctx the parse tree
	 */
	void exitOptionList(DSLSQLParser.OptionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#colType}.
	 * @param ctx the parse tree
	 */
	void enterColType(DSLSQLParser.ColTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#colType}.
	 * @param ctx the parse tree
	 */
	void exitColType(DSLSQLParser.ColTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(DSLSQLParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(DSLSQLParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(DSLSQLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(DSLSQLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#ender}.
	 * @param ctx the parse tree
	 */
	void enterEnder(DSLSQLParser.EnderContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#ender}.
	 * @param ctx the parse tree
	 */
	void exitEnder(DSLSQLParser.EnderContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(DSLSQLParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(DSLSQLParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(DSLSQLParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(DSLSQLParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#strictIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterStrictIdentifier(DSLSQLParser.StrictIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#strictIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitStrictIdentifier(DSLSQLParser.StrictIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSLSQLParser#quotedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterQuotedIdentifier(DSLSQLParser.QuotedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSLSQLParser#quotedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitQuotedIdentifier(DSLSQLParser.QuotedIdentifierContext ctx);
}