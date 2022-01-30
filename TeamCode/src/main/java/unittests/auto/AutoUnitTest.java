package unittests.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import unittests.UnitTest;
import unittests.tele.TeleUnitTest;
import util.Timer;
import util.condition.Status;

public class AutoUnitTest extends UnitTest {
    public static LinearOpMode linearOpMode;
    private Timer timer = new Timer();
    /**
     * Unit test based on auto
     * For init and stop see UnitTest
     * @link UnitTest
     */

    /**
     * Run runs once after start
     * NOTE: This is equivalent to loop in TeleUnitTest except it runs once
     */
    protected void run(){}

    @Override
    public final void test() {
        if(status.equals(Status.IDLE)){
            run();
            status = Status.ACTIVE;
        }
    }

    protected void pause(double secs){
        timer.reset();
        while (opModeIsActive() && timer.seconds() < secs){linearOpMode.idle();}
    }

    protected final boolean opModeIsActive(){return linearOpMode.opModeIsActive();}
}
