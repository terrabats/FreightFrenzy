package robotparts.hardware.Mecanum;

import static global.General.bot;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import robotparts.RobotPart;
import robotparts.electronics.continuous.CMotor;

public class MecanumIntake extends RobotPart {
    private CMotor in;

    /**
     * Create Intake Motor
     */
    @Override
    public void init() {
        in = createCMotor("in", DcMotorSimple.Direction.FORWARD);
    }

    public void move(double pow) {
        in.setPower(pow);
    }

    private Main mainIntake() { return new Main(() -> move(1)); }

    private Main mainOuttake() { return new Main(() -> move(-1)); }

    private Stop stopIntake() { return new Stop(() -> move(0)); }

    public Stage intakeTime(double s) {
        return new Stage(
            usePart(),
            mainIntake(),
            exitTime(s),
            stopIntake(),
            returnPart()
        );
    }

    public Stage outtakeTime(double s) {
        return new Stage(
            usePart(),
            mainOuttake(),
            exitTime(s),
            stopIntake(),
            returnPart()
        );
    }

    /**
     * Robot keeps on running intake until color sensor detects freight
     * @return
     */
    public Stage intakeUntilFreight() {
        return new Stage(
            usePart(),
            mainIntake(),
            bot.colorSensors.exitFreight(),
            stopIntake(),
            returnPart()
        );
    }

    public Stage intakeAndMoveForwardUntilFreight() {
        return new Stage(
            bot.mecanumDrive.usePart(),
            usePart(),
            bot.mecanumDrive.mainMoveForward(0.2),
            mainIntake(),
            bot.colorSensors.exitFreight(),
            bot.mecanumDrive.stopMove(),
            stopIntake(),
            bot.mecanumDrive.returnPart(),
            returnPart()
        );
    }
}
