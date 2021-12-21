package util.codeseg;

// TODO FIX
// Replace all of the other codesegs with this type
public interface ParameterCodeSeg<I, R>{
    R run(I input);
}
