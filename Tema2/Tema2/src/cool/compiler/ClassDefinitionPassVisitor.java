package cool.compiler;

import cool.structures.*;

import java.util.HashSet;

public class ClassDefinitionPassVisitor implements ASTVisitor<Void> {

    public static Scope globalScope;
    public static Scope currentScope;
    private final HashSet<String> illegalClassNames;
    private final HashSet<String> illegalExtendClassNames;
    public static HashSet<String> predefinedTypes;

    public ClassDefinitionPassVisitor() {
        illegalClassNames = new HashSet<>();
        illegalClassNames.add("Int");
        illegalClassNames.add("Bool");
        illegalClassNames.add("String");
        illegalClassNames.add("Object");
        illegalClassNames.add("IO");


        illegalExtendClassNames = new HashSet<>();
        illegalExtendClassNames.add("Int");
        illegalExtendClassNames.add("Bool");
        illegalExtendClassNames.add("String");
        illegalExtendClassNames.add("SELF_TYPE");

        predefinedTypes = new HashSet<>();
        predefinedTypes.add("Int");
        predefinedTypes.add("Bool");
        predefinedTypes.add("String");
        predefinedTypes.add("Object");
        predefinedTypes.add("IO");
        predefinedTypes.add("SELF_TYPE");
    }

    @Override
    public Void visit(Prog prog) {
        currentScope = new DefaultScope(null);
        globalScope = currentScope;

        var objectClass = new ClassSymbol("Object", null, null, null);
        globalScope.add(objectClass);
        globalScope.add(new ClassSymbol("Int", "Object", objectClass, null));
        globalScope.add(new ClassSymbol("Bool", "Object", objectClass, null));
        globalScope.add(new ClassSymbol("String", "Object", objectClass, null));

        for(Class cls : prog.classes) {
            cls.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Class c) {
        var className = c.classType.name.getText();
        String parentName = null;
        if (c.parentClassType.name != null) {
            parentName = c.parentClassType.name.getText();
        }

        if (className.equals("SELF_TYPE")) {
            SymbolTable.error(c.ctx, c.classType.name, "Class has illegal name " + className);
            return null;
        }
        if (illegalExtendClassNames.contains(parentName)) {
            SymbolTable.error(c.ctx, c.parentClassType.name, "Class " + className +  " has illegal parent " + parentName);
            parentName = null;
        }

        if (currentScope.lookup(className) != null || illegalClassNames.contains(className)) {
            SymbolTable.error(c.ctx, c.classType.name, "Class " + className + " is redefined");
            return null;
        }

        var symbol = new ClassSymbol(className, parentName, currentScope, c.classType.name);
        currentScope.add(symbol);
        c.classType.setScope(symbol);

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
