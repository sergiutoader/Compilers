package cool.compiler;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

// Rădăcina ierarhiei de clase reprezentând nodurile arborelui de sintaxă
// abstractă (AST). Singura metodă permite primirea unui visitor.
public abstract class ASTNode {
    Token token;
    ASTNode(Token token) {
        this.token = token;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

class TypeId extends ASTNode {
    TypeId(Token token) {
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

class Feature extends ASTNode {


    Feature(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Class extends ASTNode {
    TypeId name;
    TypeId parentName;
    ArrayList<Feature> features;

    Class(Token token, TypeId name, TypeId parentName, ArrayList<Feature> features) {
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

// Program
class Prog extends ASTNode {
    ArrayList<Class> classes;

    Prog(Token token, ArrayList<Class> classes) {
        super(token);
        this.classes = classes;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}