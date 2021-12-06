package geometry;

import static java.lang.Math.*;

import global.Constants;

public class Position extends GeometryObject {
    public Point p;
    public double ang;

    public Position(Point p, double ang) { this.p = p; this.ang = ang; }

    public boolean angIsInf() { return ang == Constants.INF; }

    @Override
    public Position getRelativeTo(Position origin) {
        Position pos2 = new Position(p, ang);
        pos2.p = pos2.p.changeOrigin(origin);
        pos2.rotate(-origin.ang);
        return pos2;
    }

    public void translate(double x, double y) { p.x += x; p.y += y; }

    public void rotate(double a) { ang += a; ang = ang % (2 * PI); }

    @Override
    public String toString() {
        return "Position{" +
                "p=" + p +
                ", ang=" + ang +
                '}';
    }
}
