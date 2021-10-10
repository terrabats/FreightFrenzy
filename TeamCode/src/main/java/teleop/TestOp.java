package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.General;
import robot.TerraBot;
import util.Fault;
import util.Fault.Expectation;
import util.Fault.Magnitude;

@TeleOp(name = "TestOp")
public class TestOp extends OpMode implements General {
// TODO: Figure out a structure for this that makes it easy to code, maybe one teleop somehow
    @Override
    public void init() {
        bot.init(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        fault.check("robotPartsNotEmpty", Expectation.SURPRISING, Magnitude.MAJOR, TerraBot.allRobotParts.size() == 1);
    }
}
