package unittests.tele.sensor;


import java.util.Arrays;

import unittests.tele.TeleUnitTest;
import static global.General.*;

/**
 * NOTE: Uncommented
 */

public class ColorTest extends TeleUnitTest {
    @Override
    protected void loop() {
        log.show("hsv", Arrays.toString(bot.colorSensors.getOuttakeColorHSV()));
        log.show("isBall", bot.colorSensors.isBall());
        log.show("isCube", bot.colorSensors.isCube());
    }
}
