package robot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

import robotparts.RobotPart;
import util.Fault;

public class RobotFramework {
    public static HardwareMap hardwareMap;
    public static Telemetry telemetry;
    public static ElapsedTime gameTime = new ElapsedTime();
    public static ArrayList<RobotPart> allRobotParts = new ArrayList<>();

    public void init(HardwareMap hwMap, Telemetry tel){
        hardwareMap = hwMap;
        telemetry = tel;
        if(!Fault.debugging){
            telemetry.setAutoClear(false);
        }
        for (RobotPart part : allRobotParts) {
            part.init();
        }
        gameTime.reset();
    }

}
