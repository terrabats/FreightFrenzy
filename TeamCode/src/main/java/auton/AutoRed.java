package auton;

import static global.General.bot;
import static global.General.fieldSide;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import elements.FieldSide;

@Autonomous(name="AutoRed")
public class AutoRed extends Auto{

    public double currentVoltage = 13.35;
    public final double voltageCoeffTurn = 1.2;

    @Override
    public void runOpMode() throws InterruptedException {

        fieldSide = FieldSide.RED;

        reference(this);
        activate();

        waitForStart();

        ready();

        bot.tankDrive.move(0.4, 0);
        sleep(false, 500);
//        bot.tankDrive.move(0, -0.4);
//        sleep(true, 920);
        turnDeg(0.5, -95);
        bot.tankDrive.move(0.4, 0);
        sleep(false, 1500);
        bot.carousel.move(1); //
        bot.tankDrive.move(0,0);
        bot.tankDrive.move(0,-0.2); //
        sleep(true, 5000); //
        bot.carousel.move(0); //
        bot.tankDrive.move(-0.4, 0);
        sleep(false, 480);
//        bot.tankDrive.move(0, 0.4);
//        sleep(true, 850);
        turnDeg(0.5, 80);
        bot.tankDrive.move(0.4, 0);
        sleep(false, 1100);
//        bot.tankDrive.move(0, 0.4);
//        sleep(true, 200);
        turnDeg(0.5, 10);
        bot.tankDrive.move(0,0);

//        turnDeg(0.4, 45);

        end();
    }

    public void sleep(boolean isTurn, long millis) {
        sleep(isTurn ? (long) (millis + (13.5 - currentVoltage) * voltageCoeffTurn) : millis);
    }

    private void turnDeg(double pow, double deg) {
        pow = Math.abs(pow) * Math.signum(deg);
        deg *= -1;
        double stDeg = bot.gyro.getHeading();
        while ((deg > 0 ? (bot.gyro.getHeading() < stDeg + deg) : (bot.gyro.getHeading() > stDeg + deg)) && opModeIsActive()) {
            bot.tankDrive.move(0, pow);
            sleep(10);
        }
        turnBack(pow, deg + stDeg);
        bot.tankDrive.move(0,0);
    }

    private void turnBack(double pow, double finalPos) {
        pow *= -0.5;
        while ((pow > 0 ? (bot.gyro.getHeading() > finalPos) : (bot.gyro.getHeading() < finalPos)) && opModeIsActive()) {
            bot.tankDrive.move(0, pow);
            sleep(10);
        }
    }

}
