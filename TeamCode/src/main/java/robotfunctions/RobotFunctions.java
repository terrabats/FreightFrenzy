package robotfunctions;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

import robot.Constants;
import robot.TerraBot;
import util.CodeSeg;
import util.Stage;
import util.TerraThread;
import util.Timer;

public class RobotFunctions {
    /* This is a description of how robot functions work
     * First a new thread is created that always runs in the background
     * This thread is in charge of running whatever robot functions are in the queue
     * Whenever it detects that there are items in the queue it runs all of them until
     * they are all done and then it clears the queue
     * To place items in the queue an ordered list of robot functions is created
     * In this list each item is associated with an index
     * When you define an automodule the automodule fills up the list with robot functions in order
     * one after the other unless there is a pause where it would skip that index
     * So for automodules (a1,a2,...) it would look something like this
     * index | value
     * 0 | a1.rf1   - first robot function
     * 1 | a1.rf2
     * 2 | null     - pause
     * 3 | a1.rf3
     * 4 | null     - 2nd pause
     * 5 | a1.rf4   - final robot function
     * 6 | a2.rf1   - first robot function of a2
     * 7 | a2.rf2
     * and so on
     * Each automodule would then tell this robot function handler to add its own robot functions to
     * the queue when the automodule starts
     * In autonomous the same thing happens except for anything that is not an robot function there is nothing (Path p)
     * 0 | p.rf1   - first robot function
     * 1 | null    -moving or stopping
     * 2 | null
     * 3 | null
     * 4 | p.rf2   - 2nd robot function
     * and so on ...
     * */

    //List of all robot functions currently in the queue (Stack is a FIFO Queue)
    public volatile Stack<Stage> rfsQueue = new Stack<>();
    private final Timer timer = new Timer();

    //Update code for running the queue
    public CodeSeg updateCode = () -> {
        if(!rfsQueue.empty()){
            Stage s = rfsQueue.peek();
            if (s.run(timer.seconds())) {
                rfsQueue.pop();
                timer.reset();
            }
        }
    };

    public void resume() {
        if (rfsQueue.peek().isPause()) {
            rfsQueue.pop();
        }
    }

    //Start the queue thread
    public void start(){
        addPause();
        TerraBot.robotFunctionsThread.setCode(updateCode);
        TerraBot.robotFunctionsThread.start();
    }

    //Stop the thread
    public void stop(){
        TerraBot.robotFunctionsThread.stopUpdating();
    }

    //Add robot functions based on index
    public final void addToQueue(ArrayList<Stage> stages){
        if (rfsQueue.empty()) timer.reset();
        rfsQueue.addAll(stages);
    }

    public final void addToQueue(Stage s) {
        if (rfsQueue.empty()) timer.reset();
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