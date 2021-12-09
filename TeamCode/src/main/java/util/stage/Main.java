package util.stage;

import util.codeseg.CodeSeg;

public class Main extends StageComponent{
    private CodeSeg loop;
    public Main(CodeSeg loopCode){
        loop = loopCode;
    }
    public void loop(){
        loop.run();
    }
}
