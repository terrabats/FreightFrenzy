package geometry;

import static java.lang.Math.*;

public class Point {
    public double x, y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point rotate(double angRad, Point origin) {
        double sx = x - origin.x, sy = y - origin.y;
        double nx = sx * cos(angRad) - sy * sin(angRad);
        double ny = sx * sin(angRad) + sy * cos(angRad);
        return new Point(nx, ny);
    }
    public String toString() {
        return x + " " + y;
    }
}
