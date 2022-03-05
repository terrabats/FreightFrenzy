package autoutil.controllers;

import autoutil.profilers.Profiler;
import util.codeseg.ReturnCodeSeg;

public abstract class Controller2D extends Controller1D {
    public Controller1D xController;
    public Controller1D yController;
    protected double xOutput = 0;
    protected double yOutput = 0;

    public Controller2D(){}

    public Controller2D(Controller1D xController, Controller1D yController){
        this.xController = xController;
        this.yController = yController;
    }

    public abstract void update(double heading);

    @Override
    public void update(){}

    public void setOutputX(double xOutput){
        this.xOutput = xOutput;
    }

    public void setOutputY(double yOutput){
        this.yOutput = yOutput;
    }

    public double getOutputX(){
        return xOutput;
    }

    public double getOutputY(){
        return yOutput;
    }

}
