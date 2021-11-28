package unittests;

import teleutil.button.Button;
import teleutil.button.ButtonEventHandler;
import teleutil.button.OnPressEventHandler;

import static global.General.*;

public class GamepadTest extends UnitTest {
    @Override
    public void start() {
        gph1.link(Button.A, ButtonEventHandler.class, args -> log.watch("A Button Held"));
        gph1.link(Button.B, ButtonEventHandler.class, args -> log.watch("B Button Held"));
        gph1.link(Button.A, OnPressEventHandler.class, args -> log.display("A Button Now Pressed"));
    }
}
