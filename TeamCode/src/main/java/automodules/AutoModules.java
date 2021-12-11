package automodules;

import java.util.ArrayList;
import static global.General.*;

import automodules.stage.Stage;
import elements.GameElement;
import global.Constants;

public class AutoModules {

//    public StageList ResetLift = new StageList(
//        stages.liftDown(-0.2)
//    );

    public volatile StageList Intake = new StageList(
        stages.liftEncoder(0.3, 0),
        stages.intakeUntilFreight(1),
        stages.outtakeLock(GameElement.BALL, 0.5),
        stages.liftEncoder(0.4, 10),
        stages.outtakeLock(GameElement.CUBE, 0.5)
    );
    public volatile StageList Backward = new StageList(
        stages.liftEncoder(0.4, 55),
        stages.turretEncoderTarget(0.4),
        stages.robotSetSlowMode(true)
    );
    public volatile StageList Forward = new StageList(
        stages.robotSetSlowMode(false),
        stages.outtakeDrop(1),
        stages.turretEncoder(0.4, 0),
        stages.liftEncoder(0.3, 10),
        stages.outtakeReset(0.7)
    );

}
