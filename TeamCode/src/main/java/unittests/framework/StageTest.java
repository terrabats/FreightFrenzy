package unittests.framework;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import unittests.UnitTest;
import util.User;

import static global.General.*;

public class StageTest extends UnitTest {
    /**
     * Tests custom stages (or modules)
     */
    @Override
    protected void start() {
        /**
         * Wait for 1 second and then move the robot forward at 0.3 power for 1 second
         */
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

    /**
     * Try to move the robot the other way in the normal loop method
     * NOTE: This results in the robot going backward, forward, and then backward again
     */
    @Override
    protected void loop() {
        bot.tankDrive.move(-0.3, 0);
    }

    @Override
    public void stop() {
        bot.tankDrive.move(0,0);
    }
}
