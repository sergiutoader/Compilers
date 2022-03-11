package cool.compiler;

import cool.structures.*;

import java.util.ArrayList;

import static cool.compiler.ClassDefinitionPassVisitor.*;

public class ResolutionVisitor implements ASTVisitor<TypeSymbol> {
    private ClassSymbol getParentClass(ClassSymbol class1, ClassSymbol class2) {
        if (class1 == null || class2 == null) {
            return null;
        }

        if (class1.getName().equals(class2.getName())) {
            return class1;
        }

        ArrayList <ClassSymbol> class1Hierarchy = new ArrayList<>();
        ArrayList <ClassSymbol> class2Hierarchy = new ArrayList<>();

        class1Hierarchy.add(class1);
        class2Hierarchy.add(class2);

        while (class1 != null && !(class1.getName().equals("Object"))) {
            class1 = (ClassSymbol) class1.getParent();
            class1Hierarchy.add(class1);
        }

        while (class2 != null && !(class2.getName().equals("Object"))) {
            class2 = (ClassSymbol) class2.getParent();
            class2Hierarchy.add(class2);
        }

        for (ClassSymbol class1Symbol : class1Hierarchy) {
            for (ClassSymbol class2Symbol : class2Hierarchy) {
                if (class1Symbol.getName().equals(class2Symbol.getName())) {
                    return class1Symbol;
                }
            }
        }
        return null;
    }


    @Override
    public TypeSymbol visit(Prog prog) {
        for(Class c : prog.classes) {
            c.accept(this);
        }

        return null;
    }

    @Override
    public TypeSymbol visit(Class c) {
        var initialScope = (ClassSymbol) c.classType.getScope();
        var aux = currentScope;
        currentScope = initialScope;
        for(Feature f : c.features) {
            f.accept(this);
        }
        currentScope = aux;
        return null;
    }

    @Override
    public TypeSymbol visit(Method method) {
        var methodName = method.name.ctx.start.getText();
        var mainClass = (ClassSymbol) method.getScope();
        var mainMethod = (MethodSymbol)mainClass.lookup(methodName, true);
        if (mainMethod == null) {
            return null;
        }

        var parentClass = mainClass.getParent();

        while(parentClass instanceof ClassSymbol) {

            var parentMethod = (MethodSymbol) ((ClassSymbol) parentClass).lookup(methodName, true);
            // check if it contains method methodName
            // extract the formal parameters and check how many of them are there
            if (parentMethod != null) {
                var parentFormals = parentMethod.getFormals();
                var mainFormals = mainMethod.getFormals();
                if (parentFormals.size() != mainFormals.size()) {
                    SymbolTable.error(method.ctx, method.name.ctx.start, "Class " + mainClass
                            + " overrides method " + mainMethod
                            + " with different number of formal parameters");
                 } else {
                    for(int i = 0; i < mainFormals.size(); i++) {
                        var formal = mainFormals.get(i);
                        var formalName = formal.getName();
                        var mainType = mainFormals.get(i).getTypeSymbol().getName();
                        var parentType = parentFormals.get(i).getTypeSymbol().getName();
                        if (!mainType.equals(parentType)) {
                            SymbolTable.error(method.ctx, method.formals.get(i).type.name, "Class " + mainClass
                                    + " overrides method " + mainMethod
                                    + " but changes type of formal parameter "
                                    + formalName + " from " + parentType + " to " + mainType);
                        }
                    }
                }
                var mainType = mainMethod.getTypeSymbol().getName();
                var parentType = parentMethod.getTypeSymbol().getName();
                if (!mainType.equals(parentType)) {
                    SymbolTable.error(method.ctx, method.type.name, "Class " + mainClass
                            + " overrides method " + mainMethod
                            + " but changes return type from " + parentType + " to " + mainType);
                }
            }

            parentClass = parentClass.getParent();
        }

        for (Formal f : method.formals) {
            f.accept(this);
        }
        var aux = currentScope;
        currentScope = mainMethod;
        var bodySymbol = method.body.accept(this);
        currentScope = aux;
        if (bodySymbol == null) {
            return null;
        }

        var returnType = bodySymbol.getName();
        var expectedReturnType = method.type.accept(this).getName();
        Scope childClass = (ClassSymbol)globalScope.lookup(returnType);
        parentClass = (ClassSymbol)globalScope.lookup(expectedReturnType);
        if (parentClass == null) {
            return null;
        }

        boolean isChildClass = false;
        while(childClass != null) {
            if (! (childClass instanceof ClassSymbol)) {
                break;
            }
            var curr = (ClassSymbol)childClass;

            if (parentClass.equals(curr)) {
                isChildClass = true;
                break;
            }

            childClass = childClass.getParent();
        }

        if(!isChildClass && !expectedReturnType.equals("Object")) {
            SymbolTable.error(method.ctx, method.body.ctx.start, "Type " +
                    returnType + " of the body of method " +
                    methodName + " is incompatible with declared return type " + expectedReturnType);
        }

        var scope = method.body.getScope();
        if (scope == null)
            return null;

        return ((MethodSymbol)scope).getTypeSymbol();
    }

