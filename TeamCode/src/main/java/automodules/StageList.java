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
     * Create a stage list using the robot parts used
     * @param stageArray
     */
    public StageList(Stage... stageArray){
        stages.addAll(Arrays.asList(stageArray));
    }

    public StageList add(Stage... stageArray){
        ArrayList<Stage> newStages = new ArrayList<>();
        newStages.addAll(stages);
        newStages.addAll(Arrays.asList(stageArray));
        return new StageList((Stage[]) newStages.toArray());
    }

    public StageList add(StageList other) {
        return add((Stage[]) other.stages.toArray());
    }

    public StageList add(StageList... other) {
        StageList newStageList = new StageList().add(this);
        for (StageList list : other) {
            add(list);
        }
        return newStageList;
    }

    /**
     * Get the arraylist of stages
     * @return stages
     */
    public ArrayList<Stage> getStages(){
        return stages;
    }
}
