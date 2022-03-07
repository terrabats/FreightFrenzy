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
import autoutil.vision.CaseScanner;
import elements.Case;
import elements.FieldSide;
import geometry.position.Point;
import geometry.position.Pose;
import util.codeseg.CodeSeg;
import util.codeseg.ParameterCodeSeg;
import util.condition.Decision;

import static global.General.bot;
import static global.General.log;

public abstract class AutoFramework extends Auto{

    protected FieldSide fieldSide = FieldSide.UNKNOWN;

    public abstract ExecutorNew getExecutor();

    protected ExecutorNew executor;

    protected Pose lastPose = new Pose(new Point(0,0),0);

    protected ArrayList<AutoSegment<? extends Reactor, ? extends Generator>> segments = new ArrayList<>();

    protected boolean scanning = false;
    protected CaseScanner caseScanner;
    protected Case caseDetected;

    public abstract void define();
    public abstract Reactor getSetpointReactor();
    public abstract Reactor getWaypointReactor();
    public abstract Generator getSetpointGenerator();
    public abstract Generator getWaypointGenerator();
    public abstract CaseScanner getCaseScanner();

    public boolean isFlipped(){
        return fieldSide.equals(FieldSide.RED);
    }

    public void addDecision(Decision decision, Decision.DecisionList decisionToCheck){
        decision.check(decisionToCheck);
    }

    public void customSide(FieldSide sideOne, CodeSeg one, FieldSide sideTwo, CodeSeg two){
        addDecision(new Decision(sideOne, one).addOption(sideTwo, two), fieldSide);
    }

    public void customCase(Case caseOne, CodeSeg one, Case caseTwo, CodeSeg two, Case caseThree, CodeSeg three){
        addDecision(new Decision(caseOne, one).addOption(caseTwo, two).addOption(caseThree, three), caseDetected);
    }

    public void scan(){
        scanning = true;
        caseScanner = getCaseScanner();
        bot.camera.setExternalScanner(caseScanner);
        bot.camera.startExternalCamera();
        whileActive(() -> !isStarted(), () -> {
            caseDetected = caseScanner.getCase();
            log.show("Case Detected: ", caseDetected);
        });
    }

    @Override
    public void runAuto() {
        define();
        if(scanning) {
            bot.camera.stopExternalCamera();
        }
        for(AutoSegment<? extends Reactor, ? extends Generator> autoSegment: segments){
            executor = getExecutor();
            executor.setReactor(autoSegment.getReactor());
            executor.setPath(autoSegment.getGenerator().getPath());
            executor.followPath();
        }
    }

    public void addSetpoint(double x, double y, double h){
        if(isFlipped()){
            x = -x;
            h = -h;
        }
        addSegment(getSetpointReactor(), getSetpointGenerator(), x, y, h);
    }

    public void addWaypoint(double x, double y, double h){
        if(isFlipped()){
            x = -x;
            h = -h;
        }
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
