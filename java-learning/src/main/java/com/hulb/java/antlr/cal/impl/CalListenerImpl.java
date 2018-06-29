package com.hulb.java.antlr.cal.impl;

import com.hulb.java.antlr.cal.grammar.LabeledExprListener;
import com.hulb.java.antlr.cal.grammar.LabeledExprParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * @author hulb
 * @date 2018/6/25 上午11:21
 */
public class CalListenerImpl  implements LabeledExprListener{
    /**
     * Enter a parse tree produced by {@link LabeledExprParser#prog}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterProg(LabeledExprParser.ProgContext ctx) {

    }

    /**
     * Exit a parse tree produced by {@link LabeledExprParser#prog}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitProg(LabeledExprParser.ProgContext ctx) {

    }

    /**
     * Enter a parse tree produced by the {@code printExpr}
     * labeled alternative in {@link LabeledExprParser#stat}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterPrintExpr(LabeledExprParser.PrintExprContext ctx) {

    }

    /**
     * Exit a parse tree produced by the {@code printExpr}
     * labeled alternative in {@link LabeledExprParser#stat}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitPrintExpr(LabeledExprParser.PrintExprContext ctx) {

    }

    /**
     * Enter a parse tree produced by the {@code assign}
     * labeled alternative in {@link LabeledExprParser#stat}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterAssign(LabeledExprParser.AssignContext ctx) {

    }

    /**
     * Exit a parse tree produced by the {@code assign}
     * labeled alternative in {@link LabeledExprParser#stat}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitAssign(LabeledExprParser.AssignContext ctx) {

    }

    /**
     * Enter a parse tree produced by the {@code blank}
     * labeled alternative in {@link LabeledExprParser#stat}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterBlank(LabeledExprParser.BlankContext ctx) {

    }

    /**
     * Exit a parse tree produced by the {@code blank}
     * labeled alternative in {@link LabeledExprParser#stat}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitBlank(LabeledExprParser.BlankContext ctx) {

    }

    /**
     * Enter a parse tree produced by the {@code parens}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterParens(LabeledExprParser.ParensContext ctx) {

    }

    /**
     * Exit a parse tree produced by the {@code parens}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitParens(LabeledExprParser.ParensContext ctx) {

    }

    /**
     * Enter a parse tree produced by the {@code MulDiv}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterMulDiv(LabeledExprParser.MulDivContext ctx) {

    }

    /**
     * Exit a parse tree produced by the {@code MulDiv}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitMulDiv(LabeledExprParser.MulDivContext ctx) {

    }

    /**
     * Enter a parse tree produced by the {@code AddSub}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterAddSub(LabeledExprParser.AddSubContext ctx) {

    }

    /**
     * Exit a parse tree produced by the {@code AddSub}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitAddSub(LabeledExprParser.AddSubContext ctx) {

    }

    /**
     * Enter a parse tree produced by the {@code id}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterId(LabeledExprParser.IdContext ctx) {

    }

    /**
     * Exit a parse tree produced by the {@code id}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitId(LabeledExprParser.IdContext ctx) {

    }

    /**
     * Enter a parse tree produced by the {@code int}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void enterInt(LabeledExprParser.IntContext ctx) {

    }

    /**
     * Exit a parse tree produced by the {@code int}
     * labeled alternative in {@link LabeledExprParser#expr}.
     *
     * @param ctx the parse tree
     */
    @Override
    public void exitInt(LabeledExprParser.IntContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }
}
