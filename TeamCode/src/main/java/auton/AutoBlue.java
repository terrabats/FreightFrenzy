package auton;

import util.ExceptionCatcher;
import util.Timer;
import static global.General.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoBlue")
public class AutoBlue extends Auto{

    public double currentVoltage = 13.35;
    public final double voltageCoeffTurn = 1.2;

    @Override
    public void runOpMode() throws InterruptedException {

        reference(this);
        activate();

        waitForStart();

        ready();

        bot.tankDrive.move(0.4, 0);
        sleep(false, 450);
        bot.tankDrive.move(0, 0.4);
        sleep(true, 920);
        bot.tankDrive.move(0.4, 0);
        sleep(false, 1500);
        bot.tankDrive.move(0,0.2);
        bot.carousel.move(1);
        sleep(true, 5000);
        bot.carousel.move(0);
        bot.tankDrive.move(-0.4, 0);
        sleep(false, 480);
        bot.tankDrive.move(0, -0.4);
        sleep(true, 850);
        bot.tankDrive.move(0.4, 0);
        sleep(false, 1100);
        bot.tankDrive.move(0, -0.4);
        sleep(true, 200);
        bot.tankDrive.move(0,0);

        end();
    }

    public void sleep(boolean isTurn, long millis) {
        sleep(isTurn ? (long) (millis + (13.5 - currentVoltage) * voltageCoeffTurn) : millis);
    }

}
