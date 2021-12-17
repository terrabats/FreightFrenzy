package unittests.sensor;


import java.util.Arrays;

import unittests.UnitTest;
import static global.General.*;

public class ColorTest extends UnitTest {
    @Override
    protected void loop() {
        log.watch("hsv", Arrays.toString(bot.color.getOuttakeColorHSV()));
        log.watch("isBall", bot.color.isBall());
        log.watch("isCube", bot.color.isCube());
    }
}
