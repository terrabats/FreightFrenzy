package robot;

import java.util.ArrayList;

import automodules.StageList;
import geometry.CoordinatePlane;
import robotparts.RobotPart;
import util.TerraThread;
import util.User;
import util.codeseg.TypeParameterCodeSeg;

import static global.General.*;

public class RobotFramework {
    public static ArrayList<RobotPart> allRobotParts = new ArrayList<>();
    public CoordinatePlane localPlane;
    public static TerraThread robotFunctionsThread;
    public static TerraThread odometryThread;
    public RobotFunctions rfsHandler;

    public boolean slowMode = false;

    public RobotFramework(){
        localPlane = new CoordinatePlane();
        rfsHandler = new RobotFunctions();
        robotFunctionsThread = new TerraThread();
        odometryThread = new TerraThread();
        rfsHandler.init();
    }

    public void init(){
        forAllParts(RobotPart::init);
        setUser(mainUser);
        robotFunctionsThread.start();
        odometryThread.start();
        gameTime.reset();
    }

    public void start() {
        rfsHandler.resume();
    }

    public void update(){
        forAllParts(part -> part.checkAccess(mainUser));
    }

    public void stop(){
        robotFunctionsThread.stopUpdating();
        odometryThread.stopUpdating();
    }

    public void halt(){ forAllParts(RobotPart::halt); }

    public void addAutoModule(StageList autoModule){
        rfsHandler.addAutoModule(autoModule);
    }

    public void setUser(User newUser){ forAllParts(part -> part.switchUser(newUser)); }

    // TODO FIX
    // MAKe this work
    public void cancelAutoModule(){
        rfsHandler.emptyQueue();
    }


    private void forAllParts(TypeParameterCodeSeg<RobotPart> run){
        for(RobotPart part: allRobotParts){
            run.run(part);
        }
    }
}
