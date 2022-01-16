package unittests.tele.hardware;

import unittests.tele.TeleUnitTest;
import static global.General.*;

public class CarouselTest extends TeleUnitTest {
    /**
     * Tests carousel
     */
    @Override
    public void loop() {
        showConfig(bot.carousel);
        /**
         * Carousel should move
         */
        log.show("Use right trigger");
        bot.carousel.move(gamepad1.right_trigger);
    }
}
