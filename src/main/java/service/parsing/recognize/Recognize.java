package service.parsing.recognize;

public class Recognize {

    public static Object parseElement(String element) {
        if (isInteger(element)){
            return safeParseInt(element);
        }else if (isFloat(element)){
            return safeParseFloat(element);
        } else if (isString(element)) {
            return element;
        }
        return null;
    }

    public static String getType(Object object) {
        if (object == null) {return "";}
        return object.getClass().getSimpleName().toLowerCase();
    }

    public static int safeParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static float safeParseFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
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
