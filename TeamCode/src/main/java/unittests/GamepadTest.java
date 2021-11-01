package unittests;

import teleutil.button.Button;
import teleutil.button.ButtonEventType;
import util.ExceptionCatcher;

import static global.General.*;

public class GamepadTest extends UnitTest {
    @Override
    public void init() {
        gph1.link(Button.A, ButtonEventType.NORMAL, args -> telemetry.addData("A Button", "Pressed"));
        gph1.link(Button.B, ButtonEventType.NORMAL, args -> telemetry.addData("B Button", "Pressed"));
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
