package autoutil.paths;

import geometry.Circle;
import geometry.CircleArc;
import geometry.Point;
import geometry.Pose;
import global.Constants;

public class Arc extends PathSegment{
    public CircleArc arc;

    public Arc(Circle generated, Point startPoint, Point endPoint) {
        arc = new CircleArc(generated.center, startPoint, endPoint, generated.r);
    }

    public Arc(Circle generated, double st, double en) {
        arc = new CircleArc(generated.center, st, en, generated.r);
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
    public void unshift(Pose origin) {
        origin.ang -= Math.PI/2;
        Pose stP = arc.getPositionFromTheta(arc.arcSt);
        Pose endP = arc.getPositionFromTheta(arc.arcEnd);
        Pose originToBeRelativeTo = new Pose(new Point(0, 0), -origin.ang);
        stP.p = stP.p.getRelativeTo(originToBeRelativeTo);
        endP.p = endP.p.getRelativeTo(originToBeRelativeTo);
        arc.center = arc.center.getRelativeTo(new Pose(new Point(0, 0), -origin.ang));

        Arc a2 = new Arc(new Circle(arc.center, arc.r), endP.p, stP.p);

        if (this.getArcLength() > a2.getArcLength()) {
            this.arc.arcSt = a2.arc.arcEnd;
            this.arc.arcEnd = a2.arc.arcSt;
        }
    }

    @Override
    public void flip(boolean x, boolean y) {
//        Pose newStPt = arc.getPositionFromTheta(arc.arcSt);
//        Pose newEndPt = arc.getPositionFromTheta(arc.arcEnd);
//        if (x) {
//            newStPt.p.x *= -1;
//            newStPt.ang *= -1;
//            newEndPt.p.x *= -1;
//            newEndPt.ang *= -1;
//            arc.center.x *= -1;
//        }
//        if (y) {
//            newStPt.p.y *= -1;
//            newStPt.ang *= -1;
//            newEndPt.p.y *= -1;
//            newEndPt.ang *= -1;
//            arc.center.y *= -1;
//        }
//        CircleArc v1 = new CircleArc(arc.center, newStPt.p, newEndPt.p, arc.r);
//        CircleArc v2 = new CircleArc(arc.center, newEndPt.p, newStPt.p, arc.r);
//        arc = (v1.getArcLength() < v2.getArcLength()) ? v1 : v2;
    }
}
