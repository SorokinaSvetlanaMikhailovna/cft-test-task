package ru.sorokina;

import ru.sorokina.exception.CreationOutputFileException;
import ru.sorokina.exception.MissingDataTypeException;
import ru.sorokina.exception.MissingInputFilesException;
import ru.sorokina.exception.MissingOutputFilesException;

public class ExceptionHandler {

    public void handle(Runnable runnable) {
        try {
            runnable.run();
        } catch (MissingDataTypeException e) {
            System.out.println("You need to add data type key -s or -i");
        } catch (MissingInputFilesException e) {
            if (e.getMessage() != null) {
                System.out.println("Input file " + e.getMessage() + " not found");
            } else {
                System.out.println("You need to set input files");
            }
        } catch (MissingOutputFilesException e) {
            System.out.println("You need to set output file");
        } catch (CreationOutputFileException e) {
            System.out.println("Output file not exist and can't be created");
        } catch (RuntimeException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
