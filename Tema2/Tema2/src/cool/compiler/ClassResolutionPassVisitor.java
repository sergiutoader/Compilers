package cool.compiler;

import cool.structures.ClassSymbol;
import cool.structures.SymbolTable;

public class ClassResolutionPassVisitor implements ASTVisitor<Void> {

    @Override
    public Void visit(Prog prog) {
        for(Class cls : prog.classes) {
            cls.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Class c) {
        var classSymbol = (ClassSymbol)c.classType.getScope();
        if (classSymbol == null) {
            return null;
        }

        var className = c.classType.name.getText();
        var parentName = classSymbol.getParentName();

        var potentialParentScope = ClassDefinitionPassVisitor.currentScope.lookup(parentName);
        if(parentName != null && potentialParentScope == null) {
            SymbolTable.error(c.ctx, c.parentClassType.name, "Class " + className + " has undefined parent " + parentName);
        } else {
            classSymbol.setParent((ClassSymbol)potentialParentScope);
        }

        c.parentClassType.setScope((ClassSymbol) potentialParentScope);

        return null;
    }

    @Override
    public Void visit(Method method) {
        return null;
    }

    @Override
    public Void visit(Attribute attribute) {
        return null;
    }

    @Override
    public Void visit(Type typeId) {
        return null;
    }

    @Override
    public Void visit(ObjectId objectId) {
        return null;
    }

    @Override
    public Void visit(Formal formal) {
        return null;
    }

    @Override
    public Void visit(Int i) {
        return null;
    }

    @Override
    public Void visit(Stringg string) {
        return null;
    }

    @Override
    public Void visit(True t) {
        return null;
    }

    @Override
    public Void visit(False f) {
        return null;
    }

    @Override
    public Void visit(Id id) {
        return null;
    }

    @Override
    public Void visit(Paren paren) {
        return null;
    }

    @Override
    public Void visit(MultDiv multDiv) {
        return null;
    }

    @Override
    public Void visit(PlusMinus plusMinus) {
        return null;
    }

    @Override
    public Void visit(Negative negative) {
        return null;
    }

    @Override
    public Void visit(Relational relational) {
        return null;
    }

    @Override
    public Void visit(Not not) {
        return null;
    }

    @Override
    public Void visit(Assign assign) {
        return null;
    }

    @Override
    public Void visit(IsVoid isVoid) {
        return null;
    }

    @Override
    public Void visit(New n) {
        return null;
    }

    @Override
    public Void visit(ImplicitDispatch implicitDispatch) {
        return null;
    }

    @Override
    public Void visit(ExplicitDispatch explicitDispatch) {
        return null;
    }

    @Override
    public Void visit(If iff) {
        return null;
    }

    @Override
    public Void visit(While whilee) {
        return null;
    }

    @Override
    public Void visit(Local local) {
        return null;
    }

    @Override
    public Void visit(Let let) {
        return null;
    }

    @Override
    public Void visit(Case casee) {
        return null;
    }

    @Override
    public Void visit(CaseBranch caseBranch) {
        return null;
    }

    @Override
    public Void visit(Block block) {
        return null;
    }
}
