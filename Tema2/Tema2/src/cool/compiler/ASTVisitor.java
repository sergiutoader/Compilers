package cool.compiler;

public interface ASTVisitor<T> {
      T visit(Prog prog);
      T visit(Class c);
      T visit(Method method);
      T visit(Attribute attribute);
      T visit(Type typeId);
      T visit(ObjectId objectId);
      T visit(Formal formal);
      T visit(Int i);
      T visit(Stringg string);
      T visit(True t);
      T visit(False f);
      T visit(Id id);
      T visit(Paren paren);
      T visit(MultDiv multDiv);
      T visit(PlusMinus plusMinus);
      T visit(Negative negative);
      T visit(Relational relational);
      T visit(Not not);
      T visit(Assign assign);
      T visit(IsVoid isVoid);
      T visit(New n);
      T visit(ImplicitDispatch implicitDispatch);
      T visit(ExplicitDispatch explicitDispatch);
      T visit(If iff);
      T visit(While whilee);
      T visit(Local local);
      T visit(Let let);
      T visit(Case casee);
      T visit(CaseBranch caseBranch);
      T visit(Block block);
}
