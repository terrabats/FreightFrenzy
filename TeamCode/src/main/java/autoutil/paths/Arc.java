package autoutil.paths;

import geometry.Circle;
import geometry.CircleArc;
import geometry.Point;
import geometry.Position;
import global.Constants;

public class Arc extends PathSegment{
    public final CircleArc arc;

    public Arc(Circle generated, Point startPoint, Point endPoint) {
        arc = new CircleArc(generated.center, startPoint, endPoint, generated.r);
    }

    public Arc(Circle generated, double st, double en) {
        arc = new CircleArc(generated.center, st, en, generated.r);
    }

    public double getArcLength() {
        return Math.abs(arc.arcEnd - arc.arcSt) * arc.r;
    }

    @Override
    public void generatePoints() {
        points.addAll(arc.getPoints(Constants.ANG_ACC_ARC));
    }

    public boolean goingCW(Position p) {
        return arc.goingCW(p);
    }

    @Override
    public void unshift(Position origin) {
//        Position orig = new Position(
//                new Point(-origin.p.x, -origin.p.y), (-origin.ang) % (2 * Math.PI)
//        );
//        arc.center = arc.center.changeOrigin(orig);
//        arc.arcSt = (arc.arcSt + orig.ang) % (2 * Math.PI);
//        arc.arcEnd = (arc.arcEnd + orig.ang) % (2 * Math.PI);
    }
}
