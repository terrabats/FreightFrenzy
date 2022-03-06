package autoutil.paths;

import java.util.ArrayList;

import geometry.position.Point;
import geometry.position.Pose;

public abstract class PathSegment2{
    protected ArrayList<Pose> poses = new ArrayList<>();

    public ArrayList<Pose> getPoses(){
        return poses;
    }

    public Pose getLast(){
        return poses.get(poses.size()-1);
    }
}