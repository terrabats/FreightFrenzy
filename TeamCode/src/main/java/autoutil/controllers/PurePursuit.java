package autoutil.controllers;

import autoutil.paths.PathLine;
import autoutil.paths.PathLine2;
import autoutil.paths.PathSegment2;
import geometry.polygons.Triangle;
import geometry.position.Line;
import geometry.position.Point;
import geometry.position.Pose;
import geometry.position.Vector;
import geometry.position.Vector2;
import math.misc.Exponential;
import math.misc.Logistic;
import math.polynomial.Quadratic;
import math.trigonmetry.Trigonometry;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.fault;
import static global.General.log;
import static global.General.telemetry;

public class PurePursuit extends Controller2D {
    public double maxRadius = 15;
    public double currentRadius = 5;

    private final Exponential radiusLogistic;

    public PurePursuit(){
        xController = new BangBang(0.5, 100);
        yController = new BangBang(0.5, 100);
        radiusLogistic = new Exponential(1, 1, (1.0/maxRadius));
    }

    @Override
    public void update(Pose pose, PathSegment2 pathSegment) {
        Line currentLine = new Line(new Point(0,0), new Point(0,0));
        if(pathSegment instanceof PathLine2){
            currentLine = ((PathLine2) pathSegment).getLine();
        }else{
            fault.check("Use Line Generator for Pure Pursuit", Expectation.UNEXPECTED, Magnitude.CATASTROPHIC);
        }
        Point targetPos = getTargetPos(pose.p, currentLine);
        xController.setTarget(targetPos.x);
        yController.setTarget(targetPos.y);
        xController.update(pose, pathSegment);
        yController.update(pose, pathSegment);
        updateRadius(currentLine.getlength());
        Vector2 powerVector = new Vector2(xController.getOutput(), yController.getOutput());
        powerVector.rotate(pose.ang);
        setOutputX(powerVector.getX());
        setOutputY(powerVector.getY());
    }

    public void updateRadius(double dis){
//        currentRadius = radiusLogistic.f(dis);
    }

    public Point getTargetPos(Point currentPos, Line currentLine){
        return currentLine.getAt(solve(currentPos, currentLine));
    }

    public double solve(Point currentPos, Line currentLine){
        double dx = currentLine.p1.x-currentPos.x;
        double dy = currentLine.p1.y-currentPos.y;
        double a = Math.pow(Trigonometry.pythag(currentLine.mx, currentLine.my),2);
        double b = 2*((dx*currentLine.mx)+(dy*currentLine.my));
        double c = Math.pow(Trigonometry.pythag(dx, dy),2)-Math.pow(currentRadius,2);
        Quadratic quadratic = new Quadratic(a, b, c);
        double ans = quadratic.roots()[0];
        if(!Double.isNaN(ans)) {
            if(ans > 0.99){
                isAtTarget = true;
                return 1;
            }else {
                return ans;
            }
        }else{
            return 1;
        }
    }

}
