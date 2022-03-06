package auton;

import java.util.ArrayList;

import automodules.StageList;
import autoutil.executors.MecanumExecutor;
import autoutil.generators.Generator;
import autoutil.generators.LineGenerator;
import autoutil.generators.PoseGenerator;
import autoutil.reactors.Reactor;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import autoutil.reactors.mecanum.MecanumPurePursuitReactor;
import autoutil.reactors.mecanum.MecanumReactor;
import geometry.position.Pose;
import util.codeseg.ParameterCodeSeg;

public abstract class MecanumAuto extends Auto{
    protected MecanumExecutor executor;

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
    }

    public void addWaypoint(double x, double y, double h){
        addSegment(new MecanumPurePursuitReactor(), new LineGenerator(getLastPose()), g -> g.addLine(x, y, h));
    }

    public void addAutoModule(StageList autoModule){
        getLast().generator.addAutoModule(autoModule);
    }

    public void addConcurrentAutoModule(StageList autoModule){
        getLast().generator.addConcurrentAutoModule(autoModule);
    }

    private <R extends MecanumReactor, G extends Generator> void addSegment(R reactor, G generator, ParameterCodeSeg<G> add){
        if (getLast().generator.getClass() == generator.getClass()){
            add.run((G) getLast().generator);
        }else{
            add.run(generator);
            segments.add(new AutoSegment<>(reactor,generator));
        }
    }

    private AutoSegment<? extends MecanumReactor, ? extends Generator> getLast(){
        return segments.get(segments.size()-1);
    }

    private Pose getLastPose(){
        return getLast().generator.getLastPose();
    }
}
