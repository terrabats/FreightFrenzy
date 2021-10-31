package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import robot.TerraBot;
import static global.General.*;


@Disabled
@TeleOp(name = "ThreadTest", group = "UnitTests")
public class ThreadTest extends OpMode implements Common {
    @Override
    public void init() {
        reference(this);
        TerraBot.robotFunctionsThread.setCode((double... args) -> {
            telemetry.addData("Code", " is running");
            telemetry.addData("Status: ", TerraBot.robotFunctionsThread.getStatus().toString());
        });
        TerraBot.odometryThread.setCode((double... args) -> {
            telemetry.addData("Code", " is running");
            telemetry.addData("Status: ", TerraBot.odometryThread.getStatus().toString());
            telemetry.update();
        });
//        telemetry.addData("Status", "Ready");
//        telemetry.update();
    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
        bot.stop();
    }
}
