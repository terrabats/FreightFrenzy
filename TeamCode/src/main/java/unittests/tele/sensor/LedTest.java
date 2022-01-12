package unittests.tele.sensor;

import robotparts.electronics.OLed;
import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import unittests.tele.TeleUnitTest;

import static global.General.bot;
import static global.General.gph1;

/**
 * NOTE: Uncommented
 */

public class LedTest extends TeleUnitTest {
    @Override
    protected void start() {
        gph1.link(Button.X, OnPressEventHandler.class, () -> bot.led.setColorOfLEDs(OLed.LEDColor.OFF));
        gph1.link(Button.Y, OnPressEventHandler.class, () -> bot.led.setColorOfLEDs(OLed.LEDColor.GREEN));
        gph1.link(Button.A, OnPressEventHandler.class, () -> bot.led.setColorOfLEDs(OLed.LEDColor.RED));
        gph1.link(Button.B, OnPressEventHandler.class, () -> bot.led.setColorOfLEDs(OLed.LEDColor.ORANGE));
    }

}
