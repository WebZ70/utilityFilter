package service.parsing;

import java.util.Optional;

public class ParsingElement {
    public static int safeParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return Integer.parseInt(null);
        }
    }

    public static float safeParseFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return Float.parseFloat(null);
        }
    }
}
