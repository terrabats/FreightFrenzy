package unittests.auto.movement;

import autoutil.executors.MecanumExecutor;
import autoutil.generators.LineGenerator;
import autoutil.generators.PoseGenerator;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import autoutil.reactors.mecanum.MecanumPurePursuitReactor;
import unittests.auto.AutoUnitTest;
import static global.General.*;

public class PurePursuitTest extends AutoUnitTest {

    LineGenerator generator = new LineGenerator();
    MecanumPurePursuitReactor reactor = new MecanumPurePursuitReactor();
    MecanumExecutor executor = new MecanumExecutor(linearOpMode);

    @Override
    protected void run() {
//        generator.addAutoModule(automodules.DuckTele);

        generator.addLine(0,40,0);

        executor.setPath(generator.getPath());
        executor.setReactor(reactor);
        executor.followPath();
    }
}