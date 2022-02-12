package autoutil.controllers;

import util.Timer;

public class PAR extends Controller{


    //Using x(t) we can calculate v(x)
    //1. First calculate v(t) which is d/dt(x(t))
    //2. Then calculate g(x) which is x(t)^-1 (inverse)
    //3. Then calculate v(g(x)) which is v(x)

    //Rest pow
    public double restOutput;
    //Approach rate [0,1] lower means stops farther away
    public double approachRate;
    //Proportional
    public double proportional;

    public PAR(double proportional, double approachRate, double restOutput) {
        this.proportional = proportional;
        this.approachRate = approachRate;
        this.restOutput = restOutput;
    }

    public double VofS(){
        return approachRate*Math.pow(Math.abs(getError()), 1/approachRate)*Math.signum(getError());
    }

    @Override
    public void update(){
        updateProfilers();
        output = (proportional * (VofS() - processVariableProfiler.getDerivative())) + (Math.signum(getError())*restOutput);
        isAtTarget = isWithinAccuracyRange();
    }
}
