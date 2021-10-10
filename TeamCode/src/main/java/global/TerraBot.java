package global;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import robotparts.MecanumDrive;
import robotparts.RobotPart;
import robotparts.RobotPartHandler;
import robotparts.Sensors;
import robotparts.TankDrive;

public class TerraBot {
    public static HardwareMap hwMap;
    public static ElapsedTime gameTime = new ElapsedTime();

    public RobotPart mechDrive = new MecanumDrive();
    public RobotPart sensors = new Sensors();


    public void init(HardwareMap hwMap){
        TerraBot.hwMap = hwMap;
        RobotPartHandler.init();
    }

}
