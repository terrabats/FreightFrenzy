package autoutil.paths;

import java.util.ArrayList;

import geometry.Position;

public class PathSegment {
    // A unit of a path, not just a geometric object
    // Classes that extend Path segment may contain geometric objects
    protected ArrayList<Position> points = new ArrayList<>();

    public void generatePoints() {}
}
