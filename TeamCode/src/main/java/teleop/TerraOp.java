package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

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
import static global.General.gamepad1;
import static global.General.gamepad2;
import static global.General.gph1;
import static global.General.gph2;

@TeleOp(name = "TerraOp", group = "TeleOp")
public class TerraOp extends Tele{
    Timer buttonTimer = new Timer();

    @Override
    public void init() {
        reference(this);

        //Gamepad 1
//        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, args -> bot.intake.moveTele(1));
//        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, args -> bot.intake.moveTele(0));
//        gph1.link(Button.LEFT_BUMPER, ButtonEventHandler.class, args -> bot.intake.moveTele(-1));
//        gph1.link(Button.LEFT_BUMPER, OnNotHeldEventHandler.class, args -> bot.intake.moveTele(0));

        //Gamepad 2
//        gph2.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, args -> bot.outtake.moveTele(0.3));
//        gph2.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, args -> bot.outtake.moveTele(0));


        //bot.addAutoModule(autoModules.ResetLift);

        activate();
    }

    @Override
    public void start() {
        ready();
        buttonTimer.reset();
    }

    @Override
    public void loop() {
        if(gamepad1.a && buttonTimer.seconds() > 0.3){
            bot.addAutoModule(autoModules.Intake);
            buttonTimer.reset();
        }

        if(gamepad1.right_bumper){
            bot.intake.moveTele(1);
        }else if(gamepad1.left_bumper){
            bot.intake.moveTele(-1);
        }else{
            bot.intake.moveTele(0);
        }

        if(gamepad2.right_bumper){
            bot.outtake.lockCube();
        }else if(gamepad2.left_bumper){
            bot.outtake.start();
        }

        // Gamepad1
        bot.tankDrive.moveTele(-gamepad1.right_stick_y, gamepad1.left_stick_x);
        bot.carousel.moveTele(gamepad1.right_trigger);

        // Gamepad2
        bot.turret.moveTele(gamepad2.left_stick_x);
        bot.lift.moveTele(-gamepad2.right_stick_y);

        update(true);
    }

    @Override
    public void stop() {
        end();
    }
}
