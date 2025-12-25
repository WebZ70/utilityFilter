package service.statistics;

import service.statistics.strategy.ICalcStatistics;

import java.util.HashMap;

public class AllStatistics {
    private final ICalcStatistics calcStatistics;

    AllStatistics(ICalcStatistics calcStatistics) {
        this.calcStatistics = calcStatistics;
    }

    public void fullStatistics() {
        this.calcStatistics.calcStatistics();
    }

    public void summaryStatistics(HashMap<String, Integer> countElements) {
        System.out.println("------------------------");
        System.out.println("Краткая статистика о записанных данных:");
        System.out.println("------------------------");
        System.out.println("Тип данных\tКоличество строк");
        countElements.forEach((key, value) -> System.out.println(key + "\t\t" + value));
        System.out.println("------------------------");
    }
}
