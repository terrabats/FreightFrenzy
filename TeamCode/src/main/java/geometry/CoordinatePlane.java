package geometry;

import java.util.ArrayList;


public class CoordinatePlane {
    double curOrientRad = 0.0;
    public ArrayList<GeometryObject> objects = new ArrayList<>();

    public void addObject(GeometryObject o) {
        objects.add(o);
    }

    public void rotate(double ang, AngleType angType) {
        curOrientRad += getAngRad(ang, angType);
    }

    public void setOrientation(double ang, AngleType angType) {
        curOrientRad = getAngRad(ang, angType);
    }

    public double getAngRad(double ang, AngleType angType) {
        return ang * (angType == AngleType.DEGREES ? (180/Math.PI) : 1);
    }

    // Implements getObjects method – uses blank line (just need it for the type of the variable)
    public ArrayList<Line> getLines() {
        return getObjects(new Line(0,0,0,0));
    }

    // Implements getObjects method – uses blank rect (just need it for the type of the variable)
    public ArrayList<Rect> getRects() {
        return getObjects(new Rect(0,0,0,0));
    }

    public ArrayList<Vector> getVectors() { return getObjects(new Vector(0, 0, AngleType.DEGREES)); }

    public <T> ArrayList<T> getObjects(T exObject) {
        ArrayList<T> ret = new ArrayList<>();
        for (GeometryObject o : objects) {
            if ((exObject.getClass()).isInstance(o)) ret.add((T) o);
        }
        return ret;
    }
}