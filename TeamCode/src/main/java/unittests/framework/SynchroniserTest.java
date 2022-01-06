package unittests.framework;
import static global.General.*;

import debugging.Synchroniser;
import unittests.UnitTest;

public class SynchroniserTest extends UnitTest {
    Synchroniser synch = new Synchroniser();

    @Override
    protected void start() {
        synch.resetDelay();
    }

    @Override
    protected void loop() {
        log.show("Did resetDelay actually reset everything? ",synch.getDelay());

        if (gamepad1.a)
        {
            synch.update();
        }
    }

    @Override
    public void stop() {
        synch.logDelay();
    }
}
