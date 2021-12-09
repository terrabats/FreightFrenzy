package automodules.stage;

import util.codeseg.CodeSeg;

public class Stop extends StageComponent{
    private CodeSeg stop;
    public Stop(CodeSeg runOnStop){
        stop = runOnStop;
    }
    public void runOnStop(){
        stop.run();
    }
}
