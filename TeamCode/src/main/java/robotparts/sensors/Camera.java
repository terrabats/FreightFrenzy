package robotparts.sensors;

import org.openftc.easyopencv.OpenCvCameraRotation;

import robotparts.RobotPart;
import robotparts.electronics.input.ICamera;

public class Camera extends RobotPart {
    private ICamera cam;

    @Override
    public void init() {
        cam = createCamera("camera1", ICamera.CameraType.EXTERNAL, OpenCvCameraRotation.UPRIGHT, true);
    }

}
