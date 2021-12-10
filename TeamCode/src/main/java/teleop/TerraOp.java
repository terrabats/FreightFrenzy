package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import teleutil.button.Button;
import teleutil.button.ButtonEventHandler;
import teleutil.button.OnPressEventHandler;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;

import static global.General.bot;
import static global.General.gamepad1;
import static global.General.gamepad2;
import static global.General.gph1;

@TeleOp(name = "TerraOp", group = "TeleOp")
public class TerraOp extends Tele{

    @Override
    public void init() {
        reference(this);

        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, args -> bot.intake.moveTele(1));
        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, args -> bot.intake.moveTele(0));
        gph1.link(Button.RIGHT_BUMPER, ButtonEventHandler.class, args -> bot.intake.moveTele(-1));

        gph1.link(Button.LEFT_BUMPER, OnTurnOnEventHandler.class, args -> bot.outtake.moveTele(0.3));
        gph1.link(Button.LEFT_BUMPER, OnTurnOffEventHandler.class, args -> bot.outtake.moveTele(0));

        activate();
    }

    @Override
    public void start() {
        ready();
    }

    @Override
    public void loop() {
        bot.tankDrive.moveTele(-gamepad1.right_stick_y, gamepad1.left_stick_x);

        bot.carousel.moveTele(gamepad1.right_trigger);

        bot.turret.moveTele(gamepad2.left_stick_x);

        bot.lift.moveTele(-gamepad2.right_stick_y);
        update(true);
    }

    @Override
    public void stop() {
        end();
    }
}
