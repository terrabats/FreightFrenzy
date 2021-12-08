package util;
import java.io.FileNotFoundException;
import java.io.IOException;

import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;
public class ExceptionCatcher {

    //Interface for runnable
    public interface InterruptedExceptionRunnable {
        void run() throws InterruptedException;
    }
    public interface NewInstanceRunnable{
        void run() throws IllegalAccessException, InstantiationException;
    }
    public interface IOExceptionRunnable{
        void run() throws IOException;
    }
    //Method to sleep for certain time
    public static void catchInterrupted(InterruptedExceptionRunnable runnable) {
        try { runnable.run(); } catch (InterruptedException ignored) {
            fault.check("Illegal Access or Not Instantiated new Instance", Expectation.UNEXPECTED, Magnitude.CRITICAL, false);
        }
    }

    public static void catchNewInstance(NewInstanceRunnable runnable) {
        try {runnable.run(); } catch (IllegalAccessException | InstantiationException ignored) {
            fault.check("Illegal Access or Not Instantiated new Instance", Expectation.UNEXPECTED, Magnitude.CRITICAL, false);
        }
    }
    public static void catchIO(IOExceptionRunnable runnable) {
        try {runnable.run(); } catch (IOException ignored) {
            fault.check("IO exception, file does not exist?", Expectation.UNEXPECTED, Magnitude.CRITICAL, false);
        }
    }
}