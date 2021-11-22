package unittests;

import util.Stage;
import util.codeseg.CodeSeg;

import static global.General.*;

public class RobotFunctionsTest extends UnitTest {
    private boolean ran = false;
    @Override
    public void loop() {
        if (!ran) {
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
            ran = true;
        }
    }
}
