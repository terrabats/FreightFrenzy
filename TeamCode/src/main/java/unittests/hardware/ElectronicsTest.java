package unittests.hardware;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.checkerframework.checker.units.qual.C;

import robotparts.RobotPart;
import robotparts.electronics.CMotor;
import robotparts.electronics.CServo;
import robotparts.electronics.PMotor;
import robotparts.electronics.PServo;
import teleutil.button.Button;
import teleutil.button.OnPressEventHandler;
import unittests.UnitTest;
import util.User;

import static global.General.gph1;
import static global.General.log;

public class ElectronicsTest extends UnitTest {
    /**
     * Class to test all of the electronics
     * NOTE: This does not test the actual electronic class since theres very few functions in that
     * This class will not work if certain parts of the robot don't exist (like the lift and outtake lock)
     */
    TestPart part = new TestPart();

    @Override
    protected void start() {
        /**
         * Initialize the part and switch the user to tele
         */
        part.init();
        part.switchUser(User.TELE);
        /**
         * Link the gamepad handler
         */
        gph1.link(Button.A, OnPressEventHandler.class, () -> part.in.setPower(0.2));
        gph1.link(Button.B, OnPressEventHandler.class, () -> part.cr.setPower(0.2));
        gph1.link(Button.Y, OnPressEventHandler.class, () -> part.li.setPower(0.2));
        gph1.link(Button.RIGHT_BUMPER, OnPressEventHandler.class, () -> part.lo.setPosition("t"));
    }

    @Override
    protected void loop() {
        /**
         * Controls
         */
        log.show("Press A to move intake");
        log.show("Press B to move carousel servo");
        log.show("Press Y to move lift");
        log.show("Press right bumper to move outtake lock");
        /**
         * All directions should be forward
         */
        log.show("Intake direction",part.in.getDirection());
        log.show("Carousel Servo Direction",part.cr.getDirection());
        log.show("Lift direction", part.li.getDirection());
        log.show("Outtake lock direction",part.lo.getDirection());
        /**
         * Should change when lift moves
         */
        log.show("Lift position",part.li.getPosition());
        /**
         * Should be 0
         */
        log.show("Lift target",part.li.getTarget());
        /**
         * Should change when outtake lock moves
         */
        log.show("Outtake lock position",part.lo.getPosition());
    }

    @Override
    public void stop() {
        /**
         * Halt the part
         */
        part.in.halt();
        part.cr.halt();
        part.li.halt();
    }

    private static class TestPart extends RobotPart{
        /**
         * Initialize motors and servos
         */
        CMotor in;
        CServo cr;
        PMotor li;
        PServo lo;

        @Override
        public void init() {
            in = createCMotor("in", DcMotorSimple.Direction.FORWARD);
            cr = createCServo("cr", DcMotorSimple.Direction.FORWARD);
            li = createPMotor("li", DcMotorSimple.Direction.FORWARD);
            lo = createPServo("lo", Servo.Direction.FORWARD, 0.25, 0.5);
            lo.addPosition("t", 0.3);
        }
    }
}
