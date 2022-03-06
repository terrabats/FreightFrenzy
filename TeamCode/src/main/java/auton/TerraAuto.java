package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import autoutil.executors.Executor;
import autoutil.executors.MecanumExecutor;
import autoutil.generators.Generator;
import autoutil.generators.LineGenerator;
import autoutil.reactors.Reactor;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import autoutil.reactors.mecanum.MecanumPurePursuitReactor;
import autoutil.reactors.mecanum.MecanumReactor;
import static global.General.*;

@Autonomous(name="TerraAuto")
public class TerraAuto extends MecanumAuto{
    @Override
    public void define() {
        addAutoModule(automodules.DuckTele);
        addSetpoint(0,20,0);
        addWaypoint(0,40,0);
        addConcurrentAutoModule(automodules.DuckTele);
        addWaypoint(20,40,0);
        addWaypoint(20,20,0);
        addSetpoint(0,0,0);
    }
}
