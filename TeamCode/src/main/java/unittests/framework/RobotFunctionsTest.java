package unittests.framework;

import util.stage.Exit;
import util.stage.Main;
import util.stage.Stage;
import util.stage.Stop;

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
