package app;

import base.Element;
import service.iofile.IOFiles;
import service.parsing.Type;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Выделение памяти под будующие строки
        List<Element> elements = new ArrayList<>();
        //Чтение строк из файла в список Element
        IOFiles.readFile("src/main/java/source/in/in1.txt", elements);
        //Определение типа элемента: int, float, string.
        // Производиться через регулярные выражения по каждому элементу в списке
        elements.stream().map(Element::getType).forEach(System.out::println);
        //Запись в файл в соответствии с определенным типом
        //Названия файлов integers.txt, floats.txt, strings.txt
        List<String> str = elements.stream().filter(element -> element.getType() == Type.FLOAT).map(Element::getValue).toList();
        IOFiles.writeFile("file.txt", str);





    }
}
