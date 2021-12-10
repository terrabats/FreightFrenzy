package autoutil.paths;

import geometry.Line;
import geometry.Point;
import geometry.Pose;

public class PathLine extends PathSegment{

    private final Line line;

    public PathLine(Point p1, Point p2) {
        line = new Line(p1, p2);
    }

    @Override
    public void generatePoints() {
        for (double a = 0; a <= 1; a += 0.05) {
            points.add(new Pose(getAt(a), Math.atan2(line.my, line.mx)));
        }
    }

    public Point getAt(double t) { return line.getAt(t); }

    @Override
    public void unshift(Pose origin) {
        //
    }

    @Override
    public void flip(boolean x, boolean y) {
        if (x) {
            line.p1.x *= -1;
            line.p2.x *= -1;
            line.mx *= -1;
        }
        if (y) {
            line.p1.y *= -1;
            line.p2.y *= -1;
            line.my *= -1;
        }
    }
}
