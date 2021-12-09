package unittests.hardware;

import teleutil.button.Button;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;
import unittests.UnitTest;
import static global.General.*;

public class OuttakeTest extends HardwareTest {
    @Override
    protected void run() {
        gph1.link(Button.RIGHT_TRIGGER, OnTurnOnEventHandler.class, args -> bot.outtake.move(0.3));
        gph1.link(Button.RIGHT_TRIGGER, OnTurnOffEventHandler.class, args -> bot.outtake.move(0));
        showConfig(bot.outtake);
    }
}
