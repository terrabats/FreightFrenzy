package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import robot.TerraBot;
import util.condition.Status;

import static global.General.*;


public class ThreadTest extends UnitTest {
    @Override
    public void loop() {
        TerraBot.robotFunctionsThread.setCode(() -> {
            log.display("Code is running");
            log.status("Robot Functions Status", TerraBot.robotFunctionsThread.getStatus());
        });
        TerraBot.odometryThread.setCode(() -> {
            log.display("Code is running");
            log.status("Odometry Thread Status", TerraBot.odometryThread.getStatus());
        });
    }
}