    @Override
    public TypeSymbol visit(Formal formal) {
        return null;
    }

    @Override
    public TypeSymbol visit(Attribute attribute) {
        Scope currScope = currentScope;
        String attributeName = attribute.name.ctx.start.getText();
        while (true) {
            var parentScope = currScope.getParent();

            if(!(parentScope instanceof ClassSymbol)) {
                break;
            }

            var classScope = ((ClassSymbol)parentScope);
            if (classScope.lookup(attributeName, false) != null) {
                SymbolTable.error(attribute.ctx, attribute.name.ctx.start, "Class " + currentScope
                        + " redefines inherited attribute " + attributeName);
                return null;
            }

            currScope = parentScope;
        }

        currScope = currentScope;
        var attributeSymbol = ((ClassSymbol)currScope).lookup(attributeName, false);

        if (attributeSymbol == null) {
            return null;
        }
        var expectedType = ((AttributeSymbol)attributeSymbol).getTypeSymbol();

        if (attribute.defaultVal != null) {
            var defaultValType = attribute.defaultVal.accept(this);
            if (defaultValType == null) {
                SymbolTable.error(attribute.ctx, attribute.defaultVal.ctx.start, "Undefined identifier " + attribute.defaultVal.ctx.getText());
                return expectedType;
            }


            Scope childClass = (ClassSymbol) globalScope.lookup(defaultValType.getName());
            Scope parentClass = (ClassSymbol) globalScope.lookup(expectedType.getName());

            boolean isChildClass = false;
            while(childClass != null) {
                if (! (childClass instanceof ClassSymbol)) {
                    break;
                }
                var curr = (ClassSymbol)childClass;

                if (parentClass.equals(curr)) {
                    isChildClass = true;
                    break;
                }

                childClass = childClass.getParent();
            }

            if(!isChildClass) {
                SymbolTable.error(attribute.ctx, attribute.defaultVal.ctx.start, "Type " +
                        defaultValType.getName() + " of initialization expression of attribute " +
                        attributeName + " is incompatible with declared type " + expectedType);
            }
        }

        return expectedType;
    }

    @Override
    public TypeSymbol visit(Let let) {
        for (Local l : let.locals) {
            l.accept(this);
        }

        var aux = currentScope;
        currentScope = let.body.getScope();

        var result = let.body.accept(this);
        currentScope = aux;

        return result;
    }

