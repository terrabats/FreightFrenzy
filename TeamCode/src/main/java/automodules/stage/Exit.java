package automodules.stage;

import util.codeseg.TypeCodeSeg;

public class Exit extends StageComponent{
    private final TypeCodeSeg<Boolean> shouldEnd;

    public Exit(TypeCodeSeg<Boolean> endCode){
        shouldEnd = endCode;
    }
    @Override
    public boolean shouldStop(){
        return shouldEnd.run();
    }
}
