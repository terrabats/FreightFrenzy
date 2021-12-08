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
        double ang = atan2(p.y - center.y, p.x - center.x);
        if (p.x == center.x) {
            ang = signum(p.y - center.y) * PI/2;
        }
        ang %= 2 * PI;
        return ang;
    }

    public Pose getPositionFromTheta(double theta) {
        Point p = new Point(center.x + r * cos(theta), center.y + r * sin(theta));
        double ang = (p.y == center.y) ? INF : -atan2(p.y - center.y, p.x - center.x);
        return new Pose(p, ang);
    }

    @Override
    public GeometryObject getRelativeTo(Pose origin) {
        return new Circle(center.getRelativeTo(origin), r);
    }
}
