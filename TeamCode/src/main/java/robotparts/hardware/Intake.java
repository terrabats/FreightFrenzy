package robotparts.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import robotparts.RobotPart;
import robotparts.electronics.CMotor;

import static global.General.bot;

public class Intake extends RobotPart {
    private CMotor in;

    @Override
    public void init() {
        in = createCMotor("in", DcMotorSimple.Direction.FORWARD, DcMotor.ZeroPowerBehavior.FLOAT, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void move(double power) {
        in.setPower(power);
    }

    public Main main(double power) {
        return new Main(() -> move(power));
    }

    public Stop stop() {
        return new Stop(() -> move(0));
    }

    public Stage intakeUntilFreight(double power) { return new Stage(
                usePart(),
                main(power),
                bot.colorSensors.exitFreight(),
                stop(),
                returnPart()
    );}
}
