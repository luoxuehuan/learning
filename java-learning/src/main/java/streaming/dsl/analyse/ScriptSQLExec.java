package streaming.dsl.analyse;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import streaming.dsl.parser.DSLSQLLexer;
import streaming.dsl.parser.DSLSQLParser;

/**
 * @author hulb
 * @date 2018/5/31 下午3:00
 */
public class ScriptSQLExec {

    public static void main(String[] args){
        String input = "CREATE SOURCE STREAM car_speed_source" +
                "      WITH (\n" +
                "        type = \"dis\",\n" +
                "        region = \"cn-north-1\",\n" +
                "        channel = \"csinput\",\n" +
                "        partitionCnt = \"1\",\n" +
                "        encode = \"csv\",\n" +
                "        fieldDelimiter = \",\"\n" +
                "      );";
        DSLSQLLexer loadLexer = new DSLSQLLexer(new ANTLRInputStream(input));

        CommonTokenStream tokens = new CommonTokenStream(loadLexer);
        DSLSQLParser parser = new DSLSQLParser(tokens);
        DSLSQLParser.StatementContext stat = parser.statement();
        ParseTreeWalker.DEFAULT.walk(new ScriptSQLExecListener(null, null), stat);

    }
}



