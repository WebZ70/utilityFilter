package app;

import base.Element;
import base.Elements;
import service.iofile.IOFiles;
import service.parsing.recognize.Recognize;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> nameFile = Arrays.asList("integer.txt", "float.txt", "string.txt");
        String path = "./";
        //Выделение памяти под будующие строки
        Elements elements = new Elements();
        //Чтение строк из файла в список Element
        IOFiles.readFile("src/main/java/source/in/in2.txt", elements);
        //Запись в файл в соответствии с определенным типом
        //Названия файлов integers.txt, floats.txt, strings.txt
//        System.out.println(elements.getElements().stream().map(Element::getElement).toList());
        System.out.println(Arrays.stream(args).toList());
        if (args.length != 0) {
            for (int parm = 0, value = parm + 1; parm < args.length; parm++) {
                switch (args[parm]) {
                    case "-p":
                        System.out.println("Префикс выходных файлов: " + args[++parm]);
                        nameFile = nameFile.stream().map(m -> args[value].concat(m)).toList();
                        break;
                    case "-o":
                        System.out.println("Путь до выходных файлов: " + args[++parm]);

                        break;
                    case "-a":
                        System.out.println("Режим добавления в существующий файл(по умолчанию перезаписывает)");
                        break;
                    case "-s":
                        System.out.println("Краткая статистика");
                        break;
                    case "-f":
                        System.out.println("Полная статистика");
                }
            }
        }


        //Фильтруем элементы
        nameFile.stream().map(m ->
            elements.getElements(m.substring(m.indexOf("_") + 1, m.lastIndexOf(".")))
        ).forEach(System.out::println);
//        IOFiles.writeFile(path.concat(nameFile), elements.getElements("String"));
    }
}
