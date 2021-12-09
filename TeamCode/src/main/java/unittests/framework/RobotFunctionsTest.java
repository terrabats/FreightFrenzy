package unittests.framework;

import automodules.stage.Exit;
import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;

import static global.General.*;

public class RobotFunctionsTest extends FrameworkTest {
    @Override
    public void run() {
        bot.rfsHandler.addToQueue(new Stage(
                new Main(() -> bot.tankDrive.move(0.3, 0)),
                new Exit(() -> bot.rfsHandler.timer.seconds() > 1),
                new Stop(() -> bot.tankDrive.move(0,0))
        ));
        bot.rfsHandler.addToQueue(new Stage(
                new Main(() -> bot.tankDrive.move(-0.3, 0)),
                new Exit(() -> bot.rfsHandler.timer.seconds() > 1),
                new Stop(() -> bot.tankDrive.move(0,0))
        ));
        bot.rfsHandler.addToQueue(new Stage(
                new Main(() -> bot.tankDrive.move(0, 0.2)),
                new Exit(() -> bot.rfsHandler.timer.seconds() > 1),
                new Stop(() -> bot.tankDrive.move(0,0))
        ));
    }
}
