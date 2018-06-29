package com.hulb.java.antlr.cal.impl;

import com.hulb.java.antlr.cal.grammar.LabeledExprLexer;
import com.hulb.java.antlr.cal.grammar.LabeledExprParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author hulb
 * @date 2018/6/23 下午4:45
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // create a CharStream thatreads from standard input
        String inputFile = "/Users/hulb/project/my/learning/java-learning/src/main/resources/data.txt";
        InputStream is = System.in;

        if ( inputFile!=null ) {
            is = new FileInputStream(inputFile);
        }
        ANTLRInputStream input = new ANTLRInputStream(is);

        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        // parse
        ParseTree tree = parser.prog();
        ParseTree statTree = tree.getChild(1);
        if(statTree instanceof LabeledExprParser.StatContext){
            statTree.getChildCount();
        }




    }
}
