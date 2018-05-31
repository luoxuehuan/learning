package streaming.dsl.analyse

import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream, ParserRuleContext}
import org.antlr.v4.runtime.tree.{ErrorNode, ParseTreeWalker, TerminalNode}
import streaming.dsl.parser.{DSLSQLLexer, DSLSQLListener, DSLSQLParser}

object ScriptSQLExec {

  def main(args: Array[String]): Unit = {


    //val input = "CREATE SOURCESTREAM car_speed_source" + "      with (\n" + "        type = \"dis\",\n" + "        region = \"cn-north-1\",\n" + "        channel = \"csinput\",\n" + "        partitionCnt = \"1\",\n" + "        encode = \"csv\",\n" + "        fieldDelimiter = \",\"\n" + "      );"
    val input = "connect jdbc \n     where \n    driver=\"com.mysql.jdbc.Driver\"\n    and url=\"jdbc:mysql://127.0.0.1/db?characterEncoding=utf8\"\n    and user=\"root\"\n    and password=\"****\"\n    as db1;"
    val kafkainput = "connect kafka (\nname string,\nid string)\n where \n    driver=\"com.mysql.jdbc.Driver\"\n    and servers=\"mq250:9092,mq250:9092\"\n    and topic=\"dtwave_access_log\"\n    and type=\"json\"\n    as kafka_topic_temp_table;"
    val loadLexer = new DSLSQLLexer(new ANTLRInputStream(kafkainput))

    println("解析sql：\n"+kafkainput)
    val tokens = new CommonTokenStream(loadLexer)
    val parser = new DSLSQLParser(tokens)
    val stat = parser.statement
    ParseTreeWalker.DEFAULT.walk(new ScriptSQLExecListener(null, null), stat)
  }

}

