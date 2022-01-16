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
    // Make this class have other methods
}
