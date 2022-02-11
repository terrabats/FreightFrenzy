package unittests.auto.framework;

import autoutil.executors.MecanumExecutor;
import autoutil.generators.PoseToPoseGenerator;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import unittests.auto.AutoUnitTest;
import static global.General.*;

public class ExecutorTest extends AutoUnitTest {

    PoseToPoseGenerator generator = new PoseToPoseGenerator();
    MecanumPIDReactor reactor = new MecanumPIDReactor();
    MecanumExecutor executor = new MecanumExecutor(linearOpMode);

    @Override
    protected void run() {

//        generator.addAutoModule(autoModules.DuckRiseTele);

        generator.addPose(20,20,0);
//        generator.addPose(40,40,0);



        executor.setPath(generator.getPath());
        executor.setReactor(reactor);
        executor.followPath();
    }
}
