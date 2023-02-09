package ru.sorokina.merger;

import ru.sorokina.model.Arguments;

import java.math.BigDecimal;

public class IntegerMerger extends GenericMerger<BigDecimal> {

    private static final String NUMBER_PATTERN = "-?\\d+";

    public IntegerMerger(Arguments arguments) {
        super(arguments);
    }

    @Override
    public BigDecimal getValue(String line) {
        return new BigDecimal(line);
    }

    @Override
    public boolean validLine(String line) {
        return line == null || line.matches(NUMBER_PATTERN);
    }
}
