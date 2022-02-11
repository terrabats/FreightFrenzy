package unittests.auto.framework;

import java.util.Arrays;

import unittests.auto.AutoUnitTest;

import static global.General.bot;
import static global.General.gamepad1;
import static global.General.log;
import static global.General.telemetry;

public class OdometryTest extends AutoUnitTest {

    @Override
    protected void run() {
        whileActive(() -> {
            log.show("Horizontal Enc Pos",bot.odometry2.ticksToCm(bot.odometry2.getHorizontalEncoderPosition()));
            log.show("Vertical Enc Pos",bot.odometry2.ticksToCm(bot.odometry2.getVerticalEncoderPosition()));
            log.show("Pos", Arrays.toString(bot.odometry2.getPose()));
            bot.mecanumDrive.move(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
        });
    }
}
