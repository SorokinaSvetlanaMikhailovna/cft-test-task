package ru.sorokina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sorokina.exception.MissingDataTypeException;
import ru.sorokina.exception.MissingInputFilesException;
import ru.sorokina.exception.MissingOutputFilesException;
import ru.sorokina.model.Arguments;
import ru.sorokina.model.DataType;
import ru.sorokina.model.SortType;

class ArgumentValidatorTest {
    private final ArgumentValidator validator = new ArgumentValidator();

    @Test
    public void invalidDataTypeTest() {
        Arguments arguments = new Arguments(
                SortType.ASC,
                null,
                "out.txt",
                new String[]{"in1.txt"}
        );
        Assertions.assertThrows(MissingDataTypeException.class, () -> validator.validate(arguments));
    }

    @Test
    public void invalidInputFileTest() {
        Arguments arguments = new Arguments(
                SortType.ASC,
                DataType.STRING,
                "out.txt",
                null
        );
        Assertions.assertThrows(MissingInputFilesException.class, () -> validator.validate(arguments));
    }

    @Test
    public void invalidOutputFileTest() {
        Arguments arguments = new Arguments(
                SortType.ASC,
                DataType.STRING,
                null,
                null
        );
        Assertions.assertThrows(MissingOutputFilesException.class, () -> validator.validate(arguments));
    }
}