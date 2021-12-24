package unittests.framework;
import automodules.StageList;
import automodules.stage.Stage;
import global.Constants;
import unittests.UnitTest;

import static global.General.*;

public class AutoModuleTest extends UnitTest {

    @Override
    protected void loop() {
        if(gamepad1.x){
            bot.addAutoModule(autoModules.Forward);
        }
        if(gamepad1.y){
            bot.addAutoModule(autoModules.Backward);
        }
        if(gamepad1.a){
            bot.addAutoModule(autoModules.Forward);
        }
        if(gamepad1.x){
            bot.cancelAutoModule();
            // For testing pausing
//            bot.rfsHandler.addAutoModule(new StageList(
//                    new Stage(bot.intake.main(1)),
//                    bot.intake.pause(),
//                    new Stage(bot.intake.stop())
//            ));
        }
//
//        if(gamepad1.x){
//            bot.resumeAutoModule();
//        }
    }
}
