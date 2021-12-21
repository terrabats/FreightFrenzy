package util.codeseg;

/**
 * Used to pass in code with a type of parameter
 * @param <P>
 */
public interface ParameterCodeSeg<P> {
    void run(P input);
}
