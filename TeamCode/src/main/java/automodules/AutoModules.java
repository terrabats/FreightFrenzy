package automodules;

import java.util.ArrayList;
import static global.General.*;

import automodules.stage.Stage;
import elements.GameElement;
import global.Constants;

public class AutoModules {

    public StageList Intake = new StageList(
        stages.liftEncoder(0.4, 0),
        stages.intakeUntilFreight(1),
        stages.outtakeLock(GameElement.BALL, 0.7),
        stages.liftEncoder(0.8, 10),
        stages.outtakeLock(GameElement.CUBE, 0.5)
    );
    public StageList Backward = new StageList(
        stages.liftEncoder(0.8, 55),
        stages.turretEncoderTarget(0.8),
        stages.robotSetSlowMode(true)
    );
    public StageList Forward = new StageList(
        stages.robotSetSlowMode(false),
        stages.outtakeDrop(1),
        stages.turretEncoder(0.8, 0),
        stages.liftEncoder(0.4, 10),
        stages.outtakeReset(0.7)
    );

}
