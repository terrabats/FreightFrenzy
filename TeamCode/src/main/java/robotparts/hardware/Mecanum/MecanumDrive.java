package robotparts.hardware.Mecanum;

import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;

import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import math.Logistic;
import robotparts.RobotPart;
import robotparts.electronics.continuous.CMotor;

/**
 * NOTE: Uncommented
 */

/**
 * Create Motors
 */
public class MecanumDrive extends RobotPart {
    private CMotor fr,br,fl,bl;

    @Override
    public void init(){
        fr = createCMotor("fr", Direction.REVERSE);
        br = createCMotor("br", Direction.REVERSE);
        fl = createCMotor("fl", Direction.FORWARD);
        bl = createCMotor("bl", Direction.FORWARD);
    }

    /**
     * Raw movement
     * @param f Forward Power
     * @param s Strafe Power
     * @param t Turn Power
     */
    public void move(double f, double s, double t){
        fr.setPower(f-s-t);
        br.setPower(f+s-t);
        fl.setPower(f+s+t);
        bl.setPower(f-s+t);
    }

    /**
     * Move the robot smoothly
     * @param f Forward Power
     * @param s Strafe Power
     * @param t Turn Power
     */
    public void moveSmooth(double f, double s, double t){
        Logistic movementCurveForward = new Logistic(10,5);
        Logistic movementCurveStrafe = new Logistic(30,6);
        Logistic movementCurveTurn = new Logistic(30,6);
        move(movementCurveForward.fodd(f), movementCurveStrafe.fodd(s), movementCurveTurn.fodd(t));
    }

    public Main mainMoveForward(double pow) { return new Main(() -> move(pow, 0, 0)); }

    /**
     * Stop the robot
     * @return
     */

    public Stop stopMove() { return new Stop(() -> move(0, 0, 0)); }
}
