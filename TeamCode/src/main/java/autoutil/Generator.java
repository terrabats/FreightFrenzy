package autoutil;

import java.util.ArrayList;

import autoutil.paths.Arc;
import autoutil.paths.Path;
import autoutil.paths.PathSegment;
import autoutil.paths.PathLine;
import geometry.Circle;
import geometry.Point;
import geometry.Pose;

import static java.lang.Math.*;

public class Generator {
    // Generates a path for the robot to follow
    // based on a array of points or conditions
    // there should be one static generator
    // should be able to create multiple paths if a decision is needed

    private final ArrayList<Pose> poses = new ArrayList<>();

//    public void addNewPos(double x, double y, double ang) {
//        addNewPos(new Pose(new Point(x, y), ang));
//    }
//
//    private void addNewPos(Pose p) {
//        p.translate(poses.get(poses.size() - 1).p.x, poses.get(poses.size() - 1).p.y);
//        p.rotate(p.ang);
//        moveTo(p);
//    }

    public void moveTo(double x, double y, double ang) {
        moveTo(new Pose(new Point(x, y), ang));
    }

    private void moveTo(Pose p) { poses.add(p); }

    public Path done() {
        Path path = new Path();
        for (int i = 0; i < poses.size() - 1; i++) {
            path.addSegs(generateSeg(poses.get(i), poses.get(i + 1)));
        }
        return path;
    }

    private ArrayList<PathSegment> generateSeg(Pose p1, Pose p2) {
        ArrayList<PathSegment> ret = generateRelSeg(p1, p2);
        p1.rotate(-PI/2);
        if (ret.size() == 3) {
            // Arcs + tangent line
            ret.get(0).generatePoints(new Pose(new Point(0, 0), PI/2));
            ret.get(1).generatePoints(ret.get(0).points.get(ret.get(0).points.size() - 1)); // TODO: FIX THIS
            ret.get(2).generatePoints(ret.get(1).points.get(ret.get(1).points.size() - 1));
        } else {
            // Arcs
            ret.get(0).generatePoints(new Pose(new Point(0, 0), PI/2));
            ret.get(1).generatePoints(ret.get(0).points.get(ret.get(0).points.size() - 1));
        }
        p1.rotate(PI/2);
        for (PathSegment p : ret) {
            p.changePointsForPath(p1);
            for (int i = 0; i < p.points.size(); i++) {
                if (p.points.get(i).ang > Math.PI) {
                    p.points.get(i).ang -= 2 * Math.PI;
                } else if (p.points.get(i).ang < -Math.PI) {
                    p.points.get(i).ang += 2 * Math.PI;
                }
            }
        }
        return ret;
    }

