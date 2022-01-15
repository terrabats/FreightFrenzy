package robotparts.electronics.input;

import static global.General.hardwareMap;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;

import robotparts.Electronic;

/**
 * NOTE: Uncommented
 */

public class IColor extends Electronic {

    private final ColorSensor colorSensor;
    public IColor(ColorSensor colorSensor){
        this.colorSensor = colorSensor;
    }

    public int getRed(){
        return colorSensor.red();
    }
    public int getGreen(){
        return colorSensor.green();
    }
    public int getBlue(){
        return colorSensor.blue();
    }

}
