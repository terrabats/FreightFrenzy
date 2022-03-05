package autoutil.controllers;

import geometry.position.Line;
import geometry.position.Point;
import geometry.position.Pose;
import geometry.position.Vector;
import geometry.position.Vector2;
import math.misc.Exponential;
import math.misc.Logistic;

public class PurePursuit extends Controller2D {
    public double maxRadius = 15;
    public double currentRadius = 5;
    public boolean isDone = false;

    private final Exponential radiusLogistic;

    public PurePursuit(){
        xController = new BangBang(0.3, 0.3);
        yController = new BangBang(0.3, 0.3);
        radiusLogistic = new Exponential(1, 1, (1.0/maxRadius));
    }

    @Override
    public void update(double heading) {
        // TODO FIX
        // Set target pos
//        double[] localTarget = getTargetPos(currentPos, currentLine);
//        updateRadius(currentLine.getDis());
        Vector2 powerVector = new Vector2(xController.getOutput(), yController.getOutput());
        powerVector.rotate(heading);
        setOutputX(powerVector.getX());
        setOutputY(powerVector.getY());
    }

    public void updateRadius(double dis){
        currentRadius = radiusLogistic.f(dis);
    }

    public double solve(Point currentPos, Line currentLine){
        double x1 = currentLine.p1.x;
        double y1 = currentLine.p1.y;
        double mx = currentLine.mx;
        double my = currentLine.my;
        double xr = currentPos.x;
        double yr = currentPos.y;
        double dx = x1-xr;
        double dy = y1-yr;
        double a = (mx*mx)+(my*my);
        double b = 2*((dx*mx)+(dy*my));
        double c = (dx*dx)+(dy*dy)-(currentRadius*currentRadius);
        double disc = (b * b) - (4 * a * c);
        double ans = (-1)*((b - Math.sqrt(disc)) / (2 * a));
        if(!Double.isNaN(ans)) {
            if(ans > 0.99){
                isDone = true;
                return 1;
            }else {
                return ans;
            }
        }else{
            return 1;
        }
    }

    public Point getTargetPos(Point currentPos, Line currentLine){
        return currentLine.getAt(solve(currentPos, currentLine));
    }

}
