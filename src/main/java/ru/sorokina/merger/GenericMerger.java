package ru.sorokina.merger;

import ru.sorokina.model.Arguments;
import ru.sorokina.model.DataEntry;
import ru.sorokina.model.SortType;
import ru.sorokina.util.FileUtil;

import java.io.*;
import java.util.*;

abstract public class GenericMerger<T extends Comparable<T>> implements Merger {

    private final Comparator<T> comparator;
    private final Arguments arguments;
    private T previous;
    private TreeMap<T, List<DataEntry<T>>> workingMap;
    private BufferedWriter writer;

    protected GenericMerger(Arguments arguments) {
        this.arguments = arguments;
        if (arguments.getSortType() == SortType.ASC) {
            this.comparator = Comparator.naturalOrder();
        } else {
            this.comparator = Comparator.reverseOrder();
        }
        this.previous = null;
    }

    public abstract T getValue(String line);

    public abstract boolean validLine(String line);

    @Override
    public void merge() {
        workingMap = initDataEntries();
        writer = FileUtil.openOutputFile(arguments.getOutputFileName());

        Map.Entry<T, List<DataEntry<T>>> mapEntry = workingMap.pollFirstEntry();
        while (mapEntry != null) {
            List<DataEntry<T>> dataEntries = mapEntry.getValue();
            if (previous == null || comparator.compare(previous, mapEntry.getKey()) <= 0) {
                for (DataEntry<T> dataEntry : dataEntries) {
                    writeValue(dataEntry);
                    nextValue(dataEntry);
                }
                previous = mapEntry.getKey();
            } else {
                for (DataEntry<T> dataEntry : dataEntries) {
                    nextValue(dataEntry);
                }
            }
            mapEntry = workingMap.pollFirstEntry();
        }

        FileUtil.closeOutputFile(writer);
    }

    private void writeValue(DataEntry<T> dataEntry) {
        try {
            writer.write(dataEntry.getCurrent().toString());
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void nextValue(DataEntry<T> dataEntry) {
        try {
            String line = nextLine(dataEntry.getReader());
            if (line != null) {
                dataEntry.setCurrent(getValue(line));
                if (workingMap.containsKey(dataEntry.getCurrent())) {
                    workingMap.get(dataEntry.getCurrent()).add(dataEntry);
                } else {
                    workingMap.put(dataEntry.getCurrent(), new ArrayList<>(List.of(dataEntry)));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private TreeMap<T, List<DataEntry<T>>> initDataEntries() {
        TreeMap<T, List<DataEntry<T>>> workingMap = new TreeMap<>(comparator);
        String[] inputs = arguments.getInputFileNames();
        for (String inputFileName : inputs) {
            try {
                File file = new File(inputFileName);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = nextLine(reader);
                if (line != null) {
                    DataEntry<T> dataEntry = new DataEntry<>(getValue(line), reader);
                    if (workingMap.containsKey(dataEntry.getCurrent())) {
                        workingMap.get(dataEntry.getCurrent()).add(dataEntry);
                    } else {
                        workingMap.put(dataEntry.getCurrent(), new ArrayList<>(List.of(dataEntry)));
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return workingMap;
    }

    private String nextLine(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        while (!validLine(line)) {
            line = bufferedReader.readLine();
        }
        return line;
    }
}
