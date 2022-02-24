package unittests.auto.framework;

import autoutil.vision.Scanner;
import autoutil.vision.TeamElementScanner;
import unittests.auto.AutoUnitTest;

import static global.General.bot;
import static global.General.log;

public class ExternalCameraTest extends AutoUnitTest {
    TeamElementScanner teamElementScanner = new TeamElementScanner();

    @Override
    public void init() {
        bot.cameras.setExternalScanner(teamElementScanner);
        bot.cameras.startExternalCamera();
    }

    @Override
    protected void run() {
        whileActive(() -> {
            log.show("External Camera FPS", bot.cameras.getInternalFPS());
        });
    }

    @Override
    public void stop() {
        bot.cameras.stopExternalCamera();
    }
}
