package base;

import service.parsing.recognize.Recognize;

import java.util.Objects;

public class Element {
    private final Object element;
    private final String type;

    public Element(String element) {
        this.element = Recognize.parseElement(element);
        this.type = Recognize.getType(Objects.requireNonNull(this.element));
    }

    public String getElement() {
        return element.toString();
    }

    public String getType() {
        return type;
    }
}
