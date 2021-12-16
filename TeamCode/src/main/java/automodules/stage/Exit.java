package automodules.stage;

import util.codeseg.BooleanCodeSeg;

public class Exit extends StageComponent{
    private final BooleanCodeSeg shouldEnd;

    public Exit(BooleanCodeSeg endCode){
        shouldEnd = endCode;
    }
    @Override
    public boolean shouldStop(){
        return shouldEnd.run();
    }
}
