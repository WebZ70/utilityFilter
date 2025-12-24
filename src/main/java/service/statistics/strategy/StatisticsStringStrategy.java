package service.statistics.strategy;

import java.util.Comparator;
import java.util.List;

public class StatisticsStringStrategy implements ICalcStatistics {
    private final List<String> elements;

    public StatisticsStringStrategy(List<String> elements) {
        this.elements = elements;
    }

    @Override
    public void calcStatistics() {
        if (elements.isEmpty()) {
            System.out.println("No elements to calculate statistics.");
            return;
        }
        System.out.println("------------------------");
        System.out.println("Полная статистика строк:");
        System.out.println("------------------------");
        elements.stream().min(Comparator.comparingInt(String::length))
                .ifPresent(s -> System.out.println("Самая короткая строка: " + s + "\nДлинна строки: " + s.length()));
        elements.stream().max(Comparator.comparingInt(String::length))
                .ifPresent(s -> System.out.println("Самая длинная строка: " + s + "\nДлинна строки: " + s.length()));
        System.out.println("------------------------");
    }
}
