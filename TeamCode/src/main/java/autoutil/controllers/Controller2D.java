package autoutil.controllers;

import autoutil.profilers.Profiler;
import util.codeseg.ReturnCodeSeg;

public abstract class Controller2D extends Controller1D {
    public Controller1D xController;
    public Controller1D yController;

    public Controller2D(){}

    public Controller2D(Controller1D xController, Controller1D yController){
        this.xController = xController;
        this.yController = yController;
    }

    public abstract void update();
}
