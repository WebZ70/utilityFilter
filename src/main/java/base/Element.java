package base;

import service.parsing.recognize.Recognize;

import java.util.Objects;
import java.util.Optional;

public class Element {
    private final Object element;
    private final String type;

    private Element(Object element) {
        this.element = element;
        this.type = Recognize.getType(Objects.requireNonNull(this.element, "Failed type " + element));
    }

    public static Optional<Element> create(String elementStr) {
        Optional<Object> parsed = Recognize.parseElement(elementStr);
        if (parsed.isPresent()) {
            Object elem = parsed.get();
            return Optional.of(new Element(elem));
        } else {
            System.err.println("Неопознанный элемент: " + elementStr);
            return Optional.empty();
        }
    }

    public String getElement() {
        return element.toString();
    }

    public String getType() {
        return type;
    }
}
