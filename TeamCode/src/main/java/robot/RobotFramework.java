package robot;

import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;

import automodules.StageList;
import elements.FieldSide;
import geometry.CoordinatePlane;
import robotparts.RobotPart;
import util.TerraThread;

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
        for (RobotPart part : allRobotParts) {
            part.init();
        }
        robotFunctionsThread.start();
        odometryThread.start();
        gameTime.reset();
    }

    public void start() {
        rfsHandler.resume();
    }

    public void stop(){
        robotFunctionsThread.stopUpdating();
        odometryThread.stopUpdating();
    }

    public void halt(){
        for(RobotPart part: allRobotParts){
            part.halt();
        }
    }
    public void addAutoModule(StageList autoModule){
        rfsHandler.addAutoModule(autoModule);
    }


    // TODO FIX
    // MAKe this work
    public void cancelAutoModule(){
        rfsHandler.emptyQueue();
    }
}
