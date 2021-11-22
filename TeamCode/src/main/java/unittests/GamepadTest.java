package unittests;

import teleutil.button.Button;
import teleutil.button.ButtonEventType;
import util.ExceptionCatcher;

import static global.General.*;

public class GamepadTest extends UnitTest {
    @Override
    public void start() {
        gph1.link(Button.A, ButtonEventType.NORMAL, args -> log.watch("A Button Held"));
        gph1.link(Button.B, ButtonEventType.NORMAL, args -> log.watch("B Button Held"));
        gph1.link(Button.A, ButtonEventType.ON_PRESS, args -> log.watch("A Button Now Pressed "));
    }
}
