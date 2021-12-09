package unittests.framework;
import static global.General.*;

public class AutoModuleTest extends FrameworkTest{
    @Override
    protected void run() {
        bot.addAutoModule(autoModules.Intake);
    }
}
