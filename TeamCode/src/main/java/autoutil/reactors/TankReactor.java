package autoutil.reactors;

import static java.lang.Math.*;
import static global.General.*;

public class TankReactor {

    // Note that the ODOMETRY shows 0 angle at wherever we start (positive y direction probably)
    // the POINTS GENERATED show 0 angle at positive x direction
    // finally the ODOMETRY is in degrees
    // the POINTS GENERATED are in radians

    // TODO: TUNE THESE CONSTANTS
    private static final double kf = 3, kt = 3;
    private static final double fPowWaypoint = 0.2;

    public boolean moveForward(double targetX, double targetY) {
        double[] curPos = bot.odometry.curPos;
        if (abs(targetX - curPos[0]) > abs(targetY - curPos[1])) {
            return ((targetX - curPos[0]) > 0) && (curPos[2] < 0);
        } else {
            return ((targetY - curPos[1]) > 0) && (abs(curPos[2]) < 90);
        }
    }

    public double forwardPowSetpoint(double targetX, double targetY) {
        double[] curPos = bot.odometry.curPos;
        return kf * sqrt(pow(curPos[1] - targetY, 2) + pow(curPos[0] - targetX, 2))
                * (moveForward(targetX, targetY) ? 1 : -1);
    }

    public double forwardPowWaypoint(double targetX, double targetY) {
        return moveForward(targetX, targetY) ? fPowWaypoint : -fPowWaypoint;
    }

    public double turnPow(double targetX, double targetY, double stPos) {
        double[] curPos = bot.odometry.curPos;
        return turnPow(atan2(targetY - curPos[1], targetX - curPos[0]), stPos);
    }

    public double turnPow(double targetH, double stPos) {
        targetH -= stPos;
        targetH *= 180/PI; // convert to degrees
        // now targetH and curPos[2] are counterclockwise > 0, 0 in +y direction, and in degrees
        // positive ROBOT turn is CW
        while (targetH > PI) targetH -= 2 * PI;
        while (targetH < PI) targetH += 2 * PI;

        double curH = bot.odometry.curPos[2];
        if (abs(targetH - curH) > abs(targetH - curH + 2 * PI)) {
            curH -= 2 * PI;
        }
        if (abs(targetH - curH) > abs(targetH - curH - 2 * PI)) {
            curH += 2 * PI;
        }
        return kt * (curH - targetH);
    }
}
