package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Objects;

import elements.FieldSide;
import robotparts.RobotPart;
import robotparts.custom.PServo;
import util.codeseg.CodeSeg;

import static global.General.*;

public class TankDrive extends RobotPart {
    private DcMotor fr,br,fl,bl;
    private PServo re;

    @Override
    public void init(){
        fr = createMotor("fr", DcMotorSimple.Direction.REVERSE);
        br = createMotor("br", DcMotorSimple.Direction.REVERSE);
        fl = createMotor("fl", DcMotorSimple.Direction.FORWARD);
        bl = createMotor("bl", DcMotorSimple.Direction.FORWARD);
        re = createPServo("re", Servo.Direction.FORWARD, 0, 1);
    }

    @Override
    public void move(double f, double t){
        fr.setPower(f-t);
        br.setPower(f-t);
        fl.setPower(f+t);
        bl.setPower(f+t);
    }

    @Override
    public void moveTele(double f, double t) {super.moveTele(f, Math.abs(t) < 0.2 ? 0 : t);}

    private double stAng = 0;
    private double endAng = 0;
    private double moveForTime = 0;
    private final double turnError = 1;

    public void setAutonNewSegment(double changeAng, double forTime) {
        stAng = bot.gyro.getHeading();
        endAng = stAng - changeAng;
        moveForTime = forTime;
    }

    private double getAutonTurnError() {
        return bot.gyro.getHeading() - endAng;
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

    public void retract(double pos){
        re.setPosition(pos);
    }
}
