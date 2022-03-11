package cool.structures;
import java.util.ArrayList;


public class MethodSymbol extends IdSymbol implements Scope {
    protected Scope parent;
    private final ArrayList<FormalSymbol> formals = new ArrayList<>();
    private TypeSymbol typeSymbol;

    public MethodSymbol(String name, Scope parent, TypeSymbol typeSymbol) {
        super(name);
        this.parent = parent;
        this.typeSymbol = typeSymbol;
    }

    @Override
    public boolean add(Symbol sym) {
        if (!(sym instanceof FormalSymbol)) {
            return false;
        }

        if (formals.contains(sym)) {
            return false;
        }

        formals.add((FormalSymbol)sym);
        return true;
    }

    @Override
    public Symbol lookup(String str) {
        for(FormalSymbol f : formals) {
            if (f.getName().equals(str)) {
                return f;
            }
        }
        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    public ArrayList<FormalSymbol> getFormals() {
        return formals;
    }

    public TypeSymbol getTypeSymbol() {
        return typeSymbol;
    }
}
