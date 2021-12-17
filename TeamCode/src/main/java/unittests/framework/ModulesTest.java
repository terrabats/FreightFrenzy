package unittests.framework;
import automodules.stage.Stage;
import unittests.UnitTest;

import static global.General.*;

public class ModulesTest extends UnitTest {
    @Override
    protected void run() {
        log.display("Works");
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
        if(bot.tankDrive.isInactive()) {
            bot.tankDrive.moveTele(-0.3, 0);
        }
    }
}
