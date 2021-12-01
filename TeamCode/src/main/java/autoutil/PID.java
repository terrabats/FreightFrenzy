package autoutil;

import util.Timer;

public class PID {
    private final Timer timer = new Timer();

    private final double kp, ki, kd;

    private double cur_integral = 0, prev_error = 0;

    private boolean prevUsed;

    public PID(double kp, double ki, double kd) {
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        timer.reset();
        prevUsed = false;
    }

    public double getPow(double error) {
        if (!prevUsed) {
            prevUsed = true;
            prev_error = error;
        }
        double dV = error - prev_error;
        cur_integral += (error + prev_error)/2 * timer.seconds();
        timer.reset();
        prev_error = error;
        return kd * dV + ki * cur_integral + kp * error;
    }
}
