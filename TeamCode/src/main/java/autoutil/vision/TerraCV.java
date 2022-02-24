package autoutil.vision;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

import elements.Case;

public class TerraCV extends OpenCvPipeline {

    private Mat output = new Mat();

    private Case caseDetected;

//    private Mat hsv = new Mat();

    private boolean shouldShow = false;

    private VisionProcessor processor = new VisionProcessor();

    @Override
    public Mat processFrame(Mat input) {
        return null;
    }
}


//
//    public Mat processFrame(Mat input)
//    {
//        input.convertTo(input, -1, 2, 100); //Artificially increase brightness
//        input.convertTo(input, -1, 2, 50);
//        Rect rectCrop = new Rect(xPos, yPos, 100,125); //define rect to crop image based on xpos and ypos
//        processed = new Mat(input, rectCrop); //crop
//        Imgproc.cvtColor(processed, hsv, Imgproc.COLOR_RGB2HSV); //convert to hsv color space
//        avgColor = Core.mean(hsv).val; //find the mean value of the colors
//        avgH = avgColor[0]; // find the mean hue value
//        avgS = avgColor[1];
//        avgV = avgColor[2];

//        if(avgH > 90){ //for zero hue is usually around 100
//            ringNum = RingNum.ZERO;
//        }else if(avgH > 50){ //for one hue is usually around 70
//            ringNum = RingNum.ONE;
//        }else if(avgH > 10){ // for four hue is usually around 30
//            ringNum = RingNum.FOUR;
//        }
//        if(avgS > 50){
//            ringNum = RingNum.FOUR;
//        }else if(avgS > 30){
//            ringNum = RingNum.ONE;
//        }else if(avgS > 5){
//            ringNum = RingNum.ZERO;
//        }
//        //Uncomment this line if you want to view fullscreen
//        if(show) {
//            Imgproc.resize(processed, processed, input.size());
//        }
//
//        return output;
//    }
//}
//
//package org.firstinspires.ftc.teamcode.symbiotes;
//
//        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//        import com.qualcomm.robotcore.hardware.HardwareMap;
//
//        import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//        import org.opencv.core.Core;
//        import org.opencv.core.Mat;
//        import org.opencv.core.Point;
//        import org.opencv.core.Rect;
//        import org.opencv.core.Scalar;
//        import org.opencv.imgproc.Imgproc;
//        import org.openftc.easyopencv.OpenCvCamera;
//        import org.openftc.easyopencv.OpenCvCameraFactory;
//        import org.openftc.easyopencv.OpenCvCameraRotation;
//        import org.openftc.easyopencv.OpenCvPipeline;
//
//public class Gobiidae {
//
//    FreightFrenzyPipeline pipeline;
//
//    /*
//     * An enum to define the Team shipping element or duck position
//     */
//    public enum BarcodePosition
//    {
//        LEFT,
//        CENTER,
//        RIGHT
//    }
//
//    // Volatile since accessed by OpMode thread w/o synchronization
//    public static volatile BarcodePosition position = BarcodePosition.LEFT;
//
//    public void setup(HardwareMap hardwaremapInput)
//    {
//        OpenCvCamera webcam;
//
//        int cameraMonitorViewId = hardwaremapInput.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwaremapInput.appContext.getPackageName());
//        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwaremapInput.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//
//        webcam.openCameraDevice();
//
//        pipeline = new FreightFrenzyPipeline();
//
//        webcam.setPipeline(pipeline);
//
//        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
//    }
//
//    public static class FreightFrenzyPipeline extends OpenCvPipeline
//    {
//
//
//        /*
//         * Some color constants
//         */
//        static final Scalar BLUE = new Scalar(0, 0, 255);
//        static final Scalar GREEN = new Scalar(0, 255, 0);
//
//        /*
//         * The core values which define the location and size of the sample regions
//         * If you want to enlarge or reduce the size of the regions, it should be done here.
//         */
//        static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(109,98);
//        static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(181,98);
//        static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(253,98);
//        static final int REGION_WIDTH = 40;
//        static final int REGION_HEIGHT = 40;
//
//        /*
//         * Points which actually define the sample region rectangles, derived from above values
//         *
//         * Example of how points A and B work to define a rectangle
//         *
//         *   ------------------------------------
//         *   | (0,0) Point A                    |
//         *   |                                  |
//         *   |                                  |
//         *   |                                  |
//         *   |                                  |
//         *   |                                  |
//         *   |                                  |
//         *   |                  Point B (70,50) |
//         *   ------------------------------------
//         *
//         */
//        Point region1_pointA = new Point(
//                REGION1_TOPLEFT_ANCHOR_POINT.x,
//                REGION1_TOPLEFT_ANCHOR_POINT.y);
//        Point region1_pointB = new Point(
//                REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
//                REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
//        Point region2_pointA = new Point(
//                REGION2_TOPLEFT_ANCHOR_POINT.x,
//                REGION2_TOPLEFT_ANCHOR_POINT.y);
//        Point region2_pointB = new Point(
//                REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
//                REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
//        Point region3_pointA = new Point(
//                REGION3_TOPLEFT_ANCHOR_POINT.x,
//                REGION3_TOPLEFT_ANCHOR_POINT.y);
//        Point region3_pointB = new Point(
//                REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
//                REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
//
//        /*
//         * Working variables
//         */
//        Mat region1_Cb, region2_Cb, region3_Cb;
//        Mat YCrCb = new Mat();
//        Mat Cb = new Mat();
//        int avg1, avg2, avg3;
//
//        /*
//         * This function takes the RGB frame, converts to YCrCb,
//         * and extracts the Cb channel to the 'Cb' variable
//         */
//        void inputToCb(Mat input)
//        {
//            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
//            Core.extractChannel(YCrCb, Cb, 2);
//        }
//
//        @Override
//        public void init(Mat firstFrame)
//        {
//            /*
//             * We need to call this in order to make sure the 'Cb'
//             * object is initialized, so that the submats we make
//             * will still be linked to it on subsequent frames. (If
//             * the object were to only be initialized in processFrame,
//             * then the submats would become delinked because the backing
//             * buffer would be re-allocated the first time a real frame
//             * was crunched)
//             */
//            inputToCb(firstFrame);
//
//            /*
//             * Submats are a persistent reference to a region of the parent
//             * buffer. Any changes to the child affect the parent, and the
//             * reverse also holds true.
//             */
//            region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
//            region2_Cb = Cb.submat(new Rect(region2_pointA, region2_pointB));
//            region3_Cb = Cb.submat(new Rect(region3_pointA, region3_pointB));
//        }
//
//        @Override
//        public Mat processFrame(Mat input)
//        {
//            /*
//             * Overview of what we're doing:
//             *
//             * We first convert to YCrCb color space, from RGB color space.
//             * Why do we do this? Well, in the RGB color space, chroma and
//             * luma are intertwined. In YCrCb, chroma and luma are separated.
//             * YCrCb is a 3-channel color space, just like RGB. YCrCb's 3 channels
//             * are Y, the luma channel (which essentially just a B&W image), the
//             * Cr channel, which records the difference from red, and the Cb channel,
//             * which records the difference from blue. Because chroma and luma are
//             * not related in YCrCb, vision code written to look for certain values
//             * in the Cr/Cb channels will not be severely affected by differing
//             * light intensity, since that difference would most likely just be
//             * reflected in the Y channel.
//             *
//             * After we've converted to YCrCb, we extract just the 2nd channel, the
//             * Cb channel. We do this because the ducks, and often team shipping elements,
//             * will be a brighter color, such as yellow, and contrast strongly.
//             *
//             * We then take the average pixel value of 3 different regions on that Cb
//             * channel, one positioned over area. After this, we find which has the highest
//             * concentration of color. That will be our element.
//             *
//             * We also draw rectangles on the screen showing where the sample regions
//             * are, as well as drawing a solid rectangle over top the sample region
//             * we believe is on top of the element.
//             *
//             * In order for this whole process to work correctly, each sample region
//             * should be positioned in the center of each of the first 3 tape markers, and
//             * be small enough such that only the duck or team shipping element is sampled,
//             * and not any of the surroundings.
//             */
//
//            /*
//             * Get the Cb channel of the input frame after conversion to YCrCb
//             */
//            inputToCb(input);
//
//            /*
//             * Compute the average pixel value of each submat region. We're
//             * taking the average of a single channel buffer, so the value
//             * we need is at index 0. We could have also taken the average
//             * pixel value of the 3-channel image, and referenced the value
//             * at index 2 here.
//             */
//            avg1 = (int) Core.mean(region1_Cb).val[0];
//            avg2 = (int) Core.mean(region2_Cb).val[0];
//            avg3 = (int) Core.mean(region3_Cb).val[0];
//
//            /*
//             * Draw a rectangle showing sample region 1 on the screen.
//             * Simply a visual aid. Serves no functional purpose.
//             */
//            Imgproc.rectangle(
//                    input, // Buffer to draw on
//                    region1_pointA, // First point which defines the rectangle
//                    region1_pointB, // Second point which defines the rectangle
//                    BLUE, // The color the rectangle is drawn in
//                    2); // Thickness of the rectangle lines
//
//            /*
//             * Draw a rectangle showing sample region 2 on the screen.
//             * Simply a visual aid. Serves no functional purpose.
//             */
//            Imgproc.rectangle(
//                    input, // Buffer to draw on
//                    region2_pointA, // First point which defines the rectangle
//                    region2_pointB, // Second point which defines the rectangle
//                    BLUE, // The color the rectangle is drawn in
//                    2); // Thickness of the rectangle lines
//
//            /*
//             * Draw a rectangle showing sample region 3 on the screen.
//             * Simply a visual aid. Serves no functional purpose.
//             */
//            Imgproc.rectangle(
//                    input, // Buffer to draw on
//                    region3_pointA, // First point which defines the rectangle
//                    region3_pointB, // Second point which defines the rectangle
//                    BLUE, // The color the rectangle is drawn in
//                    2); // Thickness of the rectangle lines
//
//
//            /*
//             * Find the min of the 3 averages
//             */
//            int minOneTwo = Math.min(avg1, avg2);
//            int min = Math.min(minOneTwo, avg3);
//
//            /*
//             * Now that we found the max, we actually need to go and
//             * figure out which sample region that value was from
//             */
//            if(min == avg1) // Was it from region 1?
//            {
//                position = BarcodePosition.LEFT; // Record our analysis
//
//                /*
//                 * Draw a solid rectangle on top of the chosen region.
//                 * Simply a visual aid. Serves no functional purpose.
//                 */
//                Imgproc.rectangle(
//                        input, // Buffer to draw on
//                        region1_pointA, // First point which defines the rectangle
//                        region1_pointB, // Second point which defines the rectangle
//                        GREEN, // The color the rectangle is drawn in
//                        -1); // Negative thickness means solid fill
//            }
//            else if(min == avg2) // Was it from region 2?
//            {
//                position = BarcodePosition.CENTER; // Record our analysis
//
//                /*
//                 * Draw a solid rectangle on top of the chosen region.
//                 * Simply a visual aid. Serves no functional purpose.
//                 */
//                Imgproc.rectangle(
//                        input, // Buffer to draw on
//                        region2_pointA, // First point which defines the rectangle
//                        region2_pointB, // Second point which defines the rectangle
//                        GREEN, // The color the rectangle is drawn in
//                        -1); // Negative thickness means solid fill
//            }
//            else if(min == avg3) // Was it from region 3?
//            {
//                position = BarcodePosition.RIGHT; // Record our analysis
//
//                /*
//                 * Draw a solid rectangle on top of the chosen region.
//                 * Simply a visual aid. Serves no functional purpose.
//                 */
//                Imgproc.rectangle(
//                        input, // Buffer to draw on
//                        region3_pointA, // First point which defines the rectangle
//                        region3_pointB, // Second point which defines the rectangle
//                        GREEN, // The color the rectangle is drawn in
//                        -1); // Negative thickness means solid fill
//            }
//
//            /*
//             * Render the 'input' buffer to the viewport. But note this is not
//             * simply rendering the raw camera feed, because we called functions
//             * to add some annotations to this buffer earlier up.
//             */
//            return input;
//        }
//    }
//
//    /*
//     * Call this from the OpMode thread to obtain the latest analysis
//     */
//    public BarcodePosition getAnalysis()
//    {
//        return position;
//    }
//
//}