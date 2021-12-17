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
//    @Override
//    public void move(double pos){
//        lo.setPosition(pos);
//    }
    @Override
    public void move(String s){
        lo.setPosition(s);
    }

    @Override
    public void moveTele(double pos) { super.moveTele(pos); }

    @Override
    public void moveTele(String pos) { super.moveTele(pos); }

    public void lockCube(){ move("cubeLock"); }
    public void lockBall(){ move("ballLock"); }
    public void align(){ move("aligned"); }
    public void start(){ move("start"); }
    public void open(){ move("open"); }

    public void lockCubeTele(){ moveTele("cubeLock"); }
    public void lockBallTele(){ moveTele("ballLock"); }
    public void alignTele(){ moveTele("aligned"); }
    public void startTele(){ moveTele("start"); }
    public void openTele(){ moveTele("open"); }
}
