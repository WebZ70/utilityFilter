package base;

import service.parsing.Type;
import service.parsing.recognize.Recognize;

public class Element {
    private final Type type;
    private final String value;

    public Element(String value) {
        this.value = value;
        this.type = setType();
    }

    public String getValue() {
        return value;
    }

    private Type setType() {
        if (Recognize.isInteger(value)) {
            return Type.INTEGER;
        } else if (Recognize.isFloat(value)) {
            return Type.FLOAT;
        } else if (Recognize.isString(value)) {
            return Type.STRING;
        }
        return Type.UNIDENTIFIED;
    }

    public Type getType() {
        return type;
    }

}
