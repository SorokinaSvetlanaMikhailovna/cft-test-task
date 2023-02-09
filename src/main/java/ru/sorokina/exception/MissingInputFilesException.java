package ru.sorokina.exception;

public class MissingInputFilesException extends RuntimeException {
    public MissingInputFilesException(String message) {
        super(message);
    }

    public MissingInputFilesException() {
    }
}