    private ArrayList<PathSegment> generateRelSeg(Pose p1, Pose p2_orig) {
        ArrayList<PathSegment> ret = new ArrayList<>();

        p1.rotate(-PI/2);
        Pose p2 = p2_orig.getRelativeTo(p1);
        p1.rotate(PI/2);

        p2.ang %= 2 * PI;
        if (p2.ang > PI) { p2.ang -= 2 * PI; }

        p2.ang = abs(p2.ang) != PI/2 ? (signum(p2.p.x * p2.p.y) * p2.ang) : p2.ang;

        if (p2.ang == 0 && signum(p2.p.x * p2.p.y) == -1) { p2.ang = PI; }

        boolean isNegPiBy2 = p2.ang == -PI/2;
        if (isNegPiBy2) p2.ang *= -1;

        boolean flipX = p2.p.x < 0;
        boolean flipY = p2.p.y < 0;

        p2.p.x = Math.abs(p2.p.x);
        p2.p.y = Math.abs(p2.p.y);

        double p = p2.ang - PI/2;
        double dx = p2.p.x;
        double dy = p2.p.y;
        double d = sqrt(pow(dx, 2) + pow(dy, 2));
        double s = sin(p);
        double c = cos(p) - 1;
        double a = 2 + c;
        double b = -(c * dx + s * dy);
        double m = -pow(d, 2)/2;
        double rtDiscriminant = sqrt(pow(b, 2) - 4 * a * m);
        double r, r2;
        r = (-b - rtDiscriminant) / (2 * a);
        r2 = (-b + rtDiscriminant) / (2 * a);

        Circle c1;
        Circle c2;

        if (abs(r) < abs(r2)) {
            c1 = new Circle(new Point(r, 0), abs(r));
            c2 = new Circle(new Point(dx + r * cos(p), dy + r * sin(p)), abs(r));
        } else {
            c1 = new Circle(new Point(r2, 0), abs(r2));
            c2 = new Circle(new Point(dx + r2 * cos(p), dy + r2 * sin(p)), abs(r2));
        }

        PathLine betweenCenters = new PathLine(c1.center, c2.center);
        Point pointOfIntersection = betweenCenters.getAt(0.5);

        Arc cir1arc = getShorterArc(c1, new Point(0, 0), pointOfIntersection);
        Arc cir2arc = getShorterArc(c2, p2.p, pointOfIntersection);

        if (isNegPiBy2) p2.ang *= -1;

        if (cir1arc.goingCW(new Pose(new Point(0, 0), PI/2)) == cir2arc.goingCW(p2)) {
            // TAKE SHORTCUT
            double mx = c2.center.x - c1.center.x;
            double my = c2.center.y - c1.center.y;

            if (mx != 0) {
                double mt = my / mx;

                double temp1 = (c1.r * mt) / sqrt(pow(mt, 2) + 1);

                double x1 = -temp1 + c1.center.x;
                double y1 = sqrt(pow(c1.r, 2) - pow(x1 - c1.center.x, 2)) + c1.center.y;

                double x2 = -temp1 + c2.center.x;
                double y2 = sqrt(pow(c1.r, 2) - pow(x2 - c2.center.x, 2)) + c2.center.y;

                PathLine tangentLine1 = new PathLine(new Point(x1, y1), new Point(x2, y2));

                Arc a11 = getShorterArc(c1, new Point(0, 0), new Point(x1, y1));
                Arc a12 = getShorterArc(c2, new Point(x2, y2), p2.p);
                double arcLensPath1 = a11.getArcLength() + a12.getArcLength();

                x1 = temp1 + c1.center.x;
                y1 = -sqrt(pow(c1.r, 2) - pow(x1 - c1.center.x, 2)) + c1.center.y;

                x2 = temp1 + c2.center.x;
                y2 = -sqrt(pow(c1.r, 2) - pow(x2 - c2.center.x, 2)) + c2.center.y;

                PathLine tangentLine2 = new PathLine(new Point(x1, y1), new Point(x2, y2));

                Arc a21 = getShorterArc(c1, new Point(0, 0), new Point(x1, y1));
                Arc a22 = getShorterArc(c2, new Point(x2, y2), p2.p);
                double arcLensPath2 = a21.getArcLength() + a22.getArcLength();

                if (arcLensPath1 < arcLensPath2) {
                    ret.add(a11);
                    ret.add(tangentLine1);
                    ret.add(a12);
                } else {
                    ret.add(a21);
                    ret.add(tangentLine2);
                    ret.add(a22);
                }
            } else {
                ret.add(new PathLine(new Point(0, 0), new Point(0, c2.center.y)));
                ret.add(getShorterArc(c2, new Point(0, c2.center.y), p2.p));
            }
        } else {
            ret.add(cir1arc);
            ret.add(cir2arc);
        }

        for (PathSegment pathSegment : ret) {
            pathSegment.flip(flipX, flipY);
        }

        return ret;
    }

    private Arc getShorterArc(Circle c, Point p1, Point p2) {
        Arc arc1 = new Arc(c, p1, p2, true);
        Arc arc2 = new Arc(c, p2, p1, false);
        return arc1.getArcLength() > arc2.getArcLength() ? arc2 : arc1;
    }

    public boolean empty() { return poses.isEmpty(); }
}
