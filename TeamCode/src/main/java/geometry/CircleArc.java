package geometry;

import java.util.ArrayList;

import static java.lang.Math.*;

public class CircleArc extends Circle {
    public double arcSt, arcEnd; // radians

    public CircleArc(Point center, Point start, Point end, double r) {
        super(center, r);
        arcSt = getThetaFromPoint(start);
        arcEnd = getThetaFromPoint(end);
    }

    public CircleArc(Point center, double st, double en, double r) {
        super(center, r);
        arcSt = st;
        arcEnd = en;
    }

    public boolean inArc(Point p) {
        return Math.min(arcSt, arcEnd) <= getThetaFromPoint(p) &&
                getThetaFromPoint(p) <= Math.max(arcSt, arcEnd);
    }

    public ArrayList<Pose> getPoints(double angDivide) {
        ArrayList<Pose> ret = new ArrayList<>();
        double endOfArc = ((arcSt < arcEnd) ? 0 : (2 * PI)) + arcEnd;
        for (double i = arcSt; i <= endOfArc; i += angDivide) {
            ret.add(getPositionFromTheta(i));
//            System.out.println(i * 180/PI);
        }
//        System.out.println(arcSt + " " + arcEnd);
        return ret;
    }

    public boolean goingCW(Pose p) {
        if (p.p.x > center.x) {
            if (p.p.y > center.y) {
                return p.ang == 0 || (p.ang >= 3 * PI / 2 && p.ang <= 2 * PI);
            } else {
                return p.ang >= PI && p.ang <= 3 * PI / 2;
            }
        } else {
            if (p.p.y > center.y) {
                return p.ang >= 0 && p.ang <= PI / 2;
            } else {
                return p.ang >= PI / 2 && p.ang <= PI;
            }
        }
    }
}
