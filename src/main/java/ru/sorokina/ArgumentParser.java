package ru.sorokina;

import ru.sorokina.model.Arguments;
import ru.sorokina.model.DataType;
import ru.sorokina.model.SortType;

import java.util.Arrays;
import java.util.Set;

public class ArgumentParser {
    private final String[] args;
    private int currentArg = 0;

    public ArgumentParser(String[] args) {
        this.args = args;
    }

    public Arguments parse() {
        Set<String> flags = Set.of("-a", "-d", "-i", "-s");
        SortType sortType = SortType.ASC;
        DataType dataType = null;
        while (currentArg < args.length && flags.contains(args[currentArg])) {
            switch (args[currentArg]) {
                case "-a":
                    sortType = SortType.ASC;
                    break;
                case "-d":
                    sortType = SortType.DESC;
                    break;
                case "-i":
                    dataType = DataType.NUMBER;
                    break;
                case "-s":
                    dataType = DataType.STRING;
                    break;

            }
            currentArg++;
        }

        String outputFileName = getOutputFileName();
        String[] inputFileNames = getInputFileNames();

        return new Arguments(sortType, dataType, outputFileName, inputFileNames);
    }

    private String getOutputFileName() {
        if (currentArg < args.length)
            return args[currentArg++];
        else {
            return null;
        }
    }

    private String[] getInputFileNames() {
        if (currentArg < args.length)
            return Arrays.copyOfRange(args, currentArg, args.length);
        else {
            return null;
        }
    }
}
