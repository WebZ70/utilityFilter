package base;

import service.statistics.Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Elements {
    private final List<Element> elements;
    private final Statistics statistics;
    public Elements() {
        elements = new ArrayList<>();
        statistics = new Statistics(this);
    }

    private void addElement(Element element) {
        elements.add(element);
    }

    public List<String> filterElementsByType(String type) {
        return elements.stream().filter(f -> f.getType().equals(type)).map(Element::getElement).toList();
    }

    public List<Object> filterElementsByTypeObject(String type) {
        return elements.stream().filter(f -> f.getType().equals(type)).collect(Collectors.toList());
    }

    public List<Element> getElementsFilteredByObject(Object object) {
        return elements.stream().filter(e -> e.equals(object)).collect(Collectors.toList());
    }

    public List<String> uniqueTypeElements() {
        return elements.stream().map(Element::getType).distinct().toList();
    }

    public void readFromFile(String path) {
        String line;
        if (!Files.exists(Paths.get(path))) {
            System.out.println("Файл не найден");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                if (Element.create(line).isPresent()) {
                    addElement(Element.create(line).get());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(String p, StandardOpenOption ...options)  {
        uniqueTypeElements().forEach(name -> {
            try {
                Path path = Paths.get(p.concat(name.concat(".txt")));
                Files.write(path, filterElementsByType(name), options);
            }catch (NoSuchFileException e){
                System.out.println("Файл не существует: " + e.getMessage());
            }catch (IOException e) {
                System.out.println("Ошибка записи в файл: " + e.getMessage());
            }
        });
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Object> getElementsObject(String type) {
        return elements.stream().filter(element -> element.getType().equals(type)).map(Element::getElement).collect(Collectors.toList());
    }

//    public List<Number> getNumberElements() {
//        return elements.stream()
//                .filter(element -> element.getType().equals("integer") || element.getType().equals("float"))
//                .map(element -> element.getElement())  // Получаем Object
//                .filter(obj -> obj instanceof Number)  // Фильтруем только Number (Integer или Float), игнорируем String
//                .map(obj -> (Number) obj)  // Безопасное приведение
//                .collect(Collectors.toList());
//    }

    public Statistics getStatistics() {
        return statistics;
    }


}
