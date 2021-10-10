package global;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.TreeMap;

import robotparts.RobotPart;

public class RobotFramework {
    public static HardwareMap hardwareMap;
    public static ElapsedTime gameTime = new ElapsedTime();
    public static ArrayList<RobotPart> allRobotParts = new ArrayList<>();

    public void init(HardwareMap hwMap){
        hardwareMap = hwMap;
        for (RobotPart part : allRobotParts) {
            part.init();
        }
    }

}
