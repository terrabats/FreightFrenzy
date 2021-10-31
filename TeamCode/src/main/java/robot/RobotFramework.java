package robot;

import java.util.ArrayList;

import robotparts.RobotPart;
import debugging.Fault;
import util.Stage;
import util.TerraThread;

import static global.General.*;

public class RobotFramework {
    public static ArrayList<RobotPart> allRobotParts = new ArrayList<>();
    public static TerraThread robotFunctionsThread;
    public static TerraThread odometryThread;
    public static TerraThread telemetryThread;
    public RobotFunctions rfsHandler;

    public RobotFramework(){
        rfsHandler = new RobotFunctions();
        if(!Fault.debugging){
            telemetry.setAutoClear(false);
        }
        for (RobotPart part : allRobotParts) {
            part.init();
        }
        robotFunctionsThread = new TerraThread();
        odometryThread = new TerraThread();
        telemetryThread = new TerraThread();
        rfsHandler.init();
        robotFunctionsThread.start();
        odometryThread.start();
        telemetryThread.start();
        gameTime.reset();
    }

    public void start() {
        rfsHandler.resume();
    }

    public void stop(){
        robotFunctionsThread.stopUpdating();
        odometryThread.stopUpdating();
    }

    public void addRFsTeleOp() {
//        rfsHandler.addToQueue(new Stage() {
//            @Override
//            public boolean run(double in) {
//                bot.mechDrive.move(0.3, 0, 0);
//                return in > 1.5;
//            }
//            @Override
//            public void runOnStop() {
//                bot.mechDrive.move(0, 0, 0);
//            }
//        });
    }

}
