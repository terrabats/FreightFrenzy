package unittests;

import teleutil.button.Button;
import teleutil.button.ButtonEventHandler;
import teleutil.button.ChangeHoldEventHandler;
import teleutil.button.OnPressEventHandler;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;
import teleutil.button.WhenOffEventHandler;
import teleutil.button.WhenOnEventHandler;

import static global.General.*;

public class GamepadTest extends UnitTest {
    private int a;
    @Override
    public void start() {
        gph1.link(Button.A, ButtonEventHandler.class, args -> log.watch("Held"));
        gph1.link(Button.A, OnPressEventHandler.class, args -> log.display("Now Pressed" + a));
        gph1.link(Button.A, ChangeHoldEventHandler.class, args -> log.display("Changed Hold" + a));
        gph1.link(Button.A, OnTurnOnEventHandler.class, args -> log.display("Now turned on" + a));
        gph1.link(Button.A, OnTurnOffEventHandler.class, args -> log.display("Now turned off" + a));
        gph1.link(Button.A, WhenOnEventHandler.class, args -> log.watch("On"));
        gph1.link(Button.A, WhenOffEventHandler.class, args -> log.watch("Off"));
    }

    @Override
    protected void loop() {
        a++;
    }
}
