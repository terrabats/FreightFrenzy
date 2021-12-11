package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

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

@TeleOp(name = "TerraOp", group = "TeleOp")
public class TerraOpBlue extends Tele{
    Timer aTimer = new Timer();
    Timer bTimer = new Timer();
    Timer xTimer = new Timer();
    Timer yTimer = new Timer();

    @Override
    public void init() {
        fieldSide = FieldSide.BLUE;

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
        aTimer.reset();
        bTimer.reset();
        yTimer.reset();
        xTimer.reset();
    }

    @Override
    public void loop() {
        if(gamepad1.a && aTimer.seconds() > 0.3){
            bot.addAutoModule(autoModules.Intake);
            aTimer.reset();
        }

        if(gamepad1.b && bTimer.seconds() > 0.3){
            bot.addAutoModule(autoModules.Backward);
            bTimer.reset();
        }
//
        if(gamepad1.y && yTimer.seconds() > 0.3){
            bot.addAutoModule(autoModules.Forward);
            yTimer.reset();
        }
//        log.display("size", bot.rfsHandler.rfsQueue.size());

        if(gamepad1.x && xTimer.seconds() > 0.3){
            bot.rfsHandler.emptyQueue();
            xTimer.reset();
        }

        if(gamepad1.right_bumper){
            bot.intake.moveTele(1);
        }else if(gamepad1.left_bumper){
            bot.intake.moveTele(-1);
        }else{
            bot.intake.moveTele(0);
        }

        if(gamepad2.right_bumper){
            bot.outtake.lockCubeTele();
        }else if(gamepad2.left_bumper){
            bot.outtake.startTele();
        }

        // Gamepad1
        if(!bot.slowMode) {
            bot.tankDrive.moveTele(-gamepad1.right_stick_y, gamepad1.left_stick_x);
        }else {
            bot.tankDrive.moveTele(-gamepad1.right_stick_y*0.4, gamepad1.left_stick_x*0.4);
        }
        bot.carousel.moveTele(gamepad1.right_trigger);

        // Gamepad2
        bot.turret.moveTele(gamepad2.left_stick_x);
        bot.lift.moveTele(-gamepad2.right_stick_y+Constants.LIFT_REST_POW);

        update(true);
    }

    @Override
    public void stop() {
        end();
    }
}