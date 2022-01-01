package autoutil.reactors;

import static java.lang.Math.*;
import static global.General.*;

public class TankReactor {

    // Note that the ODOMETRY shows 0 angle at wherever we start (positive y direction probably)
    // the POINTS GENERATED show 0 angle at positive x direction
    // finally the ODOMETRY is in degrees
    // the POINTS GENERATED are in radians

    // TODO: TUNE THESE CONSTANTS
    private static final double kf = 0.022, kt = 0.015;
    private static final double fPowWaypoint = 0.3; // 0.15;
    private static final double fPowSetpoint = 0.2;

    public boolean moveForward(double targetX, double targetY) {
        double[] curPos = bot.odometry.curPos;
        if (abs(targetX - curPos[0]) > abs(targetY - curPos[1])) {
            return signum(targetX - curPos[0]) != signum(curPos[2]);
        } else {
            return (((targetY - curPos[1]) > 0) && (abs(curPos[2]) < 90))
                    || (((targetY - curPos[1]) < 0) && (abs(curPos[2]) > 90));
        }
    }

    public double forwardPowSetpoint(double targetX, double targetY) {
//        return forwardPowWaypoint(targetX, targetY) * fPowSetpoint/fPowWaypoint;
        double[] curPos = bot.odometry.curPos;
        return kf * sqrt(pow(curPos[1] - targetY, 2) + pow(curPos[0] - targetX, 2))
                * (moveForward(targetX, targetY) ? 1 : -1);
    }

    public double forwardPowWaypoint(double targetX, double targetY) {
        return moveForward(targetX, targetY) ? fPowWaypoint : -fPowWaypoint;
    }

    public double turnPowWay(double targetX, double targetY, double stPos) {
        double[] curPos = bot.odometry.curPos;
        return turnPow(atan2(targetY - curPos[1], targetX - curPos[0]), stPos);
    }

    public double turnPow(double targetH, double stPos) {
        log.showAndRecord("TargetH1", targetH);
        targetH -= stPos;
        targetH *= 180/PI; // convert to degrees
        log.showAndRecord("TargetH2", targetH);
        // now targetH and curPos[2] are counterclockwise > 0, 0 in +y direction, and in degrees
        // positive ROBOT turn is CW
        while (targetH > 180) targetH -= 360;
        while (targetH < -180) targetH += 360;

        double curH = bot.odometry.curPos[2] * 180/PI;
        if (abs(targetH - curH) > abs(targetH - curH + 360)) {
            curH -= 360;
        }
        if (abs(targetH - curH) > abs(targetH - curH - 360)) {
            curH += 360;
        }
        log.show("curH", curH);
        return kt * (curH - targetH);
    }
}
