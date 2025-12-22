package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Context {
    private String[] arguments;

    public Context(String ...arguments) {
        this.arguments = arguments;
    }

    public String[] getArguments() {
        return arguments;
    }
}
