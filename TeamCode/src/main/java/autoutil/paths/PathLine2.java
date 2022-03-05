package autoutil.paths;

import geometry.position.Line;
import geometry.position.Point;
import geometry.position.Pose;

public class PathLine2 extends PathPose{
    {
        poses.add(new Pose(new Point(0,0),0));
    }

    private final Line line;

    public PathLine2(double x, double y, double h){
        super(x,y,h);
        line = new Line(poses.get(poses.size()-2).p,poses.get(poses.size()-1).p);
    }


    public Point getAt(double t) { return line.getAt(t); }


}
