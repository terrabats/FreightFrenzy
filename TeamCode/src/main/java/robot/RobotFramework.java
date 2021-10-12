package robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

import robotparts.RobotPart;
import util.Fault;
import util.TerraThread;
import util.Timer;

public class RobotFramework {
    public static HardwareMap hardwareMap;
    public static Telemetry telemetry;
    public static ElapsedTime gameTime = new ElapsedTime();
    public static ArrayList<RobotPart> allRobotParts = new ArrayList<>();
    public static TerraThread robotFunctionsThread = new TerraThread();
    public static TerraThread odometryThread = new TerraThread();

    public void init(HardwareMap hwMap, Telemetry tel){
        hardwareMap = hwMap;
        telemetry = tel;
        if(!Fault.debugging){
            telemetry.setAutoClear(false);
        }
        for (RobotPart part : allRobotParts) {
            part.init();
        }
        if(robotFunctionsThread.isAlive()){
            telemetry.addData("alive", "");
        }else{
            telemetry.addData("dead", "");
        }
        telemetry.update();
        //odometryThread.start();
        gameTime.reset();
        Timer timer = new Timer();
        timer.reset();
        while (timer.seconds() < 2){

        }
        robotFunctionsThread.start();
    }
    public void stop(){
        robotFunctionsThread.stopUpdating();
        odometryThread.stopUpdating();
    }

}
