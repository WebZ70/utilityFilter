package service.iofile;

import base.Element;
import base.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class IOFiles {
    public static boolean writeElemFile(String filePath, List<String> elements, StandardOpenOption ...options) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, elements, options);
        }
        catch (Exception e) {
            System.out.println("Ошибка при записи файла:" + e.getMessage());
        }
        return true;
    }
    
    public static void writeElemFile(String p, Elements elements, StandardOpenOption ...options) {
        elements.uniqueTypeElements().forEach(name -> {
            try {
                Path path = Paths.get(p.concat(name.concat(".txt")));
                Files.write(path, elements.getElementsByType(name), options);
            }catch (NoSuchFileException e){
                System.out.println("Файл не существует: " + e.getMessage());
            }catch (IOException e) {
                System.out.println("Ошибка записи в файл: " + e.getMessage());
            }
        });
    }

    public static List<String> readFile(String filePath) {
        List<String> list = new ArrayList<>();
        String line;

        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("Файл не найден");
            return list;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                list.add(line);
            }
            return list;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
