package autoutil.controllers;

import util.Timer;

public class PAR extends Controller{


    //Using x(t) we can calculate v(x)
    //1. First calculate v(t) which is d/dt(x(t))
    //2. Then calculate g(x) which is x(t)^-1 (inverse)
    //3. Then calculate v(g(x)) which is v(x)

    //Rest pow
    public double restPow = 0;
    //Approach rate [0,1] lower means stops farther away
    public double approachRate = 0;
    //Proportional
    public double proportional = 0;

    public void setPAR(double p, double a, double r){
        proportional = p;
        approachRate = a;
        restPow = r;
    }

    public double VofS(){
        return approachRate*Math.pow(Math.abs(getError()), 1/approachRate)*Math.signum(getError());
    }

    @Override
    public void update(){
        updateProfilers();
        output = (proportional * (VofS() - processVariableProfiler.getDerivative())) + (Math.signum(getError())*restPow);
        isAtTarget = isWithinAccuracyRange();
    }
}
