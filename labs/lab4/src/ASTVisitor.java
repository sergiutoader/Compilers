public interface ASTVisitor<T> {
    T visit(Id id);
    T visit(Int intt);
    T visit(If iff);
    // TODO 2: Adăugați metode pentru fiecare clasă definită anterior
    T visit(Call call);
    T visit(Float floatt);
    T visit(Bool bool);
    T visit(Assign assign);
    T visit(Paren paren);
    T visit(UnaryMinus unaryMinus);
    T visit(Type type);
    T visit(Formal formal);
    T visit(VarDef vardef);
    T visit(FuncDef funcDef);
    T visit(Prog prog);
    T visit(Op op);
    T visit(Arithmetic arithmetic);
    T visit(Relational relational);
}
