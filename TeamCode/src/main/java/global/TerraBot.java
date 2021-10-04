package global;

import com.qualcomm.robotcore.hardware.HardwareMap;

import robotparts.MecanumDrive;

public class TerraBot {
    public MecanumDrive mechDrive = new MecanumDrive();
    public void init(HardwareMap hwMap){
        mechDrive.init(hwMap);
    }

}
