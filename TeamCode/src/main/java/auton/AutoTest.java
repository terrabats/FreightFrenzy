package auton;

import util.ExceptionCatcher;
import util.Timer;
import static global.General.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="AutoTest")
public class AutoTest extends Auto{
    @Override
    public void runOpMode() throws InterruptedException {

        reference(this);
        activate();

        waitForStart();

        ready();

        bot.tankDrive.move(0.4, 0);
        sleep(450);
        bot.tankDrive.move(0, 0.4);
        sleep(920);
        bot.tankDrive.move(0.4, 0);
        sleep(1500);
        bot.tankDrive.move(0,0.2);
        bot.carousel.move(1);
        sleep(5000);
        bot.carousel.move(0);
        bot.tankDrive.move(-0.4, 0);
        sleep(480);
        bot.tankDrive.move(0, -0.4);
        sleep(850);
        bot.tankDrive.move(0.4, 0);
        sleep(1200);
        bot.tankDrive.move(0, -0.4);
        sleep(500);
        bot.tankDrive.move(0,0);

        end();
    }

}
