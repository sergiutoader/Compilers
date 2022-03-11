import java.util.ArrayList;

public class ResolutionPassVisitor implements ASTVisitor<TypeSymbol> {
    @Override
    public TypeSymbol visit(Program prog) {
        for (var stmt: prog.stmts) {
            stmt.accept(this);
        }
        return null;
    }
    
    @Override
    public TypeSymbol visit(Id id) {
        // Verificăm dacă într-adevăr avem de-a face cu o variabilă
        // sau cu o funcție, al doilea caz constituind eroare.
        // Puteți folosi instanceof.
        var symbol = (IdSymbol)id.getScope().lookup(id.getToken().getText());

        if (symbol instanceof FunctionSymbol) {
            ASTVisitor.error(id.getToken(),
                  id.getToken().getText() + " is not a variable");
            return null;
        }

        // TODO 2: Întoarcem informația de tip salvată deja în simbol încă de la
        // definirea variabilei.
        return symbol.getType();
    }
    
    @Override
    public TypeSymbol visit(VarDef varDef) {
        if (varDef.initValue != null) {
            var varType  = varDef.id.getSymbol().getType();
            var initType = varDef.initValue.accept(this);
            
            // TODO 5: Verificăm dacă expresia de inițializare are tipul potrivit
            // cu cel declarat pentru variabilă.

            if (varType == null || initType == null) {
                return null;
            }

            if(!varType.equals(initType)) {
                ASTVisitor.error(varDef.token, "Type of initilization expression does not match variable type");
            }

            return initType;
            
        }
        
        return null;
    }
    
    @Override
    public TypeSymbol visit(FuncDef funcDef) {
        var returnType = funcDef.id.getSymbol().getType();
        var bodyType = funcDef.body.accept(this);
        
        // TODO 5: Verificăm dacă tipul de retur declarat este compatibil
        // cu cel al corpului.
        if (bodyType == null || returnType == null) {
            return null;
        }

        if (!bodyType.equals(returnType)) {
            ASTVisitor.error(funcDef.token, "Return type does not match body type");
        }

        return returnType;
    }

    @Override
    public TypeSymbol visit(Call call) {
        // Verificăm dacă funcția există în scope. Nu am putut face
        // asta în prima trecere din cauza a forward references.
        //
        // De asemenea, verificăm că Id-ul pe care se face apelul de funcție
        // este, într-adevăr, o funcție și nu o variabilă.
        //
        // Hint: pentru a obține scope-ul, putem folosi call.id.getScope(),
        // setat la trecerea anterioară.
        var id = call.id;
        var symbol = id.getScope().lookup(id.getToken().getText());

        if (symbol == null) {
            ASTVisitor.error(id.getToken(),
                  id.getToken().getText() + " function undefined");
            return null;
        }

        if (!(symbol instanceof FunctionSymbol)) {
            ASTVisitor.error(id.getToken(),
                  id.getToken().getText() + " is not a function");
            return null;
        }
        
        var functionSymbol = (FunctionSymbol)symbol;
        id.setSymbol(functionSymbol);
        
        // TODO 6: Verificați dacă numărul parametrilor actuali coincide
        // cu cel al parametrilor formali, și că tipurile sunt compatibile.
        var formals = functionSymbol.getFormals();
        var actualExpr= call.args;
        if (actualExpr == null || formals == null) {
            return null;
        }

        var actuals = new ArrayList<>();
        for (Expression e : actualExpr) {
            actuals.add(e.accept(this));
        }

        if (actuals.size() != formals.size()) {
            ASTVisitor.error(call.getToken(), id.getToken().getText() + " applied to wrong number of arguments");
            return null;
        }

        int i = 0;
        for(Symbol s : formals.values()) {
            if(!((IdSymbol)s).getType().equals(actuals.get(i)))  {

                ASTVisitor.error(call.getToken(), "Argument " + (i+1) + " of " + id.token.getText() + " has wrong type");
                return null;
            }
            i++;
        }

        return functionSymbol.type;
    }   
    
    @Override
    public TypeSymbol visit(Assign assign) {
        var idType   = assign.id.accept(this);
        var exprType = assign.expr.accept(this);
        
        // TODO 5: Verificăm dacă expresia cu care se realizează atribuirea
        // are tipul potrivit cu cel declarat pentru variabilă.
        if (idType == null || exprType == null) {
            return null;
        }

        if (!idType.equals(exprType)) {
            ASTVisitor.error(assign.token, "Assignment with incompatible types");
            return null;
        }
        
        return idType;
    }

