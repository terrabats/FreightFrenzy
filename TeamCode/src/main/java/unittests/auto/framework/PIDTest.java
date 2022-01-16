package unittests.auto.framework;

import java.util.Arrays;

import autoutil.controllers.PID;
import unittests.auto.AutoUnitTest;

import static global.General.bot;
import static global.General.log;
import static global.General.telemetry;

public class PIDTest extends AutoUnitTest {
    // TODO COMMENT
    PID movePID = new PID(0.01,0,0);

    @Override
    public void run() {
        log.show("Coefficients (1,0,0)", Arrays.toString(movePID.getCoefficients()));
        log.showTelemetry();

        movePID.setProcessVariable(() -> bot.odometry.getCurY());
        movePID.setTarget(20);
        movePID.setMinimumOutput(0.05);
        movePID.setMaximumTime(0.05);

        log.show("Target (20)", movePID.getTarget());
        log.showTelemetry();

        while (opModeIsActive() && !movePID.isAtTarget()){
            movePID.update();
            log.show("Error", movePID.getError());
            log.show("Output", movePID.getOutput());
            log.showTelemetry();
            bot.tankDrive.move(movePID.getOutput(),0);
        }

        movePID.reset();

        log.show("Done with movement");
        log.showTelemetry();

    }
}
