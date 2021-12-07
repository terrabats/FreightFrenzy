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
//        Position orig = new Position(
//                new Point(-origin.p.x, -origin.p.y), (-origin.ang) % (2 * Math.PI)
//                );
//        line.p1 = line.p1.changeOrigin(orig);
//        line.p2 = line.p2.changeOrigin(orig);
    }
}
