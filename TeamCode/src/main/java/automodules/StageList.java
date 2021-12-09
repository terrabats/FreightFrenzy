package automodules;


import java.util.ArrayList;
import java.util.Arrays;

import automodules.stage.Stage;

public class StageList {
    private ArrayList<Stage> stages;
    public StageList(Stage... stages){
        this.stages = (ArrayList<Stage>) Arrays.asList(stages.clone());
    }

    public ArrayList<Stage> getStages(){
        return stages;
    }
}
