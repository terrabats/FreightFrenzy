package unittests.framework;

import teleutil.button.Button;
import teleutil.button.ButtonEventHandler;
import teleutil.button.ChangeHoldEventHandler;
import teleutil.button.OnPressEventHandler;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;
import teleutil.button.WhenOffEventHandler;
import teleutil.button.WhenOnEventHandler;
import unittests.UnitTest;

import static global.General.*;

public class GamepadTest extends UnitTest {
    /**
     * Tests gamepad handler by using the a button
     */


    /**
     * Used so that messages are different ever time presses are logged
     */
    private int i;

    @Override
    protected void start() {
        gph1.link(Button.A, ButtonEventHandler.class, () -> log.show("Held"));
        gph1.link(Button.A, OnPressEventHandler.class, () -> log.showAndRecord("Now Pressed" , i));
        gph1.link(Button.A, ChangeHoldEventHandler.class, () -> log.showAndRecord("Changed Hold" , i));
        gph1.link(Button.A, OnTurnOnEventHandler.class, () -> log.showAndRecord("Now turned on" , i));
        gph1.link(Button.A, OnTurnOffEventHandler.class, () -> log.showAndRecord("Now turned off" , i));
        gph1.link(Button.A, WhenOnEventHandler.class, () -> log.show("On"));
        gph1.link(Button.A, WhenOffEventHandler.class, () -> log.show("Off"));
    }

    @Override
    protected void loop() {
        log.show("Click the A button to test");
        i++;
    }
}
