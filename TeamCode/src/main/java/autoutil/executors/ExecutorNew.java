package autoutil.executors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import autoutil.paths.Path;
import autoutil.reactors.Reactor;
import util.codeseg.ReturnCodeSeg;

public abstract class ExecutorNew {

    protected Path path;
    protected Reactor reactor;
    protected ReturnCodeSeg<Boolean> whileOpModeIsActive;

    public ExecutorNew(LinearOpMode opMode){
        whileOpModeIsActive = opMode::opModeIsActive;
    }

    public void setPath(Path path){
        this.path = path;
    }

    public void setReactor(Reactor reactor){
        this.reactor = reactor;
    }

    public abstract void followPath();
}