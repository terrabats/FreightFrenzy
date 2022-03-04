package unittests.auto.movement;

import autoutil.executors.MecanumExecutor;
import autoutil.generators.PoseGenerator;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import unittests.auto.AutoUnitTest;
import static global.General.*;

public class PurePursuitTest extends AutoUnitTest {

    PoseGenerator generator = new PoseGenerator();
    MecanumPIDReactor reactor = new MecanumPIDReactor();
    MecanumExecutor executor = new MecanumExecutor(linearOpMode);

    @Override
    protected void run() {
        generator.addAutoModule(automodules.DuckTele);

        generator.addPose(0,40,0);

        executor.setPath(generator.getPath());
        executor.setReactor(reactor);
        executor.followPath();
    }
}