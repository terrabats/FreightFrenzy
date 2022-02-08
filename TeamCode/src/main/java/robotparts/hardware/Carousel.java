package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import math.polynomial.Linear;
import robotparts.RobotPart;
import robotparts.electronics.continuous.CMotor;
import util.Timer;

public class Carousel extends RobotPart {
    /**
     * Carousel left and right (cl and cr respectively)
     */
    public CMotor cr;


    private final Timer timerA = new Timer();

    /**
     * Init method creates the cservos (or continuous servos)
     */
    @Override
    public void init() { cr = createCMotor("cr", DcMotor.Direction.REVERSE); }

    /**
     * Move the carousels at the given power
     * NOTE: Positive should be so that the right carousel spins clockwise
     * @param p
     */
    public void move(double p){
        cr.setPower(p);
    }

    private Main mainSpin() {
        return new Main(() -> move(1));
    }

    private Stop stop() {
        return new Stop(() -> move(0));
    }

    public Stage spin(double time) {
        return new Stage(
                usePart(),
                mainSpin(),
                exitTime(time),
                stop()
        );
    }



    public Stage spinOneDuck(double time, double minPow, double maxPow) {
        return new Stage(
                usePart(),
                new Initial(timerA::reset),
                new Main(() ->{
                    double secs = timerA.seconds();
                    double halfTime = time/2;
                    double slope = (maxPow-minPow)/halfTime;
                    Linear l1 = new Linear(slope,minPow);
                    Linear l2 = new Linear(-slope,l1.f(halfTime));
                    if(secs < halfTime) {
                        move(l1.f(secs));
                    }else{
                        move(l2.f(secs-halfTime));
                    }
                }),
                exitTime(time),
                stop(),
                returnPart()
        );
    }



}