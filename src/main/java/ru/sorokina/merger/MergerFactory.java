package ru.sorokina.merger;

import ru.sorokina.model.Arguments;

public class MergerFactory {
    public static Merger getMerger(Arguments arguments) {
        switch (arguments.getDataType()) {
            case STRING:
                return new StringMerger(arguments);
            case NUMBER:
                return new IntegerMerger(arguments);
            default:
                throw new IllegalArgumentException("Unsupported dataType: " + arguments.getDataType());
        }
    }
}
