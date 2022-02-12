package auton.rise;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import auton.Auto;
import autoutil.executors.MecanumExecutor;
import autoutil.generators.PoseToPoseGenerator;
import autoutil.reactors.mecanum.MecanumPIDReactor;

@Autonomous(name = "AutoRiseBlue")
public class AutoRiseBlue extends Auto {


    PoseToPoseGenerator generator = new PoseToPoseGenerator();
    MecanumPIDReactor reactor = new MecanumPIDReactor();
//    MecanumExecutor executor = new MecanumExecutor(this);

    @Override
    public void initAuto() {

    }

    @Override
    public void runAuto() {
//        generator.addPose(0,20,90);
//        generator.addPose(50, 20, 90);
//        executor.setPath(generator.getPath());
//        executor.setReactor(reactor);
//        executor.followPath();
    }
}
