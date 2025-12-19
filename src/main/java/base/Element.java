package base;

public class Element {
    private String type;
    private String value;

    public Element(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
