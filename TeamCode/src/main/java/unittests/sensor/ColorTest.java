package unittests.sensor;


import java.util.Arrays;

import unittests.UnitTest;
import static global.General.*;

public class ColorTest extends UnitTest {
    @Override
    protected void loop() {
        log.show("hsv", Arrays.toString(bot.colorSensors.getOuttakeColorHSV()));
        log.show("isBall", bot.colorSensors.isBall());
        log.show("isCube", bot.colorSensors.isCube());
    }
}
