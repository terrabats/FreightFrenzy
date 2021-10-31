package unittests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import global.Common;
import robot.TerraBot;
import util.Stage;

import static global.General.*;


@Disabled
@TeleOp(name = "RobotFunctionsTest", group = "UnitTests")
public class RobotFunctionsTest extends OpMode implements Common {
    @Override
    public void init() {
        reference(this);
        bot.rfsHandler.addToQueue(new Stage() {
            @Override
            public boolean run(double in) {
                bot.mechDrive.move(0.3, 0, 0);
                return in > 1.5;
            }
            @Override
            public void runOnStop() {
                bot.mechDrive.move(0, 0, 0);
            }
        });
        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    @Override
    public void start() {
        bot.start();
    }

    @Override
    public void loop() {
    }

    @Override
    public void stop() {
        bot.stop();
    }
}
