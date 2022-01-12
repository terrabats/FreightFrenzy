package unittests.tele.framework;

import unittests.tele.TeleUnitTest;

import static global.General.*;


/**
 * NOTE: Uncommented
 */

public class LagTest extends TeleUnitTest {
    @Override
    protected void loop() {
        log.show("Delay: ", sync.getDelay());
    }
}
