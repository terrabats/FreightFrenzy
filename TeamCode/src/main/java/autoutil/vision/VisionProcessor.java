package autoutil.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class VisionProcessor {
    public static final Scalar BLUE = new Scalar(0, 0, 255);
    public static final Scalar GREEN = new Scalar(0, 255, 0);
    public static final Scalar RED = new Scalar(255, 0, 0);


    private final Mat YCrCb = new Mat();
    private final Mat Cb = new Mat();


    /*
     * This function takes the RGB frame, converts to YCrCb,
     * and extracts the Cb channel to the 'Cb' variable
     */
    public void toCb(Mat input)
    {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 2);
    }

    public Mat getCbSubmat(Point top_left, Point bottom_right){
        return Cb.submat(new Rect(top_left, bottom_right));
    }

    public int getAverage(Mat mat){
        return ((int) Core.mean(mat).val[0]);
    }
}
