package unittests.tele.hardware;

import static global.General.bot;
import static global.General.*;

import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import unittests.UnitTest;
import util.condition.Status;

public class OdometryServoTest extends UnitTest {
    /**
     * Unit test based on tele
     * For init and stop see UnitTest
     * @link UnitTest
     */


    /**
     * Start method runs once
     */
    protected void start() {
        gph1.link(Button.B, OnPressEventHandler.class, () -> bot.tankDrive.up());
        gph1.link(Button.Y, OnPressEventHandler.class, () -> bot.tankDrive.down());
    }

    protected void loop() {
    }

    /**
     * Run by running start, setting the status to active, and then running loop
     * This ensures that start runs once and then loop runs over and over
     */
    @Override
    public void run(){
        if(status.equals(Status.IDLE)){
            start();
            status = Status.ACTIVE;
        }else{
            loop();
        }
    }
}
