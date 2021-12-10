package automodules.stage;

import util.codeseg.CodeSeg;

public class Initial extends StageComponent{
    private CodeSeg start;
    public Initial(CodeSeg startCode){
        start = startCode;
    }
    @Override
    public void start(){
        start.run();
    }
}
