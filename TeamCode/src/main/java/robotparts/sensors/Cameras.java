package robotparts.sensors;

import org.openftc.easyopencv.OpenCvCameraRotation;

import robotparts.RobotPart;
import robotparts.electronics.input.ICamera;

public class Cameras extends RobotPart {
    private ICamera ecam;

    @Override
    public void init() {
        ecam = createCamera("camera1", ICamera.CameraType.NORMAL, OpenCvCameraRotation.UPRIGHT, true);
    }

    public void startExternalCamera(){
        ecam.start();
    }

    public void stopExternalCamera(){ ecam.halt(); }

    public double getFPS(){
        return ecam.getFramesPerSecond();
    }

}
