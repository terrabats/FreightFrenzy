package geometry.position;

import static java.lang.Math.atan2;
import static java.lang.Math.*;

public class Vector2 {
    private double x;
    private double y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2 rotate(double phi){
        double newX = x * cos(phi) - y * sin(phi);
        double newY = x * sin(phi) + y * cos(phi);
        x = newX;
        y = newY;
        return copy();
    }

    public Vector2 add(Vector2 toAdd){
        x += toAdd.x;
        y += toAdd.y;
        return copy();
    }

    public Vector2 copy(){
        return new Vector2(x,y);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
}
