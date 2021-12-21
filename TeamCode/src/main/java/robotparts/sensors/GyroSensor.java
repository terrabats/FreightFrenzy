package robotparts.sensors;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import robotparts.RobotPart;
import robotparts.electronics.Gyro;

public class GyroSensor extends RobotPart {
    private Gyro gsr, gsl;

    // TODO TEST/FIX
    // This class takes forever to init, maybe calibration problem?
    // Run this and try to see what the problem is

    @Override
    public void init() {
        gsr = createGyro("gsr");
        gsl = createGyro("gsl");
    }

    public void resetRight() {
        //
    }


//    public double getLeftHeading(){
//        return gsl.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
//    }
    public double getRightHeading() {
        return gsr.getHeading();
    }
    public double getLeftHeading() {
        return gsl.getHeading();
    }

}
