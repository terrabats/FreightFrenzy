package automodules.stage;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;

public class Stage {

    private final ArrayList<StageComponent> components = new ArrayList<>();
    private Pause pause = new Pause(false);
    private boolean hasStarted = false;

    public Stage(StageComponent...stageComponents){
        components.addAll(Arrays.asList(stageComponents));
    }
    public Stage(Pause p){
        pause = p;
    }
    public boolean hasStarted(){
        return hasStarted;
    }

    public void start(){
        for(StageComponent sc: components){
            sc.start();
        }
        hasStarted = true;
    }
    public void loop(){
        for(StageComponent sc: components){
            sc.loop();
        }
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
        for(StageComponent sc: components){
            sc.runOnStop();
        }
        hasStarted = false;
    }
    public boolean isPause(){
        return pause.isActive();
    }

}
