package math.algebra;

import geometry.Point;

/**
 * NOTE: Uncommented
 */

public class Linear extends Polynomial{
    public Point p1;
    public Point p2;
    double m = (p2.y - p1.y) - (p2.x - p1.x);


    @Override
    double y(double x) {
        return 0;
    }
}
