package geometry;
import geometry.circles.AngleType;
import geometry.position.Pose;

import static java.lang.Math.*;

/**
 * NOTE: Uncommented
 */

public abstract class GeometryObject {
    public GeometryObject getRelativeTo(Pose origin) {
        return this;
    }
    protected double boundAngleTo2Pi(double ang){
        return ang % (2*PI);
    }
    protected double radToDeg(double rads){return Math.toDegrees(rads);}
    protected double degToRad(double degs){return Math.toRadians(degs);}
    protected double toRad(double in, AngleType type){ return type.equals(AngleType.RADIANS) ? in : degToRad(in); }
    protected double toDeg(double in, AngleType type){ return type.equals(AngleType.DEGREES) ? in : radToDeg(in); }
}
