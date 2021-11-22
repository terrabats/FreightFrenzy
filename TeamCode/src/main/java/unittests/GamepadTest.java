package unittests;

import teleutil.button.Button;
import teleutil.button.ButtonEventType;
import util.ExceptionCatcher;

import static global.General.*;

// WORKS!

public class GamepadTest extends UnitTest {
    public static int i = 0;
    @Override
    protected void start() {
        gph1.link(Button.A, ButtonEventType.NORMAL, args -> log.display("A Button Held " + GamepadTest.i));
        gph1.link(Button.B, ButtonEventType.NORMAL, args -> log.display("B Button Held " + GamepadTest.i));
        gph1.link(Button.A, ButtonEventType.ON_PRESS, args -> log.display("A Button Now Pressed " + GamepadTest.i));
    }

    @Override
    public void loop() {
        i++;
    }
}
