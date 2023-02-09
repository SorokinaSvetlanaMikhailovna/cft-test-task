package ru.sorokina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sorokina.model.Arguments;
import ru.sorokina.model.DataType;
import ru.sorokina.model.SortType;

class ArgumentsParserTest {

    @Test
    public void descendingKeysAreOutOfOrder() {
        String[] args = {"-s", "-d", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertEquals(SortType.DESC, arguments.getSortType());
        Assertions.assertEquals(DataType.STRING, arguments.getDataType());
    }

    @Test
    public void stringTest() {
        String[] args = {"-s", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertEquals(SortType.ASC, arguments.getSortType());
        Assertions.assertEquals(DataType.STRING, arguments.getDataType());
    }

    @Test
    public void ascendingStringTest() {
        String[] args = {"-a", "-s", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertEquals(SortType.ASC, arguments.getSortType());
        Assertions.assertEquals(DataType.STRING, arguments.getDataType());
    }

    @Test
    public void descendingStringTest() {
        String[] args = {"-d", "-s", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertEquals(SortType.DESC, arguments.getSortType());
        Assertions.assertEquals(DataType.STRING, arguments.getDataType());
    }

    @Test
    public void numberTest() {
        String[] args = {"-i", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertEquals(SortType.ASC, arguments.getSortType());
        Assertions.assertEquals(DataType.NUMBER, arguments.getDataType());
    }

    @Test
    public void ascendingNumberTest() {
        String[] args = {"-a", "-i", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertEquals(SortType.ASC, arguments.getSortType());
        Assertions.assertEquals(DataType.NUMBER, arguments.getDataType());
    }

    @Test
    public void descendingNumberTest() {
        String[] args = {"-d", "-i", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertEquals(SortType.DESC, arguments.getSortType());
        Assertions.assertEquals(DataType.NUMBER, arguments.getDataType());
    }

    @Test
    public void singleInputFileTest() {
        String[] args = {"-d", "-i", "out.txt", "in1.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertArrayEquals(new String[]{"in1.txt"}, arguments.getInputFileNames());
    }

    @Test
    public void multipleInputFileTest() {
        String[] args = {"-d", "-i", "out.txt", "in1.txt", "in2.txt", "in3.txt", "in4.txt", "in5.txt", "in6.txt", "in7.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertArrayEquals(new String[]{"in1.txt", "in2.txt", "in3.txt", "in4.txt", "in5.txt", "in6.txt", "in7.txt"}, arguments.getInputFileNames());
    }

    @Test
    public void outputFileTest() {
        String[] args = {"-d", "-i", "out.txt", "in1.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertEquals("out.txt", arguments.getOutputFileName());
    }

    @Test
    public void noDataTypeTest() {
        String[] args = {"-a", "out.txt", "in.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertNull(arguments.getDataType());
    }

    @Test
    public void noInputFilesTest() {
        String[] args = {"-d", "-i", "out.txt"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertNull(arguments.getInputFileNames());
    }

    @Test
    public void noOutputFileTest() {
        String[] args = {"-d", "-i"};
        Arguments arguments = new ArgumentParser(args).parse();
        Assertions.assertNull(arguments.getOutputFileName());
    }
}