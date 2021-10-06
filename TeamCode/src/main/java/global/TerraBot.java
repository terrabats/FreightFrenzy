package global;

import com.qualcomm.robotcore.hardware.HardwareMap;

import robotparts.MecanumDrive;
import robotparts.RobotPart;
import robotparts.Sensors;

public class TerraBot {
    public RobotPart mechDrive = new MecanumDrive();
    public RobotPart sensors = new Sensors();
    public void init(HardwareMap hwMap){
        RobotPart.initAll(hwMap);
    }

}
