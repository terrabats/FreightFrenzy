package unittests.hardware;

import unittests.UnitTest;
import static global.General.*;

public class CarouselTest extends UnitTest {
    @Override
    public void loop() {
        bot.carousel.spin(gamepad1.right_trigger);
    }
}
