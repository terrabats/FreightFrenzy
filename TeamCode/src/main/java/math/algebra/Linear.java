package math.algebra;

import geometry.Point;

/**
 * NOTE: Uncommented
 */

public class Linear extends Polynomial{
    // Make these classes and add support for coefficents, derivatives, and parametric forms, and roots
    double y(double x) {
        return 0;
    }
    public Point p1;
    public Point p2;
    double m = (p2.y - p1.y) - (p2.x - p1.x);
    double derivative = m;
    

}
