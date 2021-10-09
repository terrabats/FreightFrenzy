package global;

import com.qualcomm.robotcore.hardware.HardwareMap;

import robotparts.MecanumDrive;
import robotparts.RobotPart;
import robotparts.RobotPartHandler;
import robotparts.Sensors;

public class TerraBot {
    public static HardwareMap hwMap;
    public RobotPart mechDrive = new MecanumDrive();
    public RobotPart sensors = new Sensors();


    public void init(HardwareMap hwMap){
        TerraBot.hwMap = hwMap;
        RobotPartHandler.init();
    }

}
