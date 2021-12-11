package automodules;

import java.util.ArrayList;
import static global.General.*;

import automodules.stage.Stage;
import elements.GameElement;

public class AutoModules {

    public StageList ResetLift = new StageList(
        stages.liftDown(-0.2)
    );

    public StageList Intake = new StageList(
        stages.liftDown(-0.2),
        stages.intakeUntilFreight(1),
        stages.outtakeLock(GameElement.BALL, 0.5)
    );
    public StageList Backward = new StageList(
        stages.liftEncoder(0.4, 10),
        stages.outtakeLock(GameElement.CUBE, 0.5),
        stages.turretEncoderTarget(0.4)
    );
    public StageList Forward = new StageList(
        stages.outtakeDrop(1),
        stages.turretEncoder(0.4, 0),
        stages.liftEncoder(0.4, 5)
    );

}
