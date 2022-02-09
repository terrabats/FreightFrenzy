package autoutil.reactors.mecanum;

import java.util.ArrayList;

import autoutil.controllers.PID;
import util.codeseg.ParameterCodeSeg;

import static global.General.bot;

public class MecanumPIDReactor extends MecanumReactor{
    public PID xPID = new PID(0.05,0.005,0.005, 0.05, 0.05);
    public PID yPID = new PID(0.05,0.005,0.005, 0.05, 0.05);
    public PID hPID = new PID(0.05,0.005,0.005, 0.05 , 0.05);

    public MecanumPIDReactor(){
        xPID.setProcessVariable(() -> getPose()[0]); yPID.setProcessVariable(() -> getPose()[1]); hPID.setProcessVariable(() -> getPose()[2]);
        addControllers(xPID, yPID, hPID);
    }



    @Override
    public void setTarget(double[] target) {
        xPID.setTarget(target[0]); yPID.setTarget(target[1]); hPID.setTarget(target[2]);
    }

    @Override
    public void nextTarget() {
        xPID.reset(); yPID.reset(); hPID.reset();
    }

    @Override
    public boolean isAtTarget() {
        return xPID.isAtTarget() && yPID.isAtTarget() && hPID.isAtTarget();
    }

    @Override
    public void moveToTarget() {
        xPID.update(); yPID.update(); hPID.update();
        bot.mecanumDrive.move(yPID.getOutput(), xPID.getOutput(), hPID.getOutput());
    }
}
