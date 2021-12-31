package unittests.framework;

import unittests.UnitTest;
import util.Access;
import util.Timer;

import static global.General.*;

public class AccessTest extends UnitTest {
    private final Access access = new Access();
    private final Timer timer = new Timer();
    private Thread thread;
    private volatile boolean threadAccess = true;

    @Override
    public void init() {
        access.allow();
        thread = new Thread(() -> {
            timer.reset();
            while (timer.seconds() < 2){}
            access.deny();
            threadAccess = access.isAllowed();
            timer.reset();
            while (timer.seconds() < 2){}
            access.allow();
            threadAccess = access.isDenied();
        });
    }

    @Override
    protected void start() {
        thread.start();
    }

    @Override
    protected void loop() {
        // Should both be true until 2 seconds has passed, then access will not be allowed in thread.
        log.showAndRecord("Access is allowed here?", access.isAllowed());
        log.showAndRecord("Access is allowed in thread?", threadAccess);
    }
}
