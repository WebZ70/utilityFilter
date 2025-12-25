package app;

import base.Elements;
import service.statistics.TypeStatistics;

import java.nio.file.StandardOpenOption;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Выделение памяти под объект
        Elements elements = new Elements();
        //Параметры по умолчанию
        String pref = "";
        String pathOut = "";
        StandardOpenOption option = StandardOpenOption.CREATE;
        System.out.println("Выполнение с параметрами: " + Arrays.stream(args).toList());

        if (args.length != 0 && Arrays.stream(args).noneMatch(s -> s.endsWith(".txt"))) {
            System.out.println("Входные файлы не найдены");
            System.exit(0);
        }

        if (args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                //Чтение строк из файла и заполнение объекта Elements
                switch (args[i]) {
                    case "-p":
                        if (!args[i+1].startsWith("-")) {
                            pref = args[++i];
                            break;
                        }
                        System.out.println("Пустое значение параметра '-p'");
                        System.exit(-1);
                    case "-o":
                        if (!args[i+1].startsWith("-")) {
                            pathOut = args[++i];
                            break;
                        }
                        System.out.println("Пустое значение параметра '-o'");
                        System.exit(-1);
                    case "-a":
                        option = StandardOpenOption.APPEND;
                        System.out.println("Режим добавления в существующий файл(по умолчанию перезаписывает)");
                        break;
                    case "-s":
                        if (elements.getStatistics().getTypeStatistics().equals(TypeStatistics.NONE)) {
                            elements.getStatistics().setTypeStatistics(TypeStatistics.SUMMARY);
                        } else {
                            System.out.println("Нужно выбрать только один тип статистики (-f или -s)");
                            System.exit(-1);
                        }
                        break;
                    case "-f":
                        if (elements.getStatistics().getTypeStatistics().equals(TypeStatistics.NONE)) {
                            elements.getStatistics().setTypeStatistics(TypeStatistics.FULL);
                        } else {
                            System.out.println("Нужно выбрать только один тип статистики (-f или -s)");
                            System.exit(-1);
                        }
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

        elements.writeToFile(pathOut.concat(pref), option);
        elements.getStatistics().show();

    }
}
