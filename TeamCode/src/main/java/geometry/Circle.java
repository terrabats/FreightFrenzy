package geometry;

import static java.lang.Math.*;
import static global.Constants.*;

public class Circle extends GeometryObject{
    public Point center;
    public double r; // center is (h, k) and radius is r

    public Circle(Point center, double r) {
        this.center = center;
        this.r = r;
    }

    public double getThetaFromPoint(Point p) {
        double ang = tan((p.y - center.y)/(p.x - center.x));
        if (p.x < center.x) ang += PI;
        ang = ang > 0 ? ang : ang + 2 * PI;
        return ang;
    }

    public Position getPositionFromTheta(double theta) {
        Point p = new Point(center.x + r * cos(theta), center.y + r * sin(theta));
        double ang = (p.y == center.y) ? INF : tan(-(p.x - center.x)/(p.y - center.y));
        return new Position(p, ang);
    }

    @Override
    public GeometryObject getRotated(double angRad, Point origin) {
        return new Circle((Point) center.rotate(angRad, origin), r);
    }
}
