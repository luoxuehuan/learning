package streaming.dsl.analyse.adaptor

import streaming.dsl.analyse.{DslAdaptor, ScriptSQLExecListener}
import streaming.dsl.parser.DSLSQLParser._

class SourceAdaptor(scriptSQLExecListener: ScriptSQLExecListener) extends DslAdaptor {
  override def parse(ctx: SqlContext): Unit = {

    var option = Map[String, String]()
    var dbName = ""

    (0 to ctx.getChildCount() - 1).foreach { tokenIndex =>
      ctx.getChild(tokenIndex) match {
        case s: FormatContext =>
          option += ("format" -> s.getText)

        case s: ExpressionContext =>
          option += (cleanStr(s.identifier().getText) -> cleanStr(s.STRING().getText))
        case s: BooleanExpressionContext =>
          option += (cleanStr(s.expression().identifier().getText)-> cleanStr(s.expression().STRING().getText))
        case s: DbContext =>
          dbName= s.getText
        case _ =>

      }
    }

    println(option)
    println(dbName)
  }
}
