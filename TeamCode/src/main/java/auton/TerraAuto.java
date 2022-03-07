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
import elements.Case;
import elements.FieldSide;
import teleop.TerraOp;

import static global.General.*;

public class TerraAuto extends MecanumAuto{

    @Override
    public void initAuto() {
//        scan();
        caseDetected = Case.RIGHT;
    }

    @Override
    public void define() {
        addWaypoint(0,30,45);
        addWaypoint(30,30,90);
        customSide(FieldSide.BLUE,() -> {
            customCase(Case.RIGHT, () -> {
                addSetpoint(50,30,0);
            }, Case.CENTER, () -> {
                addSetpoint(50,30,45);
            }, Case.LEFT, () ->{
                addSetpoint(50,30,90);
            });
        }, FieldSide.RED, () ->{
            customCase(Case.RIGHT, () -> {
                addSetpoint(50,30,0);
            }, Case.CENTER, () -> {
                addSetpoint(50,30,-45);
            }, Case.LEFT, () ->{
                addSetpoint(50,30,-90);
            });
        });
        addAutoModule(automodules.DuckTele);
    }


    @Autonomous(name = "TerraAutoBlue", group = "Auto")
    public static class TerraAutoBlue extends TerraAuto {{ fieldSide = FieldSide.BLUE; }}
    @Autonomous(name = "TerraAutoRed", group = "auto")
    public static class TerraAutoRed extends TerraAutoBlue {{ fieldSide = FieldSide.RED; }}
}
