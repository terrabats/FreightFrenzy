package autoutil.paths;

import geometry.Circle;
import geometry.CircleArc;
import geometry.Point;
import geometry.Position;
import global.Constants;

public class Arc extends PathSegment{
    private final CircleArc arc;

    public Arc(Circle generated, Point startPoint, Point endPoint) {
        arc = new CircleArc(generated.center, startPoint, endPoint, generated.r);
    }

    public double getArcLength() {
        return (arc.arcEnd - arc.arcSt) * arc.r;
    }

    @Override
    public void generatePoints() {
        points.addAll(arc.getPoints(Constants.ANG_ACC_ARC));
    }

    public boolean goingCW(Position p) {
        return arc.goingCW(p);
    }
}
