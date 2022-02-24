package automodules;

import java.util.ArrayList;
import java.util.Arrays;

import automodules.stage.Stage;
import robotparts.RobotPart;

import static global.General.log;

public class StageList {
    /**
     * List of stages
     */
    private final ArrayList<Stage> stages = new ArrayList<>();

    /**
     * Create a stage list using the robot parts used
     * @param stageArray
     */
    public StageList(Stage... stageArray){
        stages.addAll(Arrays.asList(stageArray));
    }

    public StageList define(Stage... stageArray){
        stages.addAll(Arrays.asList(stageArray));
        return this;
    }

    public StageList add(StageList other) {
        stages.addAll(other.getStages());
        return this;
    }

    /**
     * Get the arraylist of stages
     * @return stages
     */
    public ArrayList<Stage> getStages(){
        return stages;
    }
}
