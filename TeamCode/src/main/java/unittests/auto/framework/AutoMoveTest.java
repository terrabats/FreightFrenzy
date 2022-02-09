package unittests.auto.framework;

import autoutil.controllers.PID;
import unittests.auto.AutoUnitTest;

import static global.General.bot;

public class AutoMoveTest extends AutoUnitTest {

    PID xPID = new PID(0.05,0.005,0.005, 0.05, 0.05);
    PID yPID = new PID(0.05,0.005,0.005, 0.05, 0.05);
    PID hPID = new PID(0.05,0.005,0.005, 0.05 , 0.05);

    @Override
    protected void run() {
//        log.show("Coefficients ", Arrays.toString(yPID.getCoefficients()));

        yPID.setProcessVariable(() -> bot.odometry2.getCurY());
        xPID.setProcessVariable(() -> bot.odometry2.getCurX());

        yPID.setMinimumOutput(0.05);
        yPID.setMaximumTime(0.05);
        yPID.setMaximumDerivative(10);

        xPID.setMinimumOutput(0.05);
        xPID.setMaximumTime(0.05);
        xPID.setMaximumDerivative(10);


        yPID.setTarget(-20);
        xPID.setTarget(-20);

//        log.show("Target (20)", yPID.getTarget());

        whileActive(() -> !yPID.isAtTarget() && !xPID.isAtTarget(), () -> {
            yPID.update();
            xPID.update();
//            log.show("Error, Output", "\n"+ yPID.getError()+", \n"+ yPID.getOutput());
            bot.mecanumDrive.move(yPID.getOutput(), xPID.getOutput(), 0);
        });

        yPID.reset();
        xPID.reset();

        bot.tankDrive.move(0,0);
//
//        log.show("Done with movement");

    }
}
