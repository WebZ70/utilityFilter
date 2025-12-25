package service.statistics.strategy;

import base.Element;
import base.Elements;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class StatisticsNumberStrategy implements ICalcStatistics {
    List<Number> elements;
    public StatisticsNumberStrategy(List<Number> number) {
        this.elements = number;
    }
    @Override
    public void calcStatistics() {
        if (elements.isEmpty()) {
            System.out.println("Данные не найдены");
            return;
        }

        double sum = elements.stream().mapToDouble(Number::doubleValue).sum();
        OptionalDouble min = elements.stream().mapToDouble(Number::doubleValue).min();
        OptionalDouble max = elements.stream().mapToDouble(Number::doubleValue).max();
        double avg = sum / elements.size();

        System.out.println("------------------------");
        System.out.println("Полная статистика о записанных данных тип - числа:");
        System.out.println("------------------------");
        System.out.println("Sum: " + sum);
        System.out.println("Min: " + (min.isPresent() ? min.getAsDouble() : "N/A"));
        System.out.println("Max: " + (max.isPresent() ? max.getAsDouble() : "N/A"));
        System.out.println("Avg: " + avg);
        System.out.println("------------------------");

    }
}
