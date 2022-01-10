package automodules;

import java.util.ArrayList;
import java.util.Arrays;

import automodules.stage.Stage;

public class StageList {
    /**
     * List of stages
     */
    private final ArrayList<Stage> stages = new ArrayList<>();

    /**
     * Create a stage list using stages
     * @param stageArray
     */
    public StageList(Stage... stageArray){
        stages.addAll(Arrays.asList(stageArray));
    }

    /**
     * Get the arraylist of stages
     * @return stages
     */
    public ArrayList<Stage> getStages(){
        return stages;
    }
}
