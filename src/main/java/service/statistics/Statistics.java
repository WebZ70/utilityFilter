package service.statistics;

//В процессе фильтрации данных необходимо собирать статистику по каждому типу
//данных. Статистика должна поддерживаться двух видов: краткая и полная. Выбор
//статистики производится опциями -s и -f соответственно. Краткая статистика
//содержит только количество элементов записанных в исходящие файлы. Полная
//статистика для чисел дополнительно содержит минимальное и максимальное
//значения, сумма и среднее. Полная статистика для строк, помимо их количества,
//содержит также размер самой короткой строки и самой длинной.

import base.Element;
import base.Elements;

import java.util.HashMap;
import java.util.List;

public class Statistics {
    private final HashMap<String, Integer> countElements;
    AllStatistics allStatistics;
    TypeStatistics typeStatistics;
    Elements elements;

    public Statistics(Elements elements) {
        countElements = new HashMap<>();
        allStatistics = new AllStatistics(null);
        typeStatistics = TypeStatistics.NONE;
        this.elements = elements;
    }

    public TypeStatistics getTypeStatistics() {
        return typeStatistics;
    }

    public void setTypeStatistics(TypeStatistics typeStatistics) {
        this.typeStatistics = typeStatistics;
    }

    public void show() {
        //Запись полной статистики
        switch (this.typeStatistics) {
            case FULL: showFullStatistics(); break;
            case SUMMARY: showSummaryStatistics(); break;
        }
    }

    public void counter(String type, int count) {
        if (!typeStatistics.equals(TypeStatistics.NONE)){
            if (countElements.containsKey(type)) {
                countElements.replace(type, countElements.get(type), countElements.get(type) + count);
            }else {
                this.countElements.put(type, count);
            }
        }
    }

    public void showSummaryStatistics() {
        allStatistics.summaryStatistics(countElements);
    }

    public void showFullStatistics() {
        allStatistics.summaryStatistics(countElements);
        allStatistics = new StatisticsNumber(getElementsNumber());
        allStatistics.fullStatistics();
        allStatistics = new StatisticsString(getElementsString());
        allStatistics.fullStatistics();
    }

    public List<Number> getElementsNumber() {
        return elements.getElements().stream().map(Element::getElementObject).toList()
                .stream().filter(element -> element instanceof Number)
                .map(element -> (Number) element).toList();
    }

    public List<String> getElementsString() {
        return elements.getElements().stream().map(Element::getElementObject).toList()
                .stream().filter(element -> element instanceof String)
                .map(element -> (String) element).toList();
    }
}
