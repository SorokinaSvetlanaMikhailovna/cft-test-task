package ru.sorokina;

import ru.sorokina.merger.MergerFactory;
import ru.sorokina.model.Arguments;

public class Main {

    public static void main(String[] args) {
        Arguments arg = new ArgumentParser(args).parse();
        ArgumentValidator validator = new ArgumentValidator();
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        exceptionHandler.handle(() -> {
            validator.validate(arg);
            MergerFactory.getMerger(arg).merge();
        });
    }
}
