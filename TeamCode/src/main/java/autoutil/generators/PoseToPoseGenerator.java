package autoutil.generators;

import automodules.StageList;
import autoutil.paths.PathAutoModule;
import autoutil.paths.PathPose;

public class PoseToPoseGenerator extends Generator{
    public void addPose(double x, double y, double heading){
        path.addSegment(new PathPose(x, y, Math.toRadians(heading)));
    }
    public void addAutoModule(StageList automodule){
        path.addSegment(new PathAutoModule(automodule));
    }
}