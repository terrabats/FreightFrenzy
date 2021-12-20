package robotparts.sensors;

import com.qualcomm.robotcore.hardware.TouchSensor;

import robotparts.RobotPart;
import robotparts.electronics.Touch;

public class TouchSensors extends RobotPart {
    private Touch tso;

    @Override
    public void init() {
        tso = createTouchSensor("tso");
    }

    public boolean isOuttakePressingTouchSensor(){
        return tso.isPressed();
    }
}
