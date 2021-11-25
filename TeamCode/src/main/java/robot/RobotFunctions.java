package robot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import util.Stage;
import util.codeseg.CodeSeg;
import util.condition.Status;
import util.Timer;

import static global.General.*;
import static robot.RobotFramework.robotFunctionsThread;

public class RobotFunctions {

    //List of all robot functions currently in the queue (LinkedList is a FIFO Queue)
    public volatile Queue<Stage> rfsQueue = new LinkedList<>();
    private final Timer timer = new Timer();

    //Update code for running the queue
    public CodeSeg updateCode = () -> {
        if(!rfsQueue.isEmpty()){
            Stage s = rfsQueue.peek();
            if (s.run(timer.seconds())) {
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

    //Add robot functions based on index
    public final void addToQueue(ArrayList<Stage> stages){
        if (rfsQueue.isEmpty()) {
            timer.reset();
            robotFunctionsThread.setStatus(Status.ACTIVE);
        }
        rfsQueue.addAll(stages);
    }

    public final void addToQueue(Stage s) {
        if (rfsQueue.isEmpty()) {
            log.display("Starting RFsThread again");
            timer.reset();
            robotFunctionsThread.setStatus(Status.ACTIVE);
        }
        rfsQueue.add(s);
    }

    public void addPause() {
        addToQueue(new Stage() {
            @Override
            public boolean isPause() {
                return true;
            }
        });
    }
}