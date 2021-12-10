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

    public void addNewPos(double x, double y, double ang) {
        addNewPos(new Pose(new Point(x, y), ang));
    }

    private void addNewPos(Pose p) {
        p.translate(poses.get(poses.size() - 1).p.x, poses.get(poses.size() - 1).p.y);
        p.rotate(p.ang);
        addNewAbsPos(p);
    }

    public void addNewAbsPos(double x, double y, double ang) {
        addNewAbsPos(new Pose(new Point(x, y), ang));
    }

    private void addNewAbsPos(Pose p) { poses.add(p); }

    public Path done() {
        Path path = new Path();
        for (int i = 0; i < poses.size() - 1; i++) {
            path.addSegs(generateSeg(poses.get(i), poses.get(i + 1)));
        }
        for (PathSegment p : path.segments) {
            p.generatePoints();
        }
        return path;
    }

    private ArrayList<PathSegment> generateSeg(Pose p1, Pose p2) {
        ArrayList<PathSegment> ret = generateRelSeg(p1, p2);
//        p1.rotate(-PI/2);
        for (PathSegment ps : ret) ps.unshift(p1);
        return ret;
    }

    private ArrayList<PathSegment> generateRelSeg(Pose p1, Pose p2) {
        ArrayList<PathSegment> ret = new ArrayList<>();

        p1.rotate(-PI/2);
        p2 = p2.getRelativeTo(p1);
        p1.rotate(PI/2);

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
        double r = (-b - rtDiscriminant)/(2 * a);
        double r2 = (-b + rtDiscriminant)/(2 * a);

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
//        System.out.println(cir1arc.arc.getPositionFromTheta(cir1arc.arc.arcSt));
//        System.out.println(cir1arc.arc.getPositionFromTheta(cir1arc.arc.arcEnd));
//        System.out.println(cir1arc.arc.arcSt + " " + cir1arc.arc.arcEnd);
        Arc cir2arc = getShorterArc(c2, p2.p, pointOfIntersection);
//        System.out.println(cir2arc.arc.getPositionFromTheta(cir2arc.arc.arcSt));
//        System.out.println(cir2arc.arc.getPositionFromTheta(cir2arc.arc.arcEnd));
//        System.out.println(cir2arc.arc.arcSt + " " + cir2arc.arc.arcEnd);


        double mt = (c2.center.y - c1.center.y)/(c2.center.x - c1.center.x);

        double temp1 = (r2 * mt)/sqrt(pow(mt, 2) + 1);

        double x1 = -temp1 + c1.center.x;
        double y1 = sqrt(pow(r2, 2) - pow(x1 - c1.center.x, 2)) + c1.center.y;


        double x2 = -temp1 + c2.center.x;
        double y2 = sqrt(pow(r2, 2) - pow(x2 - c2.center.x, 2)) + c2.center.y;

        PathLine tangentLine1 = new PathLine(new Point(x1, y1), new Point(x2, y2));

        x1 = temp1 + c1.center.x;
        y1 = -sqrt(pow(r2, 2) - pow(x1 - c1.center.x, 2)) + c1.center.y;

        x2 = temp1 + c2.center.x;
        y2 = -sqrt(pow(r2, 2) - pow(x2 - c2.center.x, 2)) + c2.center.y;

        PathLine tangentLine2 = new PathLine(new Point(x1, y1), new Point(x2, y2));

        if (cir1arc.goingCW(new Pose(new Point(0, 0), PI/2)) == cir2arc.goingCW(p2)) {
            // TAKE SHORTCUT
            System.out.println("GOING THROUGH SHORTCUT");
            ret.add(cir1arc);
            ret.add(cir2arc);
            ret.add(tangentLine1);
            ret.add(tangentLine2);
        } else {
            System.out.println("NOT USING SHORTCUT");
            ret.add(cir1arc);
            ret.add(cir2arc);
        }

        for (PathSegment pathSegment : ret) {
            pathSegment.flip(flipX, flipY);
        }

        return ret;
    }

    private Arc getShorterArc(Circle c, Point p1, Point p2) {
        Arc arc1 = new Arc(c, p1, p2);
        Arc arc2 = new Arc(c, p2, p1);
        return arc1.getArcLength() > arc2.getArcLength() ? arc2 : arc1;
    }
}
