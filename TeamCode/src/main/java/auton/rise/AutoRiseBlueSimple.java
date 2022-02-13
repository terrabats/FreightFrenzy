package auton.rise;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import auton.Auto;
import util.Timer;

import static global.General.bot;

@Autonomous(name = "AutoRiseBlueSimple")
public class AutoRiseBlueSimple extends Auto {


    Timer timer = new Timer();

    @Override
    public void initAuto() {

    }

    @Override
    public void runAuto() {
        moveTime(1.5,1,0.0,-0.3);
        moveTime(1,0,1 , 0);
    }


    public void moveTime(double time, double f, double s, double t){
        timer.reset();
        while (opModeIsActive() && timer.seconds() < time) {
            bot.mecanumDrive.moveSmooth(f, s, t);
        }
    }
}
