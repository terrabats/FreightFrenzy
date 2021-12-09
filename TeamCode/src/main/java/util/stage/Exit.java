package util.stage;

import util.codeseg.BooleanCodeSeg;
import util.codeseg.CodeSeg;

public class Exit extends StageComponent{
    private BooleanCodeSeg shouldEnd;

    public Exit(BooleanCodeSeg endCode){
        shouldEnd = endCode;
    }

    public boolean shouldEnd(){
        return shouldEnd.run();
    }
}
