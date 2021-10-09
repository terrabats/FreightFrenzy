package robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sensors extends RobotPart{

    // TODO: Figure out if sensor math should be done in this class or somewhere else?

    private BNO055IMU gyro;

    public Sensors(){
        super();
    }

    @Override
    public void init(){
        gyro = createGyro("gyro");
    }

    // BACKUP METHODS
    public BNO055IMU gyro() { return gyrosensors.get("gyro"); }

}
