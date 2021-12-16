package unittests.sensor;

import static global.General.*;

import geometry.Point;
import unittests.UnitTest;

//TOD: TEST THIS

public class OdometryTest extends SensorTest {
    @Override
    public void init() {
        bot.localPlane.add(
                new Point(0, 0),
                new Point(10, 0),
                new Point(0, 10),
                new Point(-10, 0),
                new Point(0, -10)
        );
    }

    @Override
    public void run() {
        //bot.updateCoordinates();

        // TOD: Display points and pos of robot
    }
}
