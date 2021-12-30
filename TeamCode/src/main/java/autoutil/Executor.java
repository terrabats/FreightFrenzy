package autoutil;

import java.util.*;

import autoutil.paths.PathSegment;
import autoutil.reactors.TankReactor;
import geometry.AngleType;
import geometry.Point;
import geometry.Pose;

import static global.General.*;
import static java.lang.Math.*;

public class Executor {

    // Uses the desired motion predictor and reactor to move the robot along the desired path
    // There should be one static executor
    // The start heading is counterclockwise (PI/2 is upward/forward)
    // every other heading is clockwise (relative to start angle)

    public ArrayList<ArrayList<Pose>> paths = new ArrayList<>();

    private ArrayList<Generator> generators = new ArrayList<>();
    private TankReactor reactor = new TankReactor();

    private final double startH;

    private boolean running = false;

    private int curPath = 0;
    private int curPose = 0;

    //region PUBLIC FUNCTIONS

    public Executor(double startX, double startY, double startH, AngleType angleType) {
        generators.add(new Generator());
        this.startH = startH * (angleType == AngleType.DEGREES ? (Math.PI/180) : 1);
        addWaypoint(startX, startY, 0, AngleType.RADIANS);
    }

    public void addWaypoint(double x, double y, double h, AngleType angleType) {
        h *= angleType == AngleType.DEGREES ? (Math.PI/180) : 1;
        generators.get(generators.size() - 1).moveTo(x, y, startH - h);
    }

    public void addSetpoint(double x, double y, double h, AngleType angleType) {
        addWaypoint(x, y, h, angleType);
        generators.add(new Generator());
        addWaypoint(x, y, h, angleType);
    }

    public void complete() {
        for (Generator g : generators) {
            ArrayList<Pose> poses = new ArrayList<>();
            ArrayList<PathSegment> pss = g.done().segments;
            for (PathSegment ps : pss) {
                poses.addAll(ps.points);
            }
            paths.add(poses);
        }
        if (generators.get(generators.size() - 1).empty()) {
            paths.remove(paths.size() - 1);
        }
        resume();
    }

    public void resume() { running = true; }

    public void pause() { running = false; }

    public void update() { updateMovement(); }

    public boolean finished() { return curPath == paths.size(); }

    //endregion

    //region BACKGROUND FUNCTIONS

    private void updateMovement() {
        if (running) {
            while (doneWithCurPoint()) {
                curPose++;
                if (paths.get(curPath).size() == curPose) {
                    break;
                } else if (paths.get(curPath).size() < curPose) {
                    curPath++;
                    curPose = 0;
                    if (curPath == paths.size()) {
                        bot.tankDrive.move(0, 0);
                        running = false;
                        return;
                    }
                }
            }
            if (paths.get(curPath).size() == curPose) {
                Pose nextPose = paths.get(curPath).get(curPose - 1);
                bot.tankDrive.move(
                        reactor.forwardPowSetpoint(nextPose.p.x, nextPose.p.y),
                        reactor.turnPow(nextPose.ang)
                );
            } else {
                Pose nextPose = paths.get(curPath).get(curPose);
                bot.tankDrive.move(
                        reactor.forwardPowWaypoint(nextPose.p.x, nextPose.p.y),
                        reactor.turnPow(nextPose.ang)
                );
            }
        } else {
            bot.tankDrive.move(0, 0);
        }
    }

    private boolean doneWithCurPoint() {
        Point nextPose = paths.get(curPath).get(curPose).p;
        return sqrt(pow(bot.odometry.curPos[0] - nextPose.x, 2)
                + pow(bot.odometry.curPos[1] - nextPose.y, 2)) < 0.05; // TODO: TUNE CONSTANT
    }

    //endregion

}
