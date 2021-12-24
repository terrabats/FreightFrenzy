package robotparts.sensors;

import robotparts.RobotPart;
import robotparts.electronics.IEncoder;
import util.codeseg.CodeSeg;

import static java.lang.Math.*;
import static global.General.*;
import static robot.RobotFramework.*;

public class Odometry extends RobotPart {

    private static final double R = 4.1;
    private static final double ODO1_TO_CENTER_X = 4.6;
    private static final double ODO1_TO_CENTER_Y = 0.92;

    private double prevOdoOnePos = 0;
//    private double prevOdoTwoPos = 0;

    private final CodeSeg odometryUpdateCode = this::update;

//    private IEncoder rEnc;
    private IEncoder cEnc;

    public double[] curPos = new double[] { 0, 0, 0 };
    public double[] lastChangePos = new double[] { 0, 0, 0 };

    @Override
    public void init() {
//        rEnc = createEncoder("br", "rEnc", IEncoder.Type.NORMAL);
        cEnc = createEncoder("bl", "cEnc", IEncoder.Type.NORMAL);
        update();
        curPos = new double[] { 0, 0, 0 };
        odometryThread.setCode(odometryUpdateCode);

    }

    private double getDeltaOdoOne() {
        double delta = -cEnc.getPos() - prevOdoOnePos;
        prevOdoOnePos += delta;
        return ticksToCm(delta);
    }

//    private double getDeltaOdoTwo() {
//        double delta = rEnc.getPos() - prevOdoTwoPos;
//        prevOdoTwoPos += delta;
//        return ticksToCm(delta);
//    }

    private double ticksToCm(double ticks) {
        return ticks/8192 * 3.5 * Math.PI;
    }

    public double getCurX() { return curPos[0]; }
    public double getCurY() { return curPos[1]; }
    public double getCurThetaRad() {
        curPos[2] %= (2 * Math.PI);
        if (curPos[2] < 0) { curPos[2] += 2 * Math.PI; }
        if (curPos[2] > Math.PI) { curPos[2] -= 2 * Math.PI; }
        return curPos[2];
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

    public double[] getPosChange() {
        lastChangePos = getPosChangeCenter();
        double[] posChange = new double[3];
        posChange[0] = lastChangePos[1] * sin(curPos[2] - PI/2);
        posChange[1] = lastChangePos[1] * cos(curPos[2] - PI/2);
        posChange[2] = lastChangePos[2];
        return posChange;
    }

    // NOTE: Odometry modules are to the left and to the back of the center of the robot
    public double[] getPosChangeCenter() {
        double dtheta = bot.gyroSensor.getRightHeadingRad() - curPos[2];

        double dt = getDeltaOdoOne() - dtheta * ODO1_TO_CENTER_X;
//        double d2 = getDeltaOdoTwo() - theta * (ODO1_TO_CENTER_X + R);

        return new double[] { 0, dt, dtheta };

        //

//        // d1 and d2 are positive when moving forward, negative when moving backward
//        double d1 = getDeltaOdoOne();
//        double d2 = getDeltaOdoTwo();
//        log.show("delta odo1", d1);
//        log.show("delta odo2", d2);
//        // theta is positive when turning counterclockwise
//        double theta = -(d2 - d1)/R;
//        log.show("delta theta", theta);
//        if (theta != 0) {
//            double r2 = -d1 / theta;
////            log.watch("r2", r2);
//            double r3 = signum(ODO1_TO_CENTER_X - r2) *
//                    sqrt(pow(ODO1_TO_CENTER_X - r2, 2) + pow(ODO1_TO_CENTER_Y, 2));
////            log.watch("r3", r3);
//            double dx = r3 * (1 - cos(theta));
//            double dy = r3 * sin(theta);
//            curPos[0] += dx; curPos[1] += dy; curPos[2] += theta;
//            return new double[]{dx, dy, theta};
//        } else {
//            curPos[1] += d1;
//            return new double[] { 0, d1, 0 };
//        }
    }
}
