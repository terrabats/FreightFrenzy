package robotparts;

import static global.General.*;
import static java.lang.Math.*;

public class Odometry extends RobotPart {

    private static final double R = 10.0; // TODO: GET THE REAL VALUE
    private static final double ODO1_TO_CENTER_X = 10.0; // TODO: GET THE REAL VALUE
    private static final double ODO1_TO_CENTER_Y = 10.0; // TODO: GET THE REAL VALUE

    private double prevOdoOnePos;
    private double prevOdoTwoPos;

    @Override
    public void init() {
        prevOdoOnePos = 0.0;
        prevOdoTwoPos = 0.0;
    }

    public double getDeltaOdoOne() {
        double delta = bot.mechDrive.getPos("fr") - prevOdoOnePos;
        prevOdoOnePos += delta;
        return delta;
    }

    public double getDeltaOdoTwo() {
        double delta = bot.mechDrive.getPos("br") - prevOdoTwoPos;
        prevOdoTwoPos += delta;
        return delta;
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