    @Override
    public TypeSymbol visit(Local local) {
        if (local.expression == null) {
            return null;
        }
        var currScope = currentScope;
        currentScope = local.expression.getScope();

        TypeSymbol childSymbol = local.expression.accept(this);
        if (childSymbol == null) {
            SymbolTable.error(local.ctx, local.expression.ctx.start, "Undefined identifier " + local.expression.ctx.getText());
            return null;
        }
        TypeSymbol parentSymbol = local.type.accept(this);
        if (parentSymbol == null) {
            SymbolTable.error(local.ctx, local.id.ctx.start, "Undefined identifier " + local.id.ctx.getText());
            return null;
        }

        ClassSymbol parentClass = (ClassSymbol) globalScope.lookup(parentSymbol.getName());
        if (parentClass == null) {
            return TypeSymbol.OBJECT;
        }
        Scope childClass = (ClassSymbol) globalScope.lookup(childSymbol.getName());

        boolean isChildClass = false;
        while(childClass != null) {
            if (! (childClass instanceof ClassSymbol)) {
                break;
            }
            var curr = (ClassSymbol)childClass;

            if (parentClass.equals(curr)) {
                isChildClass = true;
                break;
            }

            childClass = childClass.getParent();
        }

        currentScope = currScope;
        if (!isChildClass) {
            SymbolTable.error(local.ctx, local.expression.ctx.start, "Type " + childSymbol.getName()
                    + " of initialization expression of identifier " + local.id.ctx.start.getText()
                    + " is incompatible with declared type " + parentSymbol.getName());
            return TypeSymbol.OBJECT;
        }

        return parentSymbol;
    }

    @Override
    public TypeSymbol visit(Type typeId) {
        return new TypeSymbol(typeId.name.getText());
    }

    @Override
    public TypeSymbol visit(ObjectId objectId) {
        Scope currScope = currentScope;
        String idName = objectId.ctx.start.getText();

        while (currScope != null) {
            if (currScope instanceof ClassSymbol) {
                var classScope = (ClassSymbol)currScope;
                var symbol = classScope.lookup(idName, false);
                if (symbol instanceof AttributeSymbol) {
                    var attributeSymbol = (AttributeSymbol)symbol;
                    return attributeSymbol.getTypeSymbol();
                }
            } else if (currScope instanceof LetSymbol) {
                var letScope = (LetSymbol)currScope;
                var symbol = letScope.lookup(idName);
                if (symbol instanceof VariableSymbol) {
                    var variableSymbol = (VariableSymbol)symbol;
                    return variableSymbol.getTypeSymbol();
                }
            } else if (currScope instanceof MethodSymbol) {
                var methodScope = (MethodSymbol)currScope;
                var symbol = methodScope.lookup(idName);
                if (symbol instanceof FormalSymbol) {
                    var formalSymbol = (FormalSymbol)symbol;
                    return formalSymbol.getTypeSymbol();
                }
            }

            currScope = currScope.getParent();
        }

        return null;
    }

