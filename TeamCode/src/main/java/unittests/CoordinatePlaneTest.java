package unittests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import geometry.*;
import global.Common;
import robot.TerraBot;

import static global.General.*;

// WORKS!

public class CoordinatePlaneTest extends UnitTest {
    public CoordinatePlane coordinatePlane = new CoordinatePlane();

    @Override
    public void init() {
        coordinatePlane.add(new Vector(0, 10, AngleType.RADIANS));
        coordinatePlane.add(new Line(new Point(1, 10), new Point(5, 10)));
    }

    @Override
    public void loop() {
        coordinatePlane.setOrientation(0, AngleType.DEGREES);
        log.display("Vector original", coordinatePlane.getVectors().get(0));
        log.display("Line original", coordinatePlane.getLines().get(0));
        coordinatePlane.setOrientation(90, AngleType.DEGREES);
        log.display("Vector final", coordinatePlane.getVectors().get(0));
        log.display("Line final", coordinatePlane.getLines().get(0));
        coordinatePlane.rotate(45, AngleType.DEGREES);
        log.display("Vector final2", coordinatePlane.getVectors().get(0));
        log.display("Line final2", coordinatePlane.getLines().get(0));
    }
}
