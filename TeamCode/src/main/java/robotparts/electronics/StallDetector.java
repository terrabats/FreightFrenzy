package robotparts.electronics;

import util.Timer;

public class StallDetector {
    private double prevPos;
    private final Timer timer = new Timer();

    public StallDetector() {
        prevPos = 0.0;
        timer.reset();
    }

    public StallDetector(double stPos) {
        prevPos = stPos;
        timer.reset();
    }

    public boolean isStalling(double curPos, double range) {
        boolean ret = Math.abs(curPos - prevPos) <= range;
        prevPos = curPos;
        if (ret && timer.seconds() > 0.05) return true;
        if (!ret) timer.reset();
        return false;
    }
}
