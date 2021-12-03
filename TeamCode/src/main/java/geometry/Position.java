package geometry;

import global.Constants;

public class Position {
    public Point p;
    public double ang;

    public Position(Point p, double ang) { this.p = p; this.ang = ang; }

    public boolean angIsInf() { return ang == Constants.INF; }
}
