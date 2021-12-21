package unittests.sensor;

import robotparts.electronics.OLed;
import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import unittests.UnitTest;

import static global.General.bot;
import static global.General.gph1;

public class LedTest extends UnitTest {
    @Override
    protected void start() {
        gph1.link(Button.X, OnPressEventHandler.class, () -> bot.led.setColorOfLEDs(OLed.LEDColor.OFF));
        gph1.link(Button.Y, OnPressEventHandler.class, () -> bot.led.setColorOfLEDs(OLed.LEDColor.GREEN));
        gph1.link(Button.A, OnPressEventHandler.class, () -> bot.led.setColorOfLEDs(OLed.LEDColor.RED));
        gph1.link(Button.B, OnPressEventHandler.class, () -> bot.led.setColorOfLEDs(OLed.LEDColor.ORANGE));
    }

}
