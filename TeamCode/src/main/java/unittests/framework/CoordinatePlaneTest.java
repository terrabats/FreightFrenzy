package unittests.framework;

import geometry.*;
import unittests.UnitTest;

import static global.General.*;


public class CoordinatePlaneTest extends FrameworkTest {
    public CoordinatePlane coordinatePlane = new CoordinatePlane();

    @Override
    public void init() {
        coordinatePlane.add(new Vector(0, 10, AngleType.RADIANS));
        coordinatePlane.add(new Line(new Point(1, 10), new Point(5, 10)));
        coordinatePlane.add(new Pose(new Point(2,3), 0));
    }

    @Override
    public void loop() {
        coordinatePlane.setOrientation(0, AngleType.DEGREES);
//        log.display("Vector original", coordinatePlane.getVectors().get(0));
//        log.display("Line original", coordinatePlane.getLines().get(0));
        log.display("Position original", coordinatePlane.getPositions().get(0));
        coordinatePlane.setOrientation(90, AngleType.DEGREES);
//        log.display("Vector final", coordinatePlane.getVectors().get(0));
//        log.display("Line final", coordinatePlane.getLines().get(0));
        log.display("Position final", coordinatePlane.getPositions().get(0));
        coordinatePlane.rotate(45, AngleType.DEGREES);
//        log.display("Second vector final", coordinatePlane.getVectors().get(0));
//        log.display("Second line final", coordinatePlane.getLines().get(0));
        log.display("Second position final", coordinatePlane.getPositions().get(0));
    }
}
