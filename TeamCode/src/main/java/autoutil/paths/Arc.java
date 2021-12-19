package autoutil.paths;

import geometry.Circle;
import geometry.CircularArc;
import geometry.Point;
import geometry.Pose;
import global.Constants;

public class Arc extends PathSegment{
    public CircularArc arc;

    public Arc(Circle generated, Point startPoint, Point endPoint) {
        arc = new CircularArc(generated.center, startPoint, endPoint, generated.r);
    }

    public Arc(Circle generated, double st, double en) {
        arc = new CircularArc(generated.center, st, en, generated.r);
    }

    public double getArcLength() {
        return arc.getArcLength();
    }

    @Override
    public void generatePoints() {
        points.addAll(arc.getPoints(Constants.ANG_ACC_ARC));
    }

    public boolean goingCW(Pose p) {
        return arc.goingCW(p);
    }

    @Override
    public void flip(boolean x, boolean y) {
        Pose newStPt = arc.getPositionFromTheta(arc.arcSt);
        Pose newEndPt = arc.getPositionFromTheta(arc.arcEnd);
        if (x) {
            newStPt.p.x *= -1;
            newStPt.ang *= -1;
            newEndPt.p.x *= -1;
            newEndPt.ang *= -1;
            arc.center.x *= -1;
        }
        if (y) {
            newStPt.p.y *= -1;
            newStPt.ang *= -1;
            newEndPt.p.y *= -1;
            newEndPt.ang *= -1;
            arc.center.y *= -1;
        }
        CircularArc v1 = new CircularArc(arc.center, newStPt.p, newEndPt.p, arc.r);
        CircularArc v2 = new CircularArc(arc.center, newEndPt.p, newStPt.p, arc.r);
        arc = (v1.getArcLength() < v2.getArcLength()) ? v1 : v2;
    }
}
