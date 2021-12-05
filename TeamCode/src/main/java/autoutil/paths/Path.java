package autoutil.paths;

import java.util.ArrayList;

public class Path {
    // Composed of multiple path segments
    // Has the information about where the robot should be at a given time or distance
    // Includes any other relevant information to execute the path

    private ArrayList<PathSegment> segments = new ArrayList<>();

    public void addSegs(ArrayList<PathSegment> p) { segments.addAll(p); }
}
