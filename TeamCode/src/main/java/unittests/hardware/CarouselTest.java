package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class CarouselTest extends UnitTest {
    /**
     * Tests carousel
     */
    @Override
    protected void start() {
        showConfig(bot.carousel);
    }

    @Override
    public void loop() {
        log.show("Use right trigger");
        bot.carousel.move(gamepad1.right_trigger);
    }
}
