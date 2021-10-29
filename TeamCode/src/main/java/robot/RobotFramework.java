package robot;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

import developing.InitOp;
import robotparts.RobotPart;
import teleutil.GamepadHandler;
import util.Fault;
import util.Stage;
import util.TerraThread;

import static global.General.*;

public class RobotFramework {
    public static ArrayList<RobotPart> allRobotParts = new ArrayList<>();
    public static TerraThread robotFunctionsThread;
    public static TerraThread odometryThread;
    public RobotFunctions rfsHandler;
    private final InitOp initOp = new InitOp();

    public void init(HardwareMap hwMap, Telemetry tel, Gamepad gp1, Gamepad gp2){
        //initOp.initObjects();
        fault = new Fault();
        hardwareMap = hwMap;
        telemetry = tel;
        rfsHandler = new RobotFunctions();
        gph1 = new GamepadHandler(gp1);
        gph2 = new GamepadHandler(gp2);
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
            @Override
            public void runOnStop() {
                bot.mechDrive.move(0, 0, 0);
            }
        });
    }

}
