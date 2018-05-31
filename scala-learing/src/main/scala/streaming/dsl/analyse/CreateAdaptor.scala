package streaming.dsl.analyse

import org.antlr.v4.runtime.misc.Interval
import streaming.dsl.parser.DSLSQLLexer
import streaming.dsl.parser.DSLSQLParser._

class CreateAdaptor(scriptSQLExecListener: ScriptSQLExecListener) extends DslAdaptor {

  override def parse(ctx: SqlContext): Unit = {

    var streamName = ""
    var format = ""
    var option = Map[String, String]()
    var path = ""
    var tableName = ""
    var sourceOrSink = ""

    (0 to ctx.getChildCount() - 1).foreach { tokenIndex =>

      println("===="+tokenIndex)
      println(ctx.getChild(tokenIndex).getText)

      ctx.getChild(tokenIndex) match {
        case s: FormatContext =>
          format = s.getText


        case s: ExpressionContext =>
          option += (cleanStr(s.identifier().getText) -> cleanStr(s.STRING().getText))
        case s: BooleanExpressionContext =>
          option += (cleanStr(s.expression().identifier().getText) -> cleanStr(s.expression().STRING().getText))

        case s: TableNameContext =>
          tableName = s.getText
        case _ =>
      }
    }

    println(streamName)
    println(tableName)
    println(sourceOrSink)
    println(format)
    //val originalText = input.getText(interval)
    //scriptSQLExecListener.sparkSession.sql(originalText).count()
  }
}
