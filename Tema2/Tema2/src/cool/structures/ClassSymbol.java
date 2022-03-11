package cool.structures;

import org.antlr.v4.runtime.Token;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassSymbol extends IdSymbol implements Scope {

    private final String parentName;
    private final Token nameToken;

    private Map<String, Symbol> attributes = new LinkedHashMap<>();
    private Map<String, Symbol> methods = new LinkedHashMap<>();
    protected Scope parent;


    public ClassSymbol(String name, String parentName, Scope parent, Token nameToken) {
        super(name);
        this.parent = parent;
        this.parentName = parentName;
        this.nameToken = nameToken;
    }

    @Override
    public boolean add(Symbol sym) {
        // Ne asigurăm că simbolul nu există deja în domeniul de vizibilitate
        // curent.
        if (sym instanceof AttributeSymbol) {
            if (attributes.containsKey(sym.getName())) {
                return false;
            }
            attributes.put(sym.getName(), sym);
            return true;
        } else if (sym instanceof MethodSymbol) {
            if (methods.containsKey(sym.getName())) {
                return false;
            }

            methods.put(sym.getName(), sym);
            return true;
        }

        return false;
    }

    @Override
    public Symbol lookup(String s) {
        return methods.get(s);
    }

    public Symbol lookup(String s, boolean isMethod) {
        return isMethod ? methods.get(s) : attributes.get(s);
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    public void setParent(ClassSymbol parent) {
        this.parent = parent;
    }

    public String getParentName() {
        return parentName;
    }
}
