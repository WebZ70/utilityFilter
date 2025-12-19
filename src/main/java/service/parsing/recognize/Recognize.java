package service.parsing.recognize;

public class Recognize {
    public static boolean isInteger(String value) {
        return value != null && value.matches("^-?\\d+$");
    }

    public static boolean isFloat(String value) {
        return value != null && value.matches("^-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?$");
    }

    public static boolean isString(String value) {
        return value != null && value.matches(".*\\D.*");
    }
}
