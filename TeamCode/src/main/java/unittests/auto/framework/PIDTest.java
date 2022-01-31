package unittests.auto.framework;

import java.util.Arrays;

import autoutil.controllers.PID;
import unittests.auto.AutoUnitTest;

import static global.General.bot;
import static global.General.log;
import static global.General.telemetry;

public class PIDTest extends AutoUnitTest {
    // TODO TEST
    PID movePID = new PID(0.05,0.005,0.005);//0.01

    @Override
    protected void run() {
        log.show("Coefficients ", Arrays.toString(movePID.getCoefficients()));

        movePID.setProcessVariable(() -> bot.odometry.getCurY());
        movePID.setTarget(-20);
        movePID.setMinimumOutput(0.05);
        movePID.setMaximumTime(0.05);
        movePID.setMaximumDerivative(10);

        log.show("Target (20)", movePID.getTarget());

        whileActive(() -> !movePID.isAtTarget(), () -> {
            movePID.update();
            log.show("Error, Output", "\n"+movePID.getError()+", \n"+ movePID.getOutput());
            bot.tankDrive.move(movePID.getOutput(),0);
        });

        movePID.reset();
        bot.tankDrive.move(0,0);

        log.show("Done with movement");

    }
}
