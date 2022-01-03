package teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static global.General.bot;

@TeleOp(name = "TerraTest", group = "TeleOp")
public class TerraTest extends Tele{
    @Override
    public void loop() {
        bot.mecanumDrive.move(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
        super.loop();
    }
}