    @Override
    public TypeSymbol visit(If iff) {        
        var condType = iff.cond.accept(this);
        var thenType = iff.thenBranch.accept(this);
        var elseType = iff.elseBranch.accept(this);
        
        // TODO 4: Verificați tipurile celor 3 componente, afișați eroare
        // dacă este cazul, și precizați tipul expresiei.

        if(condType == null || thenType == null || elseType == null) {
            return null;
        }

        if (!condType.equals(TypeSymbol.BOOL) ) {
            ASTVisitor.error(iff.token, "Condition of if expression has type other than Bool");
            return null;
        }

        if (!thenType.equals(elseType)) {
            ASTVisitor.error(iff.token, "Branches of if expression have incompatible types");
            return null;
        }
        
        return thenType;
    }

    @Override
    public TypeSymbol visit(Type type) {
        return null;
    }

    @Override
    public TypeSymbol visit(Formal formal) {
        return formal.id.getSymbol().getType();
    }

    // Operații aritmetice.
    @Override
    public TypeSymbol visit(UnaryMinus uMinus) {
        var exprType = uMinus.expr.accept(this);

        if (exprType == null) {
            return null;
        }
        
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        if (!exprType.equals(TypeSymbol.INT) && !exprType.equals(TypeSymbol.FLOAT)) {
            ASTVisitor.error(uMinus.token, "Operand of - has incompatible type");
            return null;
        }
        return exprType;
    }

    @Override
    public TypeSymbol visit(Div div) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        var leftType = div.left.accept(this);
        var rightType = div.right.accept(this);

        if ( !((leftType.equals(TypeSymbol.INT) && rightType.equals(TypeSymbol.INT))) &&
                !((leftType.equals(TypeSymbol.FLOAT) && rightType.equals(TypeSymbol.FLOAT)))) {
            ASTVisitor.error(div.token, "Operands of / have incompatible types");
            return null;
        }

        return leftType;
    }

    @Override
    public TypeSymbol visit(Mult mult) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        var leftType = mult.left.accept(this);
        var rightType = mult.right.accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if ( !((leftType.equals(TypeSymbol.INT) && rightType.equals(TypeSymbol.INT))) &&
                !((leftType.equals(TypeSymbol.FLOAT) && rightType.equals(TypeSymbol.FLOAT)))) {
            ASTVisitor.error(mult.token, "Operands of * have incompatible types");
            return null;
        }

        return leftType;
    }

    @Override
    public TypeSymbol visit(Plus plus) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        var leftType = plus.left.accept(this);
        var rightType = plus.right.accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if ( !((leftType.equals(TypeSymbol.INT) && rightType.equals(TypeSymbol.INT))) &&
                !((leftType.equals(TypeSymbol.FLOAT) && rightType.equals(TypeSymbol.FLOAT)))) {
            ASTVisitor.error(plus.token, "Operands of + have incompatible types");
            return null;
        }
        return leftType;
    }

    @Override
    public TypeSymbol visit(Minus minus) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        var leftType = minus.left.accept(this);
        var rightType = minus.right.accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if ( !((leftType.equals(TypeSymbol.INT) && rightType.equals(TypeSymbol.INT))) &&
                !((leftType.equals(TypeSymbol.FLOAT) && rightType.equals(TypeSymbol.FLOAT)))) {
            ASTVisitor.error(minus.token, "Operands of - have incompatible types");
            return null;
        }
        return leftType;
    }

    @Override
    public TypeSymbol visit(Relational relational) {
        // TODO 3: Verificăm tipurile operanzilor, afișăm eroare dacă e cazul,
        // și întoarcem tipul expresiei.
        // Puteți afla felul operației din relational.getToken().getType(),
        // pe care îl puteți compara cu CPLangLexer.EQUAL etc.
        var leftType = relational.left.accept(this);
        var rightType = relational.right.accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if ( !((leftType.equals(TypeSymbol.INT) && rightType.equals(TypeSymbol.INT))) &&
                !((leftType.equals(TypeSymbol.FLOAT) && rightType.equals(TypeSymbol.FLOAT)))) {
            ASTVisitor.error(relational.token, "Operands of " + relational.token.getText() + " have incompatible types");
            return null;
        }
        return TypeSymbol.BOOL;
    }

    // Tipurile de bază
    @Override
    public TypeSymbol visit(Int intt) {
        // TODO 2: Întoarcem tipul corect.
        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(Bool bool) {
        // TODO 2: Întoarcem tipul corect.
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(FloatNum floatNum) {
        // TODO 2: Întoarcem tipul corect.
        return TypeSymbol.FLOAT;
    }
    
};