package robotparts.sensors;

import global.Constants;
import robotparts.RobotPart;
import robotparts.electronics.IEncoder;
import util.codeseg.CodeSeg;

import static java.lang.Math.*;
import static global.General.*;
import static robot.RobotFramework.*;

public class Odometry extends RobotPart {

    private static final double R = 4.1;
    private static final double ODO1_TO_CENTER_X = 4.6;
    private static final double ODO1_TO_CENTER_Y = 0; // 0.92;

    private double prevOdoOnePos = 0;
    private double prevOdoTwoPos = 0;

    private final CodeSeg odometryUpdateCode = () -> {};

    private IEncoder rEnc;
    private IEncoder cEnc;

    public double[] curPos = new double[] { 0, 0, 0 };

    @Override
    public void init() {
        rEnc = createEncoder("br", "rEnc", IEncoder.Type.NORMAL);
        cEnc = createEncoder("bl", "cEnc", IEncoder.Type.NORMAL);
        getPosChangeCenter();
        curPos = new double[] { 0, 0, 0 };
        odometryThread.setCode(odometryUpdateCode);

    }

    private double getDeltaOdoOne() {
        double delta = -cEnc.getPos() - prevOdoOnePos;
        prevOdoOnePos += delta;
        return ticksToCm(delta);
    }

    private double getDeltaOdoTwo() {
        double delta = rEnc.getPos() - prevOdoTwoPos;
        prevOdoTwoPos += delta;
        return ticksToCm(delta);
    }

    private double ticksToCm(double ticks) {
        return ticks/8192 * 3.5 * Math.PI;
    }

    public double getCurX() { return curPos[0]; }
    public double getCurY() { return curPos[1]; }
    public double getCurTheta() {
        curPos[2] %= (2 * Math.PI);
        if (curPos[2] < 0) { curPos[2] += 2 * Math.PI; }
        if (curPos[2] > Math.PI) { curPos[2] -= 2 * Math.PI; }
        return curPos[2];
    }

    public double[] getPosChangeCenter() {
        double d1 = getDeltaOdoOne();
        double d2 = getDeltaOdoTwo();
        log.show("delta odo1", d1);
        log.show("delta odo2", d2);
        double theta = -(d2 - d1)/R;
        log.show("delta theta", theta);
        if (theta != 0) {
            double r2 = d1 / theta;
//            log.watch("r2", r2);
            double r3 = sqrt(pow(ODO1_TO_CENTER_X - r2, 2) + pow(ODO1_TO_CENTER_Y, 2));
//            log.watch("r3", r3);
            double dx = r3 * (1 - cos(theta));
            double dy = r3 * sin(theta);
            curPos[0] += dx; curPos[1] += dy; curPos[2] += theta;
            return new double[]{dx, dy, theta};
        } else {
            curPos[1] += d1;
            return new double[] { 0, d1, 0 };
        }
    }
}
