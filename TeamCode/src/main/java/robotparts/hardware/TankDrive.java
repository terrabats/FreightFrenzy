package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import elements.FieldSide;
import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.PServo;

import static global.General.*;

public class TankDrive extends RobotPart {
    private CMotor fr,br,fl,bl;
    private PServo re;

    @Override
    public void init(){
        fr = createCMotor("fr", DcMotorSimple.Direction.REVERSE);
        br = createCMotor("br", DcMotorSimple.Direction.REVERSE);
        fl = createCMotor("fl", DcMotorSimple.Direction.FORWARD);
        bl = createCMotor("bl", DcMotorSimple.Direction.FORWARD);
        re = createPServo("re", Servo.Direction.FORWARD, 0, 1);
    }

    public void move(double f, double t){
        fr.setPower(f-t);
        br.setPower(f-t);
        fl.setPower(f+t);
        bl.setPower(f+t);
    }

    private double stAng = 0;
    private double endAng = 0;
    private double moveForTime = 0;
    private final double turnError = 1;

    public void setAutonNewSegment(double changeAng, double forTime) {
        stAng = bot.gyroSensor.getRightHeading()
        endAng = stAng - changeAng;
        moveForTime = forTime;
    }

    private double getAutonTurnError() {
        return bot.gyroSensor.getRightHeading() - endAng;
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
}
