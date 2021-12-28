package geometry;

import java.util.ArrayList;

import static java.lang.Math.*;

public class CircularArc extends Circle {
    public double arcSt, arcEnd; // radians

    public CircularArc(Point center, Point start, Point end, double r) {
        super(center, r);
        arcSt = getThetaFromPoint(start);
        arcEnd = getThetaFromPoint(end);
    }

    public CircularArc(Point center, double st, double en, double r) {
        super(center, r);
        arcSt = st;
        arcEnd = en;
    }

    public boolean inArc(Point p) {
        return Math.min(arcSt, arcEnd) <= getThetaFromPoint(p) &&
                getThetaFromPoint(p) <= Math.max(arcSt, arcEnd);
    }

    public ArrayList<Pose> getPoints(double angDivide, Pose pose) {
        ArrayList<Pose> ret = new ArrayList<>();
        double endOfArc = ((arcSt <= arcEnd) ? 0 : (2 * PI)) + arcEnd;
        for (double i = arcSt; i <= endOfArc; i += angDivide) {
            ret.add(getPositionFromTheta(i));
//            System.out.println(i * 180/PI);
        }
        if (goingCW(pose)) {
            ArrayList<Pose> ret2 = new ArrayList<>();
            for (int i = ret.size() - 1; i >= 0; i--) {
                ret2.add(ret.get(i));
            }
            return ret2;
        }
//        System.out.println(arcSt + " " + arcEnd);
        return ret;
    }

    public boolean goingCW(Pose p) {
        if (p.p.x > center.x) {
            if (p.p.y > center.y) {
                return p.ang == 0 || (p.ang >= 3 * PI / 2 && p.ang <= 2 * PI);
            } else if (p.p.y < center.y) {
                return p.ang >= PI && p.ang <= 3 * PI / 2;
            } else {
                return p.ang == 3 * PI / 2;
            }
        } else if (p.p.x < center.x) {
            if (p.p.y > center.y) {
                return p.ang >= 0 && p.ang <= PI / 2;
            } else if (p.p.y < center.y) {
                return p.ang >= PI / 2 && p.ang <= PI;
            } else {
                return p.ang == PI/2;
            }
        } else {
            if (p.p.y > center.y) {
                return p.ang == 0;
            } else {
                return p.ang == PI;
            }
        }
    }

    public double getArcLength() {
        double dAng = arcEnd - arcSt;
        dAng = dAng + ((dAng < 0) ? 2 * PI : 0);
        if (abs(dAng) < 0.1) { return 0; }
        return dAng * r;
    }
}
