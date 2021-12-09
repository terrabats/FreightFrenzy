package unittests.framework;
import automodules.stage.Stage;

import static global.General.*;

public class ModulesTest extends FrameworkTest{
    @Override
    protected void run() {
        bot.rfsHandler.addToQueue(new Stage(
                modules.mainTankDrive(0.3,0),
                modules.exitTime(1),
                modules.stopTankDrive()
        ));
    }
}
