package ru.sorokina.model;

public class Arguments {
    private final SortType sortType;
    private final DataType dataType;
    private final String outputFileName;
    private final String[] inputFileNames;

    public Arguments(SortType sortType, DataType dataType, String outputFileName, String[] inputFileNames) {
        this.dataType = dataType;
        this.sortType = sortType;
        this.outputFileName = outputFileName;
        this.inputFileNames = inputFileNames;
    }

    public SortType getSortType() {
        return sortType;
    }

    public DataType getDataType() {
        return dataType;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public String[] getInputFileNames() {
        return inputFileNames;
    }
}
