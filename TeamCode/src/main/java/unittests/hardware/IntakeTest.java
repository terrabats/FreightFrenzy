package unittests.hardware;

import static global.General.*;

import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;
import unittests.UnitTest;

public class IntakeTest extends UnitTest {
    @Override
    public void run() {
        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, args -> bot.intake.spin(1));
        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, args -> bot.intake.spin(0));
        gph1.link(Button.RIGHT_BUMPER, OnPressEventHandler.class, args -> bot.intake.spin(-1));
    }
}
