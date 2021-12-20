package robotparts.electronics;

import com.qualcomm.robotcore.hardware.TouchSensor;

import robotparts.Electronic;

public class Touch extends Electronic {
    private final TouchSensor touchSensor;
    public Touch(TouchSensor touchSensor){
        this.touchSensor = touchSensor;
    }
}
