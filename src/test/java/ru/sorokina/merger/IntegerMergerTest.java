package ru.sorokina.merger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sorokina.model.Arguments;
import ru.sorokina.model.DataType;
import ru.sorokina.model.SortType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class IntegerMergerTest {
    private final String pathResources = "./src/test/resources/";

    @Test
    public void twoFilesAscTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.NUMBER,
                pathResources + "integer/twoFilesAsc/out.txt",
                new String[]{pathResources + "integer/twoFilesAsc/in1.txt", pathResources + "integer/twoFilesAsc/in2.txt"}
        );
        IntegerMerger merger = new IntegerMerger(arg);
        merger.merge();
        checkOutput("integer/twoFilesAsc/expectedOut.txt", "integer/twoFilesAsc/out.txt");
    }


    @Test
    public void oneFileAscTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.NUMBER,
                pathResources + "integer/oneFileAsc/out.txt",
                new String[]{pathResources + "integer/oneFileAsc/in1.txt"}
        );
        IntegerMerger merger = new IntegerMerger(arg);
        merger.merge();
        checkOutput("integer/oneFileAsc/expectedOut.txt", "integer/oneFileAsc/out.txt");
    }

    @Test
    public void twoFilesDescTest() {
        Arguments arg = new Arguments(
                SortType.DESC,
                DataType.NUMBER,
                pathResources + "integer/twoFilesDesc/out.txt",
                new String[]{pathResources + "integer/twoFilesDesc/in1.txt", pathResources + "integer/twoFilesDesc/in2.txt"}
        );
        IntegerMerger merger = new IntegerMerger(arg);
        merger.merge();
        checkOutput("integer/twoFilesDesc/expectedOut.txt", "integer/twoFilesDesc/out.txt");
    }

    @Test
    public void twoFilesInvalidSymbolsTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.NUMBER,
                pathResources + "integer/twoFilesInvalidSymbols/out.txt",
                new String[]{pathResources + "integer/twoFilesInvalidSymbols/in1.txt", pathResources + "integer/twoFilesInvalidSymbols/in2.txt"}
        );
        IntegerMerger merger = new IntegerMerger(arg);
        merger.merge();
        checkOutput("integer/twoFilesInvalidSymbols/expectedOut.txt", "integer/twoFilesInvalidSymbols/out.txt");
    }

    @Test
    public void emptyFileTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.NUMBER,
                pathResources + "integer/emptyFile/out.txt",
                new String[]{pathResources + "integer/emptyFile/in1.txt"}
        );
        IntegerMerger merger = new IntegerMerger(arg);
        merger.merge();
        checkOutput("integer/emptyFile/expectedOut.txt", "integer/emptyFile/out.txt");
    }


    @Test
    public void noOutputFileTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.NUMBER,
                pathResources + "integer/noOutputFile/out.txt",
                new String[]{pathResources + "integer/noOutputFile/in1.txt", pathResources + "integer/noOutputFile/in2.txt"}
        );
        IntegerMerger merger = new IntegerMerger(arg);
        merger.merge();

        checkOutput("integer/noOutputFile/expectedOut.txt", "integer/noOutputFile/out.txt");
    }

    @Test
    public void wrongSortTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.NUMBER,
                pathResources + "integer/wrongSort/out.txt",
                new String[]{pathResources + "integer/wrongSort/in1.txt", pathResources + "integer/wrongSort/in2.txt"}
        );
        IntegerMerger merger = new IntegerMerger(arg);
        merger.merge();
        checkOutput("integer/wrongSort/expectedOut.txt", "integer/wrongSort/out.txt");
    }

    private void checkOutput(String expected, String actual) {
        Assertions.assertEquals(readOutputFile(expected), readOutputFile(actual));
    }

    private List<String> readOutputFile(String name) {
        List<String> listLine = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathResources + name))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                listLine.add(line);
                line = bufferedReader.readLine();
            }
            return listLine;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}