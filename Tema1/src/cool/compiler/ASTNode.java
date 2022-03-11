package cool.compiler;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public abstract class ASTNode {
    public Token token;
    public ASTNode(Token token) {
        this.token = token;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

class Type extends ASTNode {
    public Type(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class ObjectId extends ASTNode {
    ObjectId(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Expr extends ASTNode {

    public Expr(Token token) {
        super(token);
    }
}

class Block extends Expr {
    public ArrayList<Expr> subexpressions;

    public Block(Token token, ArrayList<Expr> subexpressions) {
        super(token);
        this.subexpressions = subexpressions;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class New extends Expr {
    public ObjectId id;

    public New(Token token, ObjectId id) {
        super(token);
        this.id = id;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Local extends ASTNode {
    public ObjectId id;
    public Type type;
    public Expr expression;

    public Local(Token token, ObjectId id, Type type, Expr expression) {
        super(token);
        this.id = id;
        this.type = type;
        this.expression = expression;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CaseBranch extends ASTNode {
    public ObjectId id;
    public Type type;
    public Expr e;

    public CaseBranch(Token token, ObjectId id, Type type, Expr e) {
        super(token);
        this.id = id;
        this.type = type;
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Case extends Expr {
    public Expr ref_expr;
    public ArrayList<CaseBranch> case_branches;

    public Case(Token token, Expr ref_expr, ArrayList<CaseBranch> case_branches) {
        super(token);
        this.ref_expr = ref_expr;
        this.case_branches = case_branches;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Let extends Expr {
    public ArrayList<Local> locals;
    public Expr body;

    public Let(Token token, ArrayList<Local> locals, Expr body) {
        super(token);
        this.locals = locals;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class While extends Expr {
    public Expr cond;
    public Expr body;

    public While(Token token, Expr cond, Expr body) {
        super(token);
        this.cond = cond;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class If extends Expr {
    public Expr cond;
    public Expr thenBranch;
    public Expr elseBranch;

    public If(Token token, Expr cond, Expr thenBranch, Expr elseBranch) {
        super(token);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class ImplicitDispatch extends Expr {
    public ObjectId funcName;
    public ArrayList<Expr> formalParams;

    public ImplicitDispatch(Token token, ObjectId funcName, ArrayList<Expr> formalParams) {
        super(token);
        this.funcName = funcName;
        this.formalParams = formalParams;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class ExplicitDispatch extends Expr {
    public Expr object;
    public Type type;
    public ObjectId funcName;
    public ArrayList<Expr> formalParams;

    public ExplicitDispatch(Token token, Expr object, Type type, ObjectId funcName, ArrayList<Expr> formalParams) {
        super(token);
        this.object = object;
        this.type = type;
        this.funcName = funcName;
        this.formalParams = formalParams;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class IsVoid extends Expr {
    public Expr e;

    public IsVoid(Token token, Expr e) {
        super(token);
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Assign extends Expr {
    public Expr e;
    public ObjectId id;

    public Assign(Token token, ObjectId id, Expr e) {
        super(token);
        this.id = id;
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Not extends Expr {
    public Expr e;

    public Not(Token token, Expr e) {
        super(token);
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Relational extends Expr {
    public Expr left;
    public Expr right;
    public Token op;

    public Relational(Token token, Expr left, Token op, Expr right) {
        super(token);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Negative extends Expr {
    public Expr e;

    public Negative(Token token, Expr e) {
        super(token);
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class MultDiv extends Expr {
    public Expr left;
    public Expr right;
    public Token op;

    public MultDiv(Token token, Expr left, Token op, Expr right) {
        super(token);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class PlusMinus extends Expr {
    public Expr left;
    public Expr right;
    public Token op;

    public PlusMinus(Token token, Expr left, Token op, Expr right) {
        super(token);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Paren extends Expr {
    public Expr e;

    public Paren(Token token, Expr e) {
        super(token);
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Id extends Expr {
    public ObjectId value;

    public Id(Token token) {
        super(token);
        this.value = new ObjectId(token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Stringg extends Expr {
    public Token value;

    public Stringg(Token token) {
        super(token);
        this.value = token;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class True extends Expr {
    public Token value;

    public True(Token token) {
        super(token);
        this.value = token;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class False extends Expr {
    public Token value;

    public False(Token token) {
        super(token);
        this.value = token;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Int extends Expr {
    public Token value;

    public Int(Token token) {
        super(token);
        this.value = token;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

abstract class Feature extends ASTNode {
    public Feature(Token token) {
        super(token);
    }
}

class Formal extends ASTNode {
    public ObjectId id;
    public Type type;

    public Formal(Token token, ObjectId id, Type type) {
        super(token);
        this.id = id;
        this.type = type;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Method extends Feature {
    public ObjectId name;
    public ArrayList<Formal> formals;
    public Type type;
    public Expr body;

    public Method(Token token, ObjectId name, ArrayList<Formal> formals, Type type, Expr body) {
        super(token);
        this.name = name;
        this.formals = formals;
        this.type = type;
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Attribute extends Feature {
    public ObjectId name;
    public Type type;
    public Expr defaultVal;

    public Attribute(Token token, ObjectId name, Type type, Expr defaultVal) {
        super(token);
        this.name = name;
        this.type = type;
        this.defaultVal = defaultVal;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}


class Class extends ASTNode {
    public Type name;
    public Type parentName;
    public ArrayList<Feature> features;

    public Class(Token token, Type name, Type parentName, ArrayList<Feature> features) {
        super(token);
        this.name = name;
        this.parentName = parentName;
        this.features = features;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}


class Prog extends ASTNode {
    public ArrayList<Class> classes;

    public Prog(Token token, ArrayList<Class> classes) {
        super(token);
        this.classes = classes;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}