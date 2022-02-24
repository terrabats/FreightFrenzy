package autoutil.vision;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

import org.opencv.core.Point;

import java.util.Arrays;
import java.util.Collections;

import elements.Case;

import static autoutil.vision.Processor.BLUE;
import static autoutil.vision.Processor.GREEN;

public abstract class Scanner extends OpenCvPipeline {

    private volatile Case caseDetected;

    private final Processor processor = new Processor();

    private final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(109,98);
    private final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(181,98);
    private final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(253,98);
    private final int REGION_WIDTH = 40;
    private final int REGION_HEIGHT = 40;

    private final int width = 320;
    private final int height = 240;

    /**
     * Point A is top left and B is bottom right
     */

    Point region1_pointA = new Point(REGION1_TOPLEFT_ANCHOR_POINT.x, REGION1_TOPLEFT_ANCHOR_POINT.y);
    Point region1_pointB = new Point(REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH, REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region2_pointA = new Point(REGION2_TOPLEFT_ANCHOR_POINT.x, REGION2_TOPLEFT_ANCHOR_POINT.y);
    Point region2_pointB = new Point(REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH, REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region3_pointA = new Point(REGION3_TOPLEFT_ANCHOR_POINT.x, REGION3_TOPLEFT_ANCHOR_POINT.y);
    Point region3_pointB = new Point(REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH, REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    Mat region1_Cb, region2_Cb, region3_Cb;

    int avg1, avg2, avg3;




    @Override
    public void init(Mat firstFrame)
    {
        processor.toCb(firstFrame);

        region1_Cb = processor.getCbSubmat(region1_pointA, region1_pointB);
        region2_Cb = processor.getCbSubmat(region2_pointA, region2_pointB);
        region3_Cb = processor.getCbSubmat(region3_pointA, region3_pointB);
    }

    @Override
    public Mat processFrame(Mat input)
    {

        processor.toCb(input);

        avg1 = processor.getAverage(region1_Cb);
        avg2 = processor.getAverage(region2_Cb);
        avg3 = processor.getAverage(region3_Cb);

        processor.drawRectangle(input, region1_pointA, region1_pointB, BLUE);
        processor.drawRectangle(input, region2_pointA, region2_pointB, BLUE);
        processor.drawRectangle(input, region3_pointA, region3_pointB, BLUE);

        int min = Collections.max(Arrays.asList(avg1, avg2, avg3));

        if(min == avg1) {
            caseDetected = Case.LEFT;
            processor.drawFilledRectangle(input, region1_pointA, region1_pointB, GREEN);
        } else if(min == avg2) {
            caseDetected = Case.CENTER;
            processor.drawFilledRectangle(input, region2_pointA, region2_pointB, GREEN);
        } else if(min == avg3) {
            caseDetected = Case.RIGHT;
            processor.drawFilledRectangle(input, region3_pointA, region3_pointB, GREEN);
        }

        return input;
    }

    public Case getCase(){
        return caseDetected;
    }
}