package unittests.hardware;

import automodules.AutoModules;
import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import unittests.UnitTest;
import static global.General.*;

public class LiftTest extends UnitTest {
    @Override
    protected void start() {
        showConfig(bot.lift);

        gph1.link(Button.A, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.test));
        gph1.link(Button.B, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.test2));
    }

    @Override
    public void loop() {
        bot.lift.move(-gamepad1.right_stick_y);

        log.show("target pos", bot.lift.getTarget());
        log.show("pos", bot.lift.getLiftPos());
    }
}
