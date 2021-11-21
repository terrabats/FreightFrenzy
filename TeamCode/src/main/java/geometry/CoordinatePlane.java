package geometry;

import java.util.ArrayList;

import static java.lang.Math.*;

public class CoordinatePlane {
    double curOrientRad = 0.0;
    Point origin = new Point(0, 0);

    private final ArrayList<GeometryObject> objects = new ArrayList<>();

    public void add(GeometryObject o) {
        objects.add(o);
    }

    public void rotate(double ang, AngleType angType) {
        curOrientRad += getAngRad(ang, angType);
    }

    public void setOrientation(double ang, AngleType angType) {
        curOrientRad = getAngRad(ang, angType);
    }

    public double getAngRad(double ang, AngleType angType) {
        return ang * (angType == AngleType.DEGREES ? (PI/180) : 1);
    }

    // Implements getObjects method – uses blank line (just need it for the type of the variable)
    public ArrayList<Line> getLines() {
        return getObjects(Line.class);
    }

    // Implements getObjects method – uses blank rect (just need it for the type of the variable)
    public ArrayList<Rect> getRects() {
        return getObjects(Rect.class);
    }

    public ArrayList<Vector> getVectors() { return getObjects(Vector.class); }

    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> getObjects(Class<T> type) {
        ArrayList<T> ret = new ArrayList<>();
        for (GeometryObject o : getRotatedObjects()) {
            if (o.getClass() == type) {
                ret.add((T) o);
            }
        }
        return ret;
    }

    private ArrayList<GeometryObject> getRotatedObjects() {
        ArrayList<GeometryObject> ret = new ArrayList<>();
        for (GeometryObject a : objects) {
            ret.add(a.getRotated(curOrientRad, origin));
        }
        return ret;
    }
}