package unittests.framework;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import robot.TerraBot;
import unittests.UnitTest;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;


public class CommonTest extends FrameworkTest {
    @Override
    public void start() {
        fault.check("robotPartsIsEmpty", Expectation.SURPRISING, Magnitude.MAJOR, TerraBot.allRobotParts.size() != 0);
    }

    @Override
    public void loop() {
        log.display("Common is Working");
    }
}
