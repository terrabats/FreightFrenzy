package autoutil;

import java.util.*;

import autoutil.paths.PathSegment;
import autoutil.reactors.TankReactor;
import geometry.AngleType;
import geometry.Pose;

import static global.General.*;
import static java.lang.Math.*;

public class MovementExecutor {

    // Uses the desired motion predictor and reactor to move the robot along the desired path
    // There should be one static executor
    // The start heading is counterclockwise (PI/2 is upward/forward)
    // every other heading is clockwise (relative to start angle)

    public ArrayList<ArrayList<Pose>> paths = new ArrayList<>();

    protected ArrayList<Generator> generators = new ArrayList<>();
    private final TankReactor reactor = new TankReactor();

    private final double startH;

    protected boolean moveRunning = false;

    public int curPath = 0;
    public int curPose = 0;

    //region PUBLIC FUNCTIONS

    public MovementExecutor(double startX, double startY, double startH, AngleType angleType) {
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
    }

    public void resumeMove() { moveRunning = true; }

    public void pauseMove() { moveRunning = false; }

//    public void update() { updateMovement(); }

    public boolean finishedMove() { return curPath == paths.size(); }

    //endregion

    //region BACKGROUND FUNCTIONS

    public void updateMovement() {
        if (moveRunning && !finishedMove()) {
            updateCurPoint();
            if (curPose > paths.get(curPath).size()) {
                curPath++;
                curPose = 0;
                pauseMove();
                bot.tankDrive.move(0, 0);
                return;
//                if (curPath == paths.size()) {
//                    return;
//                }
            }
            if (paths.get(curPath).size() == curPose) {
                Pose nextPose = paths.get(curPath).get(curPose - 1);
                bot.tankDrive.move(
                        reactor.forwardPowSetpoint(nextPose.p.x, nextPose.p.y),
                        reactor.turnPow(nextPose.ang, startH)
                );
                log.show("Forward pow", reactor.forwardPowSetpoint(nextPose.p.x, nextPose.p.y));
//                log.show(reactor.turnPow(nextPose.ang, startH));
                log.show("Turn pow", reactor.turnPow(nextPose.ang, startH));
            } else {
                Pose nextPose = paths.get(curPath).get(curPose);
                bot.tankDrive.move(
                        reactor.forwardPowWaypoint(nextPose.p.x, nextPose.p.y),
                        reactor.turnPowWay(nextPose.p.x, nextPose.p.y, startH)
                );
                log.show("Forward pow", reactor.forwardPowSetpoint(nextPose.p.x, nextPose.p.y));
//                log.show(reactor.turnPow(nextPose.ang, startH));
                log.show("Turn pow", reactor.turnPowWay(nextPose.p.x, nextPose.p.y, startH));
            }
        } else {
            bot.tankDrive.move(0, 0);
        }
    }

    private void updateCurPoint() {
        for (int i = curPose; i < paths.get(curPath).size(); i++) {
            if (doneWithPoint(i)) curPose = i + 1;
        }
        if (curPose == paths.get(curPath).size() && doneWithSetpoint()) {
            curPose++;
        }
    }

    private boolean doneWithSetpoint() {
        Pose nextPose = paths.get(curPath).get(paths.get(curPath).size() - 1);
        return abs(reactor.turnPow(nextPose.ang, startH)) < 0.2
                && sqrt(pow(bot.odometry.curPos[0] - nextPose.p.x, 2)
                + pow(bot.odometry.curPos[1] - nextPose.p.y, 2)) < 2;
    }

    private boolean doneWithPoint(int i) {
        Pose nextPose = paths.get(curPath).get(i);
        return sqrt(pow(bot.odometry.curPos[0] - nextPose.p.x, 2)
                + pow(bot.odometry.curPos[1] - nextPose.p.y, 2)) < 4;
    }

    //endregion

}
