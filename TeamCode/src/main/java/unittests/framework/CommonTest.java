package unittests.framework;

import robot.TerraBot;
import unittests.UnitTest;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;


public class CommonTest extends UnitTest{
    @Override
    public void start() {
        fault.check("robotPartsIsEmpty", Expectation.SURPRISING, Magnitude.MAJOR, TerraBot.allRobotParts.size() == 0, false);
    }

    @Override
    public void loop() {
        log.show("Common is Working");
        log.show("Current User", mainUser.toString());
    }
}
