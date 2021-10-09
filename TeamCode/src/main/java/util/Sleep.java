package util;

public class Sleep {

// TODO: Move this to some sort of helper class or something
    // Related note: Make some sort of error system that throws errors when things break



    //Interface for runnable
    public interface InterruptedExceptionRunnable {
        void run() throws InterruptedException;
    }
    //Method to sleep for certain time
    public static void trySleep(InterruptedExceptionRunnable runnable) {
        try { runnable.run(); } catch (InterruptedException ignored) { }
    }
}