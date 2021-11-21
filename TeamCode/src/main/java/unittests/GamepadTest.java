package unittests;

import teleutil.button.Button;
import teleutil.button.ButtonEventType;
import util.ExceptionCatcher;

import static global.General.*;

public class GamepadTest extends UnitTest {
    @Override
    protected void start() {
        gph1.link(Button.A, ButtonEventType.NORMAL, args -> log.display("A Button", "Pressed"));
        gph1.link(Button.B, ButtonEventType.NORMAL, args -> log.display("B Button", "Pressed"));
        gph1.link(Button.A, ButtonEventType.ON_PRESS, args -> {
            log.display("A Button Changed hold");
            if (args[0] == 1) {
                log.display("A Button Now Pressed");
            } else {
                log.display("A Button Now Not Pressed");
            }
        });
    }
}
