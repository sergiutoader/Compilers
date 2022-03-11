package cool.structures;

public class FormalSymbol extends Symbol {

    private TypeSymbol typeSymbol;

    public FormalSymbol(String name, TypeSymbol typeSymbol) {
        super(name);
        this.typeSymbol = typeSymbol;
    }

    public TypeSymbol getTypeSymbol() {
        return typeSymbol;
    }
}
