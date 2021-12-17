package robotparts.hardware;

import com.qualcomm.robotcore.hardware.Servo;

import robotparts.RobotPart;
import robotparts.electronics.PServo;

public class Outtake extends RobotPart {
    private PServo lo;

    @Override
    public void init(){
        lo = createPServo("lo", Servo.Direction.FORWARD, 0.25, 0.5);
        lo.addPosition("cubeLock", 0.0);
        lo.addPosition("ballLock", 0.27);
        lo.addPosition("aligned", 0.35);
        lo.addPosition("start", 0.41);
        lo.addPosition("open", 1.0);
        lo.setPosition("start");
    }

    public void move(String s){
        lo.setPosition(s);
    }

    public void lockCube(){ move("cubeLock"); }
    public void lockBall(){ move("ballLock"); }
    public void align(){ move("aligned"); }
    public void start(){ move("start"); }
    public void open(){ move("open"); }

    public void lockCubeTele(){ move("cubeLock"); }
    public void lockBallTele(){ move("ballLock"); }
    public void alignTele(){ move("aligned"); }
    public void startTele(){ move("start"); }
    public void openTele(){ move("open"); }
}
