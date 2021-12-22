package teleop;

import static global.General.autoModules;
import static global.General.bot;
import static global.General.fieldSide;
import static global.General.stages;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import automodules.StageList;
import elements.FieldSide;
import global.Constants;
import util.Timer;

@TeleOp(name = "TerraOpRed", group = "TeleOp")
public class TerraOpRed extends Tele{
    Timer aTimer = new Timer();
    Timer bTimer = new Timer();
    Timer xTimer = new Timer();
    Timer yTimer = new Timer();

    @Override
    public void init() {

        super.reference(this);

        //Gamepad 1
//        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, args -> bot.intake.moveTele(1));
//        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, args -> bot.intake.moveTele(0));
//        gph1.link(Button.LEFT_BUMPER, ButtonEventHandler.class, args -> bot.intake.moveTele(-1));
//        gph1.link(Button.LEFT_BUMPER, OnNotHeldEventHandler.class, args -> bot.intake.moveTele(0));

        //Gamepad 2
//        gph2.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, args -> bot.outtake.moveTele(0.3));
//        gph2.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, args -> bot.outtake.moveTele(0));

        super.activate(FieldSide.RED);
    }

    @Override
    public void start() {
        bot.addAutoModule(new StageList(stages.liftEncoder(0.4,10)));

        super.start();
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
            bot.intake.move(1);
        }else if(gamepad1.left_bumper){
            bot.intake.move(-1);
        }else{
            bot.intake.move(0);
        }

//        if(gamepad2.right_bumper){
//            bot.outtake.lockCubeTele();
//        }else if(gamepad2.left_bumper){
//            bot.outtake.startTele();
//        }

        // Gamepad1
        bot.tankDrive.moveSmooth(-gamepad1.right_stick_y, gamepad1.left_stick_x);

        bot.carousel.move(gamepad1.right_trigger);

        // Gamepad2
        bot.turret.move(gamepad2.left_stick_x);
        bot.lift.move(-gamepad2.right_stick_y+Constants.LIFT_REST_POW);

        super.loop();
    }
}
