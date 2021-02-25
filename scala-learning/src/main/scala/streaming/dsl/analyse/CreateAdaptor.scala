package streaming.dsl.analyse

import java.util

import org.antlr.v4.runtime.misc.Interval
import streaming.dsl.parser.DSLSQLLexer
import streaming.dsl.parser.DSLSQLParser._

class CreateAdaptor(scriptSQLExecListener: ScriptSQLExecListener) extends DslAdaptor {

  override def parse(ctx: SqlContext): Unit = {

    var streamName = ""
    val options = new util.HashMap[String,String]
    val cols = new util.HashMap[String,String]


    (0 to ctx.getChildCount() - 1).foreach { tokenIndex =>

      //println("===="+tokenIndex)
      //println(ctx.getChild(tokenIndex).getText)

      ctx.getChild(tokenIndex) match {
        case s: TableNameContext =>
          streamName = s.getText
        case s: ColTypeListContext =>{
          (0 to s.getChildCount() - 1).foreach{ colIndex => {
            s.getChild(colIndex) match {
              case colxtx:ColTypeContext => {
                val colName = colxtx.identifier().getText
                val colType = colxtx.dataType().getText
                cols.put(colName,colType)
                //println("字段名称:"+colName +" 字段类型:"+colType)
              }
              case _ =>
            }
          }
          }
        }

        case s: OptionListContext =>{
          (0 to s.getChildCount() - 1).foreach{ colIndex => {
            s.getChild(colIndex) match {
              case colxtx:ExpressionContext => {
                val optionKey = colxtx.identifier().getText
                val optionValue = colxtx.STRING().getText
                options.put(optionKey,optionValue)
                //println("option名称:"+optionKey +" 字段类型:"+optionValue)
              }
              case _ =>
            }
          }
          }
        }
        case _ =>
      }
    }

    println("\n注册为表:"+streamName)
    println("\n字段配置:"+cols)
    println("\n参数配置:"+options)

    //val originalText = input.getText(interval)
    //scriptSQLExecListener.sparkSession.sql(originalText).count()
  }
}
