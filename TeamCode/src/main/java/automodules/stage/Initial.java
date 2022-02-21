package automodules.stage;

import util.codeseg.CodeSeg;
// TODO FIX
// Make multiple initials works
public class Initial extends StageComponent{
    /**
     * Initial runs once in the start of a stage
     */
    private final CodeSeg start;
    public Initial(CodeSeg startCode){
        start = startCode;
    }
    @Override
    public void start(){
        start.run();
    }
}
