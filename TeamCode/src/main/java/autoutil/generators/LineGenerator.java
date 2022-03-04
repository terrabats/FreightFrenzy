package autoutil.generators;

import automodules.StageList;
import autoutil.paths.PathAutoModule;
import autoutil.paths.PathLine;
import autoutil.paths.PathLine2;
import autoutil.paths.PathPose;

public class LineGenerator extends Generator{
    public void addLine(double x, double y, double heading){
        path.addSegment(new PathLine2(x, y, Math.toRadians(heading)));
    }
}
