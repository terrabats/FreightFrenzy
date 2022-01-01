package geometry;
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
}
