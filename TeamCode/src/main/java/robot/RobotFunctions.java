package robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import automodules.StageList;
import util.codeseg.CodeSeg;
import util.condition.Status;
import util.Timer;
import automodules.stage.Pause;
import automodules.stage.Stage;

import static global.General.*;
import static robot.RobotFramework.robotFunctionsThread;

public class RobotFunctions {

    //List of all robot functions currently in the queue (LinkedList is a FIFO Queue)
    public volatile Queue<Stage> rfsQueue = new LinkedList<>();
    public final Timer timer = new Timer();

    //Update code for running the queue
    public CodeSeg updateCode = () -> {
        if(!rfsQueue.isEmpty()){
            Stage s = rfsQueue.peek();
            assert s != null;
            if(!s.hasStarted()){
                s.start();
            }
            s.loop();
            if (s.shouldStop()) {
                s.runOnStop();
                rfsQueue.poll();
                timer.reset();
            } else if (s.isPause()) {
                robotFunctionsThread.setStatus(Status.IDLE);
            }
        } else {
            robotFunctionsThread.setStatus(Status.IDLE);
        }
        telemetry.update();
    };

    public void resume() {
        if (!rfsQueue.isEmpty() && rfsQueue.peek().isPause()) {
            rfsQueue.poll();
            timer.reset();
            robotFunctionsThread.setStatus(Status.ACTIVE);
        }
    }

    //Start the queue thread
    public void init(){
        addPause();
        robotFunctionsThread.setCode(updateCode);
    }
    public final void addAutoModule(StageList autoModule){
        if (rfsQueue.isEmpty()) {
            timer.reset();
            robotFunctionsThread.setStatus(Status.ACTIVE);
        }
        rfsQueue.addAll(autoModule.getStages());
    }

    public final void addToQueue(Stage s) {
        if (rfsQueue.isEmpty()) {
            timer.reset();
            robotFunctionsThread.setStatus(Status.ACTIVE);
        }
        rfsQueue.add(s);
    }

    public final void emptyQueue(){
        rfsQueue.clear();
        timer.reset();
    }

    public void addPause() {
        addToQueue(new Stage(new Pause()));
    }
}