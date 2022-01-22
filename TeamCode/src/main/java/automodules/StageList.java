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
     * List of the robot parts being used in this stageList
     */
    private final ArrayList<RobotPart> robotPartsUsed = new ArrayList<>();

    /**
     * Create a stage list using the robot parts used
     * @param parts
     */
    public StageList(RobotPart... parts){
        robotPartsUsed.addAll(Arrays.asList(parts));
    }

    /**
     * Define the stage list using stages
     * NOTE:
     * @param stageArray
     * @return this stageList
     */
    public StageList define(Stage... stageArray){
        if(usedPartsExist()){
            stages.addAll(Arrays.asList(stageArray));
        }else{
            log.record("Some parts were missing for automodule of length", stageArray.length);
        }
        return this;
    }

    /**
     * Do used parts exist? (i.e. all parts are non null)
     * @return part nonnull
     */
    public boolean usedPartsExist(){
        for(RobotPart part: getRobotPartsUsed()){
            if(part == null){ return false; }
        }
        return true;
    }

    /**
     * Get the arraylist of stages
     * @return stages
     */
    public ArrayList<Stage> getStages(){
        return stages;
    }

    /**
     * Get the arraylist of robotParts used
     * @return parts
     */
    public ArrayList<RobotPart> getRobotPartsUsed(){ return robotPartsUsed; }
}
