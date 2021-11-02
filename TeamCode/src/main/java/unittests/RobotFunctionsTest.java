package unittests;

import util.Stage;
import util.codeseg.CodeSeg;

import static global.General.*;


public class RobotFunctionsTest extends UnitTest {
    @Override
    public void init() {
        bot.rfsHandler.addToQueue(new Stage(new CodeSeg() {
            @Override
            public void run() {
                bot.mechDrive.move(0,0,0);
            }
        }));
//            @Override
        //() -> bot.mechDrive.move(0.3, 0, 0), () -> bot.mechDrive.move(0,0,0), args -> args[0] > 1.5;
//            public boolean run(double in) {
//                bot.mechDrive.move(0.3, 0, 0);
//                return in > 1.5;
//            }
//            @Override
//            public void runOnStop() {
//                bot.mechDrive.move(0, 0, 0);
//            }
//        });
    }
}
