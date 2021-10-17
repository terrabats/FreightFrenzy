package teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.RobotFramework;
import robot.TerraBot;
import util.Fault.Expectation;
import util.Fault.Magnitude;
import static robot.General.*;

@Disabled
@TeleOp(name = "TestOp")
public class TestOp extends OpMode {
// TODO: Figure out a structure for this that makes it easy to code, maybe one teleop somehow
    @Override
    public void init() {
        bot = new TerraBot();
        bot.init(hardwareMap, telemetry);
//        TerraBot.robotFunctionsThread.setCode(() -> {
//            telemetry.addData("Code", " is running");
//            telemetry.addData("Status: ", TerraBot.robotFunctionsThread.getStatus().toString());
//            telemetry.update();
//        });
        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    @Override
    public void start() {
        bot.start();
    }

    @Override
    public void loop() {
//        if(gamepad1.y){
//            TerraBot.robotFunctionsThread.setStatus(Status.IDLE);
//        }else if(gamepad1.x){
//            TerraBot.robotFunctionsThread.setStatus(Status.ACTIVE);
//        }else if(gamepad1.b){
//            TerraBot.robotFunctionsThread.setStatus(Status.DISABLED);
//        }else if(TerraBot.robotFunctionsThread.getStatus().equals(Status.IDLE)){
//            telemetry.addData("Status: ", TerraBot.robotFunctionsThread.getStatus().toString());
//            telemetry.update();
//        }
        fault.check("robotPartsNotEmpty", Expectation.SURPRISING, Magnitude.MAJOR, TerraBot.allRobotParts.size() == 0);
    }

    @Override
    public void stop() {
        bot.stop();
    }
}
