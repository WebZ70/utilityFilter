package service.statistics;

import base.Element;
import service.statistics.strategy.StatisticsNumberStrategy;

import java.util.List;

public class StatisticsNumber extends AllStatistics {
    public StatisticsNumber(List<Number> elements) {
        super(new StatisticsNumberStrategy(elements));
    }
}
