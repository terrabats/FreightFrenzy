package geometry.polygons;

import geometry.position.Point;

/**
 * NOTE: Uncommented
 */

public class Triangle extends Polygon {
    Point p1;
    Point p2;
    Point p3;

    public Triangle(Point p1, Point p2, Point p3){
        super(p1, p2, p3);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    // TODO FIX
    // Remove this from here put into trig
    // Make this class have other methods
    //Calculates law of cosines for C knowing a,b and the angle between them
    public static double lawOfCosinesC(double a, double b, double rad){
        return Math.sqrt(a*a+b*b-(2*a*b*Math.cos(rad)));
    }
    //Calculates law of sines for the angle knowing a,b and the angle opposite to b
    public static double lawOfSinesAngle(double a, double b, double bAng){
        return Math.asin(a*Math.sin(bAng)*(1/b));
    }
    //Calculates the pythagorean theorem for the c knowing a and b
    public static double pythagoreanC(double a, double b){
        return Math.sqrt(a*a + b*b);
    }
}
