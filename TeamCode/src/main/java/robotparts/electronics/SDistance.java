package robotparts.electronics;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import robotparts.Electronic;

public class SDistance extends Electronic {
    private final DistanceSensor distanceSensor;

    public SDistance(DistanceSensor distanceSensor) {
        this.distanceSensor = distanceSensor;
    }

    public double getDistance(){
        return distanceSensor.getDistance(DistanceUnit.CM);
    }
}
