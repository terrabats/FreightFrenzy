package automodules.stage;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;

import util.codeseg.ParameterCodeSeg;

public class Stage {

    private final ArrayList<StageComponent> components = new ArrayList<>();
    private volatile boolean hasStarted = false;
    private volatile boolean isPause = false;

    public Stage(StageComponent...stageComponents){
        components.addAll(Arrays.asList(stageComponents));
    }
    public Stage(boolean isPause){
        this.isPause = isPause;
    }
    public boolean hasStarted(){
        return hasStarted;
    }

    public void start(){
        runForAllStageComponents(StageComponent::start);
        hasStarted = true;
    }
    public void loop(){
        runForAllStageComponents(StageComponent::loop);
    }
    public boolean shouldStop(){
        for(StageComponent sc: components){
            if(sc.shouldStop()){
                return true;
            }
        }
        return false;
    }
    public void runOnStop(){
        runForAllStageComponents(StageComponent::runOnStop);
        hasStarted = false;
    }
    public boolean isPause(){
        return isPause;
    }

    public void runForAllStageComponents(ParameterCodeSeg<StageComponent> code){
        for(StageComponent sc: components){
            code.run(sc);
        }
    }

}
