package autoutil.paths;

import java.util.ArrayList;

public class Path {

    public final ArrayList<PathSegment2> segments = new ArrayList<>();

    public void addSegments(ArrayList<PathSegment2> p) { segments.addAll(p); }
    public void addSegment(PathSegment2 p) { segments.add(p); }

    public ArrayList<PathSegment2> getSegments(){return segments;}

    public PathSegment2 getLast(){
        return segments.get(segments.size()-1);
    }
}