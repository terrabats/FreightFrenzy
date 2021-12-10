package automodules;


import java.util.ArrayList;
import java.util.Arrays;

import automodules.stage.Stage;

public class StageList {
    private ArrayList<Stage> stages;
    public StageList(Stage... stageArray){
        stages.addAll(Arrays.asList(stageArray));
    }

    public ArrayList<Stage> getStages(){
        return stages;
    }
}
