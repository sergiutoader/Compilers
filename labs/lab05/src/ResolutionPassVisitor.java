public class ResolutionPassVisitor implements ASTVisitor<Void> {     
    @Override
    public Void visit(Program prog) {
        for (var stmt: prog.stmts) {
            stmt.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Assign assign) {
        /*
         TODO 3: Verificăm dacă membrul din stânga este într-adevăr
                 o variabilă sau este o funcție, fiind eroare. Puteți folosi instanceof.
         */
        var id = assign.id;
        if (id.getScope().lookup(id.getToken().getText()) instanceof FunctionSymbol) {
            ASTVisitor.error(id.getToken(), "LHS of assignment " + id.getToken().getText() + " cannot be a function");
        }

        assign.id.accept(this);
        assign.expr.accept(this);
        return null;
    }

    @Override
    public Void visit(Call call) {
        /*
         TODO 3: Verificăm dacă funcția există în scope. Nu am putut face
                 asta în prima trecere din cauza a forward declarations.
                 De asemenea, verificăm că Id-ul pe care se face apelul de funcție
                 este, într-adevăr, o funcție și nu o variabilă.
          Hint: pentru a obține scope-ul, putem folosi call.id.getScope(),
                setat la trecerea anterioară.
         */
        var scope = call.id.getScope();
        var name = call.id.getToken().getText();
        var looked_up_symbol = scope.lookup(name);
        if (looked_up_symbol == null) {
            ASTVisitor.error(call.id.getToken(), name + " undefined");
        } else if (!(looked_up_symbol instanceof FunctionSymbol)) {
            ASTVisitor.error(call.id.getToken(), name + " is not a function");
        }

        return null;
    }   

    @Override
    public Void visit(VarDef varDef) {
        if (varDef.initValue != null)
            varDef.initValue.accept(this);
        return null;
    }

    @Override
    public Void visit(FuncDef funcDef) {
        return null;
    }

    @Override
    public Void visit(Id id) {
        return null;
    }

    @Override
    public Void visit(If iff) {
        iff.cond.accept(this);
        iff.thenBranch.accept(this);
        iff.elseBranch.accept(this);
        return null;
    }

    @Override
    public Void visit(Type type) {
        return null;
    }

    @Override
    public Void visit(Formal formal) {
        formal.id.accept(this);
        return null;
    }

    // Operații aritmetice.
    @Override
    public Void visit(UnaryMinus uMinus) {
        uMinus.expr.accept(this);
        return null;
    }

    @Override
    public Void visit(Div div) {
        div.left.accept(this);
        div.right.accept(this);
        return null;
    }

    @Override
    public Void visit(Mult mult) {
        mult.left.accept(this);
        mult.right.accept(this);
        return null;
    }

    @Override
    public Void visit(Plus plus) {
        plus.left.accept(this);
        plus.right.accept(this);
        return null;
    }

    @Override
    public Void visit(Minus minus) {
        minus.left.accept(this);
        minus.right.accept(this);
        return null;
    }

    @Override
    public Void visit(Relational relational) {
        return null;
    }

    // Tipurile de bază
    @Override
    public Void visit(Int intt) {
        return null;
    }

    @Override
    public Void visit(Bool bool) {
        return null;
    }

    @Override
    public Void visit(FloatNum floatNum) {
        return null;
    }
    
};