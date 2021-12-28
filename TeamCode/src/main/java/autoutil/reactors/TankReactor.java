package autoutil.reactors;

import static java.lang.Math.abs;
import static global.General.bot;

public class TankReactor {

    private boolean moveForward(double targetX, double targetY) {
        double[] curPos = bot.odometry.curPos;
        if (abs(targetX - curPos[0]) > abs(targetY - curPos[1])) {
            return ((targetX - curPos[0]) > 0) && (abs(curPos[2]) < 90);
        } else {
            return ((targetY - curPos[1]) > 0) && (curPos[2] > 0);
        }
    }

    private double turnPow(double targetH) {
        return 0;
    }
}
