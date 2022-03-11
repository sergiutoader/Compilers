package cool.compiler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import cool.lexer.*;
import cool.parser.*;

import java.io.*;
import java.util.ArrayList;

public class Compiler {
    // Annotates class nodes with the names of files where they are defined.
    public static ParseTreeProperty<String> fileNames = new ParseTreeProperty<>();

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("No file(s) given");
            return;
        }
        
        CoolLexer lexer = null;
        CommonTokenStream tokenStream = null;
        CoolParser parser = null;
        ParserRuleContext globalTree = null;
        
        // True if any lexical or syntax errors occur.
        boolean lexicalSyntaxErrors = false;
        
        // Parse each input file and build one big parse tree out of
        // individual parse trees.
        for (var fileName : args) {
            var input = CharStreams.fromFileName(fileName);
            
            // Lexer
            if (lexer == null)
                lexer = new CoolLexer(input);
            else
                lexer.setInputStream(input);

            // Token stream
            if (tokenStream == null)
                tokenStream = new CommonTokenStream(lexer);
            else
                tokenStream.setTokenSource(lexer);
            
            // Parser
            if (parser == null)
                parser = new CoolParser(tokenStream);
            else
                parser.setTokenStream(tokenStream);
            
            // Customized error listener, for including file names in error
            // messages.
            var errorListener = new BaseErrorListener() {
                public boolean errors = false;
                
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg,
                                        RecognitionException e) {
                    String newMsg = "\"" + new File(fileName).getName() + "\", line " +
                                        line + ":" + (charPositionInLine + 1) + ", ";
                    
                    Token token = (Token)offendingSymbol;
                    if (token.getType() == CoolLexer.ERROR)
                        newMsg += "Lexical error: " + token.getText();
                    else
                        newMsg += "Syntax error: " + msg;
                    
                    System.err.println(newMsg);
                    errors = true;
                }
            };
            
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            
            // Actual parsing
            var tree = parser.program();
            if (globalTree == null)
                globalTree = tree;
            else
                // Add the current parse tree's children to the global tree.
                for (int i = 0; i < tree.getChildCount(); i++)
                    globalTree.addAnyChild(tree.getChild(i));
                    
            // Annotate class nodes with file names, to be used later
            // in semantic error messages.
            for (int i = 0; i < tree.getChildCount(); i++) {
                var child = tree.getChild(i);
                // The only ParserRuleContext children of the program node
                // are class nodes.
                if (child instanceof ParserRuleContext)
                    fileNames.put(child, fileName);
            }
            
            // Record any lexical or syntax errors.
            lexicalSyntaxErrors |= errorListener.errors;
        }

        // Stop before semantic analysis phase, in case errors occurred.
        if (lexicalSyntaxErrors) {
            System.err.println("Compilation halted");
            return;
        }
        

        var astConstructionVisitor = new CoolParserBaseVisitor<ASTNode>() {

            @Override public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
                ArrayList<Class> classes = new ArrayList<>();
                for (CoolParser.ClasssContext c : ctx.classes) {
                    classes.add((Class) visit(c));
                }

                return new Prog(ctx.start, classes);
            }

            @Override public ASTNode visitClasss(CoolParser.ClasssContext ctx) {
                Type name = new Type(ctx.name);
                Type parent_name = new Type(ctx.parent_name);

                ArrayList<Feature> features = new ArrayList<>();
                for (CoolParser.FeatureContext f : ctx.features) {
                    features.add((Feature) visit(f));
                }

                return new Class(ctx.start, name, parent_name, features);
            }

            @Override
            public ASTNode visitMethod(CoolParser.MethodContext ctx) {
                ArrayList<Formal> formals = new ArrayList<>();
                for(CoolParser.FormalContext f : ctx.formals) {
                    formals.add((Formal) visit(f));
                }

                return new Method(ctx.start, new ObjectId(ctx.name), formals, new Type(ctx.type), (Expr) visit(ctx.body));
            }

            @Override
            public ASTNode visitAttribute(CoolParser.AttributeContext ctx) {
                return new Attribute(ctx.start, new ObjectId(ctx.name), new Type(ctx.type), ctx.e == null ? null : (Expr) visit(ctx.e));
            }

            @Override public ASTNode visitFormal(CoolParser.FormalContext ctx) {
                return new Formal(ctx.start, new ObjectId(ctx.id), new Type(ctx.type));
            }

            @Override
            public ASTNode visitPlusMinus(CoolParser.PlusMinusContext ctx) {
                return new PlusMinus(ctx.start, (Expr) visit(ctx.left), ctx.operation, (Expr) visit(ctx.right));
            }

            @Override
            public ASTNode visitMultDiv(CoolParser.MultDivContext ctx) {
                return new MultDiv(ctx.start, (Expr) visit(ctx.left), ctx.operation, (Expr) visit(ctx.right));
            }

            @Override public ASTNode visitNew(CoolParser.NewContext ctx) {
                return new New(ctx.start, new ObjectId(ctx.id));
            }

            @Override public ASTNode visitString(CoolParser.StringContext ctx) { return new Stringg(ctx.start); }

            @Override public ASTNode visitAssignment(CoolParser.AssignmentContext ctx) {
                return new Assign(ctx.start, new ObjectId(ctx.id), (Expr) visit(ctx.e));
            }

            @Override public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) {
                return new IsVoid(ctx.start, (Expr) visit(ctx.e));
            }

            @Override public ASTNode visitFalse(CoolParser.FalseContext ctx) { return new False(ctx.start); }

            @Override public ASTNode visitWhile(CoolParser.WhileContext ctx) {
                return new While(ctx.start, (Expr) visit(ctx.cond), (Expr) visit(ctx.body));
            }

            @Override public ASTNode visitInt(CoolParser.IntContext ctx) { return new Int(ctx.start); }

            @Override public ASTNode visitNegative(CoolParser.NegativeContext ctx) {
                return new Negative(ctx.start, (Expr) visit(ctx.expr()));
            }

            @Override public ASTNode visitNot(CoolParser.NotContext ctx) {
                return new Not(ctx.start, (Expr) visit(ctx.expr()));
            }

            @Override public ASTNode visitParen(CoolParser.ParenContext ctx) {
                return new Paren(ctx.start, (Expr) visit(ctx.e));
            }

            @Override public ASTNode visitTrue(CoolParser.TrueContext ctx) { return new True(ctx.start); }

            @Override public ASTNode visitBlock(CoolParser.BlockContext ctx) {
                ArrayList<Expr> subexpressions = new ArrayList<>();
                for(CoolParser.ExprContext e : ctx.subexpr) {
                    subexpressions.add((Expr) visit(e));
                }

                return new Block(ctx.start, subexpressions);
            }

            @Override public ASTNode visitLet(CoolParser.LetContext ctx) {
                ArrayList<Local> locals = new ArrayList<>();

                for(CoolParser.LocalContext l : ctx.local()) {
                    locals.add((Local) visit(l));
                }

                return new Let(ctx.start, locals, (Expr) visit(ctx.body));
            }

            @Override public ASTNode visitRelational(CoolParser.RelationalContext ctx) {
                return new Relational(ctx.start, (Expr) visit(ctx.left), ctx.operation, (Expr) visit(ctx.right));
            }

            @Override public ASTNode visitId(CoolParser.IdContext ctx) { return new Id(ctx.start); }

            @Override public ASTNode visitSpecified_dispatch(CoolParser.Specified_dispatchContext ctx) {
                ArrayList<Expr> formalParameters = new ArrayList<>();
                for(CoolParser.ExprContext e : ctx.formal_params) {
                    formalParameters.add((Expr) visit(e));
                }

                return new ExplicitDispatch(
                        ctx.start,
                        (Expr) visit(ctx.object),
                        ctx.type == null ? null : new Type(ctx.type),
                        new ObjectId(ctx.funcName),
                        formalParameters);
            }

            @Override public ASTNode visitUnspecified_dispatch(CoolParser.Unspecified_dispatchContext ctx) {
                ArrayList<Expr> formalParameters = new ArrayList<>();
                for(CoolParser.ExprContext e : ctx.formal_params) {
                    formalParameters.add((Expr) visit(e));
                }

                return new ImplicitDispatch(ctx.start, new ObjectId(ctx.funcName), formalParameters);
            }

            @Override public ASTNode visitIf(CoolParser.IfContext ctx) {
                return new If(ctx.start, (Expr) visit(ctx.cond), (Expr) visit(ctx.thenBranch), (Expr) visit(ctx.elseBranch));
            }

            @Override public ASTNode visitCase(CoolParser.CaseContext ctx) {
                ArrayList<CaseBranch> branches = new ArrayList<>();
                for (CoolParser.Case_branchContext cbc : ctx.case_branches) {
                    branches.add((CaseBranch) visit(cbc));
                }

                return new Case(ctx.start, (Expr) visit(ctx.ref_expr), branches);
            }

            @Override
            public ASTNode visitLocal(CoolParser.LocalContext ctx) {
                return new Local(ctx.start, new ObjectId(ctx.id), new Type(ctx.type), ctx.expr() == null ? null : (Expr) visit(ctx.expr()));
            }

            @Override
            public ASTNode visitCase_branch(CoolParser.Case_branchContext ctx) {
                return new CaseBranch(ctx.start, new ObjectId(ctx.id), new Type(ctx.type), ctx.e == null ? null : (Expr) visit(ctx.e));
            }
        };

        var ast = astConstructionVisitor.visit(globalTree);

        var printVisitor = new ASTVisitor<Void>() {
            int indent = 0;

            @Override
            public Void visit(Prog prog) {
                printIndent("program");
                indent++;
                for (Class c : prog.classes) {
                    c.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Class classs) {
                printIndent("class");
                indent++;
                classs.name.accept(this);
                if (classs.parentName.token != null) {
                    classs.parentName.accept(this);
                }
                for(Feature f : classs.features) {
                    f.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Method method) {
                printIndent("method");
                indent++;
                method.name.accept(this);
                for(Formal f : method.formals) {
                    f.accept(this);
                }
                method.type.accept(this);
                method.body.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Attribute attribute) {
                printIndent("attribute");
                indent++;
                attribute.name.accept(this);
                attribute.type.accept(this);
                if (attribute.defaultVal != null) {
                    attribute.defaultVal.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Type type) {
                printIndent(type.token.getText());
                return null;
            }

            @Override
            public Void visit(ObjectId objectId) {
                printIndent(objectId.token.getText());
                return null;
            }

            @Override
            public Void visit(Formal formal) {
                printIndent("formal");
                indent++;
                formal.id.accept(this);
                formal.type.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Int intt) {
                printIndent(intt.token.getText());
                return null;
            }

            @Override
            public Void visit(Stringg string) {
                printIndent(string.token.getText());
                return null;
            }

            @Override
            public Void visit(True t) {
                printIndent(t.token.getText());
                return null;
            }

            @Override
            public Void visit(False f) {
                printIndent(f.token.getText());
                return null;
            }

            @Override
            public Void visit(Id id) {
                id.value.accept(this);
                return null;
            }

            @Override
            public Void visit(Paren paren) {
               paren.e.accept(this);
               return null;
            }

            @Override
            public Void visit(MultDiv multDiv) {
                printIndent(multDiv.op.getText());
                indent++;
                multDiv.left.accept(this);
                multDiv.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(PlusMinus plusMinus) {
                printIndent(plusMinus.op.getText());
                indent++;
                plusMinus.left.accept(this);
                plusMinus.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Negative negative) {
                printIndent("~");
                indent++;
                negative.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Relational relational) {
                printIndent(relational.op.getText());
                indent++;
                relational.left.accept(this);
                relational.right.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Not not) {
                printIndent("not");
                indent++;
                not.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Assign assign) {
                printIndent("<-");
                indent++;
                assign.id.accept(this);
                assign.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(IsVoid isVoid) {
                printIndent("isvoid");
                indent++;
                isVoid.e.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(New n) {
                printIndent("new");
                indent++;
                n.id.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(ImplicitDispatch implicitDispatch) {
                printIndent("implicit dispatch");
                indent++;
                implicitDispatch.funcName.accept(this);
                for (Expr e : implicitDispatch.formalParams) {
                    e.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(ExplicitDispatch explicitDispatch) {
                printIndent(".");
                indent++;
                explicitDispatch.object.accept(this);
                if (explicitDispatch.type != null) {
                    explicitDispatch.type.accept(this);
                }
                explicitDispatch.funcName.accept(this);
                for (Expr e : explicitDispatch.formalParams) {
                    e.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(If iff) {
                printIndent("if");
                indent++;
                iff.cond.accept(this);
                iff.thenBranch.accept(this);
                iff.elseBranch.accept(this);
                indent--;

                return null;
            }

            @Override
            public Void visit(While whilee) {
                printIndent("while");
                indent++;
                whilee.cond.accept(this);
                whilee.body.accept(this);
                indent--;

                return null;
            }

            @Override
            public Void visit(Local local) {
                printIndent("local");
                indent++;
                local.id.accept(this);
                local.type.accept(this);
                if(local.expression != null) {
                    local.expression.accept(this);
                }
                indent--;

                return null;
            }

            @Override
            public Void visit(Let let) {
                printIndent("let");
                indent++;
                for(Local l : let.locals) {
                    l.accept(this);
                }
                let.body.accept(this);
                indent--;
                return null;
            }

            @Override
            public Void visit(Case casee) {
                printIndent("case");
                indent++;
                casee.ref_expr.accept(this);
                for(CaseBranch branches : casee.case_branches) {
                    branches.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(CaseBranch caseBranch) {
                printIndent("case branch");
                indent++;
                caseBranch.id.accept(this);
                caseBranch.type.accept(this);
                if(caseBranch.e != null) {
                    caseBranch.e.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Block block) {
                printIndent("block");
                indent++;
                for(Expr sub_expr : block.subexpressions) {
                    sub_expr.accept(this);
                }
                indent--;
                return null;
            }

            void printIndent(String str) {
                for (int i = 0; i < indent; i++)
                    System.out.print("  ");
                System.out.println(str);
            }
        };

        ast.accept(printVisitor);
    }
}
