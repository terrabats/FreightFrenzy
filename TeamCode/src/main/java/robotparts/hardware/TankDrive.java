package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import automodules.stage.Main;
import automodules.stage.Stop;
import elements.FieldSide;
import math.algebra.Logistic;
import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.PServo;

import static global.General.*;

public class TankDrive extends RobotPart {
    /**
     * Contunous motors for drivetrain
     */
    private CMotor fr,br,fl,bl;
    /**
     * Positional Servo for retracting odometer
     */
    private PServo re;

    /**
     * Init method to create all of the motors and the servo
     */
    @Override
    public void init(){
        fr = createCMotor("fr", DcMotorSimple.Direction.REVERSE);
        br = createCMotor("br", DcMotorSimple.Direction.REVERSE);
        fl = createCMotor("fl", DcMotorSimple.Direction.FORWARD);
        bl = createCMotor("bl", DcMotorSimple.Direction.FORWARD);
        re = createPServo("re", Servo.Direction.FORWARD, 0, 1);
    }

    /**
     * Move the robot forward at power f and turn at power t
     * @param f
     * @param t
     */
    public void move(double f, double t){
        fr.setPower(f-t);
        br.setPower(f-t);
        fl.setPower(f+t);
        bl.setPower(f+t);
    }

    /**
     * Move the robot using logistic curves (to make it easier to use in teleop)
     * @param f
     * @param t
     */
    public void moveSmooth(double f, double t){
        Logistic movementCurveForward = new Logistic(10,5);
        Logistic movementCurveTurn = new Logistic(30,6);
        move(movementCurveForward.fodd(f), movementCurveTurn.fodd(t));
    }

    private double stAng = 0;
    private double endAng = 0;
    private double moveForTime = 0;
    private final double turnError = 1;

    public void setAutonNewSegment(double changeAng, double forTime) {
        stAng = bot.gyroSensors.getRightHeadingDeg();
        endAng = stAng - changeAng;
        moveForTime = forTime;
    }

    private double getAutonTurnError() {
        return bot.gyroSensors.getRightHeadingDeg() - endAng;
    }

    public boolean moveAutonDone(double f, double t, double curTime) {
        if (fieldSide == FieldSide.BLUE) {
            t *= -1;
        }
        if (Math.abs(f) > 0) {
            // move forward/backward
            t = getAutonTurnError() * 0.01;
            move(f, t);
            return curTime * 1000 >= moveForTime;
        } else {
            // turn
            double err = getAutonTurnError();
            move(0, t * Math.min(1, err/15));
            return moveForTime == 0 ? (Math.abs(err) < turnError) : (curTime >= moveForTime);
        }
    }

    public Main main(double forward, double turn){
        return new Main(() -> move(forward, turn));
    }

    public Stop stop(){
        return new Stop(() -> move(0,0));
    }
}
