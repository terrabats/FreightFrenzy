package util.codeseg;

/**
 * Used to return and object and input a parameter
 * @param <P>
 * @param <R>
 */
public interface ReturnParameterCodeSeg<P, R>{
    R run(P input);
}
