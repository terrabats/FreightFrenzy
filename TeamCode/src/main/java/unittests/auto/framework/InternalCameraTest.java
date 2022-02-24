package unittests.auto.framework;

import unittests.UnitTest;
import unittests.auto.AutoUnitTest;

import static global.General.bot;
import static global.General.log;

public class InternalCameraTest extends AutoUnitTest {
    @Override
    public void init() {
        bot.cameras.startInternalCamera();
    }

    @Override
    protected void run() {
        whileActive(() -> {
            log.show("Internal Camera FPS", bot.cameras.getInternalFPS());
        });
    }

    @Override
    public void stop() {
        bot.cameras.stopInternalCamera();
    }
}
