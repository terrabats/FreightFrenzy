package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class CarouselTest extends UnitTest {
    @Override
    protected void start() {
        showConfig(bot.carousel);
    }

    @Override
    public void loop() {
        bot.carousel.move(gamepad1.right_trigger);
    }
}
