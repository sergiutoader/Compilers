package cool.compiler;

import cool.compiler.Prog;

public interface ASTVisitor<T> {
      T visit(Prog prog);
      T visit(Class classs);
      T visit(Feature feature);
      T visit(TypeId typeId);
      T visit(ObjectId objectId);
}
