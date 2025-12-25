package base;

import service.statistics.Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Elements {
    private final List<Element> elements;
    private final Statistics statistics;
    public Elements() {
        elements = new ArrayList<>();
        statistics = new Statistics(this);
    }

    public List<Element> getElements() {
        return elements;
    }

    public Statistics getStatistics() {
        return statistics;
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
                    elements.add(Element.create(line).get());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(String p, StandardOpenOption ...options)  {
        uniqueTypeElements().forEach(type -> {
            try {
                Path path = Paths.get(p.concat(type.concat(".txt")));
                Files.write(path, filterElementsByType(type), options);
                statistics.counter(type, filterElementsByType(type).size());
            }catch (NoSuchFileException e){
                System.out.println("Файл не существует: " + e.getMessage());
            }catch (IOException e) {
                System.out.println("Ошибка записи в файл: " + e.getMessage());
            }
        });
    }

    public List<String> filterElementsByType(String type) {
        return elements.stream().filter(f -> f.getType().equals(type)).map(Element::getElement).toList();
    }

    public List<String> uniqueTypeElements() {
        return elements.stream().map(Element::getType).distinct().toList();
    }
}
