package autoutil.generators;

import automodules.StageList;
import autoutil.paths.PathAutoModule;
import autoutil.paths.PathPose;

public class LineGenerator extends Generator{
    public void addPose(double x, double y, double heading){
        path.addSegment(new PathPose(x, y, Math.toRadians(heading)));
    }
}
