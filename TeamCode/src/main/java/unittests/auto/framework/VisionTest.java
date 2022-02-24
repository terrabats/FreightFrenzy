package unittests.auto.framework;

import unittests.UnitTest;
import unittests.auto.AutoUnitTest;

import static global.General.bot;
import static global.General.log;

public class VisionTest extends AutoUnitTest {
    @Override
    public void init() {
        bot.cameras.startExternalCamera();
    }
    @Override
    protected void run() {
        whileActive(() -> {
            log.show("FPS", bot.cameras.getFPS());
        });
    }

    @Override
    public void stop() {
        bot.cameras.stopExternalCamera();
    }
}
