package unittests;

import util.condition.Status;

import static global.General.*;


public class LoggerTest extends UnitTest {
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
        log.display("log.display is working");
        log.monitor("Double", d);
        log.monitor("Boolean", b);
        log.watch("Status Now", s);
        log.save("All Status", s);
    }
}
