package geometry;

import java.util.ArrayList;

public class CircleArc extends Circle {
    double arcSt, arcEnd; // radians

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

    public ArrayList<Position> getPoints(double angDivide) {
        ArrayList<Position> ret = new ArrayList<>();
        if (arcSt < arcEnd) {
            for (double i = arcSt; i <= arcEnd; i += angDivide) {
                ret.add(getPositionFromTheta(i));
            }
        } else {
            for (double i = arcEnd; i <= arcSt; i += angDivide) {
                ret.add(getPositionFromTheta(i));
            }
        }
        return ret;
    }
}
