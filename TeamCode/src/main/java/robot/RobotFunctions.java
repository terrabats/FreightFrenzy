package robot;

import java.util.LinkedList;
import java.util.Queue;

import automodules.StageList;
import util.User;
import util.codeseg.CodeSeg;
import util.condition.Status;
import util.Timer;
import automodules.stage.Stage;

import static global.General.*;
import static robot.RobotFramework.robotFunctionsThread;

public class RobotFunctions {

    /**
     * List of all robot functions currently in the queue (LinkedList is a FIFO Queue)
     *
     */
    public volatile Queue<Stage> rfsQueue = new LinkedList<>();
    /**
     * Timer for robotfunctions, resets after every stage is run
     */
    public final Timer timer = new Timer();
    /**
     * Define the updateCode codeseg to contain the code that will run in the Thread
     */
    public CodeSeg updateCode = () -> {
        /**
         * Check if the robot has access to move for robotfunctions user or ROFU
         */
        bot.checkAccess(User.ROFU);
        /**
         * If the robotfunctions queue is not empty
         */
        if(!rfsQueue.isEmpty()){
            /**
             * Get the oldest stage
             */
            Stage s = rfsQueue.peek();
            assert s != null;
            /**
             * If the stage has not started start it,
             * Then run the loop code
             */
            if(!s.hasStarted()){
                s.start();
            }
            s.loop();
            /**
             * If the stage should stop, then run on stop code, remove the stage, and reset the timer
             * Otherwise, set the thread to Status.IDLE to prevent unnecessary lag
             */
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
    };

    /**
     * Resume the robotfunctions,
     * if the queue is not empty and the oldest stage is a pause then delete it and start the thread again
     * (by seting the status to active)
     */
    public void resume() {
        if (!rfsQueue.isEmpty() && rfsQueue.peek().isPause()) {
            rfsQueue.poll();
            timer.reset();
            robotFunctionsThread.setStatus(Status.ACTIVE);
        }
    }

    /**
     * Initialize the update code in the thread,
     * Make the first stage a pause so the thread doesn't start updating until resume is called
     */
    public void init(){
        addToQueue(new Stage(true));
        robotFunctionsThread.setCode(updateCode);
    }

    /**
     * Add the automodule by reseting the time and starting the thread
     * Add all of the stages in the automodule to the queue
     * @param autoModule
     */
    public final void addAutoModule(StageList autoModule){
        if (rfsQueue.isEmpty()) {
            timer.reset();
            robotFunctionsThread.setStatus(Status.ACTIVE);
        }
        rfsQueue.addAll(autoModule.getStages());
    }

    /**
     * Add a specific stage to the the queue
     * @param s
     * @link addAutoModule
     */
    public final void addToQueue(Stage s) {
        if (rfsQueue.isEmpty()) {
            timer.reset();
            robotFunctionsThread.setStatus(Status.ACTIVE);
        }
        rfsQueue.add(s);
    }

    /**
     * Empty the queue and reset the timer
     */
    public final void emptyQueue(){
        if (!rfsQueue.isEmpty()) rfsQueue.peek().runOnStop();
        rfsQueue.clear();
        timer.reset();
    }
}