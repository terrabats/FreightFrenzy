package teleop;

import static global.General.bot;
import static global.General.gph1;

public class TestOp extends Tele{

    @Override
    public void initTele() {

    }

    @Override
    public void loopTele() {
        bot.drive.move(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }

}
