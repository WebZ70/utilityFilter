package service.iofile;

import base.Element;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class IOFiles {
    public static void writeFile(String filePath, List<String> elements, StandardOpenOption ...options) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, elements, options);
        }
        catch (Exception e) {
            System.out.println("Ошибка при записи файла:");
            e.printStackTrace();
        }
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
