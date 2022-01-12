package teleop;
import elements.FieldSide;
import static global.General.bot;

public class TerraTest extends Tele{
    @Override
    public void initTele() {
        activate(FieldSide.BLUE);
    }
    @Override
    public void loopTele() {
        bot.mecanumDrive.move(gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
    }
}


