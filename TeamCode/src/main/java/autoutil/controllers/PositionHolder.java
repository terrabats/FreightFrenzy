package autoutil.controllers;

import autoutil.paths.PathSegment2;
import geometry.position.Pose;

public class PositionHolder extends Controller1D{

    private double velocityThreshold = 0.01;
    private double restPower = 0;
    private double deltaPower = 0.01;

    public PositionHolder(double restPower, double deltaPower, double velocityThreshold){
        this.restPower = restPower;
        this.deltaPower = deltaPower;
        this.velocityThreshold = velocityThreshold;
    }

    @Override
    public void update(Pose pose, PathSegment2 pathSegment) {
        updateProfilers();
        double currentVel = processVariableProfiler.getDerivative();
        if(Math.abs(currentVel) > velocityThreshold){
            restPower -= (Math.signum(restPower)*deltaPower);
        }else{
            isAtTarget = true;
        }
        setOutput(restPower);
    }
}
