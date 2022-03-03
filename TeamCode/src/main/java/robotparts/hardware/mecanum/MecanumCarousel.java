package robotparts.hardware.mecanum;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static global.General.*;

import automodules.stage.Main;
import automodules.stage.Stage;
import automodules.stage.Stop;
import elements.FieldSide;
import robotparts.RobotPart;
import robotparts.electronics.continuous.CMotor;

public class MecanumCarousel extends RobotPart {
    private CMotor carousel;

    @Override
    public void init() {
        carousel = createCMotor("car", DcMotorSimple.Direction.FORWARD);
    }

    public void move(double power) {
        carousel.setPower(power * (fieldSide == FieldSide.BLUE ? -1 : 1));
    }

    private Main mainSpin() { return new Main(() -> move(1)); }

    private Stop stopSpin() { return new Stop(() -> move(0)); }

    public Stage spin(double time) {
        return new Stage(
            usePart(),
            mainSpin(),
            exitTime(time),
            stopSpin(),
            returnPart()
        );
    }

}
