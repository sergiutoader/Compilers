package cool.structures;

import java.util.ArrayList;
import java.util.List;

public class TypeSymbol extends Symbol {

    public static final TypeSymbol INT = new TypeSymbol("Int");
    public static final TypeSymbol STRING = new TypeSymbol("String");
    public static final TypeSymbol BOOL = new TypeSymbol("Bool");
    public static final TypeSymbol OBJECT = new TypeSymbol("Object");

    public static final List<TypeSymbol> PRIMITIVE_TYPES = new ArrayList<>();
    static {
        PRIMITIVE_TYPES.add(TypeSymbol.INT);
        PRIMITIVE_TYPES.add(TypeSymbol.STRING);
        PRIMITIVE_TYPES.add(TypeSymbol.BOOL);
        PRIMITIVE_TYPES.add(TypeSymbol.OBJECT);
    }

    public TypeSymbol(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getName().equals(((TypeSymbol)obj).getName());
    }
}
