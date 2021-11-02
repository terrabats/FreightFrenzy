package util;

public class ExceptionCatcher {

    //Interface for runnable
    public interface InterruptedExceptionRunnable {
        void run() throws InterruptedException;
    }
    public interface NewInstanceRunnable{
        void run() throws IllegalAccessException, InstantiationException;
    }
    //Method to sleep for certain time
    public static void catchInterrupted(InterruptedExceptionRunnable runnable) {
        try { runnable.run(); } catch (InterruptedException ignored) { }
    }

    public static void catchNewInstance(NewInstanceRunnable runnable) {
        try {runnable.run(); } catch (IllegalAccessException | InstantiationException ignored) {}
    }
}