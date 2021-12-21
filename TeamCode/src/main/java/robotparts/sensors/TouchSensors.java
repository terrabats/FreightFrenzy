package robotparts.sensors;

import robotparts.RobotPart;
import robotparts.electronics.ITouch;

public class TouchSensors extends RobotPart {
    private ITouch tso;

    @Override
    public void init() {
        tso = createTouchSensor("tso");
    }

    public boolean isOuttakePressingTouchSensor(){
        return tso.isPressed();
    }
}
