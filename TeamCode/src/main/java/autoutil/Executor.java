package autoutil;

import java.util.*;

import autoutil.paths.Path;
import autoutil.reactors.TankReactor;
import geometry.AngleType;

public class Executor {

    // Uses the desired motion predictor and reactor to move the robot along the desired path
    // There should be one static executor

    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Generator> generators = new ArrayList<>();
    private TankReactor reactor = new TankReactor();

    private double startPos;

    public Executor(double startPos) {
        generators.add(new Generator());
        this.startPos = startPos;
    }

    public void addWaypoint(double x, double y, double h, AngleType angleType) {
        h *= angleType == AngleType.DEGREES ? (Math.PI/180) : 1;
        generators.get(generators.size() - 1).moveTo(x, y, h - startPos);
    }

    public void addSetpoint(double x, double y, double h, AngleType angleType) {
        addWaypoint(x, y, h, angleType);
        generators.add(new Generator());
        addWaypoint(x, y, h, angleType);
    }

    public void complete() {
        for (Generator g : generators) { paths.add(g.done()); }
        if (generators.get(generators.size() - 1).empty()) {
            paths.remove(paths.size() - 1);
        }
    }

}
