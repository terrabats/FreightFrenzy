package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class CarouselTest extends UnitTest {
    @Override
    protected void run() {
        showConfig(bot.carousel);
    }

    @Override
    public void loop() {
        bot.carousel.move(gamepad1.right_trigger);
    }
}
