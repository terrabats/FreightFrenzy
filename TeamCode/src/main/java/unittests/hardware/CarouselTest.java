package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class CarouselTest extends UnitTest {
    /**
     * Tests carousel
     */

    @Override
    public void loop() {
//        showConfig(bot.carousel);
        log.show("Use right trigger");
        bot.carousel.move(gamepad1.right_trigger);
    }
}
