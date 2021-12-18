package robotparts.sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import robotparts.RobotPart;

public class Gyro extends RobotPart {
    private BNO055IMU gsr, gsl;

    // TODO FIX
    // This class takes forever to init, maybe calibration problem?

    @Override
    public void init() {
        gsr = createGyro("gsr");
//        gsl = createGyro("gsl");
    }

    public void resetRight() {
        //
    }

    public double getRightHeading(){
        return gsr.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }
//    public double getLeftHeading(){
//        return gsl.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
//    }
    public double getHeading() {
        return getRightHeading();
    }

}
