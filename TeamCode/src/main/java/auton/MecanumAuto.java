package auton;

import java.util.ArrayList;

import automodules.StageList;
import autoutil.executors.MecanumExecutor;
import autoutil.generators.Generator;
import autoutil.generators.LineGenerator;
import autoutil.generators.PoseGenerator;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import autoutil.reactors.mecanum.MecanumPurePursuitReactor;
import autoutil.reactors.mecanum.MecanumReactor;
import geometry.position.Point;
import geometry.position.Pose;
import util.codeseg.ParameterCodeSeg;

public abstract class MecanumAuto extends Auto{
    protected MecanumExecutor executor;
    protected Pose lastPose = new Pose(new Point(0,0),0);

    protected ArrayList<AutoSegment<? extends MecanumReactor, ? extends Generator>> segments = new ArrayList<>();

    public abstract void define();

    @Override
    public void initAuto() {
        define();
    }

    @Override
    public void runAuto() {
        for(AutoSegment<? extends MecanumReactor, ? extends Generator> autoSegment: segments){
            executor = new MecanumExecutor(this);
            executor.setReactor(autoSegment.reactor);
            executor.setPath(autoSegment.generator.getPath());
            executor.followPath();
        }
    }

    public void addSetpoint(double x, double y, double h){
        addSegment(new MecanumPIDReactor(), new PoseGenerator(), g -> g.addPose(x, y, h));
        lastPose = new Pose(new Point(x,y),h);
    }

    public void addWaypoint(double x, double y, double h){
        addSegment(new MecanumPurePursuitReactor(), new LineGenerator(lastPose), g -> g.addLine(x, y, h));
        lastPose = new Pose(new Point(x,y),h);
    }

    public void addAutoModule(StageList autoModule){
        getLastSegment().generator.addAutoModule(autoModule);
    }

    public void addConcurrentAutoModule(StageList autoModule){
        getLastSegment().generator.addConcurrentAutoModule(autoModule);
    }

    private <R extends MecanumReactor, G extends Generator> void addSegment(R reactor, G generator, ParameterCodeSeg<G> add){
        if(isFirstSegment()){
            add.run(generator);
            segments.add(new AutoSegment<>(reactor,generator));
        }else {
            if ((getLastSegment().generator.getClass() == generator.getClass())) {
                add.run((G) getLastSegment().generator);
            } else {
                add.run(generator);
                segments.add(new AutoSegment<>(reactor, generator));
            }
        }
    }

    private AutoSegment<? extends MecanumReactor, ? extends Generator> getLastSegment(){
        if(isFirstSegment()){
            segments.add(new AutoSegment<>(new MecanumPIDReactor(), new PoseGenerator()));
        }
        return segments.get(segments.size() - 1);
    }

    public boolean isFirstSegment(){
        return segments.isEmpty();
    }
}
