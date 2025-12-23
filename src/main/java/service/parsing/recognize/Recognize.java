package service.parsing.recognize;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Recognize {

    public static Optional<Object> parseElement(String element) {
        try {
            if (isInteger(element)) {
                return Optional.of(Integer.parseInt(element));
            } else if (isFloat(element)) {
                return Optional.of(Float.parseFloat(element));
            } else if (isString(element)) {
                return Optional.of(element);
            }
            return Optional.empty();
        }catch (IllegalArgumentException e) {
            System.err.println("Parsing failed: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static String getType(Object object) {
        if (object == null) {return "";}
        return object.getClass().getSimpleName().toLowerCase();
    }

    public static int safeParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing int: " + e.getMessage());
            throw new IllegalArgumentException("Invalid integer: " + str, e);
        }
    }

    public static float safeParseFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing float: " + e.getMessage());
            throw new IllegalArgumentException("Invalid integer: " + str, e);
        }
    }

    private static boolean isInteger(String value) {
        return value != null && value.matches("^-?\\d+$");
    }

    private static boolean isFloat(String value) {
        return value != null && value.matches("^-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?$");
    }

    private static boolean isString(String value) {
        return value != null && value.matches(".*\\D.*");
    }
}
