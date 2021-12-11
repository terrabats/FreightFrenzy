package unittests.framework;
import automodules.StageList;
import global.Constants;

import static global.General.*;

public class AutoModuleTest extends FrameworkTest{

    public StageList test = new StageList(
            stages.turretEncoder(0.2, 90)
    );

    @Override
    public void init() {
        bot.lift.move(Constants.LIFT_REST_POW);
    }

    @Override
    protected void run() {
        bot.addAutoModule(test);
    }
}
