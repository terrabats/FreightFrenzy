package unittests;

import util.condition.Status;

public class UnitTest {
    protected Status testStatus = Status.IDLE;
    public UnitTest test(){
        testStatus = Status.ACTIVE;
        return this;
    }
    public boolean isActive(){
        return testStatus.equals(Status.ACTIVE);
    }
    public void init() {}
    public void loop() {}
    public void stopIfTest() { if (isActive()) stop(); }
    protected void stop() {}
    public void startIfTest() { if (isActive()) start(); }
    protected void start() {}
}
