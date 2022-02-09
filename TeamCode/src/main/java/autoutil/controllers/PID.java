package autoutil.controllers;

import autoutil.profilers.Profiler;
import util.codeseg.ReturnCodeSeg;

public class PID extends Controller{
    private final double kp;
    private final double ki;
    private final double kd;
    private double minimumOutput;
    private double maximumTime;
    private double maximumDerivative;
    private double maximumIntegralRange;

    private double currentValue = 0;
    private double targetValue = 0;
    private double currentTime = 0;

    private final Profiler profiler;

    private ReturnCodeSeg<Double> processVariable;

    private double output = 0;
    private boolean isAtTarget = false;

    public PID(double kp, double ki, double kd){
        this.kp = kp; this.ki = ki; this.kd = kd;
        this.minimumOutput = 0; this.maximumTime = 0.05; this.maximumDerivative = 1000000; this.maximumIntegralRange = 1000000;
        profiler = new Profiler(this::getError);
    }

    public PID(double kp, double ki, double kd, double minimumOutputBeforeNext, double maximumTimeBeforeNext){
        this.kp = kp; this.ki = ki; this.kd = kd;
        this.minimumOutput = minimumOutputBeforeNext; this.maximumTime = maximumTimeBeforeNext; this.maximumDerivative = 1000000; this.maximumIntegralRange = 1000000;
        profiler = new Profiler(this::getError);
    }

    public void setProcessVariable(ReturnCodeSeg<Double> processVariable){
        this.processVariable = processVariable;
    }

    public void setMinimumOutput(double minimumOutput){
        this.minimumOutput = minimumOutput;
    }

    public void setMaximumTime(double maximumTime){ this.maximumTime = maximumTime; }

    public void setMaximumIntegralRange(double range){this.maximumIntegralRange = range;}

    public void setMaximumDerivative(double maximumDerivative){this.maximumDerivative = maximumDerivative;}

    public void update(){
        currentValue = processVariable.run();
        profiler.update();
        if(Math.abs(getError()) > maximumIntegralRange){
            profiler.resetIntegral();
        }
        output = (kp * getError()) + (ki * profiler.getIntegral()) + (kd * profiler.getDerivative());
        if(Math.abs(getOutput()) < minimumOutput){
            isAtTarget = ((profiler.getCurrentTime() - currentTime) > maximumTime) && (Math.abs(profiler.getDerivative()) < maximumDerivative);
        }else{
            currentTime = profiler.getCurrentTime();
        }
    }

    public double getOutput(){
        return output;
    }

    public double[] getCoefficients(){
        return new double[]{kp, ki, kd};
    }

    public void setTarget(double targetValue){
        this.targetValue = targetValue;
    }

    public double getTarget(){
        return targetValue;
    }

    public double getError(){
        return targetValue-currentValue;
    }

    public boolean isAtTarget(){ return isAtTarget;}

    public void reset(){
        targetValue = 0;
        profiler.reset();
        isAtTarget = false;
    }

}
