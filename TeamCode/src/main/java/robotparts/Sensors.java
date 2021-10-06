package robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Sensors extends RobotPart{
    private BNO055IMU gyro;

    public Sensors(){
        super();
    }

    public void init(){
        gyro = createGyro("gyro");
    }

}
