package service.iofile;

import base.Element;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class IOFiles {
    public static void writeFile(String fileName, String content) {

    }

    public static void readFile(String filePath, List<Element> inputElement) {
        String line;

        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Файл не найден");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                inputElement.add(new Element(line));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
