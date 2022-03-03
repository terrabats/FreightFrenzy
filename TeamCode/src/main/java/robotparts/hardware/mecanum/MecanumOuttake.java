package robotparts.hardware.mecanum;

import com.qualcomm.robotcore.hardware.Servo;

import automodules.StageList;
import automodules.stage.Main;
import automodules.stage.Stage;
import robotparts.RobotPart;
import robotparts.electronics.positional.PServo;

public class MecanumOuttake extends RobotPart {
    /**
     * orel is Outtake Release Controller
     * over is Outtake Vertical Controller
     * ohor is Outtake Horizontal Controller
     */
    private PServo orel, over, ohor;

    @Override
    public void init() {
        orel = createPServo("orel", Servo.Direction.FORWARD, 0, 1);
        over = createPServo("over", Servo.Direction.FORWARD, 0, 1);
        ohor = createPServo("ohor", Servo.Direction.FORWARD, 0, 1);

        orel.addPosition("released", 1);
        orel.addPosition("locked", 0);

        over.addPosition("locked", 0);
        over.addPosition("released", 1);

        ohor.addPosition("default", 0);
        ohor.addPosition("shared", 1);
    }

    /**
     * Releases the release servo
     */
    public void releaseRelease() { orel.setPosition("released"); }

    /**
     * Locks the release servo
     */
    public void lockRelease() { orel.setPosition("locked"); }

    /**
     * Moves the horizontal servo for shared shipping hub
     */
    public void moveHorizontalForShared() { ohor.setPosition("shared"); }

    /**
     * Moves the horizontal servo for everything except shared shipping hub
     */
    public void resetHorizontal() { ohor.setPosition("default"); }

    /**
     * Moves the vertical servo for locking
     */
    public void lockVertical() { over.setPosition("locked"); }

    /**
     * Moves the vertical servo for releasing
     */
    public void releaseVertical() { over.setPosition("released"); }

    /**
     * Mains for all of the above functions
     * @return Main for related function
     */
    private Main mainReleaseRelease() { return new Main(this::releaseRelease); }
    private Main mainLockRelease() { return new Main(this::lockRelease); }
    private Main mainSharedHorizontal() { return new Main(this::moveHorizontalForShared); }
    private Main mainResetHorizontal() { return new Main(this::resetHorizontal); }
    private Main mainLockVertical() { return new Main(this::lockVertical); }
    private Main mainReleaseVertical() { return new Main(this::releaseVertical); }

    /**
     * Stages for all of the above mains
     * Exit condition is 1 second
     * @return Stage for related main
     */
    private Stage stageRelease() {
        return new Stage(
                usePart(),
                mainReleaseRelease(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageLock() {
        return new Stage(
                usePart(),
                mainLockRelease(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageSharedHorizontal() {
        return new Stage(
                usePart(),
                mainSharedHorizontal(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageResetHorizontal() {
        return new Stage(
                usePart(),
                mainResetHorizontal(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageLockVertical() {
        return new Stage(
                usePart(),
                mainLockVertical(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageReleaseVertical() {
        return new Stage(
                usePart(),
                mainReleaseVertical(),
                exitTime(1),
                returnPart()
        );
    }

    /**
     * Resets the outtake after shared or alliance shipping hub
     * @return StageList to reset
     */
    public StageList reset() {
        return new StageList(
            stageResetHorizontal(),
            stageLock(),
            stageLockVertical()
        );
    }

    /**
     * Moves the outtake for alliance shipping hub
     * @return StageList to move for alliance shipping hub
     */
    public StageList moveForAlliance() {
        return new StageList(
            stageReleaseVertical(),
            stageRelease()
        );
    }

    /**
     * Moves the outtake for shared shipping hub
     * @return StageList to move for shared shipping hub
     */
    public StageList moveForShared() {
        return new StageList(
            stageReleaseVertical(),
            stageSharedHorizontal()
        );
    }

    /**
     * Releases the game element
     * @return StageList to release
     */
    public StageList release() {
        return new StageList(
            stageRelease()
        );
    }
}
