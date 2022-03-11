package cool.structures;

public class LetSymbol implements Scope {

    private Scope parent;
    private VariableSymbol variable;

    public LetSymbol(Scope parent) {
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        if (!(sym instanceof VariableSymbol)) {
            return false;
        }

        variable = (VariableSymbol) sym;
        return true;
    }

    @Override
    public Symbol lookup(String str) {
        if (variable == null) {
            return null;
        }

        if (variable.getName().equals(str)) {
            return variable;
        }
        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }
}
