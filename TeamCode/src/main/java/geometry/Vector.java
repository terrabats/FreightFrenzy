package geometry;

import static java.lang.Math.*;

public class Vector extends GeometryObject {
    //x and y coords of the tip of vector, theta is angle measured from the right horizontal
    public double x;
    public double y;
    public double theta;

    //Constructor to create vect using coords
    public Vector(double x1, double y1){
        x = x1;
        y = y1;
        theta = atan2(y, x);
    }
    //Constructor to create vect using angle and length
    public Vector(double angle, double len, AngleType unit) {
        if (unit.equals(AngleType.DEGREES)) {
            angle *= PI/180;
        }
        this.x = len * cos(angle);
        this.y = len * sin(angle);
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
        return this.x;
    }
    //Gets y
    public double getY() {
        return this.y;
    }
    //Gets length
    public double getLen() {
        return sqrt(pow(x, 2) + pow(y, 2));
    }
    //Gets angle
    public double getAngle(AngleType type) {
        if (type == AngleType.RADIANS) {
            return atan2(y,x);
        } else {
            return atan2(y,x) * 180/PI;
        }
    }
    //Sets x and y coords
    public void setXY(double x1, double y1){
        x = x1;
        y = y1;
        theta = atan2(y, x);
    }
    //Creates a string representation
    public String toString() {
        return "x: " + this.getX() + ", y: " + this.getY() + ", angle: " + this.getAngle(AngleType.DEGREES) + ", length: " + this.getLen();
    }


    public Vector add(Vector in){
        return new Vector(x+in.x, y+in.y);
    }

    public Vector subtract(Vector in){
        return new Vector(x-in.x, y-in.y);
    }

}