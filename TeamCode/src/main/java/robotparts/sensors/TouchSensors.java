package robotparts.sensors;

import robotparts.RobotPart;
import robotparts.electronics.input.ITouch;

public class TouchSensors extends RobotPart {
    /**
     * Touch sensor for outtake
     */
    private ITouch tso;

    // TODO DESIGN FIX
    // Remove the touch sensor or move it to a different place

    @Override
    public void init() {
        tso = createTouchSensor("tso");
    }

    /**
     * Is the outtake pressing the touch sensor (i.e. all the way down)
     * @return pressed
     */
    public boolean isOuttakePressingTouchSensor(){
        return tso.isPressed();
    }
}
