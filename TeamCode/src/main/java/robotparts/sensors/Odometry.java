package robotparts.sensors;

import robotparts.RobotPart;
import robotparts.custom.Encoder;
import util.codeseg.CodeSeg;

import static global.General.*;
import static java.lang.Math.*;
import static robot.RobotFramework.*;

public class Odometry extends RobotPart {

    private static final double R = 10.0; // TODO: GET THE REAL VALUE
    private static final double ODO1_TO_CENTER_X = 10.0; // TODO: GET THE REAL VALUE
    private static final double ODO1_TO_CENTER_Y = 10.0; // TODO: GET THE REAL VALUE

    private double prevOdoOnePos;
    private double prevOdoTwoPos;

    private final CodeSeg odometryUpdateCode = () -> {};

    private Encoder rEnc;
    private Encoder cEnc;

    @Override
    public void init() {
        rEnc = createEncoder("fr", "rEnc", Encoder.Type.NORMAL);
        cEnc = createEncoder("br", "cEnc", Encoder.Type.NORMAL);
        prevOdoOnePos = 0.0;
        prevOdoTwoPos = 0.0;
        odometryThread.setCode(odometryUpdateCode);

    }

    private double getDeltaOdoOne() {
        double delta = ticksToCm(rEnc.getPos() - prevOdoOnePos);
        prevOdoOnePos += delta;
        return delta;
    }

    private double getDeltaOdoTwo() {
        double delta = ticksToCm(cEnc.getPos() - prevOdoTwoPos);
        prevOdoTwoPos += delta;
        return delta;
    }

    private double ticksToCm(double ticks) {
        return ticks; // TODO: FIX THIS METHOD
    }

    public double[] getPosChangeCenter() {
        double d1 = getDeltaOdoOne();
        double d2 = getDeltaOdoTwo();
        double theta = (d2 - d1)/R;
        if (theta != 0) {
            double r2 = d1 / theta;
            double r3 = sqrt(pow(ODO1_TO_CENTER_X - r2, 2) + pow(ODO1_TO_CENTER_Y, 2));
            double dx = r3 * (1 - cos(theta));
            double dy = r3 * sin(theta);
            return new double[]{dx, dy, theta};
        } else {
            return new double[] { 0, d1, 0 };
        }
    }
}
