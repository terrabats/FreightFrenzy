package unittests;

import java.util.ArrayList;
import java.util.TreeMap;

import util.condition.Status;

public class UnitTest {
    protected Status testStatus = Status.IDLE;
    public UnitTest(){
        UnitTester.allUnitTests.add(this);
    }
    public void test(){
        testStatus = Status.ACTIVE;
    }
    public boolean active(){
        return testStatus.equals(Status.ACTIVE);
    }
    public void init(){

    }
    public void loop(){

    }
    public void stop(){

    }

}
