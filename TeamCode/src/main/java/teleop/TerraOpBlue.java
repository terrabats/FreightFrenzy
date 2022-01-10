package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import automodules.StageList;
import automodules.stage.Stage;
import elements.FieldSide;
import global.Constants;
import teleutil.GamepadHandler;
import teleutil.button.Button;
import teleutil.button.ButtonEventHandler;
import teleutil.button.NotHeldEventHandler;
import teleutil.button.OnNotHeldEventHandler;
import teleutil.button.OnPressEventHandler;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;
import util.Timer;

import static global.General.autoModules;
import static global.General.bot;
import static global.General.fieldSide;
import static global.General.gamepad1;
import static global.General.gamepad2;
import static global.General.gph1;
import static global.General.gph2;
import static global.General.log;

/**
 * NOTE: Uncommented
 */

@TeleOp(name = "TerraOpBlue", group = "TeleOp")
public class TerraOpBlue extends Tele{

    @Override
    public void init() {
        super.init();

        //Gamepad 1
        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, () -> bot.intake.move(1));
        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, () -> bot.intake.move(0));
        gph1.link(Button.LEFT_BUMPER, OnPressEventHandler.class, () -> bot.intake.move(-1));
        gph1.link(Button.LEFT_BUMPER, OnNotHeldEventHandler.class, () -> bot.intake.move(0));

        //Gamepad 2
        gph2.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, () -> bot.outtake.open());
        gph2.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, () -> bot.outtake.start());

        gph1.link(Button.A, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.Intake));
        gph1.link(Button.B, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.Backward));
        gph1.link(Button.Y, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.Forward));
        gph1.link(Button.X, OnPressEventHandler.class, bot::cancelAutoModules);

        gph1.link(Button.DPAD_DOWN, OnPressEventHandler.class, bot::pauseAutoModules);
        gph1.link(Button.DPAD_UP, OnPressEventHandler.class, bot::resumeAutoModules);

        bot.outtake.start();

        super.activate(FieldSide.BLUE);
    }

    @Override
    public void loop() {

        // Gamepad1
        bot.tankDrive.moveSmooth(-gamepad1.right_stick_y, gamepad1.left_stick_x);

        bot.carousel.move(gamepad1.right_trigger);

        // Gamepad2
        bot.turret.move(gamepad2.left_stick_x);

        bot.lift.move(-gamepad2.right_stick_y + Constants.LIFT_REST_POW);

        super.loop();
    }
}
