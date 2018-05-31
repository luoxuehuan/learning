package streaming.dsl.analyse.adaptor

import java.util

import streaming.dsl.analyse.{DslAdaptor, ScriptSQLExecListener}
import streaming.dsl.parser.DSLSQLParser._

class SourceAdaptor(scriptSQLExecListener: ScriptSQLExecListener) extends DslAdaptor {

  override def parse(ctx: SqlContext): Unit = {

    var streamName = ""
    val options = new util.HashMap[String, String]
    val cols = new util.HashMap[String, String]

    (0 to ctx.getChildCount() - 1).foreach { tokenIndex =>
      ctx.getChild(tokenIndex) match {
        case s: TableNameContext =>
          streamName = s.getText
        case s: ColTypeListContext => {
          (0 to s.getChildCount() - 1).foreach { colIndex => {
            s.getChild(colIndex) match {
              case colxtx: ColTypeContext => {
                val colName = colxtx.identifier().getText
                val colType = colxtx.dataType().getText
                cols.put(colName, colType)
              }
              case _ =>
            }
          }
          }
        }

        case s: OptionListContext => {
          (0 to s.getChildCount() - 1).foreach { colIndex => {
            s.getChild(colIndex) match {
              case colxtx: ExpressionContext => {
                val optionKey = colxtx.identifier().getText
                val optionValue = colxtx.STRING().getText
                options.put(optionKey, optionValue)
              }
              case _ =>
            }
          }
          }
        }
        case _ =>
      }
    }

    println("\n注册为source表:" + streamName)
    println("\nsource字段配置:" + cols)
    println("\nsource参数配置:" + options)
  }
}
