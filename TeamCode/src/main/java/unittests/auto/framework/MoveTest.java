package unittests.auto.framework;

import unittests.auto.AutoUnitTest;
import util.Timer;

import static global.General.bot;

public class MoveTest extends AutoUnitTest {
    Timer timer = new Timer();
    @Override
    public void init() {
        timer.reset();
    }

    @Override
    protected void run() {
        while (timer.seconds() < 1 && opModeIsActive()){
            bot.tankDrive.move(0.3, 0);
        }
    }

    @Override
    public void stop() {
        bot.tankDrive.move(0,0);
    }
}
