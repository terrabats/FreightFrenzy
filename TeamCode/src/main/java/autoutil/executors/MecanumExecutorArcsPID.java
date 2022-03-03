package autoutil.executors;

import static global.General.bot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import autoutil.generators.PoseToPoseGenerator;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import geometry.position.Pose;

public class MecanumExecutorArcsPID extends Executor {

    LinearOpMode opMode;

    public MecanumExecutorArcsPID(LinearOpMode opMode) {
        super();
        this.opMode = opMode;
    }

    @Override
    public void move(double f, double t) {
        bot.drive.move(f, 0, t);
    }

    @Override
    public void moveSetpoint(Pose nextPose) {
        PoseToPoseGenerator generator = new PoseToPoseGenerator();
        MecanumExecutorPID executor = new MecanumExecutorPID(opMode);

        double ang = -nextPose.ang; // Make CW Positive
        ang -= Math.PI/2; // Make +y 0
        ang *= 180/Math.PI; // Make it degrees

        generator.addPose(nextPose.p.x, nextPose.p.y, ang);

        executor.setPath(generator.getPath());
        executor.setReactor(new MecanumPIDReactor());
    }
}
