package unittests.framework;

import unittests.UnitTest;
import util.condition.Status;

import static global.General.*;


public class LoggerTest extends UnitTest{
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
        log.show("Double", d);
        log.show("Boolean", b);
        log.show("log.display is working");
        log.showAndRecord("Status Now", s);
        log.showAndRecord("Status Now 2", s);
        log.record("Last Status", s);
    }
}
