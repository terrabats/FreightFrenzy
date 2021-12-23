package auton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import elements.FieldSide;

@Autonomous(name="AutoBlue")
public class AutoBlue extends Auto{

    public double currentVoltage = 13.35;
    public final double voltageCoeffTurn = 1.2;

    @Override
    public void runOpMode() throws InterruptedException {

        reference(this);

        activate(FieldSide.BLUE);

        waitForStart();

        ready();

        bot.tankDrive.move(0.4, 0);
        sleep(false, 480);
//        bot.tankDrive.move(0, -0.4);
//        sleep(true, 920);
        turnDeg(0.5, 90);
        bot.tankDrive.move(0.4, 0);
        sleep(false, 1500);
        bot.carousel.move(1); //
        bot.tankDrive.move(0,0);
        bot.tankDrive.move(0,0.2); //
        sleep(true, 5000); //
        bot.carousel.move(0); //
        bot.tankDrive.move(-0.4, 0);
        sleep(false, 480);
//        bot.tankDrive.move(0, 0.4);
//        sleep(true, 850);
        turnDeg(0.5, -80);
        bot.tankDrive.move(0.4, 0);
        sleep(false, 1100);
//        bot.tankDrive.move(0, 0.4);
//        sleep(true, 200);
        turnDeg(0.5, -10);
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
        double stDeg = bot.gyroSensor.getRightHeadingDeg();
        while ((deg > 0 ? (bot.gyroSensor.getRightHeadingDeg() < stDeg + deg) : (bot.gyroSensor.getRightHeadingDeg() > stDeg + deg)) && opModeIsActive()) {
            bot.tankDrive.move(0, pow);
            sleep(10);
        }
        turnBack(pow, deg + stDeg);
        bot.tankDrive.move(0,0);
    }

    private void turnBack(double pow, double finalPos) {
        pow *= -0.5;
        while ((pow > 0 ? (bot.gyroSensor.getRightHeadingDeg() > finalPos) : (bot.gyroSensor.getRightHeadingDeg() < finalPos)) && opModeIsActive()) {
            bot.tankDrive.move(0, pow);
            sleep(10);
        }
    }

}
