package unittests.tele.hardware.Mecanum;

import static global.General.bot;
import static global.General.gamepad1;
import static global.General.gph1;

import automodules.MecanumAutoModules;
import teleutil.button.Button;
import unittests.tele.TeleUnitTest;

public class MecanumLiftTest extends TeleUnitTest {
    @Override
    public void init() {
        gph1.link(Button.A, MecanumAutoModules.UpLift);
        gph1.link(Button.B, MecanumAutoModules.ResetLift);
    }

    @Override
    protected void loop() {
        bot.lift.move(-gamepad1.right_stick_y);
    }
}
