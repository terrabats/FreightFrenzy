package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import elements.FieldSide;
import teleutil.button.Button;
import teleutil.button.OnNotHeldEventHandler;
import teleutil.button.OnPressEventHandler;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;

import static global.General.autoModules;
import static global.General.bot;
import static global.General.gph1;
import static global.General.gph2;

/**
 * NOTE: Uncommented
 */

@Deprecated
public class TerraOp extends Tele{
    /**
     * Main teleop file
     */

    // TODO FIX
    // Make this for the new robot

    /**
     * The field side (Used to create teleops for different sides)
     */
    protected FieldSide fieldSide;

    @Override
    public void initTele() {
        /**
         * Set the button handlers for the intake
         */
        //Gamepad 1
        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, () -> bot.intake.move(1));
        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, () -> bot.intake.move(0));
        gph1.link(Button.LEFT_BUMPER, OnPressEventHandler.class, () -> bot.intake.move(-1));
        gph1.link(Button.LEFT_BUMPER, OnNotHeldEventHandler.class, () -> bot.intake.move(0));

        /**
         * Set the button handlers for the outtake
         */
        //Gamepad 2
        gph2.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, () -> bot.outtake.open());
        gph2.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, () -> bot.outtake.start());

        gph1.link(Button.A, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.IntakeTele));
        gph1.link(Button.B, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.BackwardTele()));
        gph1.link(Button.Y, OnPressEventHandler.class, () -> bot.addAutoModule(autoModules.ForwardTele()));
        gph1.link(Button.X, OnPressEventHandler.class, bot::cancelAutoModules);

        gph1.link(Button.DPAD_DOWN, OnPressEventHandler.class, bot::pauseAutoModules);
        gph1.link(Button.DPAD_UP, OnPressEventHandler.class, bot::resumeAutoModules);

        bot.outtake.start();

        bot.lift.move(0);

        activate(fieldSide);
    }

    @Override
    public void loopTele() {

        // Gamepad1
        bot.tankDrive.moveSmooth(-gamepad1.right_stick_y, gamepad1.left_stick_x);

        bot.carousel.move(gamepad1.right_trigger);

        // Gamepad2
        bot.turret.move(gamepad2.left_stick_x);

        bot.lift.move(-gamepad2.right_stick_y);

    }












    /**
     * Define the two teleops for each side
     */
    @TeleOp(name = "TerraOpBlue", group = "TeleOp")
    public static class TerraOpBlue extends TerraOp{{ fieldSide = FieldSide.BLUE; }}
    @TeleOp(name = "TerraOpRed", group = "TeleOp")
    public static class TerraOpRed extends TerraOpBlue{{ fieldSide = FieldSide.RED; }}
}


