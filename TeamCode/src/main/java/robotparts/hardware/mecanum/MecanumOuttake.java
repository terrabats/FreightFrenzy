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
    private PServo od, or, ol, ot;

    @Override
    public void init() {
        od = createPServo("od", Servo.Direction.FORWARD, 0, 1);
        or = createPServo("or", Servo.Direction.FORWARD, 0, 1);
        ol = createPServo("ol", Servo.Direction.REVERSE, 0, 1);
        ot = createPServo("ot", Servo.Direction.FORWARD, 0, 1);

        od.addPosition("releaseLeft", 0.0);
        od.addPosition("lock", 0.53);
        od.addPosition("releaseRight", 0.9);

        or.addPosition("start", 0.1);
        or.addPosition("horizontal", 1);
        ol.addPosition("start", 0.05);
        ol.addPosition("horizontal", 0.92);

        ot.addPosition("sharedLeft", 0.18);
        ot.addPosition("center", 0.555);
        ot.addPosition("sharedRight", 0.9);

        lock();
        centerTurret();
//        turnToStart();
    }

    /**
     * Releases the release servo
     */
    public void drop() { od.setPosition("releaseRight"); }

    /**
     * Locks the release servo
     */
    public void lock() { od.setPosition("lock"); }

    /**
     * Moves the horizontal servo for shared shipping hub
     */
    public void sharedTurretRight() { ot.setPosition("sharedRight"); }

    /**
     * Moves the horizontal servo for shared shipping hub
     */
    public void sharedTurretLeft() { ot.setPosition("sharedLeft"); }

    /**
     * Moves the horizontal servo for everything except shared shipping hub
     */
    public void centerTurret() { ot.setPosition("center"); }

    /**
     * Moves the vertical servo for locking
     */
    public void turnToStart() {
        or.setPosition("start");
        ol.setPosition("start");
    }

    /**
     * Moves the vertical servo for releasing
     */
    public void turnToHorizontal() {
        or.setPosition("horizontal");
        ol.setPosition("horizontal");
    }

    /**
     * Mains for all of the above functions
     * @return Main for related function
     */
    private Main mainDrop() { return new Main(this::drop); }
    private Main mainLock() { return new Main(this::lock); }
    private Main mainCenterTurret() { return new Main(this::centerTurret); }
    private Main mainSharedTurret() { return new Main(this::sharedTurretRight); }
    private Main mainTurnToStart() { return new Main(this::turnToStart); }
    private Main mainTurnToHorizontal() { return new Main(this::turnToHorizontal); }

    /**
     * Stages for all of the above mains
     * Exit condition is 1 second
     * @return Stage for related main
     */
    private Stage stageDrop() {
        return new Stage(
                usePart(),
                mainDrop(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageLock() {
        return new Stage(
                usePart(),
                mainLock(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageCenterTurret() {
        return new Stage(
                usePart(),
                mainCenterTurret(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageSharedTurret() {
        return new Stage(
                usePart(),
                mainSharedTurret(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageTurnToStart() {
        return new Stage(
                usePart(),
                mainTurnToStart(),
                exitTime(1),
                returnPart()
        );
    }
    private Stage stageTurnToHorizontal() {
        return new Stage(
                usePart(),
                mainTurnToHorizontal(),
                returnPart()
        );
    }

    /**
     * Resets the outtake after shared or alliance shipping hub
     * @return StageList to reset
     */
    public StageList moveForReset() {
        return new StageList(
            stageCenterTurret(),
            stageTurnToStart(),
            stageDrop()
        );
    }

    /**
     * Moves the outtake for alliance shipping hub
     * @return StageList to move for alliance shipping hub
     */
    public StageList moveForAlliance() {
        return new StageList(
            stageTurnToHorizontal(),
            stageCenterTurret()
        );
    }

    /**
     * Moves the outtake for shared shipping hub
     * @return StageList to move for shared shipping hub
     */
    public StageList moveForShared() {
        return new StageList(
            stageTurnToHorizontal(),
            stageSharedTurret()
        );
    }

    /**
     * Releases the game element
     * @return StageList to release
     */
    public StageList moveForDrop() {
        return new StageList(
            stageDrop()
        );
    }
}
