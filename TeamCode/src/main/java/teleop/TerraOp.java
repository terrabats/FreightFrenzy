package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import elements.FieldSide;
import teleutil.button.Button;
import teleutil.button.OnNotHeldEventHandler;
import teleutil.button.OnPressEventHandler;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;

import static global.General.bot;
import static global.General.gph1;
import static global.General.*;


public class TerraOp extends Tele{

    // TODO FIX
    // Make Rest of Teleop

    @Override
    public void initTele() {
        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, () -> bot.intake.move(1));
        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, () -> bot.intake.move(0));
        gph1.link(Button.LEFT_BUMPER, OnPressEventHandler.class, () -> bot.intake.move(-1));
        gph1.link(Button.LEFT_BUMPER, OnNotHeldEventHandler.class, () -> bot.intake.move(0));
        gph1.link(Button.X, OnPressEventHandler.class, bot::cancelAutoModules);

        gph1.link(Button.RIGHT_TRIGGER, automodules.OneDuck);
        gph1.link(Button.DPAD_DOWN, OnPressEventHandler.class, bot::pauseAutoModules);
        gph1.link(Button.DPAD_UP, OnPressEventHandler.class, bot::resumeAutoModules);


        gph2.link(Button.RIGHT_TRIGGER, OnPressEventHandler.class, () -> bot.outtake.lock());
        gph2.link(Button.LEFT_TRIGGER, OnPressEventHandler.class, () -> bot.outtake.drop());
        gph2.link(Button.RIGHT_BUMPER, OnPressEventHandler.class, () -> bot.outtake.turnToHorizontal());
        gph2.link(Button.LEFT_BUMPER, OnPressEventHandler.class, () -> bot.outtake.turnToStart());
        gph2.link(Button.DPAD_RIGHT, OnPressEventHandler.class, () -> bot.outtake.sharedTurretRight());
        gph2.link(Button.DPAD_DOWN, OnPressEventHandler.class, () -> bot.outtake.centerTurret());
        gph2.link(Button.DPAD_LEFT, OnPressEventHandler.class, () -> bot.outtake.sharedTurretLeft());

        // TODO FIX
        // Add mode switching
        gph1.link(Button.A, automodules.IntakeUntilFreight);
        gph1.link(Button.B, automodules.LiftUpShared);
        gph1.link(Button.B, automodules.LiftUpAlliance);
        gph1.link(Button.Y, automodules.ResetLiftAndOuttake);
    }

    @Override
    public void loopTele() {

        // Gamepad1
        bot.drive.moveSmooth(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
        bot.carousel.move(gamepad1.left_trigger);

        bot.lift.move(-gamepad2.right_stick_y);

        // Gamepad2

//        bot.lift.move(-gamepad2.right_stick_y);
    }

    /**
     * Define the two teleops for each side
     */
    @TeleOp(name = "TerraOpBlue", group = "TeleOp")
    public static class TerraOpBlue extends TerraOp{{ fieldSide = FieldSide.BLUE; }}
    @TeleOp(name = "TerraOpRed", group = "TeleOp")
    public static class TerraOpRed extends TerraOpBlue{{ fieldSide = FieldSide.RED; }}
}


