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

    public StageList testPause;
    @Override
    public void init() {
        testPause = new StageList(
                new Stage(
                        bot.intake.usePart(),
                        bot.intake.main(1),
                        bot.intake.exitTime(1)
//                        bot.intake.exitTime(1),
//                        bot.intake.stop(),
//                        bot.intake.returnPart()
                )
//                new Stage(
//                        bot.intake.exitTime(1)
//                ),
//                new Stage(
//                        bot.intake.exit(),
//                        bot.intake.stop(),
//                        bot.intake.returnPart()
//                )
//                new Stage(
//                        bot.intake.exitTime(1)),
////                bot.intake.pause(),
//                new Stage(
//                        bot.intake.stop(),
//                        bot.intake.returnPart()
//                )
        );
    }

    @Override
    protected void start() {
        gph1.link(Button.A, OnPressEventHandler.class, () -> bot.addAutoModule(testPause));
//        gph1.link(Button.A, OnPressEventHandler.class,() -> bot.addAutoModule(autoModules.Intake));
        gph1.link(Button.B, OnPressEventHandler.class,() -> bot.addAutoModule(autoModules.Backward));
        gph1.link(Button.Y, OnPressEventHandler.class,() -> bot.addAutoModule(autoModules.Forward));
//        gph1.link(Button.X, OnPressEventHandler.class, () -> bot.resumeAutoModule());
//        gph1.link(Button.X, OnPressEventHandler.class, () -> bot.cancelAutoModule());
    }
}
