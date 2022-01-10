package unittests.framework;

import unittests.UnitTest;
import util.condition.Status;

import static global.General.*;


public class LoggerTest extends UnitTest{
    /**
     * Used to test logger
     */


    /**
     * Variables that are constantly changing
     */
    private double d = 0;
    private boolean b = false;
    private Status s = Status.ACTIVE;

    @Override
    public void loop() {
        d += 0.1;
        b = !b;
        if(s.equals(Status.ACTIVE)){
            s = Status.IDLE;
        }else{
            s = Status.ACTIVE;
        }
        /**
         * Show should just show telemetry like normal
         */
        log.show("Double", d);
        log.show("Boolean", b);
        log.show("log.display is working");
        /**
         * Show and record should show telemetry and record the values to the logcat
         */
        log.showAndRecord("Status Now", s);
        log.showAndRecord("Status Now 2", s);
        /**
         * Record just records to logcat
         */
        log.record("Last Status", s);
    }
}
