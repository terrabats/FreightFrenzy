package unittests.auto.framework;

import unittests.auto.AutoUnitTest;
import util.Timer;
import static global.General.log;

import static global.General.bot;

import android.app.ActionBar;

public class EmptyTest extends AutoUnitTest {
    Timer timer = new Timer();
    @Override
    public void init() {
        log.showAndRecord("init", "");
    }

    @Override
    protected void run() {
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 1) {
            log.showAndRecord("run", timer.seconds());
            log.showTelemetry();
        }
    }
}