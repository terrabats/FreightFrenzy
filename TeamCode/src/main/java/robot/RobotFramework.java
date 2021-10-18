package robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

import robotfunctions.RobotFunctions;
import robotparts.RobotPart;
import util.Fault;
import util.Stage;
import util.TerraThread;

import static robot.General.*;

public class RobotFramework {
    public static ArrayList<RobotPart> allRobotParts = new ArrayList<>();
    public static TerraThread robotFunctionsThread;
    public static TerraThread odometryThread;
    public RobotFunctions rfsHandler;

    public void init(HardwareMap hwMap, Telemetry tel){
        fault = new Fault();
        hardwareMap = hwMap;
        telemetry = tel;
        rfsHandler = new RobotFunctions();
        if(!Fault.debugging){
            telemetry.setAutoClear(false);
        }
        for (RobotPart part : allRobotParts) {
            part.init();
        }
        robotFunctionsThread = new TerraThread();
        odometryThread = new TerraThread();
        rfsHandler.init();
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

    public void addRFsTeleOp() {
        rfsHandler.addToQueue(new Stage() {
            @Override
            public boolean run(double in) {
                bot.mechDrive.move(0.3, 0, 0);
                return in > 1.5;
            }
        });
        rfsHandler.addToQueue(new Stage() {
            @Override
            public boolean run(double in) {
                bot.mechDrive.move(0, 0, 0);
                return true;
            }
        });
    }

}
