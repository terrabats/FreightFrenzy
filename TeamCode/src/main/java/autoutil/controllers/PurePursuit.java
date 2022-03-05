package autoutil.controllers;

import geometry.position.Line;
import geometry.position.Point;

public class PurePursuit extends Controller2D {


    @Override
    public void update() {
        updateProfilers();

    }

    public BangBang control = new BangBang(0.3, 0.3);

    public double maxRadius = 15;
    public double currentRadius = 5;
    public boolean isDone = false;

    public void updateRadius(double dis){
        currentRadius = maxRadius*(1-Math.exp(-(1/maxRadius)*(dis)));
    }

    public double solve(double[] currentPose, Line currentLine){
        double x1 = currentLine.p1.x;
        double y1 = currentLine.p1.y;
        double mx = currentLine.mx;
        double my = currentLine.my;
        double xr = currentPose[0];
        double yr = currentPose[1];
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

    public Point getTargetPos(double[] currentPose, Line currentLine){
        return currentLine.getAt(solve(currentPose, currentLine));
    }

}
