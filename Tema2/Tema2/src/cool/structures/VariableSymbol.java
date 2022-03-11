package cool.structures;

public class VariableSymbol extends IdSymbol {

    private TypeSymbol typeSymbol;

    public VariableSymbol(String name, TypeSymbol typeSymbol) {
        super(name);
        this.typeSymbol = typeSymbol;
    }

    public TypeSymbol getTypeSymbol() {
        return typeSymbol;
    }
}
