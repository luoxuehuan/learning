package streaming.dsl.analyse;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import streaming.dsl.parser.DSLSQLListener;
import streaming.dsl.parser.DSLSQLParser;

import java.util.Map;

/**
 * @author hulb
 * @date 2018/5/31 下午3:07
 */
public class ScriptSQLExecListener implements DSLSQLListener{

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#sql}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitSql(DSLSQLParser.SqlContext ctx) {
        switch (ctx.getChild(0).getText().toLowerCase() ){
            case "create":new CreateAdaptor(this).parse(ctx);
        }
    }

    public ScriptSQLExecListener(Map map , String _pathPrefix){

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#statement}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterStatement(DSLSQLParser.StatementContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#statement}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitStatement(DSLSQLParser.StatementContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#sql}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterSql(DSLSQLParser.SqlContext ctx) {

    }


    /**
     * Enter a parse tree produced by {@link DSLSQLParser#overwrite}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterOverwrite(DSLSQLParser.OverwriteContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#overwrite}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitOverwrite(DSLSQLParser.OverwriteContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#append}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterAppend(DSLSQLParser.AppendContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#append}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitAppend(DSLSQLParser.AppendContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#errorIfExists}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterErrorIfExists(DSLSQLParser.ErrorIfExistsContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#errorIfExists}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitErrorIfExists(DSLSQLParser.ErrorIfExistsContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#ignore}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterIgnore(DSLSQLParser.IgnoreContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#ignore}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitIgnore(DSLSQLParser.IgnoreContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#booleanExpression}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterBooleanExpression(DSLSQLParser.BooleanExpressionContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#booleanExpression}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitBooleanExpression(DSLSQLParser.BooleanExpressionContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#expression}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterExpression(DSLSQLParser.ExpressionContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#expression}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitExpression(DSLSQLParser.ExpressionContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#ender}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterEnder(DSLSQLParser.EnderContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#ender}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitEnder(DSLSQLParser.EnderContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#format}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterFormat(DSLSQLParser.FormatContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#format}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitFormat(DSLSQLParser.FormatContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#path}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterPath(DSLSQLParser.PathContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#path}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitPath(DSLSQLParser.PathContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#setValue}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterSetValue(DSLSQLParser.SetValueContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#setValue}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitSetValue(DSLSQLParser.SetValueContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#setKey}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterSetKey(DSLSQLParser.SetKeyContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#setKey}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitSetKey(DSLSQLParser.SetKeyContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#db}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterDb(DSLSQLParser.DbContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#db}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitDb(DSLSQLParser.DbContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#tableName}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterTableName(DSLSQLParser.TableNameContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#tableName}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitTableName(DSLSQLParser.TableNameContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#streamName}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterStreamName(DSLSQLParser.StreamNameContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#streamName}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitStreamName(DSLSQLParser.StreamNameContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#functionName}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterFunctionName(DSLSQLParser.FunctionNameContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#functionName}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitFunctionName(DSLSQLParser.FunctionNameContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#col}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterCol(DSLSQLParser.ColContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#col}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitCol(DSLSQLParser.ColContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#qualifiedName}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterQualifiedName(DSLSQLParser.QualifiedNameContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#qualifiedName}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitQualifiedName(DSLSQLParser.QualifiedNameContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#identifier}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterIdentifier(DSLSQLParser.IdentifierContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#identifier}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitIdentifier(DSLSQLParser.IdentifierContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#strictIdentifier}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterStrictIdentifier(DSLSQLParser.StrictIdentifierContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#strictIdentifier}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitStrictIdentifier(DSLSQLParser.StrictIdentifierContext ctx) {

    }

    /**
     * Enter a parse tree produced by {@link DSLSQLParser#quotedIdentifier}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterQuotedIdentifier(DSLSQLParser.QuotedIdentifierContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link DSLSQLParser#quotedIdentifier}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitQuotedIdentifier(DSLSQLParser.QuotedIdentifierContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }
}
