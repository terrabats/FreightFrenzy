package util;

public class ExceptionCatcher {

    //Interface for runnable
    public interface InterruptedExceptionRunnable {
        void run() throws InterruptedException;
    }
    //Method to sleep for certain time
    public static void catchInterrupted(InterruptedExceptionRunnable runnable) {
        try { runnable.run(); } catch (InterruptedException ignored) { }
    }
}