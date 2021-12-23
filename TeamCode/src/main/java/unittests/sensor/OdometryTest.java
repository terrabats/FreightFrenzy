package unittests.sensor;

import static global.General.*;

import unittests.UnitTest;

//TOD: TEST THIS

public class OdometryTest extends UnitTest {
    @Override
    public void init() {
//        bot.localPlane.add(
//                new Point(0, 0),
//                new Point(10, 0),
//                new Point(0, 10),
//                new Point(-10, 0),
//                new Point(0, -10)
//        );
    }

    @Override
    public void start() {
        // TOD: Display points and pos of robot
    }

    @Override
    protected void loop() {
        bot.odometry.update();
        log.show("x", bot.odometry.getCurX());
        log.show("y", bot.odometry.getCurY());
        log.show("h", bot.odometry.getCurThetaDeg());
    }
}
