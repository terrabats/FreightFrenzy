package unittests.tele.framework;

import com.qualcomm.robotcore.hardware.DcMotor;

import robot.TerraBot;
import unittests.tele.TeleUnitTest;
import util.TerraThread;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

// TODO TEST VARINI
// Test this
public class ThreadTest extends TeleUnitTest {
    /**
     * Tests two new two threads on the robot by setting their execution code
     */


    /**
     * Test threads
     */
    TerraThread testThread;
    TerraThread testThread2;


    /**
     * Create and start threads in init
     */
    @Override
    public void init() {
        testThread = new TerraThread();
        testThread.start();
        testThread2 = new TerraThread();
        testThread2.start();
    }



    @Override
    public void loop() {
        /**
         * One thread will crash, so a fault message should appear
         */
        testThread.setExecutionCode(() -> {
            throw new RuntimeException();
        });
        /**
         * Should display this
         * NOTE: This may clash with telemetry in the main thread so it might appear on an off
         */
        testThread2.setExecutionCode(() -> {
            log.show("Hello from testThread2");
        });
    }

    /**
     * Stop updating the threads
     */
    @Override
    public void stop() {
        testThread.stopUpdating();
        testThread2.stopUpdating();
    }
}
