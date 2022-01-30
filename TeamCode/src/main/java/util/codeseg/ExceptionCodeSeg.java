package util.codeseg;

public interface ExceptionCodeSeg<E extends Throwable> {
    void run() throws E;
}
