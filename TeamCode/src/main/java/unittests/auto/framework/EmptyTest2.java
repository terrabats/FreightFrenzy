package unittests.auto.framework;

import unittests.auto.AutoUnitTest;
import util.Timer;

import static global.General.log;

public class EmptyTest2 extends AutoUnitTest {
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