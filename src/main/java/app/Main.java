package app;

import base.Element;
import base.Elements;

import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //Параметры по умолчанию
        String pref = "";
        String pathOut = "";
        StandardOpenOption option = StandardOpenOption.CREATE;
        //Выделение памяти под объект
        Elements elements = new Elements();
        System.out.println("Выполнение с параметрами: " + Arrays.stream(args).toList());

        if (args.length != 0 && Arrays.stream(args).noneMatch(s -> s.endsWith(".txt"))) {
            System.out.println("Входные файлы не заданы");
            System.exit(0);
        }

        if (args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                //Чтение строк из файла и заполнение объекта Elements
                switch (args[i]) {
                    case "-p":
                        if (!args[i+1].startsWith("-")) {
                            pref = args[++i];
                            System.out.println("Префикс выходных файлов: " + pref);
                            break;
                        }
                        System.out.println("Пустое значение параметра '-p'");
                        System.exit(-1);
                    case "-o":
                        if (!args[i+1].startsWith("-")) {
                            pathOut = args[++i];
                            System.out.println("Путь до выходных файлов: " + pathOut);
                            break;
                        }
                        System.out.println("Пустое значение параметра '-o'");
                        System.exit(-1);
                    case "-a":
                        option = StandardOpenOption.APPEND;
                        System.out.println("Режим добавления в существующий файл(по умолчанию перезаписывает)");
                        break;
                    case "-s":
                        System.out.println("Краткая статистика");
                        break;
                    case "-f":
                        System.out.println("Полная статистика");
                        break;
                    default:
                        if(args[i].endsWith(".txt")) {
                            elements.readFromFile(args[i]);
                        } else {
                            System.out.println("Неопознанный параметр: " + args[i]);
                        }
                }
            }
        }

        elements.writeToFile(pathOut, option);
        elements.getStatistics().collectorAll();



//        elements.filterElementsByTypeObject("integer").stream()
//                .map(elements::getElementsFilteredByObject)
//                .map(element -> element.stream()
//                        .map(Element::getElementObject)
//                ).forEach(System.out::println);



    }
}
