package unittests.auto.framework;

import autoutil.executors.MecanumExecutor;
import autoutil.generators.PoseToPoseGenerator;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import unittests.auto.AutoUnitTest;

public class ExecutorTest extends AutoUnitTest {

    PoseToPoseGenerator generator = new PoseToPoseGenerator();
//    MecanumPIDReactor reactor = new MecanumPIDReactor();
//    MecanumExecutor executor = new MecanumExecutor(linearOpMode);

    @Override
    protected void run() {
        whileTime(() -> {}, 2);
//
//        generator.addPose(0,10,0);
//
//
//
//        executor.setPath(generator.getPath());
//        executor.setReactor(reactor);
//        executor.followPath();
    }
}
