package cool.structures;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CaseBranchSymbol implements Scope {
    private HashMap<String, VariableSymbol> variables = new LinkedHashMap<>();
    private Scope parent;

    public CaseBranchSymbol(Scope parent) {
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        if (!(sym instanceof VariableSymbol)) {
            return false;
        }
        if (variables.containsKey(sym.getName())) {
            return false;
        }
        variables.put(sym.getName(), (VariableSymbol) sym);
        return true;
    }

    @Override
    public Symbol lookup(String str) {
        return variables.get(str);
    }

    @Override
    public Scope getParent() {
        return null;
    }

    public HashMap<String, VariableSymbol> getVariables() {
        return variables;
    }
}
