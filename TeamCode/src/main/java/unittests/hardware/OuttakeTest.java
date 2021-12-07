package unittests.hardware;

import teleutil.button.Button;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;
import unittests.UnitTest;
import static global.General.*;

public class OuttakeTest extends UnitTest {
    @Override
    protected void run() {
        gph1.link(Button.LEFT_BUMPER, OnTurnOnEventHandler.class, args -> bot.outtake.move(0.3));
        gph1.link(Button.LEFT_BUMPER, OnTurnOffEventHandler.class, args -> bot.outtake.move(0));
    }
}
