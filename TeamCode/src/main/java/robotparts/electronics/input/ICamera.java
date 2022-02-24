package robotparts.electronics.input;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import autoutil.vision.TerraCV;
import robotparts.Electronic;

public class ICamera extends Electronic {
    private final OpenCvCamera camera;
    private final TerraCV terraCV;
    private final CameraType cameraType;
    private final OpenCvCameraRotation orientation;
    private final int width = 320;
    private final int height = 240;

    public ICamera(OpenCvCamera cam, CameraType t, OpenCvCameraRotation rotation){
        this.camera =  cam;
        this.cameraType = t;
        this.orientation = rotation;

        terraCV = new TerraCV();

        camera.setPipeline(terraCV);
    }

    public void start(){
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener(){
            @Override
            public void onOpened() {camera.startStreaming(width, height, orientation); }
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
        INTERNAL,
        EXTERNAL
    }
}
