package teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.TerraBot;
import util.condition.Expectation;
import util.condition.Magnitude;
import static global.General.*;

// TODO
// Get this ready to test the chassis

@Disabled
@TeleOp(name = "TestOp", group = "TeleOp")
public class TestOp extends OpMode {
    @Override
    public void init() {
//        bot = new TerraBot();
//        bot.init(hardwareMap, telemetry, gamepad1, gamepad2);
//        telemetry.addData("Status", "Ready");
//        telemetry.update();
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
