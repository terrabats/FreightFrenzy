package autoutil.generators;

import autoutil.paths.PathPose;

public class PoseToPoseGenerator extends Generator{
    public void addPose(double x, double y, double heading){
        path.addSegment(new PathPose(x, y, Math.toRadians(heading)));
    }
}
