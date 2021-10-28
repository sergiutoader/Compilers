// Generated from /home/student/Compilers/labs/lab3/parser-makefile/CPLangParser.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CPLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CPLangParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CPLangParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(CPLangParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPLangParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CPLangParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPLangParser#func_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_arg(CPLangParser.Func_argContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variable_definition}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_definition(CPLangParser.Variable_definitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code function_definition}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_definition(CPLangParser.Function_definitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minus}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(CPLangParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(CPLangParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(CPLangParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary_minus}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_minus(CPLangParser.Unary_minusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lt}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt(CPLangParser.LtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code float}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(CPLangParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code func_call}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_call(CPLangParser.Func_callContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(CPLangParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plus}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(CPLangParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(CPLangParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equal}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqual(CPLangParser.EqualContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen(CPLangParser.ParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code le}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLe(CPLangParser.LeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(CPLangParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(CPLangParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(CPLangParser.AssignContext ctx);
}