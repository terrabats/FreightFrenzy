package automodules;

import java.util.ArrayList;
import static global.General.*;

import automodules.stage.Stage;

public class AutoModules {
    public StageList ResetLift = new StageList(
            new Stage(
                    modules.mainLift(-0.2),
                    modules.exitLiftDown(),
                    modules.stopLife()
            )
    );
    public StageList Intake = new StageList(
            new Stage(
                    modules.mainTankDrive(0.3,0.2),
                    modules.exitTime(1),
                    modules.stopTankDrive()
            ),
            new Stage(
                    modules.mainTankDrive(0.2,0.3),
                    modules.exitTime(2),
                    modules.stopTankDrive()
            )
    );
}
