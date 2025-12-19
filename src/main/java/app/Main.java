package app;

import base.Element;
import service.iofile.IOFiles;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Element> elements = new ArrayList<>();
        IOFiles.readFile("src/main/java/source/in/in1.txt", elements);
        elements.stream().map(Element::getValue).forEach(System.out::println);

    }
}
