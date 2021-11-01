package debugging;

import util.Timer;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public class Fault {
    private int checkNum = 0;
    private final Timer lagTimer = new Timer();
    private double lastLagTime;

    public Fault() {
        lastLagTime = lagTimer.seconds();
    }

    public double getUpdateSpeed() {
        double newTime = lagTimer.seconds();
        double deltaTime = newTime - lastLagTime;
        lastLagTime = newTime;
        return deltaTime;
    }

    public void check(String msg, Expectation e, Magnitude m, boolean test){
        checkNum++;
        if(!test){
            String out = "Fault: " + checkNum + " Msg: " + msg + " Exp: " + e.toString() + " Mag: " + m.toString();
            log.display(out);
            throw new RuntimeException(out);
        }
    }

}
