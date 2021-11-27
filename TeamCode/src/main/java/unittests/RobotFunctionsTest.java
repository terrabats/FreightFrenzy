package unittests;

import util.Stage;
import util.codeseg.CodeSeg;

import static global.General.*;

public class RobotFunctionsTest extends UnitTest {
    @Override
    public void run() {
        bot.rfsHandler.addToQueue(new Stage() {
            @Override
            public boolean run(double in) {
                bot.mechDrive.move(0.3, 0, 0);
                return in > 1.5;
            }

            @Override
            public void runOnStop() {
                bot.mechDrive.move(0, 0, 0);
            }
        });
        bot.rfsHandler.addToQueue(new Stage() {
            @Override
            public boolean run(double in) {
                bot.mechDrive.move(-0.3, 0, 0);
                return in > 1;
            }

            @Override
            public void runOnStop() {
                bot.mechDrive.move(0, 0, 0);
            }
        });
        bot.rfsHandler.addToQueue(new Stage() {
            @Override
            public boolean run(double in) {
                bot.mechDrive.move(0, 0, 0.2);
                return in > 1;
            }

            @Override
            public void runOnStop() {
                bot.mechDrive.move(0, 0, 0);
            }
        });

//        bot.rfsHandler.addToQueue(new Stage() {
//            @Override
//            public boolean run(double in) {
//                bot.tankDrive.move(0.3, 0);
//                return in > 1.5;
//            }
//
//            @Override
//            public void runOnStop() {
//                bot.tankDrive.move(0, 0);
//            }
//        });
    }
}
