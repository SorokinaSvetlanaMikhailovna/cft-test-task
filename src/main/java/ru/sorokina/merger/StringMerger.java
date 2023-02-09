package ru.sorokina.merger;

import ru.sorokina.model.Arguments;

public class StringMerger extends GenericMerger<String> {

    private static final String STRING_PATTERN = "\\S+";

    public StringMerger(Arguments arguments) {
        super(arguments);
    }

    @Override
    public String getValue(String line) {
        return line;
    }

    @Override
    public boolean validLine(String line) {
        return line == null || line.matches(STRING_PATTERN);
    }
}
