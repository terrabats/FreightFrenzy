package robotparts.sensors;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static robot.RobotFramework.odometryThread;

import robotparts.electronics.input.IEncoder;
import util.codeseg.ExceptionCodeSeg;

/**
 * NOTE: Uncommented
 */

public class TwoOdometry extends Odometry {

    private final double ODO2_TO_CENTER_Y;

    private double prevOdoTwoPos = 0;

    private final ExceptionCodeSeg<RuntimeException> odometryUpdateCode = this::update;

    private IEncoder rEnc;

    public TwoOdometry(double odo1_to_center_x, double odo2_to_center_y) {
        super(odo1_to_center_x);
        ODO2_TO_CENTER_Y = odo2_to_center_y;
    }

    @Override
    public void init() {
        cEnc = createEncoder("br", "cEnc", IEncoder.Type.NORMAL);
        rEnc = createEncoder("bl", "rEnc", IEncoder.Type.NORMAL);
        update();
        curPos = new double[] { 0, 0, 0 };
        odometryThread.setExecutionCode(odometryUpdateCode);

    }

    public double hEncPos(){
        return cEnc.getPos();
    }

    public double vEncPos(){
        return rEnc.getPos();
    }

    private double getDeltaOdoTwo() {
        double delta = vEncPos() - prevOdoTwoPos;
        prevOdoTwoPos += delta;
        return ticksToCm(delta);
    }

    public double ticksToCm(double ticks) {
        return ticks/8192 * 3.5 * Math.PI;
    }

    // TODO SKETCH These should be reversed
    public double getCurX() { return curPos[1]; }
    public double getCurY() { return curPos[0]; }
    public double getCurThetaRad() {
        return -curPos[2];
    }

    public void processTheta() {
        curPos[2] %= (2 * Math.PI);
        if (curPos[2] < 0) { curPos[2] += 2 * Math.PI; }
        if (curPos[2] > Math.PI) { curPos[2] -= 2 * Math.PI; }
    }

    public double getCurThetaDeg() {
        return getCurThetaRad() * 180/PI;
    }

    public void update() {
        double[] change = getPosChange();
        curPos[0] += change[0];
        curPos[1] += change[1];
        curPos[2] += change[2];
    }

    // TODO: TEST THIS
    public double[] getPosChange() {
        lastChangePos = getPosChangeCenter();
        double[] posChange = new double[3];
        posChange[0] = lastChangePos[0] * cos(curPos[2]) + lastChangePos[1] * cos(curPos[2] + PI/2);
        posChange[1] = lastChangePos[0] * sin(curPos[2]) + lastChangePos[1] * sin(curPos[2] + PI/2);
        posChange[2] = lastChangePos[2];
        return posChange;
    }

    // NOTE: Odometry modules are to the left and to the back of the center of the robot
    public double[] getPosChangeCenter() {
        double[] posChangeNoStrafe = super.getPosChangeCenter();
        double dx = getDeltaOdoTwo() - posChangeNoStrafe[2] * ODO2_TO_CENTER_Y;
        return new double[] { dx, posChangeNoStrafe[1], posChangeNoStrafe[2] };

    }

    public double[] getCurPos(){
        return new double[]{getCurX(), getCurY(), getCurThetaRad()};
    }
}