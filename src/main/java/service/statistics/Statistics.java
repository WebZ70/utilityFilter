package service.statistics;

//В процессе фильтрации данных необходимо собирать статистику по каждому типу
//данных. Статистика должна поддерживаться двух видов: краткая и полная. Выбор
//статистики производится опциями -s и -f соответственно. Краткая статистика
//содержит только количество элементов записанных в исходящие файлы. Полная
//статистика для чисел дополнительно содержит минимальное и максимальное
//значения, сумма и среднее. Полная статистика для строк, помимо их количества,
//содержит также размер самой короткой строки и самой длинной.

import service.parsing.Type;

import java.util.HashMap;
import java.util.List;

public class Statistics {
    private HashMap<Object, Integer> countElementsIn;
    public Statistics() {
        countElementsIn = new HashMap<>();
    }

    public HashMap<Object, Integer> getCountElementsIn() {
        return countElementsIn;
    }

    public void incrementCountElementsIn(Object type) {
        if (!countElementsIn.containsKey(type)) {
            countElementsIn.replace(type, countElementsIn.get(type), countElementsIn.get(type) + 1);
        }else {
            System.out.println("Ключ \"" + type + "\" не найден");
        }
    }
}
