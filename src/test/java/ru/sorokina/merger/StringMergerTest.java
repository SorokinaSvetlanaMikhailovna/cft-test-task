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

class StringMergerTest {
    private final String pathResources = "./src/test/resources/";


    @Test
    public void twoFilesAscTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.STRING,
                pathResources + "string/twoFilesAsc/out.txt",
                new String[]{pathResources + "string/twoFilesAsc/in1.txt", pathResources + "string/twoFilesAsc/in2.txt"}
        );
        StringMerger merger = new StringMerger(arg);
        merger.merge();
        checkOutput("string/twoFilesAsc/expectedOut.txt", "string/twoFilesAsc/out.txt");
    }

    @Test
    public void oneFileAscTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.STRING,
                pathResources + "string/oneFileAsc/out.txt",
                new String[]{pathResources + "string/oneFileAsc/in1.txt"}
        );
        StringMerger merger = new StringMerger(arg);
        merger.merge();
        checkOutput("string/oneFileAsc/expectedOut.txt", "string/oneFileAsc/out.txt");
    }

    @Test
    public void twoFilesDescTest() {
        Arguments arg = new Arguments(
                SortType.DESC,
                DataType.STRING,
                pathResources + "string/twoFilesDesc/out.txt",
                new String[]{pathResources + "string/twoFilesDesc/in1.txt", pathResources + "string/twoFilesDesc/in2.txt"}
        );
        StringMerger merger = new StringMerger(arg);
        merger.merge();
        checkOutput("string/twoFilesDesc/expectedOut.txt", "string/twoFilesDesc/out.txt");
    }

    @Test
    public void twoFilesInvalidSymbolsTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.STRING,
                pathResources + "string/twoFilesInvalidSymbols/out.txt",
                new String[]{pathResources + "string/twoFilesInvalidSymbols/in1.txt", pathResources + "string/twoFilesInvalidSymbols/in2.txt"}
        );
        StringMerger merger = new StringMerger(arg);
        merger.merge();
        checkOutput("string/twoFilesInvalidSymbols/expectedOut.txt", "string/twoFilesInvalidSymbols/out.txt");
    }

    @Test
    public void emptyFileTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.STRING,
                pathResources + "string/emptyFile/out.txt",
                new String[]{pathResources + "string/emptyFile/in1.txt"}
        );
        StringMerger merger = new StringMerger(arg);
        merger.merge();
        checkOutput("string/emptyFile/expectedOut.txt", "string/emptyFile/out.txt");
    }

    private void checkOutput(String expected, String actual) {
        Assertions.assertEquals(readOutputFile(expected), readOutputFile(actual));
    }


    @Test
    public void noOutputFileTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.STRING,
                pathResources + "string/noOutputFile/out.txt",
                new String[]{pathResources + "string/noOutputFile/in1.txt", pathResources + "string/noOutputFile/in2.txt", pathResources + "string/noOutputFile/in3.txt"}
        );
        StringMerger merger = new StringMerger(arg);
        merger.merge();
        checkOutput("string/noOutputFile/expectedOut.txt", "string/noOutputFile/out.txt");
    }

    @Test
    public void wrongSortTest() {
        Arguments arg = new Arguments(
                SortType.ASC,
                DataType.STRING,
                pathResources + "string/wrongSort/out.txt",
                new String[]{pathResources + "string/wrongSort/in1.txt", pathResources + "string/wrongSort/in2.txt"}
        );
        StringMerger merger = new StringMerger(arg);
        merger.merge();
        checkOutput("string/wrongSort/expectedOut.txt", "string/wrongSort/out.txt");
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