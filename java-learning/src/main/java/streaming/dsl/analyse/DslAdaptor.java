package streaming.dsl.analyse;

import streaming.dsl.parser.DSLSQLParser;

public interface DslAdaptor {

    void parse(DSLSQLParser.SqlContext ctx);


}
