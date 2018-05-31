package streaming.dsl.analyse.adaptor

import streaming.dsl.analyse.{DslAdaptor, ScriptSQLExec, ScriptSQLExecListener}
import streaming.dsl.parser.DSLSQLParser._

class ConnectAdaptor(scriptSQLExecListener: ScriptSQLExecListener) extends DslAdaptor {
  override def parse(ctx: SqlContext): Unit = {

    var option = Map[String, String]()
    var dbName = ""

    (0 to ctx.getChildCount() - 1).foreach { tokenIndex =>
      ctx.getChild(tokenIndex) match {
        case s: FormatContext =>
          option += ("format" -> s.getText)
        case s: ColTypeListContext =>{
            (0 to s.getChildCount() - 1).foreach{ colIndex => {
              s.getChild(colIndex) match {
                case colxtx:ColTypeContext => {
                  val colName = colxtx.identifier().getText
                  val colType = colxtx.dataType().getText
                  println("字段名称:"+colName +" 字段类型:"+colType)
                }
                case _ =>
              }
            }
          }
        }
        case s: ExpressionContext =>
          option += (cleanStr(s.identifier().getText) -> cleanStr(s.STRING().getText))
        case s: BooleanExpressionContext =>
          option += (cleanStr(s.expression().identifier().getText)-> cleanStr(s.expression().STRING().getText))
        case s: DbContext =>
          dbName= s.getText
        case _ =>

      }
    }

    println("source配置项："+option)
    println("注册为表:"+dbName)
  }
}
