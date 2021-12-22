package robot;

import java.util.ArrayList;

import automodules.StageList;
import geometry.CoordinatePlane;
import robotparts.RobotPart;
import util.TerraThread;
import util.User;
import util.codeseg.ParameterCodeSeg;

import static global.General.*;

public class RobotFramework {
    /**
     * The allRobotParts arraylist contains all of the robotParts in the robot,
     * The RobotPart constructor automatically adds itself to this static arraylist
     */
    public static ArrayList<RobotPart> allRobotParts = new ArrayList<>();
    /**
     * The localPlane Coordinate plane represents the robots local perspective
     */
    public CoordinatePlane localPlane;
    /**
     * The terrathread robotFunctionsThread is used for running robotfunction related tasks,
     * it is usually not neccessary to access this directly
     */
    public static TerraThread robotFunctionsThread;
    /**
     * The odometry thread is used to update odometry
     */
    public static TerraThread odometryThread;
    /**
     * rfsHandler is used for running rfs code. Stages can be added to the queue
     */
    public RobotFunctions rfsHandler;
    /**
     *  Represents if the robot is in slow mode
     */
    // TODO NEW
    // Make slowMode better by doing testing and seeing if there is better way for the driver to slow down the robot
//    public boolean slowMode = false;

    /**
     * Constructor for creating a robotFramework, this should be overriden by terraBot by extending this class
     * Objects are instantiated here, and rfsHandler is also initialized, which sets the update code
     */
    protected RobotFramework(){
        localPlane = new CoordinatePlane();
        rfsHandler = new RobotFunctions();
        robotFunctionsThread = new TerraThread();
        odometryThread = new TerraThread();
        rfsHandler.init();
    }

    /**
     * This method is run from TerraBot and this initializes all of the robotparts, sets the user to main user
     * It then starts the robotfunctionsthread, odometry thread, and resets the gametimer
     * NOTE: Threads are started in init to prevent lag in start
     */
    public void init(){
        forAllParts(RobotPart::init);
        setUser(mainUser);
        robotFunctionsThread.start();
        odometryThread.start();
        gameTime.reset();
    }

    /**
     * Start, starts the robotfunctions by "resuming" (resume and start are the same thing)
     */
    public void start() {
        rfsHandler.resume();
    }

    /**
     * the stop method stops updating threads, and halts the robot
     * @link #halt
     */
    public void stop(){
        robotFunctionsThread.stopUpdating();
        odometryThread.stopUpdating();
        halt();
    }

    /**
     * Stops all of the robotparts by setting all of the cmotors and cservos to 0 power
     * NOTE: This will only work if the current user is main user, do NOT use this in a thread
     */
    public void halt(){ forAllParts(RobotPart::halt); }

    /**
     * Adds an automodule (or list of stages) to the robotfunctions to add it to the queue
     * @param autoModule
     */
    public void addAutoModule(StageList autoModule){
        rfsHandler.addAutoModule(autoModule);
    }

    /**
     * Sets the user to the new user specified
     * NOTE: This does not change the access of the user which must be updated explicity with checkAccess
     * @param newUser
     */
    public void setUser(User newUser){ forAllParts(part -> part.switchUser(newUser)); }

    /**
     * Checks the access of the potential user to all of the robot parts
     * This should be called every time a user wants to use the robot, to check if the current user privileges are updated
     * @param potentialUser
     */
    public void checkAccess(User potentialUser){ forAllParts(part -> part.checkAccess(potentialUser)); }


    // TODO TEST
    // Might have been fixed – test
    public void cancelAutoModule(){
        rfsHandler.emptyQueue();
    }

    /**
     * This runs the specified code for all of the robot parts.
     * The type parameter code segment will accept the current robotpart
     * @param run
     */
    private void forAllParts(ParameterCodeSeg<RobotPart> run){
        for(RobotPart part: allRobotParts){
            run.run(part);
        }
    }
}
