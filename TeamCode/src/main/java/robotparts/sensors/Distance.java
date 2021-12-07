package robotparts.sensors;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robotparts.RobotPart;

public class Distance extends RobotPart {
    private DistanceSensor dsl,dsr,dsfl,dsfr,dso;

    @Override
    public void init() {
        dsl = createDistanceSensor("dsl");
        dsr = createDistanceSensor("dsr");
        dsfl = createDistanceSensor("dsfl");
        dsfr = createDistanceSensor("dsfr");
        dso = createDistanceSensor("cso");
    }

    public double getLeftDistance(){
        return dsl.getDistance(DistanceUnit.CM);
    }
    public double getRightDistance(){
        return dsr.getDistance(DistanceUnit.CM);
    }
    public double getFrontLeftDistance(){
        return dsfl.getDistance(DistanceUnit.CM);
    }
    public double getFrontRightDistance(){
        return dsfr.getDistance(DistanceUnit.CM);
    }
    public double getOuttakeDistance(){
        return dso.getDistance(DistanceUnit.CM);
    }
}
