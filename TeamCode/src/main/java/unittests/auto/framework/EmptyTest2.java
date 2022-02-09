package unittests.auto.framework;

import unittests.auto.AutoUnitTest;
import util.Timer;

import static global.General.log;

public class EmptyTest2 extends AutoUnitTest {
    Timer timer = new Timer();
    @Override
    public void init() {
        log.showAndRecord("init", "");
        timer.reset();
    }

    @Override
    protected void run() {
        whileTime(() -> log.showAndRecord("run", timer.seconds()), 1);
    }
}