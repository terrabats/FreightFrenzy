package auton;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import elements.FieldSide;
import util.Timer;
import util.codeseg.CodeSeg;

@Autonomous(name="AutoRed2")
public class AutoRed2 extends Auto{

    public double currentVoltage = 13.35;
    public final double voltageCoeffTurn = 1.2;

    @Override
    public void runOpMode() throws InterruptedException {

        reference(this);
        activate(FieldSide.RED);

        waitForStart();

        ready();

        // forward speed, turn speed, move time, change in angle
        double[][] autonPath = new double[][] {
            new double[] { 0.4, 0, 480, 0 },
            new double[] { 0, 0.3, 0, -95 },
//            new double[] { 0.4, 0, 1500, 0 },
//            new double[] { 0, -0.2, 5000, 0 },
//            new double[] { -0.4, 0, 480, 0 },
//            new double[] { 0, 0.5, 0, 80 },
//            new double[] { 0.4, 0, 1100, 0 },
//            new double[] { 0, 0.5, 0, 10 },
            new double[] { 0, 0, 0, 0 }
        };

        CodeSeg[][] toRunOnSt = new CodeSeg[][] {
            null,
            null,
//            null,
//            new CodeSeg[] { () -> bot.carousel.move(1) },
//            new CodeSeg[] { () -> bot.carousel.move(0) },
//            null,
//            null,
//            null,
            null
        };

        Timer timer = new Timer();

        for (int i = 0; i < autonPath.length; i++) {
            timer.reset();
            if (toRunOnSt[i] != null) {
                for (CodeSeg cs : toRunOnSt[i]) cs.run();
            }
            double[] currentPart = autonPath[i];
            bot.tankDrive.setAutonNewSegment(currentPart[3], currentPart[2]);
            while (true) {
                if (bot.tankDrive.moveAutonDone(currentPart[0], currentPart[1], timer.seconds())) {
                    break;
                }
            }
        }

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
