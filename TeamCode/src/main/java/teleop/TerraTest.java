package teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import elements.FieldSide;
import static global.General.bot;

@TeleOp(name = "TerraTest", group = "TeleOp")
public class TerraTest extends Tele{
    @Override
    public void loopTele() {
//        bot.mecanumDrive.moveSmooth(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
        bot.intake.move(-gamepad1.right_stick_y);
    }

}


