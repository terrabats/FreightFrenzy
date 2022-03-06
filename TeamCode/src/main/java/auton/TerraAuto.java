package auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import autoutil.executors.Executor;
import autoutil.executors.MecanumExecutor;
import autoutil.generators.Generator;
import autoutil.generators.LineGenerator;
import autoutil.reactors.Reactor;
import autoutil.reactors.mecanum.MecanumPIDReactor;
import autoutil.reactors.mecanum.MecanumPurePursuitReactor;
import autoutil.reactors.mecanum.MecanumReactor;
import elements.FieldSide;
import teleop.TerraOp;

import static global.General.*;

public class TerraAuto extends MecanumAuto{

    @Override
    public void define() {
        addWaypoint(0,30,45);
        addWaypoint(30,30,90);
        customSide(() -> {
            addSetpoint(50,30,60);
        }, () ->{
            addSetpoint(50,30,120);
        });
        addAutoModule(automodules.DuckTele);
    }


    @Autonomous(name = "TerraAutoBlue", group = "Auto")
    public static class TerraAutoBlue extends TerraAuto {{ fieldSide = FieldSide.BLUE; }}
    @Autonomous(name = "TerraAutoRed", group = "auto")
    public static class TerraAutoRed extends TerraAutoBlue {{ fieldSide = FieldSide.RED; }}
}
