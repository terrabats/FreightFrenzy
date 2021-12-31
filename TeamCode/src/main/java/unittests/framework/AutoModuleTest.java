package unittests.framework;
import automodules.StageList;
import automodules.stage.Initial;
import automodules.stage.Stage;
import global.Constants;
import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import unittests.UnitTest;

import static global.General.*;

public class AutoModuleTest extends UnitTest {
    /**
     * Test automobiles using intake as an example
     */

    @Override
    protected void start() {
        gph1.link(Button.A, OnPressEventHandler.class,() -> bot.addAutoModule(autoModules.Intake));
        gph1.link(Button.B, OnPressEventHandler.class, bot::cancelAutoModules);
        gph1.link(Button.RIGHT_BUMPER, OnPressEventHandler.class, bot::pauseAutoModules);
        gph1.link(Button.LEFT_BUMPER, OnPressEventHandler.class, bot::resumeAutoModules);
    }

    @Override
    protected void loop() {
        log.show("Click a to start intake");
        log.show("Click b to cancel the AutoModules");
        log.show("Click right bumper to pause the AutoModules");
        log.show("Click left bumper to resume the AutoModules");
    }
}
