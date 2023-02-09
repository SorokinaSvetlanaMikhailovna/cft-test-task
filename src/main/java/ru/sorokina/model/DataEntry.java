package ru.sorokina.model;

import java.io.BufferedReader;

public class DataEntry<T>  {
    private T current;
    private final BufferedReader reader;

    public DataEntry(T current, BufferedReader reader) {
        this.current = current;
        this.reader = reader;
    }

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
