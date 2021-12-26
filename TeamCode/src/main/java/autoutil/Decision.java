package autoutil;

import static global.General.*;
import static java.lang.Math.*;

public class Decision {
    // Decisions that need to be made while the robot is moving

    public boolean moveForward(double targetX, double targetY) {
        double[] curPos = bot.odometry.curPos;
        if (abs(targetX - curPos[0]) > abs(targetY - curPos[1])) {
            return ((targetX - curPos[0]) > 0) && (abs(curPos[2]) < 90);
        } else {
            return ((targetY - curPos[1]) > 0) && (curPos[2] > 0);
        }
    }
}
