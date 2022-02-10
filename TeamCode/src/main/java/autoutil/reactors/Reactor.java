package autoutil.reactors;

import java.util.ArrayList;
import java.util.Arrays;

import autoutil.controllers.Controller;

public abstract class Reactor {

    protected ArrayList<Controller> controllers = new ArrayList<>();

    public abstract double[] getPose();
    public abstract void setTarget(double[] target);
    public abstract void nextTarget();
    public abstract boolean isAtTarget();
    public abstract void moveToTarget();
    public abstract double[] getError();


    protected void addControllers(Controller... newControllers){
        controllers.addAll(Arrays.asList(newControllers));
    }

}
