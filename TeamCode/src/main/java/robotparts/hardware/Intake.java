package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import robotparts.RobotPart;
import robotparts.electronics.continuous.CMotor;

import static global.General.bot;

public class Intake extends RobotPart {
    /**
     * Intake motor
     */
    private CMotor in;

    /**
     * Init method creates the intake motor
     */
    @Override
    public void init() {
        in = createCMotor("in", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.FLOAT, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Moves the intake motor at the given power
     * @param power
     */
    public void move(double power) {
        in.setPower(power);
    }

    /**
     * Moves the intake in a main (for automodules)
     * @param power
     * @return main
     */
    public Main main(double power) {
        return new Main(() -> move(power));
    }

    /**
     * Stops the intake
     * @return stop
     */
    public Stop stop() {
        return new Stop(() -> move(0));
    }

    /**
     * Turns on the intake until a freight is detected
     * NOTE: Uses the colorsensor to detect this
     * @param power
     * @return stage
     */
    public Stage intakeUntilFreight(double power) { return new Stage(
        usePart(),
        main(power),
        bot.colorSensors.exitFreight(),
        stop(),
        returnPart()
    );}

    public Stage intakeUntilFreightLiftDown(double power) { return new Stage(
            usePart(),
            bot.lift.usePart(),
            bot.lift.main(-0.2),
            main(power),
            bot.colorSensors.exitFreight(),
            bot.lift.returnPart(),
            returnPart()
    );}
}
