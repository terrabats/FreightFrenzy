package unittests;

import static global.General.*;

import geometry.Point;

// TODO: TEST THIS

public class OdometryTest extends UnitTest {
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

        // TODO: Display points and pos of robot
    }
}
