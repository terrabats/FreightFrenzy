package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import robotparts.RobotPart;
import robotparts.custom.PServo;

public class Outtake extends RobotPart {
    private PServo lo;

    @Override
    public void init(){
        lo = createPServo("lo", Servo.Direction.FORWARD, 0.25, 0.5);
        lo.addPosition("cubeLock", 0.0);
        lo.addPosition("ballLock", 0.27);
        lo.addPosition("aligned", 0.35);
        lo.addPosition("start", 0.41);
        lo.addPosition("open", 0.5);
        lo.setPosition("start");
    }

    public void move(double pos){lo.setPosition(pos);}
    public void lockCube(){ lo.setPosition("cubeLock"); }
    public void lockBall(){ lo.setPosition("ballLock"); }
    public void align(){ lo.setPosition("aligned"); }
    public void start(){ lo.setPosition("start"); }
    public void open(){ lo.setPosition("open"); }
}
