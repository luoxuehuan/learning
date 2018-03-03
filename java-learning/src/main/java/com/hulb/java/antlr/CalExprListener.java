// Generated from /Users/hulb/project/my/learning/java-learning/src/main/resources/antlr/CalExpr.g4 by ANTLR 4.7
package com.hulb.java.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalExprParser}.
 */
public interface CalExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link CalExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(CalExprParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link CalExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(CalExprParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CalExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign(CalExprParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CalExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign(CalExprParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blank}
	 * labeled alternative in {@link CalExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlank(CalExprParser.BlankContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link CalExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlank(CalExprParser.BlankContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parens}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(CalExprParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(CalExprParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(CalExprParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(CalExprParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(CalExprParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(CalExprParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(CalExprParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(CalExprParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(CalExprParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CalExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(CalExprParser.IntContext ctx);
}