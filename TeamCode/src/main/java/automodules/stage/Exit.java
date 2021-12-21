package automodules.stage;

import util.codeseg.ReturnCodeSeg;

public class Exit extends StageComponent{
    private final ReturnCodeSeg<Boolean> shouldEnd;

    public Exit(ReturnCodeSeg<Boolean> endCode){
        shouldEnd = endCode;
    }
    @Override
    public boolean shouldStop(){
        return shouldEnd.run();
    }
}
