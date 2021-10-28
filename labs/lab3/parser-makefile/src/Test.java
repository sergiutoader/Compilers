import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Test {
    /* Variable pentru contorizarea definițiilor de variabile și funcții */
    public static int varDefs = 0;
    public static int funcDefs = 0;
    public static StringBuilder task3buffer = new StringBuilder();
    public static List<Integer> task3list = new LinkedList<Integer>();

    public static CPLangParser readFromFile(String filename)
            throws IOException {
        var input = CharStreams.fromFileName(filename);

        var lexer = new CPLangLexer(input);
        var tokenStream = new CommonTokenStream(lexer);

        /* Decomentează pentru a afișa mulțimea de tokeni generată
         * după analiza lexicală pe fișierul de intrare.
         **/
		
		/*
		tokenStream.fill();
		List<Token> tokens = tokenStream.getTokens();
		for (var token : tokens) {
		    var text = token.getText();
		    var type = CPLangLexer.VOCABULARY.getSymbolicName(token.getType());
		    
		    System.out.println(text + " : " + type);
		}
		*/

        return new CPLangParser(tokenStream);
    }

    public static void task12reset() {
        varDefs = 0;
        funcDefs = 0;
    }

    public static CPLangParserListener task12setup() {
        // Interfața CPLangParserListener conține, pentru fiecare alternativă
        // etichetată, câte o pereche de metode enter/exit. Spre exemplu,
        // pentru eticheta if, avem perechea de metode enterIf(IfContext)
        // și exitIf(IfContext). Clasa CPLangParserBaseListener oferă
        // implementări goale ale acestor metode, astfel încât noi să putem
        // supradefini doar metodele de interes.
        //
        // Listenerii au avantajul că parcurgerea arborelui de derivare este
        // realizată automat, pe baza unui walker, ca mai jos. Dezavantajul
        // constă în faptul că este parcurs întregul arbore de derivare, chiar
        // dacă pe noi ne intesează doar anumite noduri particulare.
        return new CPLangParserBaseListener() {
            /* TODO 2
             * Suprascrie metodele din Listener pentru a contoriza numărul
             * de definiții de variabile, atât globale cât și ca parametri
             * formali ai unor funcții și, separat, numărul
             * de definiții de funcții.
             * !!! Variabilele folosite pentru contori sunt varDefs și funcDefs.
             *
             * HINT: Uită-te în clasa CPLangParserBaseListener
             * la metodele exit<ETICHETA>, unde eticheta corespunde unei
             * reguli din gramatică. Trebuie să suprascrii cele 3 metode.
             */

            @Override public void exitVariable_definition(CPLangParser.Variable_definitionContext ctx) {
                varDefs++;
            }

            @Override public void exitFunction_definition(CPLangParser.Function_definitionContext ctx) {
                funcDefs++;
            }

            @Override public void exitFunc_arg(CPLangParser.Func_argContext ctx) {
                varDefs++;
            }
        };
    }

    public static void task12() throws IOException {
        /* Fișierul manual.txt conține un program CPLang.
         * Fișierul reference1-2.txt poate fi folosit ca referință.
         */
        var parser = readFromFile("manual.txt");

        /* TODO 1 înlocuiește expr cu regula de start din gramatică */
        var tree = parser.prog();
        System.out.println(tree.toStringTree(parser));

        var listener = task12setup();

        // Un walker realizează o parcurgere în adâncime a arborelui de
        // derivare, invocând la momentul potrivit metodele enter/exit.
        var walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        /* După parcurgerea arborelui de derivare,
         * afișăm contorii pentru definiții.
         */
        System.out.println("Definitii de variable: " + varDefs);
        System.out.println("Definitii de functii: " + funcDefs);
    }

    public static CPLangParserVisitor task3setup() {
        /* Interfața CPLangParserVisitor conține câte o metodă pentru
         * fiecare alternativă etichetată. Spre exemplu, pentru eticheta mul,
         * avem metoda T visitMul(MulContext).
         * Clasa CPLangParserBaseVisitor<T> oferă implementări implicite
         * ale acestor metode, a.i noi să putem supradefini doar metodele
         * de interes.
         *
         * De remarcat că, spre deosebire de listeners, metodele de vizitare
         * pot întoarce și o valoare utilă, care poate fi interpretată
         * recursiv. Acest lucru, alături de faptul că putem vizita doar
         * nodurile de interes pentru noi, constituie avantajul vizitatorilor.
         * Dezavantajul constă în faptul că este necesară vizitarea explicită
         * a copiilor, mai ales când trebuie să parcurgem întregul arbore.
         *
         * Pentru acest task, fiecare metodă visit va întoarce un Integer.
         *
         * TODO 3
         * Pentru acest task, programele CPLang vor conține doar
         * expresii aritmetice cu literali numerici, fără definiții sau
         * referiri la variabile!!!
         * Pentru rezolvare, trebuie suprascrise metodele visit, doar pentru
         * etichetele corespunzatoare literalilor si operatiilor aritmetice.
         * Ignoram restul.
         **/

        return new CPLangParserBaseVisitor<Integer>() {
            @Override
            public Integer visitProg(CPLangParser.ProgContext ctx) {
                for (var expr : ctx.expr()) {
                    Integer res = visit(expr);
                    task3list.add(res);
                    task3buffer.append(expr.getText() + " = " + res + "\n");
                }
                return 0;
            }

            /* TODO 3 Suprascrie visit<eticheta> unde eticheta corespunde
             * operațiilor aritmetice, operațiilor parantezate, minus-unar și
             * literalilor întregi */

            @Override
            public Integer visitInt(CPLangParser.IntContext ctx) {
                return Integer.parseInt(ctx.getText());
            }

            @Override
            public Integer visitMinus(CPLangParser.MinusContext ctx) {
                var expr1 = ctx.expr(0);
                var expr2 = ctx.expr(1);
                return visit(expr1) - visit(expr2);
            }

            @Override
            public Integer visitMult(CPLangParser.MultContext ctx) {
                var expr1 = ctx.expr(0);
                var expr2 = ctx.expr(1);
                return visit(expr1) * visit(expr2);
            }

            @Override
            public Integer visitUnary_minus(CPLangParser.Unary_minusContext ctx) {
                var expr = ctx.expr();
                return -visit(expr);
            }

            @Override
            public Integer visitPlus(CPLangParser.PlusContext ctx) {
                var expr1 = ctx.expr(0);
                var expr2 = ctx.expr(1);
                return visit(expr1) + visit(expr2);
            }

            @Override
            public Integer visitDiv(CPLangParser.DivContext ctx) {
                var expr1 = ctx.expr(0);
                var expr2 = ctx.expr(1);
                return visit(expr1) / visit(expr2);
            }

            @Override
            public Integer visitParen(CPLangParser.ParenContext ctx) {
                return visit(ctx.expr());
            }
        };
    }

    public static void task3() throws IOException {
        var visitor = task3setup();

        /* Fișierul input3.txt conține un program CPLang restricționat,
         * fără definiții, fără referiri la variabile sau funcții.
         * Conține o serie de instrucțiuni aritmetice, doar pentru literali
         * întregi.
         * Fișierul reference3.txt continue output-ul corespunzător.
         **/
        var parser = readFromFile("input3.txt");

        /* TODO 3 înlocuiește expr cu regula de start */
        var tree = parser.prog();

        visitor.visit(tree);
        System.out.println(task3buffer);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Which exercise?: [1, 2, 3]");
            return;
        }

        /* Pentru Eclipse, la Run As => Run configuration => Arguments
         *  => pune 1, 2 sau 3 */
        if (args[0].equals("1") || args[0].equals("2"))
            task12();
        else if (args[0].equals("3"))
            task3();
        else {
            System.err.println("Provide correct number!");
            return;
        }
    }
};
