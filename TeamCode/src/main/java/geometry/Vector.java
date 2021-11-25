package geometry;

import static java.lang.Math.*;

public class Vector extends GeometryObject {
    //x and y coords of the tip of vector, theta is angle measured from the right horizontal
    public Point p;
    public double theta;

    //Constructor to create vect using coords
    public Vector(double x1, double y1){
        p = new Point(x1, y1);
        theta = atan2(p.y, p.x);
    }
    //Constructor to create vect using angle and length
    public Vector(double angle, double len, AngleType unit) {
        if (unit.equals(AngleType.DEGREES)) {
            angle *= PI/180;
        }
        p = new Point(len * cos(angle), len * sin(angle));
    }
    //Gets a rotated vector of the current vector angle - positive is anticlockwise

    @Override
    public GeometryObject getRotated(double angRad, Point origin) {
        double ang = theta + angRad;
        double radius = getLen();
        return new Vector(cos(ang) * radius, sin(ang) * radius);
    }

    //Gets x
    public double getX() {
        return p.x;
    }
    //Gets y
    public double getY() {
        return p.y;
    }
    //Gets length
    public double getLen() {
        return sqrt(pow(p.x, 2) + pow(p.y, 2));
    }
    //Gets angle
    public double getAngle(AngleType type) {
        if (type == AngleType.RADIANS) {
            return atan2(p.y,p.x);
        } else {
            return atan2(p.y,p.x) * 180/PI;
        }
    }
    //Sets x and y coords
    public void setXY(double x1, double y1){
        p.x = x1;
        p.y = y1;
        theta = atan2(p.y, p.x);
    }
    //Creates a string representation
    public String toString() {
        return "x: " + this.getX() + ", y: " + this.getY() + ", angle: " + this.getAngle(AngleType.DEGREES) + ", length: " + this.getLen();
    }


    public Vector add(Vector in){
        return new Vector(p.x+in.p.x, p.y+in.p.y);
    }

    public Vector subtract(Vector in){
        return new Vector(p.x-in.p.x, p.y-in.p.y);
    }

}