package unittests.framework;

import geometry.*;
import unittests.UnitTest;

import static global.General.*;

/**
 * NOTE: Uncommented
 */

public class CoordinatePlaneTest extends UnitTest {

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
        log.show("Vector original", coordinatePlane.getVectors().get(0));
        log.show("Line original", coordinatePlane.getLines().get(0));
        log.showAndRecord("Position original", coordinatePlane.getPositions().get(0));
        coordinatePlane.setOrientation(90, AngleType.DEGREES);
        log.show(coordinatePlane.getVectors().get(0));
        log.show("Line final", coordinatePlane.getLines().get(0));
        log.showAndRecord("Position final", coordinatePlane.getPositions().get(0));
        coordinatePlane.rotate(45, AngleType.DEGREES);
        log.show("Second vector final", coordinatePlane.getVectors().get(0));
        log.show("Second line final", coordinatePlane.getLines().get(0));
        log.showAndRecord("Second position final", coordinatePlane.getPositions().get(0));
    }
}
