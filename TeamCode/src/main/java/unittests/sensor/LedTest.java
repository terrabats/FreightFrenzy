package unittests.sensor;

import robotparts.electronics.LED;
import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import unittests.UnitTest;

import static global.General.bot;
import static global.General.gph1;

public class LedTest extends UnitTest {
    @Override
    protected void start() {
        gph1.link(Button.X, OnPressEventHandler.class, args -> bot.led.setColorOfLEDs(LED.LEDColor.OFF));
        gph1.link(Button.Y, OnPressEventHandler.class, args -> bot.led.setColorOfLEDs(LED.LEDColor.GREEN));
        gph1.link(Button.A, OnPressEventHandler.class, args -> bot.led.setColorOfLEDs(LED.LEDColor.RED));
        gph1.link(Button.B, OnPressEventHandler.class, args -> bot.led.setColorOfLEDs(LED.LEDColor.ORANGE));
    }

}