    @Override
    public TypeSymbol visit(Int i) {
        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(Stringg string) {
        return TypeSymbol.STRING;
    }

    @Override
    public TypeSymbol visit(True t) {
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(False f) {
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(Id id) {
        var idName = id.value.ctx.start.getText();
        var currScope = currentScope;

        while (currScope != null) {
            if (currScope instanceof ClassSymbol) {
                var classScope = (ClassSymbol)currScope;
                var symbol = classScope.lookup(idName, false);
                if (symbol instanceof AttributeSymbol) {
                    var attributeSymbol = (AttributeSymbol)symbol;
                    return attributeSymbol.getTypeSymbol();
                }
            } else if (currScope instanceof LetSymbol) {
                var letScope = (LetSymbol)currScope;
                var symbol = letScope.lookup(idName);
                if (symbol instanceof VariableSymbol) {
                    var variableSymbol = (VariableSymbol)symbol;
                    return variableSymbol.getTypeSymbol();
                }
            } else if (currScope instanceof MethodSymbol) {
                var methodScope = (MethodSymbol)currScope;
                var symbol = methodScope.lookup(idName);
                if (symbol instanceof FormalSymbol) {
                    var formalSymbol = (FormalSymbol)symbol;
                    return formalSymbol.getTypeSymbol();
                }
            }

            currScope = currScope.getParent();
        }

        return null;
    }

    @Override
    public TypeSymbol visit(Paren paren) {
        return paren.e.accept(this);
    }

    @Override
    public TypeSymbol visit(MultDiv multDiv) {
        var leftType = multDiv.left.accept(this);
        var rightType = multDiv.right.accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if (!(leftType.equals(TypeSymbol.INT))) {
            SymbolTable.error(multDiv.ctx, multDiv.left.ctx.start, "Operand of " + multDiv.op.getText()
                    + " has type " + leftType.getName() + " instead of Int");
        } else if (!(rightType.equals(TypeSymbol.INT))) {
            SymbolTable.error(multDiv.ctx, multDiv.right.ctx.start, "Operand of " + multDiv.op.getText()
                    + " has type " + rightType.getName() + " instead of Int");
        }

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(PlusMinus plusMinus) {
        var leftType = plusMinus.left.accept(this);
        var rightType = plusMinus.right.accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if (!(leftType.equals(TypeSymbol.INT))) {
            SymbolTable.error(plusMinus.ctx, plusMinus.left.ctx.start, "Operand of " + plusMinus.op.getText()
                    + " has type " + leftType.getName() + " instead of Int");
        } else if (!(rightType.equals(TypeSymbol.INT))) {
            SymbolTable.error(plusMinus.ctx, plusMinus.right.ctx.start, "Operand of " + plusMinus.op.getText()
                    + " has type " + rightType.getName() + " instead of Int");
        }

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(Negative negative) {
        var type = negative.e.accept(this);
        if (!(type.equals(TypeSymbol.INT))) {
            SymbolTable.error(negative.ctx, negative.e.ctx.start, "Operand of ~ has type "
                    + type.getName() + " instead of Int");
        }
        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(Relational relational) {
        var leftType = relational.left.accept(this);
        var rightType = relational.right.accept(this);

        if ("=".equals(relational.op.getText())) {
            if ((TypeSymbol.PRIMITIVE_TYPES.contains(leftType) && !(leftType.equals(rightType)))
                || (TypeSymbol.PRIMITIVE_TYPES.contains(rightType) && !(leftType.equals(rightType)))
            ) {
                SymbolTable.error(relational.ctx, relational.op, "Cannot compare "
                        + leftType.getName() + " with " + rightType.getName());
            }
        } else {

            if (!(leftType.equals(TypeSymbol.INT))) {
                SymbolTable.error(relational.ctx, relational.left.ctx.start, "Operand of " + relational.op.getText()
                        + " has type " + leftType.getName() + " instead of Int");
            } else if (!(rightType.equals(TypeSymbol.INT))) {
                SymbolTable.error(relational.ctx, relational.right.ctx.start, "Operand of " + relational.op.getText()
                        + " has type " + rightType.getName() + " instead of Int");
            }

        }

        if (leftType == null || rightType == null) {
            return null;
        }

        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(Not not) {
        var type = not.e.accept(this);
        if (!(type.equals(TypeSymbol.BOOL))) {
            SymbolTable.error(not.ctx, not.e.ctx.start, "Operand of not has type "
                    + type.getName() + " instead of Bool");
        }
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(Assign assign) {

        var idName = assign.id.ctx.start.getText();
        if ("self".equals(idName)) {
            SymbolTable.error(assign.ctx, assign.ctx.start, "Cannot assign to " + idName);
            return TypeSymbol.OBJECT;
        }

        var idType = assign.id.accept(this);
        var exprType = assign.e.accept(this);

        if (idType == null) {
                SymbolTable.error(assign.ctx, assign.ctx.start, "Undefined identifier " + assign.id.ctx.start.getText());
                return TypeSymbol.OBJECT;
        }

        if (exprType == null) {
            return TypeSymbol.OBJECT;
        }

        if (!idType.equals(exprType)) {
            var leftClass = (ClassSymbol)globalScope.lookup(idType.getName());
            var rightSymbol = (ClassSymbol)globalScope.lookup(exprType.getName());
            if (leftClass == null) {
                return TypeSymbol.OBJECT;
            }

            Scope currScope = rightSymbol;

            boolean isChildClass = false;
            while(currScope != null) {
                if (! (currScope instanceof ClassSymbol)) {
                    break;
                }
                var rightClass = (ClassSymbol)currScope;

                if (leftClass.equals(rightClass)) {
                    isChildClass = true;
                    break;
                }

                currScope = rightClass.getParent();
            }

            if (!isChildClass) {
                SymbolTable.error(assign.ctx, assign.e.ctx.start, "Type " + exprType.getName() +
                        " of assigned expression is incompatible with declared type "
                        + idType.getName() + " of identifier " + idName);
                return TypeSymbol.OBJECT;
            }
        }


        return idType;
    }

    @Override
    public TypeSymbol visit(IsVoid isVoid) {
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(New n) {
        var className = n.id.name.getText();
        var classSymbol = globalScope.lookup(className);
        if(classSymbol == null) {
            SymbolTable.error(n.ctx, n.id.name, "new is used with undefined type " + className);
            return null;
        }

        return new TypeSymbol(className);
    }

    @Override
    public TypeSymbol visit(ImplicitDispatch implicitDispatch) {
        return null;
    }

    @Override
    public TypeSymbol visit(ExplicitDispatch explicitDispatch) {
        return null;
    }

    @Override
    public TypeSymbol visit(If iff) {
        var conditionType = iff.cond.accept(this);
        if (!conditionType.equals(TypeSymbol.BOOL)) {
            SymbolTable.error(iff.ctx, iff.cond.ctx.start, "If condition has type " + conditionType + " instead of Bool");
        }

        var thenType = iff.thenBranch.accept(this);
        var elseType = iff.elseBranch.accept(this);

        var thenClass = (ClassSymbol)globalScope.lookup(thenType.getName());
        var elseClass = (ClassSymbol)globalScope.lookup(elseType.getName());

        ClassSymbol object = getParentClass(thenClass, elseClass);
        if (object != null) return new TypeSymbol(object.getName());

        return TypeSymbol.OBJECT;
    }

    @Override
    public TypeSymbol visit(While whilee) {
        var conditionType = whilee.cond.accept(this);
        if (!conditionType.equals(TypeSymbol.BOOL)) {
            SymbolTable.error(whilee.ctx, whilee.cond.ctx.start, "While condition has type " + conditionType + " instead of Bool");
        }

        whilee.body.accept(this);

        return TypeSymbol.OBJECT;
    }

    @Override
    public TypeSymbol visit(Case casee) {
        
        ArrayList <TypeSymbol> caseTypes = new ArrayList<>();
        ArrayList <ClassSymbol> caseClases = new ArrayList<>();

        for(CaseBranch c : casee.case_branches) {
            caseTypes.add(c.accept(this));
        }

        for (TypeSymbol c : caseTypes) {
            caseClases.add((ClassSymbol) globalScope.lookup(c.getName()));
        }

        var resultTypeSymbol = caseClases.get(0);
        for (int i = 1; i < caseClases.size(); i++) {
            resultTypeSymbol = getParentClass(resultTypeSymbol, caseClases.get(i));
        }

        if (resultTypeSymbol == null) {
            return TypeSymbol.OBJECT;
        }
        
        
        return new TypeSymbol(resultTypeSymbol.getName());
    }

    @Override
    public TypeSymbol visit(CaseBranch caseBranch) {
        return caseBranch.e.accept(this);
    }

    @Override
    public TypeSymbol visit(Block block) {
        return block.subexpressions.get(block.subexpressions.size() - 1).accept(this);
    }
}
