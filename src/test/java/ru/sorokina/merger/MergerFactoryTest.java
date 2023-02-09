package ru.sorokina.merger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.sorokina.model.Arguments;
import ru.sorokina.model.DataType;
import ru.sorokina.model.SortType;

class MergerFactoryTest {
    @Test
    public void stringMergerTest() {
        Merger merger = MergerFactory.getMerger(new Arguments(SortType.ASC, DataType.STRING, "out.txt", new String[]{"in1.txt"}));
        Assertions.assertEquals(StringMerger.class, merger.getClass());
    }

    @Test
    public void integerMergerTest() {
        Merger merger = MergerFactory.getMerger(new Arguments(SortType.ASC, DataType.NUMBER, "out.txt", new String[]{"in1.txt"}));
        Assertions.assertEquals(IntegerMerger.class, merger.getClass());
    }
}