package unittests;

import robot.TerraBot;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class MecDriveTest extends UnitTest {
    @Override
    public void start() {
        fault.check("robotPartsIsEmpty", Expectation.SURPRISING, Magnitude.MAJOR, TerraBot.allRobotParts.size() != 0);
    }

    @Override
    public void loop() {
        bot.mechDrive.move(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
    }

    @Override
    public void stop() {
        bot.mechDrive.move(0, 0, 0);
    }
}
