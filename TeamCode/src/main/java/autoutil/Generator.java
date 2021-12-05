package autoutil;

import java.util.ArrayList;

import autoutil.paths.Arc;
import autoutil.paths.Path;
import autoutil.paths.PathSegment;
import geometry.Circle;
import geometry.Line;
import geometry.Point;
import geometry.Position;

import static java.lang.Math.*;

public class Generator {
    // Generates a path for the robot to follow
    // based on a array of points or conditions
    // there should be one static generator
    // should be able to create multiple paths if a decision is needed

    private ArrayList<Position> positions = new ArrayList<>();

    public void addNewPos(Position p) {
        p.translate(positions.get(positions.size() - 1).p.x, positions.get(positions.size() - 1).p.y);
        p.rotate(p.ang);
        addNewAbsPos(p);
    }

    public void addNewAbsPos(Position p) { positions.add(p); }

    public Path done() {
        Path path = new Path();
        for (int i = 0; i < positions.size() - 1; i++) {
            path.addSegs(generateSeg(positions.get(i), positions.get(i + 1)));
        }
        return path;
    }

    private ArrayList<PathSegment> generateSeg(Position p1, Position p2) {
        p1.rotate(-PI/2);
        p2 = p2.getRelativeTo(p1);
        p1.rotate(PI/2);

        double p = p2.ang;
        double dx = p2.p.x;
        double dy = p2.p.y;
        double d = sqrt(pow(dx, 2) + pow(dy, 2));
        double s = sin(p);
        double c = cos(p) - 1;
        double a = 2 + c;
        double b = -(c * dx + s * dy);
        double m = -pow(d, 2)/2;
        double rtDiscriminant = sqrt(pow(b, 2) - 4 * a * m);
        double r = (-b - rtDiscriminant)/(2 * a);
        double r2 = (-b + rtDiscriminant)/(2 * a);

        Circle c1;
        Circle c2;

        if (r < r2) {
            c1 = new Circle(new Point(r, 0), r);
            c2 = new Circle(new Point(dx + r * cos(p), dy + r * sin(p)), r);
        } else {
            c1 = new Circle(new Point(r2, 0), r2);
            c2 = new Circle(new Point(dx + r2 * cos(p), dy + r2 * sin(p)), r2);
        }

        Line betweenCenters = new Line(c1.center, c2.center);
        Point pointOfIntersection = betweenCenters.getAt(0.5);

        Arc cir1arc = getShorterArc(c1, new Point(0, 0), pointOfIntersection);
        Arc cir2arc = getShorterArc(c2, p2.p, pointOfIntersection);

        double mt = (c2.center.y - c1.center.x)/(c2.center.x - c1.center.x);

        double temp1 = (r2 * mt)/sqrt(pow(mt, 2) + 1);

        double x1 = -temp1 + c1.center.x;
        double y1 = sqrt(pow(r2, 2) - pow(x1 - c1.center.x, 2)) + c1.center.y;

        double x2 = -temp1 + c2.center.x;
        double y2 = sqrt(pow(r2, 2) - pow(x2 - c2.center.x, 2)) + c2.center.y;

        Line tangentLine1 = new Line(new Point(x1, y1), new Point(x2, y2));

        x1 = temp1 + c1.center.x;
        y1 = -sqrt(pow(r2, 2) - pow(x1 - c1.center.x, 2)) + c1.center.y;

        x2 = temp1 + c2.center.x;
        y2 = -sqrt(pow(r2, 2) - pow(x2 - c2.center.x, 2)) + c2.center.y;

        Line tangentLine2 = new Line(new Point(x1, y1), new Point(x2, y2));

        if (cir1arc.goingCW(new Position(new Point(0, 0), PI/2)) == cir2arc.goingCW(p2)) {
            // TAKE SHORTCUT
        }

        return new ArrayList<>();
    }

    private Arc getShorterArc(Circle c, Point p1, Point p2) {
        Arc arc1 = new Arc(c, p1, p2);
        Arc arc2 = new Arc(c, p2, p1);
        return arc1.getArcLength() > arc2.getArcLength() ? arc2 : arc1;
    }
}