import java.io.IOException;
import java.text.Normalizer;
import java.util.*;

import jdk.dynalink.Operation;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class Test {

    public static void main(String[] args) throws IOException {
        var input = CharStreams.fromFileName("program2.txt");

        var lexer = new CPLangLexer(input);
        var tokenStream = new CommonTokenStream(lexer);

        /*
        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();
        for (var token : tokens) {
            var text = token.getText();
            var type = CPLangLexer.VOCABULARY.getSymbolicName(token.getType());

            System.out.println(text + " : " + type);
        }
        */

        var parser = new CPLangParser(tokenStream);
        var tree = parser.prog();
        System.out.println(tree.toStringTree(parser));

        // Visitor-ul de mai jos parcurge arborele de derivare și construiește
        // un arbore de sintaxă abstractă (AST).
        var astConstructionVisitor = new CPLangParserBaseVisitor<ASTNode>() {

            @Override
            public ASTNode visitId(CPLangParser.IdContext ctx) {
                return new Id(ctx.ID().getSymbol());
            }

            @Override
            public ASTNode visitFloat(CPLangParser.FloatContext ctx) {
                return new Float(ctx.FLOAT().getSymbol());
            }

            @Override
            public ASTNode visitInt(CPLangParser.IntContext ctx) {
                return new Int(ctx.INT().getSymbol());
            }

            @Override
            public ASTNode visitAssign(CPLangParser.AssignContext ctx) {
                return new Assign(new Id(ctx.name),
                        (Expression)visit(ctx.e),
                        ctx.start);
            }

            @Override
            public ASTNode visitIf(CPLangParser.IfContext ctx) {
                return new If((Expression)visit(ctx.cond),
                              (Expression)visit(ctx.thenBranch),
                              (Expression)visit(ctx.elseBranch),
                              ctx.start);
            }

            // TODO 3: Completati cu alte metode pentru a trece din arborele
            // generat automat in reprezentarea AST-ului dorit
            @Override
            public ASTNode visitProg(CPLangParser.ProgContext ctx) {
                ArrayList<Statement> s = new ArrayList<>();

                for (ParseTree t : ctx.children) {
                    if (t instanceof CPLangParser.ExprContext) {
                        s.add((Expression)visit(t));
                    } else if (t instanceof CPLangParser.DefinitionContext) {
                        s.add((Definition)visit(t));
                    }
                }

                return new Prog(s, ctx.start);
            }

            @Override
            public ASTNode visitVarDef(CPLangParser.VarDefContext ctx) {

                if (ctx.init != null)
                    return new VarDef(new Type(ctx.type),
                        new Id(ctx.name),
                        (Expression) visit(ctx.init),
                        ctx.start);
                else
                    return new VarDef(new Type(ctx.type),
                            new Id(ctx.name),
                            null,
                            ctx.start);
            }

            @Override
            public ASTNode visitFuncDef(CPLangParser.FuncDefContext ctx) {
                ArrayList<Formal> formals = new ArrayList<>();
                if (ctx.formals != null) {
                    for (CPLangParser.FormalContext fctx : ctx.formals) {
                        formals.add((Formal) visit(fctx));
                    }
                }

                return new FuncDef(new Type(ctx.type),
                            new Id(ctx.name),
                            formals,
                        (Expression)visit(ctx.body),
                            ctx.start);
            }

            @Override
            public ASTNode visitFormal(CPLangParser.FormalContext ctx) {
                return new Formal(new Id(ctx.name), new Type(ctx.type), ctx.start);
            }

            @Override
            public ASTNode visitCall(CPLangParser.CallContext ctx) {
                ArrayList<Expression> args = new ArrayList<>();
                if (ctx.args != null) {
                    for (CPLangParser.ExprContext ectx : ctx.args) {
                        args.add((Expression) visit(ectx));
                    }
                }

                return new Call(
                        new Id(ctx.name),
                        args,
                        ctx.start);
            }

            @Override
            public ASTNode visitParen(CPLangParser.ParenContext ctx) {
                return new Paren((Expression) visit(ctx.e), ctx.start);
            }

            @Override
            public ASTNode visitPlusMinus(CPLangParser.PlusMinusContext ctx) {
                return new Arithmetic(
                        (Expression) visit(ctx.left),
                        new Op(ctx.op),
                        (Expression) visit(ctx.right),
                        ctx.start);
            }

            @Override
            public ASTNode visitBool(CPLangParser.BoolContext ctx) {
                return new Bool(ctx.BOOL().getSymbol());
            }

            @Override
            public ASTNode visitMultDiv(CPLangParser.MultDivContext ctx) {
                return new Arithmetic(
                        (Expression) visit(ctx.left),
                        new Op(ctx.op),
                        (Expression) visit(ctx.right),
                        ctx.start);
            }

            @Override
            public ASTNode visitUnaryMinus(CPLangParser.UnaryMinusContext ctx) {
                return new UnaryMinus((Expression) visit(ctx.e), ctx.start);
            }

            @Override
            public ASTNode visitRelational(CPLangParser.RelationalContext ctx) {
                return new Relational(
                        (Expression) visit(ctx.left),
                        new Op(ctx.op),
                        (Expression) visit(ctx.right),
                        ctx.start);
            }
        };

        // ast este AST-ul proaspăt construit pe baza arborelui de derivare.
        var ast = astConstructionVisitor.visit(tree);

        // Acest visitor parcurge AST-ul generat mai sus.
        // ATENȚIE! Avem de-a face cu două categorii de visitori:
        // (1) Cei pentru arborele de derivare, care extind
        //     CPLangParserBaseVisitor<T> și
        // (2) Cei pentru AST, care extind ASTVisitor<T>.
        // Aveți grijă să nu îi confundați!
        var printVisitor = new ASTVisitor<Void>() {
            int indent = 0;

            @Override
            public Void visit(Id id) {
                printIndent("ID " + id.token.getText());
                return null;
            }

            @Override
            public Void visit(Int intt) {
                printIndent("INT " + intt.token.getText());
                return null;
            }

            @Override
            public Void visit(If iff) {
                printIndent("IF");
                indent++;
                iff.cond.accept(this);
                iff.thenBranch.accept(this);
                iff.elseBranch.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Call call) {
                printIndent("CALL");
                indent++;
                call.name.accept(this);
                for (Expression e : call.args)
                    e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Float floatt) {
                printIndent("FLOAT " + floatt.token.getText());
                return null;
            }

            @Override
            public Void visit(Bool bool) {
                printIndent("BOOL " + bool.token.getText());
                return null;
            }

            @Override
            public Void visit(Assign assign) {
                printIndent("ASSIGN");
                indent++;
                assign.name.accept(this);
                assign.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Paren paren) {
                printIndent("PAREN");
                indent++;
                paren.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(UnaryMinus unaryMinus) {
                printIndent("UNARY_MINUS");
                indent++;
                unaryMinus.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Type type) {
                printIndent("TYPE " + type.token.getText());
                return null;
            }

            @Override
            public Void visit(Formal formal) {
                printIndent("FORMAL");
                indent++;
                formal.type.accept(this);
                formal.id.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(VarDef vardef) {
                printIndent("VARDEF");
                indent++;
                vardef.type.accept(this);
                vardef.name.accept(this);
                if(vardef.init != null)
                    vardef.init.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(FuncDef funcDef) {
                printIndent("FUNCDEF");
                indent++;
                funcDef.type.accept(this);
                funcDef.name.accept(this);
                for(Formal f : funcDef.formals)
                    f.accept(this);
                funcDef.body.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Prog prog) {
                printIndent("PROG");
                indent++;
                for (Statement s : prog.statements) {
                    s.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Op op) {
                printIndent("OP: " + op.token.getText());
                return null;
            }

            @Override
            public Void visit(Arithmetic arithmetic) {
                printIndent("ARITHMETIC");
                indent++;
                arithmetic.left.accept(this);
                arithmetic.op.accept(this);
                arithmetic.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Relational relational) {
                printIndent("RELATIONAL");
                indent++;
                relational.left.accept(this);
                relational.op.accept(this);
                relational.right.accept(this);
                indent--;
                return null;
            }

            // TODO 4: Afisati fiecare nod astfel incat nivelul pe care acesta
            // se afla in AST sa fie reprezentat de numarul de tab-uri.
            // Folositi functia de mai jos 'printIndent' si incrementati /
            // decrementati corespunzator numarul de tab-uri

            void printIndent(String str) {
                for (int i = 0; i < indent; i++)
                    System.out.print("\t");
                System.out.println(str);
            }
        };

        // TODO 5: Creati un program CPLang care sa cuprinda cat mai multe
        // constructii definite in laboratorul de astazi. Scrieti codul CPLang
        // intr-un fisier txt si modificati fisierul de input de la inceputul
        // metodei 'main'

        System.out.println("The AST is");
        ast.accept(printVisitor);
    }
}
