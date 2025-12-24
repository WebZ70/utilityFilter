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
import java.util.stream.Collectors;

public class Statistics {
    private final HashMap<String, Integer> countElements;
    Elements elements;
    AllStatistics allStatistics;
    public Statistics(Elements elements) {
        countElements = new HashMap<>();
        this.elements = elements;
    }

    public void showSummaryStatistics() {
        System.out.println(countElements);
    }

    public void showAllStatistics() {
        allStatistics.calcStatistics();
    }

    private void setCountElementsIn(String type, Integer count) {
        this.countElements.put(type, count);
    }

    private void incrementCountElementsIn(String type, int count) {
        if (countElements.containsKey(type)) {
            countElements.replace(type, countElements.get(type), countElements.get(type) + count);
        }else {
            setCountElementsIn(type, count);
        }
    }

    private void collector() {
        //Запись краткой статистики
        elements.uniqueTypeElements().forEach(type -> {
            incrementCountElementsIn(type, this.elements.getElements().stream().filter(f -> f.getType().equals(type)).map(Element::getElement).toList().size());
        });
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

    public void collectorAll() {
        //Запись полной статистики
        allStatistics = new StatisticsNumber(getElementsNumber());
        allStatistics.calcStatistics();
        allStatistics = new StatisticsString(getElementsString());
        allStatistics.calcStatistics();

    }
}
