package teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import teleutil.button.Button;
import teleutil.button.OnNotHeldEventHandler;
import teleutil.button.OnPressEventHandler;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;

import static global.General.autoModules;
import static global.General.bot;
import static global.General.gph1;

@TeleOp(name = "RiseOpBlue", group = "TeleOp")
public class RiseOpBlue extends Tele{
    @Override
    public void startTele() {
        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, () -> bot.intake.move(1));
        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, () -> bot.intake.move(0));
        gph1.link(Button.LEFT_BUMPER, OnPressEventHandler.class, () -> bot.intake.move(-1));
        gph1.link(Button.LEFT_BUMPER, OnNotHeldEventHandler.class, () -> bot.intake.move(0));

        gph1.link(Button.RIGHT_TRIGGER, OnPressEventHandler.class, () -> bot.carousel.move(1));
        gph1.link(Button.RIGHT_TRIGGER, OnNotHeldEventHandler.class, () -> bot.carousel.move(0));

        gph1.link(Button.X, OnPressEventHandler.class, () -> bot.cancelAutoModules());
        gph1.link(Button.Y, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.IntakeRiseTele));
        gph1.link(Button.B, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.ForwardRiseTele));

        gph1.link(Button.LEFT_TRIGGER, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.DuckRiseTele));
    }

    @Override
    public void loopTele() {
        bot.mecanumDrive.moveSmooth(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
        bot.intake.move(-gamepad2.right_stick_y);
        bot.lift.setToRestPow();
    }
}

