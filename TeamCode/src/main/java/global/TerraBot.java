package global;

import com.qualcomm.robotcore.hardware.HardwareMap;

import robotparts.MecanumDrive;
import robotparts.RobotPart;
import robotparts.Sensors;

public class TerraBot extends RobotFramework{

    public RobotPart mecDrive = new MecanumDrive();
    public RobotPart sensors = new Sensors();


    public void init(HardwareMap hwMap){
        mecDrive.init();
        sensors.init();
        super.init(hwMap);

    }

}
