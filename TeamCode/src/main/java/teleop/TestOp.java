package teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import robot.TerraBot;
import teleutil.button.Button;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;
import util.codeseg.ParameterCodeSeg;
import util.condition.Expectation;
import util.condition.Magnitude;
import static global.General.*;

@TeleOp(name = "TestOp", group = "TeleOp")
public class TestOp extends Tele {
    @Override
    public void init() {
        reference(this);
        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, args -> {bot.intake.spin(1);});
        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, args -> {bot.intake.spin(0);});
        activate();
    }

    @Override
    public void start() {
        ready();
    }

    @Override
    public void loop() {
        bot.tankDrive.move(-gamepad1.right_stick_y, gamepad1.left_stick_x);
        update(true);
    }

    @Override
    public void stop() {
        end();
    }
}
