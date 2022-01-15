package autoutil.controllers;

import autoutil.profilers.Profiler;
import util.codeseg.ReturnCodeSeg;

public class PID {
    private final double kp;
    private final double ki;
    private final double kd;

    private double currentValue = 0;
    private double targetValue = 0;

    private final Profiler profiler;

    private ReturnCodeSeg<Double> processVariable;

    private double output = 0;

    public PID(double kp, double ki, double kd){
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        profiler = new Profiler(this::getError);
    }

    public void setProcessVariable(ReturnCodeSeg<Double> processVariable){
        this.processVariable = processVariable;
    }

    public void update(){
        currentValue = processVariable.run();
        profiler.update();
        output = (kp * getError()) + (ki * profiler.getIntegral()) + (kd * profiler.getDerivative());
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

    public void reset(){
        targetValue = 0;
        profiler.reset();
    }

}
