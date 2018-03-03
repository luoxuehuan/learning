package com.hulb.java.antlr.use;

import com.hulb.java.antlr.CalExprLexer;
import com.hulb.java.antlr.CalExprParser;
import com.hulb.java.antlr.LabeledExprLexer;
import com.hulb.java.antlr.LabeledExprParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * @author hulb
 * @date 2018/2/8 下午2:17
 */
public class AntlrTest {

    public static void main(String[] args){

//        LabeledExprLexer lexer = new LabeledExprLexer(input);
//        CommonTokenStream tokens = new CommonTokenStream(lexer);
//        LabeledExprParser parser = new LabeledExprParser(tokens);
//        // 生成语法树
//        ParseTree tree = parser.prog();
//
//        MyVisitor visitor = new MyVisitor();
//        visitor.visit(tree);

        //ANTLRInputStream input = new ANTLRInputStream(is);
        CalExprLexer lexer = new CalExprLexer(new ANTLRInputStream("4+3="));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalExprParser parser = new CalExprParser(tokens);

        //生成语法树
        parser.getRuleNames();

//        parser.addParseListener();
//        ParseTree tree = parser.prog();
//        MyVisitor visitor = new MyVisitor();
//        visitor.visit(tree);
    }
}
