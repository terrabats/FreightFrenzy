package unittests.hardware;

import teleutil.button.Button;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;
import unittests.UnitTest;
import static global.General.*;

public class OuttakeTest extends UnitTest {
    @Override
    protected void start() {
        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, () -> bot.outtake.move("open"));
        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, () -> bot.outtake.move("start"));
        showConfig(bot.outtake);
    }
}
