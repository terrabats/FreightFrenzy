package auton;

import java.util.ArrayList;

import automodules.StageList;
import autoutil.executors.Executor;
import autoutil.executors.ExecutorNew;
import autoutil.executors.MecanumExecutor;
import autoutil.generators.Generator;
import autoutil.generators.LineGenerator;
import autoutil.generators.PoseGenerator;
import autoutil.reactors.Reactor;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import autoutil.reactors.mecanum.MecanumPurePursuitReactor;
import autoutil.reactors.mecanum.MecanumReactor;
import geometry.position.Point;
import geometry.position.Pose;
import util.codeseg.ParameterCodeSeg;

public abstract class AutoFramework extends Auto{

    public abstract ExecutorNew getExecutor();

    protected ExecutorNew executor;

    protected Pose lastPose = new Pose(new Point(0,0),0);

    protected ArrayList<AutoSegment<? extends Reactor, ? extends Generator>> segments = new ArrayList<>();

    public abstract void define();
    public abstract Reactor getSetpointReactor();
    public abstract Reactor getWaypointReactor();
    public abstract Generator getSetpointGenerator();
    public abstract Generator getWaypointGenerator();

    @Override
    public void initAuto() {
        define();
    }

    @Override
    public void runAuto() {
        for(AutoSegment<? extends Reactor, ? extends Generator> autoSegment: segments){
            executor = getExecutor();
            executor.setReactor(autoSegment.getReactor());
            executor.setPath(autoSegment.getGenerator().getPath());
            executor.followPath();
        }
    }

    public void addSetpoint(double x, double y, double h){
        addSegment(getSetpointReactor(), getSetpointGenerator(), x, y, h);
    }

    public void addWaypoint(double x, double y, double h){
        addSegment(getWaypointReactor(), getWaypointGenerator(), x, y, h);
    }

    public void addAutoModule(StageList autoModule){
        getLastSegment().getGenerator().addAutoModule(autoModule);
    }

    public void addConcurrentAutoModule(StageList autoModule){
        getLastSegment().getGenerator().addConcurrentAutoModule(autoModule);
    }

    private <R extends Reactor, G extends Generator> void addSegment(R reactor, G generator, double x, double y, double h){
        if(isFirstSegment()){
            generator.add(x,y,h);
            segments.add(new AutoSegment<>(reactor,generator));
        }else {
            if ((getLastSegment().getGenerator().getClass() == generator.getClass())) {
                getLastSegment().getGenerator().add(x,y,h);
            } else {
                generator.add(x,y,h);
                segments.add(new AutoSegment<>(reactor, generator));
            }
        }
        lastPose = new Pose(new Point(x,y),h);
    }

    private AutoSegment<? extends Reactor, ? extends Generator> getLastSegment(){
        if(isFirstSegment()){
            segments.add(new AutoSegment<>(new MecanumPIDReactor(), new PoseGenerator()));
        }
        return segments.get(segments.size() - 1);
    }

    public boolean isFirstSegment(){
        return segments.isEmpty();
    }
}
