package autoutil.controllers;

import autoutil.profilers.Profiler;
import util.codeseg.ReturnCodeSeg;

public abstract class Controller {

    protected ReturnCodeSeg<Double> processVariable;
    protected ReturnCodeSeg<Double> processError = this::getRawError;
    protected Profiler processVariableProfiler = new Profiler(() -> 0.0);
    protected Profiler errorProfiler = new Profiler(this::getError);

    protected double output = 0;
    protected boolean isAtTarget = false;

    protected double currentValue = 0;
    protected double  targetValue = 0;
    protected double currentTime = 0;

    protected double accuracy = 1000000;


    public void setAccuracy(double accuracy){
        this.accuracy = accuracy;
    }

    public boolean isWithinAccuracyRange(){
        return (Math.abs(getError()) < accuracy);
    }

    public abstract void update();

    public void updateProfilers(){
        currentValue = processVariable.run();
        processVariableProfiler.update();
        errorProfiler.update();
    }

    public double getOutput(){
        return output;
    }

    public void setTarget(double targetValue){
        this.targetValue = targetValue;
    }

    public double getTarget(){
        return targetValue;
    }

    public double getRawError(){
        return targetValue-currentValue;
    }

    public double getError(){
        return processError.run();
    }


    public void setProcessVariable(ReturnCodeSeg<Double> processVariable){
        this.processVariable = processVariable;
    }

    public void setProcessError(ReturnCodeSeg<Double> processError){
        this.processError = processError;
    }


    public boolean isAtTarget(){ return isAtTarget;}

    public void reset(){
        targetValue = 0;
        errorProfiler.reset();
        processVariableProfiler.reset();
        isAtTarget = false;
    }


    public double[] getErrorState(){
        return new double[]{getError(), errorProfiler.getIntegral(), errorProfiler.getDerivative()};
    }

    public double[] getProcessVariableState(){
        return new double[]{currentValue, processVariableProfiler.getIntegral(), processVariableProfiler.getDerivative()};
    }
}