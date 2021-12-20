package robotparts.sensors;

import com.qualcomm.robotcore.hardware.TouchSensor;

import robotparts.RobotPart;

public class TouchSensor extends RobotPart {
    private com.qualcomm.robotcore.hardware.TouchSensor tso;

    @Override
    public void init() {
        tso = createTouchSensor("tso");
    }

    public boolean isOuttakePressingTouchSensor(){
        return tso.isPressed();
    }
    public double getValue(){
        return tso.getValue();
    }
}
