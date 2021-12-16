package teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import elements.FieldSide;
import global.Common;
import robot.TerraBot;
import teleutil.button.Button;
import teleutil.button.OnTurnOffEventHandler;
import teleutil.button.OnTurnOnEventHandler;
import util.codeseg.ParameterCodeSeg;
import util.condition.Expectation;
import util.condition.Magnitude;
import static global.General.*;

@Disabled
@TeleOp(name = "TestOp", group = "TeleOp")
public class TestOp extends Tele {
    @Override
    public void init() {
        super.init();
        gph1.link(Button.RIGHT_BUMPER, OnTurnOnEventHandler.class, args -> {bot.intake.move(1);});
        gph1.link(Button.RIGHT_BUMPER, OnTurnOffEventHandler.class, args -> {bot.intake.move(0);});
        super.activate(FieldSide.UNKNOWN);
    }

    @Override
    public void loop() {
        bot.tankDrive.move(-gamepad1.right_stick_y, gamepad1.left_stick_x);
        super.loop();
    }
}
