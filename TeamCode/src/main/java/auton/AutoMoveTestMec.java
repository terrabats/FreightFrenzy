package auton;

import static java.lang.Math.PI;
import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import autoutil.executors.MecanumExecutor;
import autoutil.executors.MecanumExecutorPID;
import elements.FieldSide;
import geometry.circles.AngleType;

@Disabled
@Autonomous(name="AutoMoveTestMec")
public class AutoMoveTestMec extends CompleteAuto {
    @Override
    public void initAuto() {
        activate(FieldSide.BLUE);
    }

    @Override
    public void defineExecutor() {
        executor = new MecanumExecutor();
    }

    @Override
    public void setPoints() {
        executor.addSetpoint(60, 60, PI/2, AngleType.RADIANS);
    }

    @Override
    public void onEnd() {
        bot.mecanumDrive.move(0, 0, 0);
    }
}
