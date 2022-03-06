package auton;

import autoutil.executors.Executor;
import autoutil.executors.MecanumExecutor;
import autoutil.generators.Generator;
import autoutil.generators.LineGenerator;
import autoutil.reactors.Reactor;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import autoutil.reactors.mecanum.MecanumPurePursuitReactor;
import autoutil.reactors.mecanum.MecanumReactor;
import static global.General.*;

public class TerraAuto extends MecanumAuto<MecanumPurePursuitReactor, LineGenerator>{
    @Override
    public void define() {
        reactor = new MecanumPurePursuitReactor();
        generator = new LineGenerator();

//        generator.addAutoModule(automodules.DuckTele);
        generator.addLine(0,40,0);
//        generator.addLine(20,20,0);
    }
}
