package autoutil;

import util.Timer;

public class Profiler {
    // Fix this so that instead of PID it should contain just the things to store values of position, velocity, acceleration, and things like that
    private final Timer timer = new Timer();

    private final double kp, ki, kd;

    private double cur_integral = 0, prev_error = 0;

    private boolean prevUsed;

    public Profiler(double kp, double ki, double kd) {
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
        cur_integral += (error + prev_error)/2 * timer.seconds(); // trapezoidal Riemann sum
        timer.reset();
        prev_error = error;
        return kd * dV + ki * cur_integral + kp * error;
    }
}
