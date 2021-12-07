package unittests.sensor;


import java.util.Arrays;

import unittests.UnitTest;
import static global.General.*;

public class ColorTest extends UnitTest {
    @Override
    protected void loop() {
        log.watch("rgb", Arrays.toString(bot.color.getOutakeColorRGB()));
        log.watch("hsv", Arrays.toString(bot.color.getOuttakeColorHSV()));
    }
}
