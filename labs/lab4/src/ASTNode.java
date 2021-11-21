import jdk.jfr.Experimental;
import org.antlr.v4.runtime.Token;
import java.util.*;

// Rădăcina ierarhiei de clase reprezentând nodurile arborelui de sintaxă
// abstractă (AST). Singura metodă permite primirea unui visitor.
public abstract class ASTNode {
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

// Used as argument for Program Node
abstract class Statement extends ASTNode {}

// Orice expresie.
abstract class Expression extends Statement {
    // Reținem un token descriptiv al expresiei, pentru a putea afișa ulterior
    // informații legate de linia și coloana eventualelor erori semantice.
    Token token;

    Expression(Token token) {
        this.token = token;
    }
}

// Definition
abstract class Definition extends Statement {
    Token token;

    Definition(Token start) {
        this.token = start;
    }
}

// Program
class Prog extends ASTNode {
    ArrayList<Statement> statements;
    Token token;

    Prog(ArrayList<Statement> statements, Token token) {
        this.token = token;
        this.statements = statements;
    }


    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}


// Identificatori
class Id extends Expression {
    Id(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Literali întregi
class Int extends Expression {
    Int(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Float
class Float extends Expression {
    Float(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Boolean
class Bool extends Expression {
    Bool(Token token) {
        super(token);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Construcția if.
class If extends Expression {
    // Sunt necesare trei câmpuri pentru cele trei componente ale expresiei.
    Expression cond;
    Expression thenBranch;
    Expression elseBranch;

    If(Expression cond,
       Expression thenBranch,
       Expression elseBranch,
       Token start) {
        super(start);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Apel de functie
class Call extends Expression {
    Id name;
    ArrayList<Expression> args;

    Call(Id name,
         ArrayList<Expression> args,
         Token start) {
        super(start);
        this.name = name;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Assignment
class Assign extends Expression {
    Id name;
    Expression e;

    Assign(Id name,
         Expression e,
         Token start) {
        super(start);
        this.name = name;
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}


// Paren
class Paren extends Expression {
    Expression e;

    Paren(Expression e,
           Token start) {
        super(start);
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Unary Minus
class UnaryMinus extends Expression {
    Expression e;

    UnaryMinus(Expression e,
          Token start) {
        super(start);
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Operator
class Op extends ASTNode {
    Token token;

    Op(Token token) {
        this.token = token;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Arithmetic expression
class Arithmetic extends Expression {
    Expression left;
    Op op;
    Expression right;

    Arithmetic(Expression left,
               Op op,
               Expression right,
               Token start) {
        super(start);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Relational expression
class Relational extends Expression {
    Expression left;
    Op op;
    Expression right;

    Relational(Expression left,
               Op op,
               Expression right,
               Token start) {
        super(start);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Type
class Type extends ASTNode {
    Token token;

    Type(Token token) {
        this.token = token;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Formal
class Formal extends ASTNode {
    Token token;
    Type type;
    Id id;

    Formal(Id id,
           Type type,
           Token start) {
        this.token = start;
        this.type = type;
        this.id = id;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

// Variable definition
class VarDef extends Definition {

    Type type;
    Id name;
    Expression init;

    VarDef(Type type,
           Id name,
           Expression init,
           Token start) {
        super(start);
        this.type = type;
        this.name = name;
        this.init = init;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class FuncDef extends Definition {

    Type type;
    Id name;
    ArrayList<Formal> formals;
    Expression body;

    FuncDef(Type type,
            Id name,
            ArrayList<Formal> formals,
            Expression body,
            Token start) {
        super(start);
        this.type = type;
        this.name = name;
        this.formals = formals;
        this.body = body;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
