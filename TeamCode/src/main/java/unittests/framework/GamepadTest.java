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
        gph1.link(Button.A, ButtonEventHandler.class, () -> log.watch("Held"));
        gph1.link(Button.A, OnPressEventHandler.class, () -> log.display("Now Pressed" + a));
        gph1.link(Button.A, ChangeHoldEventHandler.class, () -> log.display("Changed Hold" + a));
        gph1.link(Button.A, OnTurnOnEventHandler.class, () -> log.display("Now turned on" + a));
        gph1.link(Button.A, OnTurnOffEventHandler.class, () -> log.display("Now turned off" + a));
        gph1.link(Button.A, WhenOnEventHandler.class, () -> log.watch("On"));
        gph1.link(Button.A, WhenOffEventHandler.class, () -> log.watch("Off"));
    }

    @Override
    protected void loop() {
        a++;
    }
}
