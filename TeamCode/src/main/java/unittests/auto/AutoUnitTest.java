package unittests.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import unittests.UnitTest;
import unittests.tele.TeleUnitTest;
import util.Timer;
import util.codeseg.CodeSeg;
import util.codeseg.ReturnCodeSeg;
import util.condition.Status;

public class AutoUnitTest extends UnitTest {
    public static LinearOpMode linearOpMode;
    private final Timer timer = new Timer();

    // TODO FIX
    // Randomyl at the end of auto test it says there are no items in arraylist?
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
        whileTime(()->{}, secs);
    }

    protected void whileTime(CodeSeg code, double secs){
        timer.reset();
        while (linearOpMode.opModeIsActive() && timer.seconds() < secs){
            code.run();
        }
    }
    protected void whileActive(CodeSeg code){
        while (linearOpMode.opModeIsActive()){
            code.run();
        }
    }
    protected void whileActive(ReturnCodeSeg<Boolean> code){
        while (linearOpMode.opModeIsActive() && code.run()){}
    }
}
