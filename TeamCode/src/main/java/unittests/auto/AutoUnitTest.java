package unittests.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import unittests.UnitTest;
import unittests.tele.TeleUnitTest;
import util.Timer;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.condition.Status;

public class AutoUnitTest extends UnitTest {
    /**
     * Unit test based on auto
     * For init and stop see UnitTest
     * @link UnitTest
     */

    /**
     * Static linear opmode
     */
    public static LinearOpMode linearOpMode;
    /**
     * Private timer for internal methods
     */
    private final Timer timer = new Timer();

    // TODO FIX
    // Randomly at the end of auto test it says there are no items in arraylist?


    /**
     * Run runs once after start
     * NOTE: This is equivalent to loop in TeleUnitTest except it runs once
     */
    protected void run(){}

    /**
     * Test runs the test
     */
    @Override
    public final void test() {
        if(status.equals(Status.IDLE)){
            run();
            status = Status.ACTIVE;
        }
    }

    /**
     * Pause for some amount of seconds
     * @param secs
     */
    protected void pause(double secs){ whileTime(()->linearOpMode.idle(), secs); }

    /**
     * Do something for some amount of time
     * @param code
     * @param secs
     */
    protected void whileTime(CodeSeg code, double secs){
        timer.reset();
        while (linearOpMode.opModeIsActive() && timer.seconds() < secs){
            code.run();
        }
    }

    /**
     * Do something while the opmode is active (default behavior)
     * @param code
     */
    protected void whileActive(CodeSeg code){
        while (linearOpMode.opModeIsActive()){
            code.run();
        }
    }

    /**
     * Do something while the opmode is active and the exit condition is true
     * @param code
     */
    protected void whileActive(ReturnCodeSeg<Boolean> active, CodeSeg code){
        while (linearOpMode.opModeIsActive() && active.run()){code.run();}
    }
}
