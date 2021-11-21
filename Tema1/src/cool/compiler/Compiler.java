package cool.compiler;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import cool.lexer.*;
import cool.parser.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


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
                

            // Test lexer only.
            tokenStream.fill();
//            List<Token> tokens = tokenStream.getTokens();
//            tokens.stream().forEach(token -> {
//                var text = token.getText();
//                var name = CoolLexer.VOCABULARY.getSymbolicName(token.getType());
//
//                System.out.println(text + " : " + name);
//                //System.out.println(token);
//            });

            
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
                for (CoolParser.ClasssContext c : ctx.classs()) {
                    classes.add((Class) visit(c));
                }

                return new Prog(ctx.start, classes);
            }

            @Override public ASTNode visitClasss(CoolParser.ClasssContext ctx) {
                TypeId name = (TypeId) visit(ctx.TYPE_ID().get(0));
                TypeId parent_name = ctx.TYPE_ID().size() > 1 ? (TypeId) visit(ctx.TYPE_ID().get(1)) : null;

                ArrayList<Feature> features = new ArrayList<>();
                for (CoolParser.FeatureContext f : ctx.feature()) {
                    features.add((Feature) visit(f));
                }

                return new Class(ctx.start, name, parent_name, features);
            }

            @Override public ASTNode visitFeature(CoolParser.FeatureContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitFormal(CoolParser.FormalContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitNew(CoolParser.NewContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitString(CoolParser.StringContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitAssignment(CoolParser.AssignmentContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitIsvoid(CoolParser.IsvoidContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitFalse(CoolParser.FalseContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitArithmetic(CoolParser.ArithmeticContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitWhile(CoolParser.WhileContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitInt(CoolParser.IntContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitNegative(CoolParser.NegativeContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitNot(CoolParser.NotContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitParen(CoolParser.ParenContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitTrue(CoolParser.TrueContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitBlock(CoolParser.BlockContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitLet(CoolParser.LetContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitRelational(CoolParser.RelationalContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitId(CoolParser.IdContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitSpecified_dispatch(CoolParser.Specified_dispatchContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitUnspecified_dispatch(CoolParser.Unspecified_dispatchContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitIf(CoolParser.IfContext ctx) { return visitChildren(ctx); }

            @Override public ASTNode visitCase(CoolParser.CaseContext ctx) { return visitChildren(ctx); }
        };

        var ast = astConstructionVisitor.visit(globalTree);

        var printVisitor = new ASTVisitor<Void>() {
            int indent = 0;
            // TODO : implement the rest of the functions
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
                if (classs.parentName != null) {
                    classs.parentName.accept(this);
                }
                for(Feature f : classs.features) {
                    f.accept(this);
                }
                indent--;
                return null;
            }

            @Override
            public Void visit(Feature feature) {
                // TODO
                return null;
            }

            @Override
            public Void visit(TypeId typeId) {
                printIndent(typeId.token.getText());
                return null;
            }

            @Override
            public Void visit(ObjectId objectId) {
                printIndent(objectId.token.getText());
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
