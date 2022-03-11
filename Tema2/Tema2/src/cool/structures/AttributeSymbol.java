package cool.structures;

public class AttributeSymbol extends IdSymbol {
    private TypeSymbol typeSymbol;

    public AttributeSymbol(String name, TypeSymbol typeSymbol) {
        super(name);
        this.typeSymbol = typeSymbol;
    }

    public TypeSymbol getTypeSymbol() {
        return typeSymbol;
    }
}
