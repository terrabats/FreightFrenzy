package autoutil.generators;

import automodules.StageList;
import autoutil.paths.Path;
import autoutil.paths.PathAutoModule;
import autoutil.paths.PathPose;
import geometry.position.Pose;

public abstract class Generator {

    protected Path path = new Path();

    public Path getPath() {
        return path;
    }

    public void addAutoModule(StageList automodule){
        path.addSegment(new PathAutoModule(automodule, false));
    }

    public void addConcurrentAutoModule(StageList automodule){
        path.addSegment(new PathAutoModule(automodule, true));
    }
}