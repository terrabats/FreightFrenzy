package robotparts.electronics;

import static global.General.hardwareMap;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;

import robotparts.Electronic;

public class SColor extends Electronic {
    private final ColorSensor colorSensor;
    public SColor(ColorSensor colorSensor){
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
