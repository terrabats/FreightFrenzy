package unittests.framework;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import unittests.UnitTest;
import util.User;

import static global.General.*;

public class ModulesTest extends UnitTest {
    @Override
    protected void run() {
        bot.rfsHandler.addToQueue(new Stage(
                modules.exitTime(1)
        ));
        bot.rfsHandler.addToQueue(new Stage(
                modules.usePart(bot.tankDrive),
                modules.mainTankDrive(0.3,0),
                modules.exitTime(1),
                modules.stopTankDrive(),
                modules.returnPart(bot.tankDrive)
        ));
    }

    @Override
    protected void loop() {
        bot.tankDrive.move(-0.3, 0);
    }
}
