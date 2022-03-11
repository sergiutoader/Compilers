package cool.compiler;
import cool.structures.MethodSymbol;
import cool.structures.Scope;
import cool.structures.Symbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public abstract class ASTNode {
    public ParserRuleContext ctx;
    public ASTNode(ParserRuleContext ctx) {
        this.ctx = ctx;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

class Type extends ASTNode {
    public Token name;
    private Scope scope;
    public Type(ParserRuleContext ctx, Token name) {
        super(ctx);
        this.name = name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public void setScope(Scope scope) {
         this.scope = scope;
    }

    public Scope getScope() {
         return scope;
    }
}

class ObjectId extends ASTNode {
    private Scope scope;

    ObjectId(ParserRuleContext ctx) {
        super(ctx);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return ctx.getText();
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        return scope;
    }
}

class Expr extends ASTNode {
    private Scope scope;

    public Expr(ParserRuleContext ctx) {
        super(ctx);
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        return scope;
    }
}

class Block extends Expr {
    public ArrayList<Expr> subexpressions;

    public Block(ParserRuleContext ctx, ArrayList<Expr> subexpressions) {
        super(ctx);
        this.subexpressions = subexpressions;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class New extends Expr {
    public Type id;

    public New(ParserRuleContext ctx, Type id) {
        super(ctx);
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

    public Local(ParserRuleContext ctx, ObjectId id, Type type, Expr expression) {
        super(ctx);
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
    private Scope scope;

    public CaseBranch(ParserRuleContext ctx, ObjectId id, Type type, Expr e) {
        super(ctx);
        this.id = id;
        this.type = type;
        this.e = e;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        return scope;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Case extends Expr {
    public Expr ref_expr;
    public ArrayList<CaseBranch> case_branches;

    public Case(ParserRuleContext ctx, Expr ref_expr, ArrayList<CaseBranch> case_branches) {
        super(ctx);
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

    public Let(ParserRuleContext ctx, ArrayList<Local> locals, Expr body) {
        super(ctx);
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

    public While(ParserRuleContext ctx, Expr cond, Expr body) {
        super(ctx);
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

    public If(ParserRuleContext ctx, Expr cond, Expr thenBranch, Expr elseBranch) {
        super(ctx);
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

    public ImplicitDispatch(ParserRuleContext ctx, ObjectId funcName, ArrayList<Expr> formalParams) {
        super(ctx);
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

    public ExplicitDispatch(ParserRuleContext ctx, Expr object, Type type, ObjectId funcName, ArrayList<Expr> formalParams) {
        super(ctx);
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

    public IsVoid(ParserRuleContext ctx, Expr e) {
        super(ctx);
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

    public Assign(ParserRuleContext ctx, ObjectId id, Expr e) {
        super(ctx);
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

    public Not(ParserRuleContext ctx, Expr e) {
        super(ctx);
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

    public Relational(ParserRuleContext ctx, Expr left, Token op, Expr right) {
        super(ctx);
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

    public Negative(ParserRuleContext ctx, Expr e) {
        super(ctx);
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

    public MultDiv(ParserRuleContext ctx, Expr left, Token op, Expr right) {
        super(ctx);
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

    public PlusMinus(ParserRuleContext ctx, Expr left, Token op, Expr right) {
        super(ctx);
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

    public Paren(ParserRuleContext ctx, Expr e) {
        super(ctx);
        this.e = e;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Id extends Expr {
    public ObjectId value;

    public Id(ParserRuleContext ctx) {
        super(ctx);
        this.value = new ObjectId(ctx);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Stringg extends Expr {
    public Token value;

    public Stringg(ParserRuleContext ctx) {
        super(ctx);
        this.value = ctx.start;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class True extends Expr {
    public Token value;

    public True(ParserRuleContext ctx) {
        super(ctx);
        this.value = ctx.start;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class False extends Expr {
    public Token value;

    public False(ParserRuleContext ctx) {
        super(ctx);
        this.value = ctx.start;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Int extends Expr {
    public Token value;

    public Int(ParserRuleContext ctx) {
        super(ctx);
        this.value = ctx.start;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

abstract class Feature extends ASTNode {
    private Scope scope;
    public Feature(ParserRuleContext ctx) {
        super(ctx);
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        return scope;
    }
}

class Formal extends ASTNode {
    public ObjectId id;
    public Type type;

    public Formal(ParserRuleContext ctx, ObjectId id, Type type) {
        super(ctx);
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

    public Method(ParserRuleContext ctx, ObjectId name, ArrayList<Formal> formals, Type type, Expr body) {
        super(ctx);
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

    public Attribute(ParserRuleContext ctx, ObjectId name, Type type, Expr defaultVal) {
        super(ctx);
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
    public Type classType;
    public Type parentClassType;
    public ArrayList<Feature> features;
    private boolean hasInheritanceCycle;

    public Class(ParserRuleContext ctx, Type classType, Type parentClassType, ArrayList<Feature> features) {
        super(ctx);
        this.classType = classType;
        this.parentClassType = parentClassType;
        this.features = features;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public boolean hasInheritanceCycle() {
        return hasInheritanceCycle;
    }

    public void setInheritanceCycle(boolean hasInheritanceCycle) {
        this.hasInheritanceCycle = hasInheritanceCycle;
    }
}


class Prog extends ASTNode {
    public ArrayList<Class> classes;

    public Prog(ParserRuleContext ctx, ArrayList<Class> classes) {
        super(ctx);
        this.classes = classes;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}