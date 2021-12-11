package automodules;

import java.util.ArrayList;
import java.util.Arrays;

import automodules.stage.Stage;

public class StageList {
    private final ArrayList<Stage> stages = new ArrayList<>();
    public StageList(Stage... stageArray){
        stages.addAll(Arrays.asList(stageArray));
    }

    public ArrayList<Stage> getStages(){
        return stages;
    }
}
