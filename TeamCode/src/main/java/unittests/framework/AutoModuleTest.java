package unittests.framework;
import automodules.StageList;
import global.Constants;
import unittests.UnitTest;

import static global.General.*;

public class AutoModuleTest extends UnitTest {

    public StageList test = new StageList(
            stages.turretEncoder(0.2, 90)
    );

    @Override
    public void init() {
        bot.lift.move(Constants.LIFT_REST_POW);
    }

    @Override
    protected void start() {
        bot.addAutoModule(test);
    }
}
