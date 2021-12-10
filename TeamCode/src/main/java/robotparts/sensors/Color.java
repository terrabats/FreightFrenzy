package robotparts.sensors;

import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import elements.GameElement;
import robotparts.RobotPart;

public class Color extends RobotPart {
    private ColorSensor cso;

    @Override
    public void init() {
        cso = createColorSensor("cso");
    }

    public float[] getOuttakeColorHSV(){
        float[] color = new float[3];
        android.graphics.Color.RGBToHSV(cso.red(), cso.green(), cso.blue(), color);
        return color;
    }

//    public float[] getOutakeColorRGB(){
//        return new float[]{(float)cso.red()*255/800, (float)cso.green()*255/800, (float)cso.blue()*255/800};
//    }

    public boolean isBall(){
        float h = getOuttakeColorHSV()[0];
        return 155 < h && h < 170;
    }
    public boolean isCube(){
        float h = getOuttakeColorHSV()[0];
        return 60 < h && h < 90;
    }

    //    // TODO: FIX THIS
//    public GameElement get_part_intake() {
//        if (in_dis.getDistance(DistanceUnit.CM) > THRESHOLD_DISTANCE) {
//            // No object?
//            return null;
//        } else if (in_cs.argb() < 30) {
//            // The object is white?
//            return GameElement.SPHERE;
//        } else {
//            // The object is yellow?
//            return GameElement.BOX;
//        }
//    }
}
