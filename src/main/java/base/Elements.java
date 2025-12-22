package base;

import service.statistics.Statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Elements {
    private final List<Element> elements;
    private Statistics statistics;
    public Elements() {
        elements = new ArrayList<>();
        statistics = new Statistics();
    }

    public List<String> getElements(String typeElement) {
        return elements.stream().filter(f -> f.getType().equals(typeElement)).map(Element::getElement).toList();
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void addElement(Element element) {
        elements.add(element);
    }


}
