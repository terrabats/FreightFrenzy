package unittests;

import util.condition.Status;

public class UnitTest {
    protected Status status = Status.IDLE;
    public void init() {}
    public void test(){
        if(status.equals(Status.IDLE)){
            run();
            status = Status.ACTIVE;
        }else{
            loop();
        }
    }
    protected void run() {}
    protected void loop() {}
    public void stop() {}
    public void start() {}
}
