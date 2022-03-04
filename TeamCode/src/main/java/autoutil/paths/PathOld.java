package autoutil.paths;

import java.util.ArrayList;

/**
 * Stores the full path of the robot
 */
public class PathOld {
    // Composed of multiple path segments
    // Has the information about where the robot should be at a given time or distance
    // Includes any other relevant information to execute the path

    /**
     * Stores all of the path segments
     */
    public final ArrayList<PathSegment> segments = new ArrayList<>();

    public void addSegments(ArrayList<PathSegment> p) { segments.addAll(p); }
    public void addSegment(PathSegment p) { segments.add(p); }

    public ArrayList<PathSegment> getSegments(){return segments;}
}
