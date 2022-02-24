package robotparts.electronics.input;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import autoutil.vision.TerraCV;
import robotparts.Electronic;

public class ICamera extends Electronic {
    private final OpenCvWebcam camera;
    private final TerraCV terraCV;
    private final CameraType cameraType;

    public ICamera(OpenCvWebcam cam, CameraType t, OpenCvCameraRotation rotation){
        this.camera = cam;
        this.cameraType = t;

        terraCV = new TerraCV();

        camera.setPipeline(terraCV);

        camera.setMillisecondsPermissionTimeout(2500);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener(){
            @Override
            public void onOpened() {camera.startStreaming(640, 480, rotation); }
            @Override
            public void onError(int errorCode) {}
        });



    }

    public double getFramesPerSecond(){
        return camera.getFps();
    }

    @Override
    public void halt(){
        camera.closeCameraDevice();
    }

    public enum CameraType{
        NORMAL,
        EXTERNAL
    }
}
