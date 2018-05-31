package streaming.dsl.analyse

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.{ErrorNode, TerminalNode}
import streaming.dsl.analyse.adaptor.{ConnectAdaptor, SinkAdaptor, SourceAdaptor}
import streaming.dsl.parser.{DSLSQLListener, DSLSQLParser}


class ScriptSQLExecListener (str:String,str2:String) extends DSLSQLListener{

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#sql}.
    *
    * @param ctx the parse tree
    */
  override def exitSql(ctx: DSLSQLParser.SqlContext): Unit = {

    ctx.getChild(0).getText.toLowerCase() match {
      case "create source stream" =>
        new SourceAdaptor(this).parse(ctx)
      case "create sink stream" =>
        new SinkAdaptor(this).parse(ctx)
      case "connect" =>
        new ConnectAdaptor(this).parse(ctx)
    }
  }


  /**
    * Enter a parse tree produced by {@link DSLSQLParser#statement}.
    *
    * @param ctx the parse tree
    */
  override def enterStatement(ctx: DSLSQLParser.StatementContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#statement}.
    *
    * @param ctx the parse tree
    */
  override def exitStatement(ctx: DSLSQLParser.StatementContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#sql}.
    *
    * @param ctx the parse tree
    */
  override def enterSql(ctx: DSLSQLParser.SqlContext): Unit = {}


  /**
    * Enter a parse tree produced by {@link DSLSQLParser#overwrite}.
    *
    * @param ctx the parse tree
    */
  override def enterOverwrite(ctx: DSLSQLParser.OverwriteContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#overwrite}.
    *
    * @param ctx the parse tree
    */
  override def exitOverwrite(ctx: DSLSQLParser.OverwriteContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#append}.
    *
    * @param ctx the parse tree
    */
  override def enterAppend(ctx: DSLSQLParser.AppendContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#append}.
    *
    * @param ctx the parse tree
    */
  override def exitAppend(ctx: DSLSQLParser.AppendContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#errorIfExists}.
    *
    * @param ctx the parse tree
    */
  override def enterErrorIfExists(ctx: DSLSQLParser.ErrorIfExistsContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#errorIfExists}.
    *
    * @param ctx the parse tree
    */
  override def exitErrorIfExists(ctx: DSLSQLParser.ErrorIfExistsContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#ignore}.
    *
    * @param ctx the parse tree
    */
  override def enterIgnore(ctx: DSLSQLParser.IgnoreContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#ignore}.
    *
    * @param ctx the parse tree
    */
  override def exitIgnore(ctx: DSLSQLParser.IgnoreContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#booleanExpression}.
    *
    * @param ctx the parse tree
    */
  override def enterBooleanExpression(ctx: DSLSQLParser.BooleanExpressionContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#booleanExpression}.
    *
    * @param ctx the parse tree
    */
  override def exitBooleanExpression(ctx: DSLSQLParser.BooleanExpressionContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#expression}.
    *
    * @param ctx the parse tree
    */
  override def enterExpression(ctx: DSLSQLParser.ExpressionContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#expression}.
    *
    * @param ctx the parse tree
    */
  override def exitExpression(ctx: DSLSQLParser.ExpressionContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#ender}.
    *
    * @param ctx the parse tree
    */
  override def enterEnder(ctx: DSLSQLParser.EnderContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#ender}.
    *
    * @param ctx the parse tree
    */
  override def exitEnder(ctx: DSLSQLParser.EnderContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#format}.
    *
    * @param ctx the parse tree
    */
  override def enterFormat(ctx: DSLSQLParser.FormatContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#format}.
    *
    * @param ctx the parse tree
    */
  override def exitFormat(ctx: DSLSQLParser.FormatContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#path}.
    *
    * @param ctx the parse tree
    */
  override def enterPath(ctx: DSLSQLParser.PathContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#path}.
    *
    * @param ctx the parse tree
    */
  override def exitPath(ctx: DSLSQLParser.PathContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#setValue}.
    *
    * @param ctx the parse tree
    */
  override def enterSetValue(ctx: DSLSQLParser.SetValueContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#setValue}.
    *
    * @param ctx the parse tree
    */
  override def exitSetValue(ctx: DSLSQLParser.SetValueContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#setKey}.
    *
    * @param ctx the parse tree
    */
  override def enterSetKey(ctx: DSLSQLParser.SetKeyContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#setKey}.
    *
    * @param ctx the parse tree
    */
  override def exitSetKey(ctx: DSLSQLParser.SetKeyContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#db}.
    *
    * @param ctx the parse tree
    */
  override def enterDb(ctx: DSLSQLParser.DbContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#db}.
    *
    * @param ctx the parse tree
    */
  override def exitDb(ctx: DSLSQLParser.DbContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#tableName}.
    *
    * @param ctx the parse tree
    */
  override def enterTableName(ctx: DSLSQLParser.TableNameContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#tableName}.
    *
    * @param ctx the parse tree
    */
  override def exitTableName(ctx: DSLSQLParser.TableNameContext): Unit = {}



  /**
    * Enter a parse tree produced by {@link DSLSQLParser#functionName}.
    *
    * @param ctx the parse tree
    */
  override def enterFunctionName(ctx: DSLSQLParser.FunctionNameContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#functionName}.
    *
    * @param ctx the parse tree
    */
  override def exitFunctionName(ctx: DSLSQLParser.FunctionNameContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#col}.
    *
    * @param ctx the parse tree
    */
  override def enterCol(ctx: DSLSQLParser.ColContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#col}.
    *
    * @param ctx the parse tree
    */
  override def exitCol(ctx: DSLSQLParser.ColContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#qualifiedName}.
    *
    * @param ctx the parse tree
    */
  override def enterQualifiedName(ctx: DSLSQLParser.QualifiedNameContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#qualifiedName}.
    *
    * @param ctx the parse tree
    */
  override def exitQualifiedName(ctx: DSLSQLParser.QualifiedNameContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#identifier}.
    *
    * @param ctx the parse tree
    */
  override def enterIdentifier(ctx: DSLSQLParser.IdentifierContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#identifier}.
    *
    * @param ctx the parse tree
    */
  override def exitIdentifier(ctx: DSLSQLParser.IdentifierContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#strictIdentifier}.
    *
    * @param ctx the parse tree
    */
  override def enterStrictIdentifier(ctx: DSLSQLParser.StrictIdentifierContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#strictIdentifier}.
    *
    * @param ctx the parse tree
    */
  override def exitStrictIdentifier(ctx: DSLSQLParser.StrictIdentifierContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#quotedIdentifier}.
    *
    * @param ctx the parse tree
    */
  override def enterQuotedIdentifier(ctx: DSLSQLParser.QuotedIdentifierContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#quotedIdentifier}.
    *
    * @param ctx the parse tree
    */
  override def exitQuotedIdentifier(ctx: DSLSQLParser.QuotedIdentifierContext): Unit = {}

  override def enterEveryRule(ctx: ParserRuleContext): Unit = {}

  override def exitEveryRule(ctx: ParserRuleContext): Unit = {}

  override def visitErrorNode(node: ErrorNode): Unit = {}

  override def visitTerminal(node: TerminalNode): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#colTypeList}.
    *
    * @param ctx the parse tree
    */
  override def enterColTypeList(ctx: DSLSQLParser.ColTypeListContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#colTypeList}.
    *
    * @param ctx the parse tree
    */
  override def exitColTypeList(ctx: DSLSQLParser.ColTypeListContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#colType}.
    *
    * @param ctx the parse tree
    */
  override def enterColType(ctx: DSLSQLParser.ColTypeContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#colType}.
    *
    * @param ctx the parse tree
    */
  override def exitColType(ctx: DSLSQLParser.ColTypeContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#dataType}.
    *
    * @param ctx the parse tree
    */
  override def enterDataType(ctx: DSLSQLParser.DataTypeContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#dataType}.
    *
    * @param ctx the parse tree
    */
  override def exitDataType(ctx: DSLSQLParser.DataTypeContext): Unit = {}

  /**
    * Enter a parse tree produced by {@link DSLSQLParser#optionList}.
    *
    * @param ctx the parse tree
    */
  override def enterOptionList(ctx: DSLSQLParser.OptionListContext): Unit = {}

  /**
    * Exit a parse tree produced by {@link DSLSQLParser#optionList}.
    *
    * @param ctx the parse tree
    */
  override def exitOptionList(ctx: DSLSQLParser.OptionListContext): Unit = {}
}