package unittests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import teleutil.button.Button;
import teleutil.button.ButtonEventType;
import util.ExceptionCatcher;

import static global.General.*;

// TO BE TESTED

@TeleOp(name = "GamepadTest")
public class GamepadTest extends OpMode {
    @Override
    public void init() {
        bot.init(hardwareMap, telemetry, gamepad1, gamepad2);

        gph1.link(Button.A, ButtonEventType.NORMAL, args -> telemetry.addData("A Button", "Pressed"));
        gph1.link(Button.B, ButtonEventType.NORMAL, args -> telemetry.addData("B Button", "Pressed"));
        gph1.link(Button.A, ButtonEventType.CHANGE_HOLD, args -> {
            telemetry.addData("A Button", "Changed hold");
            if (args[0] == 1) {
                telemetry.addData("A Button", "Now Pressed");
            } else {
                telemetry.addData("A Button", "Now Not Pressed");
            }
        });

        telemetry.addData("Status", "Ready");
        telemetry.update();
    }

    @Override
    public void loop() {
        gph1.run();
        gph2.run();

        telemetry.update();

        // 0.1 seconds to allow user to read telemetry
        ExceptionCatcher.catchInterrupted(() -> Thread.sleep(100));
    }

    @Override
    public void stop() {
        bot.stop();
    }
}
