package streaming.dsl.analyse;

import streaming.dsl.parser.DSLSQLParser;

/**
 * @author hulb
 * @date 2018/5/31 下午3:11
 */
public class CreateAdaptor implements DslAdaptor {

    private ScriptSQLExecListener scriptSQLExecListener;

    public CreateAdaptor(ScriptSQLExecListener scriptSQLExecListener){
        this.scriptSQLExecListener = scriptSQLExecListener;
    }

    @Override
    public void parse(DSLSQLParser.SqlContext ctx) {
        System.out.println("如果是create类型的sql就会进这个分支");
        //print(ctx.start.getTokenSource().getLine());

        for(int tokenIndex=0 ;tokenIndex<ctx.getChildCount();tokenIndex++){
           // ctx.getChild(tokenIndex).
            //print(ctx.getChild(tokenIndex).getText());
        }
    }



    public void print(Object source){
        System.out.println(source);
    }
}
