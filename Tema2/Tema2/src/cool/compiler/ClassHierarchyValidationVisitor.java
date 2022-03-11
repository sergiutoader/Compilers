package cool.compiler;

import cool.structures.*;

import static cool.compiler.ClassDefinitionPassVisitor.*;
import static cool.compiler.ClassDefinitionPassVisitor.currentScope;

// Checks for loops inside class hierarchy
public class ClassHierarchyValidationVisitor implements ASTVisitor<Void> {

    @Override
    public Void visit(Prog prog) {
        for (Class c : prog.classes) {
            c.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Class c) {
        ClassSymbol initialScope = (ClassSymbol) c.classType.getScope();
        if (initialScope == null) {
            return null;
        }

        Scope currentScope = c.classType.getScope();
        while (currentScope instanceof ClassSymbol) {
            var parentScope = currentScope.getParent();

            if(!(parentScope instanceof ClassSymbol)) {
                break;
            }

            var classScope = ((ClassSymbol)parentScope);
            if (classScope.getName().equals(initialScope.getName())) {
                SymbolTable.error(c.ctx, c.classType.name, "Inheritance cycle for class " + initialScope.getName());
                c.setInheritanceCycle(true);
                break;
            }

            currentScope = parentScope;
        }

        if (!c.hasInheritanceCycle()) {
            var aux = ClassDefinitionPassVisitor.currentScope;
            ClassDefinitionPassVisitor.currentScope = initialScope;
            for (Feature f : c.features) {
                f.setScope(initialScope);
                f.accept(this);
            }
            ClassDefinitionPassVisitor.currentScope = aux;
        }
        return null;
    }

    @Override
    public Void visit(Method method) {
        var nameToken = method.name.ctx.start;
        var methodName = nameToken.getText();
        var currentScope = (ClassSymbol)method.getScope();

        if (currentScope.lookup(methodName, true) != null) {
            SymbolTable.error(method.ctx, nameToken, "Class "
                    + ClassDefinitionPassVisitor.currentScope
                    + " redefines method "
                    + methodName);
            return null;
        }

        var typeName = method.type.name.getText();
        if (globalScope.lookup(typeName) == null && !predefinedTypes.contains(typeName)) {
            SymbolTable.error(method.ctx, method.type.name, "Class " + currentScope.getName()
                    + " has method " + methodName
                    + " with undefined return type " + typeName);
            return null;
        }

        var symbol = new MethodSymbol(methodName, currentScope, new TypeSymbol(method.type.name.getText()));
        currentScope.add(symbol);

        ClassDefinitionPassVisitor.currentScope = symbol;
        for (Formal f : method.formals) {
            f.id.setScope(symbol);
            f.accept(this);
        }



        method.body.setScope(symbol);
        method.body.accept(this);
        ClassDefinitionPassVisitor.currentScope = currentScope;

        return null;
    }

    @Override
    public Void visit(Let let) {

        var currentScope = (MethodSymbol) let.getScope();
        LetSymbol letScope = new LetSymbol(currentScope);

        for (Local local : let.locals) {
            var localIdToken = local.id.ctx.start;
            var localId = localIdToken.getText();
            var localTypeToken = local.type.name;
            var localType = localTypeToken.getText();

            if (localId.equals("self")) {
                SymbolTable.error(let.ctx, localIdToken, "Let variable has illegal name " + localId);
            } else if (globalScope.lookup(localType) == null && !predefinedTypes.contains(localType)) {
                SymbolTable.error(let.ctx, localTypeToken, "Let variable " + localId + " has undefined type " + localType);
            } else {
                letScope.add(new VariableSymbol(localId, new TypeSymbol(localType)));

                if (local.expression != null) {
                    local.expression.setScope(letScope.getParent());
                    local.expression.accept(this);
                }

                letScope = new LetSymbol(letScope);
            }
        }

        ClassDefinitionPassVisitor.currentScope = letScope;
        let.body.setScope(letScope);
        let.body.accept(this);
        ClassDefinitionPassVisitor.currentScope = currentScope;

        return null;
    }

    @Override
    public Void visit(Case casee) {
        var currentScope = casee.getScope();

        for (CaseBranch caseBranch : casee.case_branches) {
            caseBranch.setScope(currentScope);
            caseBranch.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(CaseBranch caseBranch) {
        var currentScope = caseBranch.getScope();
        var caseBranchScope = new CaseBranchSymbol(currentScope);

        var nameToken = caseBranch.id.ctx.start;
        var id = nameToken.getText();
        var typeToken = caseBranch.type.name;
        var type = typeToken.getText();

        if (id.equals("self")) {
            SymbolTable.error(caseBranch.ctx, nameToken, "Case variable has illegal name " + id);
        } else if (type.equals("SELF_TYPE")) {
            SymbolTable.error(caseBranch.ctx, typeToken, "Case variable " + id + " has illegal type " + type);
        } else if (globalScope.lookup(type) == null && !predefinedTypes.contains(type)) {
            SymbolTable.error(caseBranch.ctx, typeToken, "Case variable " + id + " has undefined type " + type);
        }
        else {
            caseBranchScope.add(new VariableSymbol(id, new TypeSymbol(type)));
        }

        ClassDefinitionPassVisitor.currentScope = caseBranchScope;
        caseBranch.e.setScope(caseBranchScope);
        caseBranch.e.accept(this);
        ClassDefinitionPassVisitor.currentScope = currentScope;

        return null;
    }

    @Override
    public Void visit(Formal formal) {
        var nameToken = formal.id.ctx.start;
        var formalId = nameToken.getText();
        var typeToken = formal.type.name;
        var formalType = typeToken.getText();

        var parentMethod = (MethodSymbol) formal.id.getScope();
        var parentClass = (ClassSymbol) parentMethod.getParent();

        if (formalId.equals("self")) {
            SymbolTable.error(formal.ctx, nameToken, "Method " + parentMethod.getName()
                    + " of class " + parentClass.getName()
                    + " has formal parameter with illegal name " + formalId);
            return null;
        }

        if(currentScope.lookup(formalId) != null) {
            SymbolTable.error(formal.ctx, nameToken, "Method " + parentMethod.getName()
                    + " of class " + parentClass.getName()
                    + " redefines formal parameter " + formalId);
            return null;
        }

        if (formalType.equals("SELF_TYPE")) {
            SymbolTable.error(formal.ctx, typeToken, "Method " + parentMethod.getName()
                    + " of class " + parentClass.getName()
                    + " has formal parameter " + formalId + " with illegal type " + formalType);
            return null;
        }

        if (globalScope.lookup(formalType) == null && !predefinedTypes.contains(formalType)) {
            SymbolTable.error(formal.ctx, typeToken, "Method " + parentMethod.getName()
                    + " of class " + parentClass.getName()
                    + " has formal parameter " + formalId + " with undefined type " + formalType);
            return null;
        }

        var symbol = new FormalSymbol(formalId, new TypeSymbol(formalType));
        currentScope.add(symbol);

        return null;
    }

    @Override
    public Void visit(Attribute attribute) {
        var token = attribute.name.ctx.start;
        var attributeName = token.getText();
        var currentScope = (ClassSymbol)attribute.getScope();

        if (attributeName.equals("self")) {
            SymbolTable.error(attribute.ctx, token, "Class "
                    + ClassDefinitionPassVisitor.currentScope
                    + " has attribute with illegal name "
                    + attributeName);
            return null;
        }

        if (currentScope.lookup(attributeName, false) != null) {
            SymbolTable.error(attribute.ctx, token, "Class "
                    + ClassDefinitionPassVisitor.currentScope
                    + " redefines attribute "
                    + attributeName);
            return null;
        }

        String typeName = attribute.type.name.getText();
        if (!ClassDefinitionPassVisitor.predefinedTypes.contains(typeName)
                && globalScope.lookup(typeName) == null) {
            SymbolTable.error(attribute.ctx, attribute.type.name, "Class "
                    + ClassDefinitionPassVisitor.currentScope
                    + " has attribute "
                    + attributeName + " with undefined type " + typeName);
            return null;
        }

        var symbol = new AttributeSymbol(attributeName, new TypeSymbol(typeName));
        currentScope.add(symbol);

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
        assign.id.setScope(currentScope);
        assign.id.accept(this);
        assign.e.setScope(currentScope);
        assign.e.accept(this);

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
    public Void visit(Block block) {
        return null;
    }
}
