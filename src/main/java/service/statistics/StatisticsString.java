package service.statistics;

import service.statistics.strategy.StatisticsStringStrategy;

import java.util.List;

public class StatisticsString extends AllStatistics {
    public StatisticsString(List<String> elements) {
        super(new StatisticsStringStrategy(elements));
    }
}
