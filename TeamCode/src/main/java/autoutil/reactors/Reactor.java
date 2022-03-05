package autoutil.reactors;

import java.util.ArrayList;
import java.util.Arrays;

import autoutil.controllers.Controller1D;
import autoutil.controllers.Controller2D;

public abstract class Reactor {

//    protected ArrayList<Controller1D> controllers = new ArrayList<>();
    protected Controller2D movementController;
    protected Controller1D headingController;

    public abstract void init();
    public abstract double[] getPose();
    public abstract void setTarget(double[] target);
    public abstract void nextTarget();
    public abstract boolean isAtTarget();
    public abstract void moveToTarget();

    protected void setControllers(Controller2D movementController, Controller1D headingController){
        this.movementController = movementController;
        this.headingController = headingController;
    }

}