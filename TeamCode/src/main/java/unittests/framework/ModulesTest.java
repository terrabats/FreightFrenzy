package unittests.framework;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import unittests.UnitTest;
import util.User;

import static global.General.*;

public class ModulesTest extends UnitTest {
    @Override
    protected void start() {
//        bot.rfsHandler.addToQueue(bot.intake.intakeUntilFreight(1));
        bot.rfsHandler.addToQueue(new Stage(
                bot.intake.exitTime(1)
        ));
        bot.rfsHandler.addToQueue(new Stage(
                bot.tankDrive.usePart(),
                bot.tankDrive.main(0.3, 0),
                bot.tankDrive.exitTime(1),
                bot.tankDrive.stop(),
                bot.tankDrive.returnPart()
        ));
    }

    @Override
    protected void loop() {
        bot.tankDrive.move(-0.3, 0);
    }

    @Override
    public void stop() {
        bot.tankDrive.move(0,0);
    }
}
