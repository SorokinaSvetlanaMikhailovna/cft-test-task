package ru.sorokina.util;

import ru.sorokina.exception.CreationOutputFileException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    public static BufferedWriter openOutputFile(String outputFileName) {
        File file = new File(outputFileName);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new CreationOutputFileException();
                }
            }
            return new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void closeOutputFile(BufferedWriter writer) {
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
