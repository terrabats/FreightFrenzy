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
    private int a;
    @Override
    protected void start() {
        gph1.link(Button.A, ButtonEventHandler.class, () -> log.show("Held"));
        gph1.link(Button.A, OnPressEventHandler.class, () -> log.showAndRecord("Now Pressed" , a));
        gph1.link(Button.A, ChangeHoldEventHandler.class, () -> log.showAndRecord("Changed Hold" , a));
        gph1.link(Button.A, OnTurnOnEventHandler.class, () -> log.showAndRecord("Now turned on" , a));
        gph1.link(Button.A, OnTurnOffEventHandler.class, () -> log.showAndRecord("Now turned off" , a));
        gph1.link(Button.A, WhenOnEventHandler.class, () -> log.show("On"));
        gph1.link(Button.A, WhenOffEventHandler.class, () -> log.show("Off"));
    }

    @Override
    protected void loop() {
        a++;
    }
}
