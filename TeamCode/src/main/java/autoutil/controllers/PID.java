package autoutil.controllers;

import util.templates.ParameterConstructor;
import util.templates.ParameterType;

public class PID extends Controller implements ParameterConstructor<Double> {
    private double kp;
    private double ki;
    private double kd;
    private double minimumOutput;
    private double maximumTime;
    private double maximumDerivative;
    private double maximumIntegralRange;
    private double restOutput = 0;

    /**
     * Constructor for PID
     * Input 1: Proportional coefficient
     * Input 2: Integral coefficient
     * Input 3: Derivative coefficient
     * Input 4: Minimum Output
     * Input 5: Maximum Time
     * Input 6: Maximum Derivative
     * Input 7: Maximum Integral Range
     * @param parameterType
     * @param parameters
     */
    public PID(PIDParameterType parameterType, Double... parameters){
        addConstructor(PIDParameterType.DEFAULT, 3);
        addConstructor(PIDParameterType.DEFAULT_ALL, 7);
        addConstructor(PIDParameterType.STANDARD_FORM, 3, in -> new Double[]{in[0], in[0]/in[1], in[0]*in[2]});
        addConstructor(PIDParameterType.STANDARD__FORM_ALL, 7, in -> new Double[]{in[0], in[0]/in[1], in[0]*in[2], in[3], in[4], in[5], in[6]});
        createConstructors(parameterType, parameters, new Double[]{0.0,0.0,0.0,100000.0,0.01, 100000.0, 100000.0});
    }

    @Override
    public void construct(Double[] in){
        this.kp = in[0]; this.ki = in[1]; this.kd = in[2];
        this.minimumOutput = in[3]; this.maximumTime = in[4]; this.maximumDerivative = in[5]; this.maximumIntegralRange = in[6];
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


    public enum PIDParameterType implements ParameterType {
        DEFAULT,
        DEFAULT_ALL,
        STANDARD_FORM,
        STANDARD__FORM_ALL;
    }

}