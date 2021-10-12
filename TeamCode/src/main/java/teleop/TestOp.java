package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.General;
import robot.RobotFramework;
import robot.TerraBot;
import robotparts.Status;
import util.CodeSeg;
import util.Fault;
import util.Fault.Expectation;
import util.Fault.Magnitude;

@TeleOp(name = "TestOp")
public class TestOp extends OpMode implements General {
// TODO: Figure out a structure for this that makes it easy to code, maybe one teleop somehow
    @Override
    public void init() {
        bot.init(hardwareMap, telemetry);
        TerraBot.robotFunctionsThread.setCode(() -> {
            telemetry.addData("Code", " is running");
        });
    }

    @Override
    public void loop() {
        if(gamepad1.y){
            TerraBot.robotFunctionsThread.setStatus(Status.IDLE);
        }else if(gamepad1.x){
            TerraBot.robotFunctionsThread.setStatus(Status.ACTIVE);
        }else if(gamepad1.b){
            TerraBot.robotFunctionsThread.setStatus(Status.DISABLED);
        }
        telemetry.addData("Status: ", TerraBot.robotFunctionsThread.getStatus().toString());
        telemetry.update();
        //fault.check("robotPartsNotEmpty", Expectation.SURPRISING, Magnitude.MAJOR, TerraBot.allRobotParts.size() == 1);
    }
}
