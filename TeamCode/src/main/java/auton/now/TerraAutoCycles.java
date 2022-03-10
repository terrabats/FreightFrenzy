package auton.now;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import auton.MecanumAuto;
import elements.Case;
import elements.FieldSide;

import static global.General.automodules;
import static global.General.bot;

public class TerraAutoCycles extends MecanumAuto {

    @Override
    public void initAuto() {
        scan();
//        caseDetected = Case.RIGHT;
        setBackgroundTasks(bot.lift::holdPosition);
    }

    @Override
    public void define() {
        addWaypoint(0, 30, -60);
        customNumber(2, () -> {
            customCase(Case.RIGHT, () -> {
                addConcurrentAutoModule(automodules.LiftUpTop);
                addSetpoint(15, 55, -135);
            }, Case.CENTER, () -> {
                addConcurrentAutoModule(automodules.LiftUpMiddle);
                addSetpoint(20, 60, -135);
            }, Case.LEFT, () -> {
                addConcurrentAutoModule(automodules.LiftUpBottom);
                addSetpoint(25, 65, -135);
            });
            addAutoModule(automodules.OuttakeDrop);
            addConcurrentAutoModule(automodules.ResetLiftAndOuttake);
            addWaypoint(10, 25, -115);
            addWaypoint(10, -5, -90);
            addWaypoint(-10, -10, -90);
            addConcurrentAutoModule(automodules.IntakeCombined);
            addWaypoint(-80, -10, -90);
            addWaypoint(-10,-10,-90);
            addWaypoint(10,-5,-90);
            addWaypoint(10,25,-115);
        });
        addAutoModule(automodules.OuttakeDrop);
        addConcurrentAutoModule(automodules.ResetLiftAndOuttake);
        addWaypoint(10, 25, -115);
        addWaypoint(10, -5, -90);
        addWaypoint(-10, -10, -90);
        addWaypoint(-70, -10, -90);
        addSetpoint(-70, 50, -90);
    }


    @Autonomous(name = "TerraAutoCyclesBlue", group = "auto")
    public static class TerraAutoCyclesBlue extends TerraAutoCycles {{ fieldSide = FieldSide.BLUE; }}
    @Autonomous(name = "TerraAutoCyclesRed", group = "auto")
    public static class TerraAutoCyclesRed extends TerraAutoCyclesBlue {{ fieldSide = FieldSide.RED; }}
}
