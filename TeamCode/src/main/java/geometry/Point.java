package geometry;

import static java.lang.Math.*;

public class Point extends GeometryObject {
    public double x, y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point changeOrigin(Position origin) {
        double sx = x - origin.p.x, sy = y - origin.p.y;
        double nx = sx * cos(origin.ang) - sy * sin(origin.ang);
        double ny = sx * sin(origin.ang) + sy * cos(origin.ang);
        return new Point(nx, ny);
    }
    public String toString() {
        return x + " " + y;
    }
}
