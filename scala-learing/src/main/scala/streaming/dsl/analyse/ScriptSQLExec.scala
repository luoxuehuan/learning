package streaming.dsl.analyse

import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream, ParserRuleContext}
import org.antlr.v4.runtime.tree.{ErrorNode, ParseTreeWalker, TerminalNode}
import streaming.dsl.parser.{DSLSQLLexer, DSLSQLListener, DSLSQLParser}

object ScriptSQLExec {

  def main(args: Array[String]): Unit = {

    val source = "create source stream sourceName (\nname string,\nid string)\n option \n    (driver=\"com.mysql.jdbc.Driver\"\n    , servers=\"mq250:9092,mq250:9092\"\n    , topic=\"dtwave_access_log\"\n    , type=\"json\"\n   );"
    val sink = "create sink stream sinkName (\nname string,\nid string)\n option \n    (driver=\"com.mysql.jdbc.Driver\"\n    , servers=\"mq250:9092,mq250:9092\"\n    , topic=\"dtwave_access_log\"\n    , type=\"json\"\n   );"
    val all = source+"\n\n\n"+sink;
    val loadLexer = new DSLSQLLexer(new ANTLRInputStream(all))

    println("解析sql：\n"+all)
    val tokens = new CommonTokenStream(loadLexer)
    val parser = new DSLSQLParser(tokens)
    val stat = parser.statement
    ParseTreeWalker.DEFAULT.walk(new ScriptSQLExecListener(null, null), stat)
  }

}

