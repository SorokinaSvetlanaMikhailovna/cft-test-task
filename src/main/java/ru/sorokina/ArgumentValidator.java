package ru.sorokina;

import ru.sorokina.exception.MissingDataTypeException;
import ru.sorokina.exception.MissingInputFilesException;
import ru.sorokina.exception.MissingOutputFilesException;
import ru.sorokina.model.Arguments;

import java.io.File;

public class ArgumentValidator {

    public void validate(Arguments arguments) {
        if (arguments.getDataType() == null) {
            throw new MissingDataTypeException();

        }
        if (arguments.getOutputFileName() == null) {
            throw new MissingOutputFilesException();
        }
        if (arguments.getInputFileNames() == null || arguments.getInputFileNames().length == 0) {
            throw new MissingInputFilesException();
        }
        for (String inputFile : arguments.getInputFileNames()) {
            if (!new File(inputFile).exists()) {
                throw new MissingInputFilesException(inputFile);
            }
        }
    }

}
