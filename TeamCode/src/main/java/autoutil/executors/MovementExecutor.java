package autoutil.executors;

import java.util.*;

import autoutil.generators.ArcGenerator;
import autoutil.paths.PathSegment;
import autoutil.reactors.TankReactor;
import geometry.circles.AngleType;
import geometry.position.Pose;

import static global.General.*;
import static java.lang.Math.*;

public class MovementExecutor {

    // Uses the desired motion predictor and reactor to move the robot along the desired path
    // There should be one static executor
    // The start heading is counterclockwise (PI/2 is upward/forward)
    // every other heading is clockwise (relative to start angle)

    public ArrayList<ArrayList<Pose>> paths = new ArrayList<>();

    protected ArrayList<ArcGenerator> arcGenerators = new ArrayList<>();
    private final TankReactor reactor = new TankReactor();

    private final double startH;

    protected boolean moveRunning = false;

    public int curPath = 0;
    public int curPose = 0;

    //region PUBLIC FUNCTIONS

    public MovementExecutor(double startX, double startY, double startH, AngleType angleType) {
        arcGenerators.add(new ArcGenerator());
        this.startH = startH * (angleType == AngleType.DEGREES ? (Math.PI/180) : 1);
        addWaypoint(startX, startY, 0, AngleType.RADIANS);
    }

    public void addWaypoint(double x, double y, double h, AngleType angleType) {
        h *= angleType == AngleType.DEGREES ? (Math.PI/180) : 1;
        arcGenerators.get(arcGenerators.size() - 1).moveTo(x, y, startH - h);
    }

    public void addSetpoint(double x, double y, double h, AngleType angleType) {
        addWaypoint(x, y, h, angleType);
        arcGenerators.add(new ArcGenerator());
        curPath++;
        addWaypoint(x, y, h, angleType);
    }

    public void complete() {
        for (ArcGenerator g : arcGenerators) {
            ArrayList<Pose> poses = new ArrayList<>();
            ArrayList<PathSegment> pss = g.done().segments;
            for (PathSegment ps : pss) {
                poses.addAll(ps.points);
            }
            paths.add(poses);
        }
        if (arcGenerators.get(arcGenerators.size() - 1).empty()) {
            paths.remove(paths.size() - 1);
        }
        curPath = 0;
    }

    public void resumeMove() { moveRunning = true; }

    public void pauseMove() { moveRunning = false; }

//    public void update() { updateMovement(); }

    public boolean finishedMove() { return curPath >= paths.size(); }

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
                double forwardPow = reactor.forwardPowSetpoint(nextPose.p.x, nextPose.p.y);
                if (abs(forwardPow) > 0.1) {
                    bot.tankDrive.move(
                        forwardPow,
                        reactor.turnPowWay(nextPose.p.x, nextPose.p.y, startH)
                    );
                } else {
                    bot.tankDrive.move(
                        forwardPow,
                        reactor.turnPow(nextPose.ang, startH, true)
                    );
                }
            } else {
                Pose nextPose = paths.get(curPath).get(curPose);
                bot.tankDrive.move(
                        reactor.forwardPowWaypoint(nextPose.p.x, nextPose.p.y),
                        reactor.turnPowWay(nextPose.p.x, nextPose.p.y, startH)
                );
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
        return abs(reactor.turnPow(nextPose.ang, startH, true)) < 0.2
                && sqrt(pow(bot.odometry.curPos[0] - nextPose.p.x, 2)
                + pow(bot.odometry.curPos[1] - nextPose.p.y, 2)) < 3;
    }

    private boolean doneWithPoint(int i) {
        Pose nextPose = paths.get(curPath).get(i);
        double dis = sqrt(pow(bot.odometry.curPos[0] - nextPose.p.x, 2)
                + pow(bot.odometry.curPos[1] - nextPose.p.y, 2));
        return (dis < 3);
    }

    //endregion

}
