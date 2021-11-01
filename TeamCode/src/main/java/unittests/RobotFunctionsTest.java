package unittests;

import util.Stage;

import static global.General.*;


public class RobotFunctionsTest extends UnitTest {
    @Override
    public void init() {
        bot.rfsHandler.addToQueue(new Stage() {
            @Override
            public boolean run(double in) {
                bot.mechDrive.move(0.3, 0, 0);
                return in > 1.5;
            }
            @Override
            public void runOnStop() {
                bot.mechDrive.move(0, 0, 0);
            }
        });
        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    @Override
    public void start() {
        bot.start();
    }

    @Override
    public void stop() {
        bot.stop();
    }
}
