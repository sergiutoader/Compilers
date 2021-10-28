// Generated from /home/student/Compilers/labs/lab3/parser-makefile/CPLangParser.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CPLangParser}.
 */
public interface CPLangParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CPLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(CPLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(CPLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPLangParser#formal}.
	 * @param ctx the parse tree
	 */
	void enterFormal(CPLangParser.FormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPLangParser#formal}.
	 * @param ctx the parse tree
	 */
	void exitFormal(CPLangParser.FormalContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPLangParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void enterFunc_arg(CPLangParser.Func_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPLangParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void exitFunc_arg(CPLangParser.Func_argContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variable_definition}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterVariable_definition(CPLangParser.Variable_definitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variable_definition}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitVariable_definition(CPLangParser.Variable_definitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code function_definition}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterFunction_definition(CPLangParser.Function_definitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code function_definition}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitFunction_definition(CPLangParser.Function_definitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minus}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMinus(CPLangParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minus}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMinus(CPLangParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mult}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMult(CPLangParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMult(CPLangParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBool(CPLangParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBool(CPLangParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unary_minus}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnary_minus(CPLangParser.Unary_minusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unary_minus}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnary_minus(CPLangParser.Unary_minusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lt}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLt(CPLangParser.LtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lt}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLt(CPLangParser.LtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code float}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloat(CPLangParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code float}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloat(CPLangParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code func_call}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(CPLangParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by the {@code func_call}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(CPLangParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(CPLangParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(CPLangParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code plus}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPlus(CPLangParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code plus}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPlus(CPLangParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code div}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDiv(CPLangParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code div}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDiv(CPLangParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equal}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqual(CPLangParser.EqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equal}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqual(CPLangParser.EqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParen(CPLangParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParen(CPLangParser.ParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code le}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLe(CPLangParser.LeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code le}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLe(CPLangParser.LeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(CPLangParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(CPLangParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIf(CPLangParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIf(CPLangParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssign(CPLangParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssign(CPLangParser.AssignContext ctx);
}