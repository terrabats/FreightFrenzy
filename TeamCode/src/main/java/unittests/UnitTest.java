package unittests;

import util.condition.Status;

import static global.General.*;

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
    public void stop() {bot.halt();}
    public void start() {}
    public Type getType(){
        return Type.DEFAULT;
    }
    public enum Type {
        DEFAULT,
        FRAMEWORK,
        HARDWARE,
        SENSOR
    }
}
