package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import robot.RobotFramework;
import robot.TerraBot;
import static robot.General.*;
import static robot.RobotFramework.*;

import java.util.Map;

// MOVEMENT IS WORKING!

@Disabled
@TeleOp(name = "DriveTest")
public class DriveTest extends OpMode {
    @Override
    public void init() {
        bot = new TerraBot();
        bot.init(hardwareMap, telemetry);
        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    @Override
    public void loop() {
        bot.mechDrive.move(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
        //bot.tankDrive.move(-gamepad1.right_stick_y, gamepad1.right_stick_x);
        for (Map.Entry<String, DcMotor> p : bot.mechDrive.motors.entrySet()) {
            String name = p.getKey();
            DcMotor motor = p.getValue();
            motor.setPower(1);
            telemetry.addData(name);
            telemetry.update();
        }

    }

    @Override
    public void stop() {
        bot.stop();
    }
}
