package unittests;

import static global.General.*;

import teleutil.button.Button;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;

public class IntakeTest extends UnitTest {
    @Override
    public void run() {
        gph1.link(Button.Y, OnTurnOnEventHandler.class, args -> bot.intake.spin(1));
        gph1.link(Button.Y, OnTurnOffEventHandler.class, args -> bot.intake.spin(0));
    }

    @Override
    public void stop() {
        bot.intake.spin(0);
    }
}
