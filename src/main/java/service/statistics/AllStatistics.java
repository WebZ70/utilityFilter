package service.statistics;

import service.statistics.strategy.ICalcStatistics;

public class AllStatistics {
    private final ICalcStatistics calcStatistics;

    AllStatistics(ICalcStatistics calcStatistics) {
        this.calcStatistics = calcStatistics;
    }

    public void calcStatistics() {
        this.calcStatistics.calcStatistics();
    }
}
