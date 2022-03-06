package autoutil.paths;

import geometry.position.Line;
import geometry.position.Point;
import geometry.position.Pose;

public class PathLine2 extends PathPose{

    private final Line line;

    public PathLine2(Pose start, Pose end){
        poses.add(start);
        poses.add(end);
        line = new Line(start.p,end.p);
    }

    public Line getLine(){
        return line;
    }


}
