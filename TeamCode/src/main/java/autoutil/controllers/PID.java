package autoutil.controllers;

import autoutil.profilers.Profiler;
import util.codeseg.ReturnCodeSeg;
import util.codeseg.ReturnParameterCodeSeg;

import static global.General.log;

public class PID extends Controller{
    private double kp;
    private double ki;
    private double kd;
    private double minimumOutput;
    private double maximumTime;
    private double maximumDerivative;
    private double maximumIntegralRange;
    private double restOutput = 0;

    public PID(double kp, double ki, double kd){
        init(kp, ki, kd, 100000, 0.01, 100000, 100000);
    }

    public PID(double kp, double ki, double kd, double minimumOutputBeforeNext, double maximumTimeBeforeNext){
        init(kp, ki, kd, minimumOutputBeforeNext, maximumTimeBeforeNext, 100000, 100000);
    }

    public PID(double kp, double ki, double kd, double minimumOutputBeforeNext, double maximumTimeBeforeNext, double maximumDerivative, double maximumIntegralRange){
        init(kp, ki, kd, minimumOutputBeforeNext, maximumTimeBeforeNext, maximumDerivative, maximumIntegralRange);
    }

    private void init(double kp, double ki, double kd, double mo, double mt, double md, double mi){
        this.kp = kp; this.ki = ki; this.kd = kd;
        this.minimumOutput = mo; this.maximumTime = mt; this.maximumDerivative = md; this.maximumIntegralRange = mi;
    }

    public void setToStandardForm(double kp, double ti, double td){
        this.kp = kp; this.ki = kp/ti; this.kd = kp*td;
    }

    public void setRestOutput(double restOutput){
        this.restOutput = restOutput;
    }

    public void setMinimumOutput(double minimumOutput){ this.minimumOutput = minimumOutput; }

    public void setMaximumTime(double maximumTime){ this.maximumTime = maximumTime; }

    public void setMaximumIntegralRange(double range){this.maximumIntegralRange = range;}

    public void setMaximumDerivative(double maximumDerivative){this.maximumDerivative = maximumDerivative;}

    @Override
    public void update(){
        updateProfilers();
        if(Math.abs(getError()) > maximumIntegralRange){
            errorProfiler.resetIntegral();
        }
        output = ((kp * getError()) + (ki * errorProfiler.getIntegral()) + (kd * errorProfiler.getDerivative())) + (Math.signum(getError())*restOutput);
        if(Math.abs(getOutput()) < minimumOutput){
            isAtTarget = ((errorProfiler.getCurrentTime() - currentTime) > maximumTime)
                    && (Math.abs(errorProfiler.getDerivative()) < maximumDerivative)
                    && isWithinAccuracyRange();
        }else{
            currentTime = errorProfiler.getCurrentTime();
        }
    }

    public double[] getCoefficients(){
        return new double[]{kp, ki, kd};
    }

}
