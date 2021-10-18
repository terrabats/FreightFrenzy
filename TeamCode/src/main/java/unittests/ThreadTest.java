package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import robot.TerraBot;
import static robot.General.*;

// THREADS ARE WORKING!

@Disabled
@TeleOp(name = "ThreadTest")
public class ThreadTest extends OpMode {
    @Override
    public void init() {
        bot = new TerraBot();
        bot.init(hardwareMap, telemetry);
        TerraBot.robotFunctionsThread.setCode(() -> {
            telemetry.addData("Code", " is running");
            telemetry.addData("Status: ", TerraBot.robotFunctionsThread.getStatus().toString());
        });
        TerraBot.odometryThread.setCode(() -> {
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