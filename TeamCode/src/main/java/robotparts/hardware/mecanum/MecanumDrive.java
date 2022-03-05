package robotparts.hardware.mecanum;

import static global.General.bot;

import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;

import automodules.stage.Exit;
import automodules.stage.Initial;
import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import autoutil.executors.Executor;
import autoutil.executors.MecanumExecutorArcsPID;
import geometry.circles.AngleType;
import geometry.position.Pose;
import math.misc.Logistic;
import robotparts.RobotPart;
import robotparts.electronics.continuous.CMotor;

/**
 * NOTE: Uncommented
 */

/**
 * Create Motors
 */
public class MecanumDrive extends RobotPart {
    private Executor executor;
    private CMotor fr,br,fl,bl;

    @Override
    public void init(){
        fr = createCMotor("fr", Direction.REVERSE);
        br = createCMotor("br", Direction.REVERSE);
        fl = createCMotor("fl", Direction.FORWARD);
        bl = createCMotor("bl", Direction.FORWARD);
    }

    /**
     * Raw movement
     * @param f Forward Power
     * @param s Strafe Power
     * @param t Turn Power
     */
    public void move(double f, double s, double t){
        fr.setPower(f-s-t);
        br.setPower(f+s-t);
        fl.setPower(f+s+t);
        bl.setPower(f-s+t);
    }

    /**
     * Move the robot smoothly
     * @param f Forward Power
     * @param s Strafe Power
     * @param t Turn Power
     */
    public void moveSmooth(double f, double s, double t){
        Logistic movementCurveForward = new Logistic(10,5);
        Logistic movementCurveStrafe = new Logistic(30,6);
        Logistic movementCurveTurn = new Logistic(30,6);
        move(movementCurveForward.fodd(f), movementCurveStrafe.fodd(s), movementCurveTurn.fodd(t));
    }

    public Main mainMoveForward(double pow) { return new Main(() -> move(pow, 0, 0)); }

    /**
     * Stop the robot
     * @return
     */

    public Stop stopMove() { return new Stop(() -> move(0, 0, 0)); }

    /**
     * Initial to set path for executor
     */
    public Initial setPath(Pose[][] poses) {
        return new Initial(() -> {
            executor = new MecanumExecutorArcsPID(() -> true);
            executor.addSetpoint(bot.odometry.getCurX(), bot.odometry.getCurY(),
                    bot.odometry.getCurThetaRad(), AngleType.RADIANS);
            for (Pose[] p : poses) {
                for (int i = 0; i < p.length - 1; i++) {
                    executor.addWaypoint(p[i].p.x, p[i].p.y, p[i].ang, AngleType.RADIANS);
                }
                executor.addSetpoint(p[p.length - 1].p.x, p[p.length - 1].p.y, p[p.length - 1].ang, AngleType.RADIANS);
            }
        });
    }

    /**
     * Main to execute a path
     * @return Main path executor
     */
    private Main executePath() {
        return new Main(executor::update);
    }

    /**
     * Exit to execute a path
     * @return Path finished?
     */
    private Exit exitFinishedPath() {
        return new Exit(executor::finished);
    }

    /**
     * Stage to move to a set of points
     * Autonomous movement in TeleOp
     * @param points List of arrays of points – each array of point is waypoints & 1 setpoint at the end
     * @return Stage to move
     */
    public Stage moveToPoint(Pose[]... points) {
        return new Stage(
            usePart(),
            setPath(points),
            executePath(),
            exitFinishedPath(),
            returnPart()
        );
    }
}
