package geometry;

public abstract class GeometryObject {
    public GeometryObject getRelativeTo(Position origin) {
        return this;
    }
}
