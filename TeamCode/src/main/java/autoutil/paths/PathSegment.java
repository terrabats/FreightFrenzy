package autoutil.paths;

import java.util.ArrayList;

import geometry.Pose;

public class PathSegment {
    // A unit of a path, not just a geometric object
    // Classes that extend Path segment may contain geometric objects
    public ArrayList<Pose> points = new ArrayList<>();

    public void generatePoints() {}

    public void unshift(Pose origin) {}
}
