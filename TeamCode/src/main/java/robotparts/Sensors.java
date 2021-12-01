package robotparts;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import global.GameElement;

public class Sensors extends RobotPart {

    private ColorSensor in_cs;
    private DistanceSensor in_dis;

    private static final double THRESHOLD_DISTANCE = 20;

    @Override
    public void init() {
        in_cs = createColorSensor("in_cs");
        in_dis = createDistanceSensor("in_dis");
    }

    // TODO: FIX THIS
    public GameElement get_part_intake() {
        if (in_dis.getDistance(DistanceUnit.CM) > THRESHOLD_DISTANCE) {
            // No object?
            return null;
        } else if (in_cs.argb() < 30) {
            // The object is white?
            return GameElement.SPHERE;
        } else {
            // The object is yellow?
            return GameElement.BOX;
        }
    }

}
