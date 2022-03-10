package auton.now;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import auton.MecanumAuto;
import elements.Case;
import elements.FieldSide;

import static global.General.*;

public class TerraAutoDuck extends MecanumAuto {

    @Override
    public void initAuto() {
        scan();
//        caseDetected = Case.RIGHT;
        setBackgroundTasks(bot.lift::holdPosition);
    }

    @Override
    public void define() {
        addWaypoint(20,10,45);
        addSetpoint(30,20,90);
        addAutoModule(automodules.OneDuckAuto);
        customCase(Case.RIGHT, () -> {
            addSetpoint(-28,55,135);
            addAutoModule(automodules.AllianceLiftUp(automodules.LiftUpTop));
        }, Case.CENTER, () -> {
            addSetpoint(-35,60,135);
            addAutoModule(automodules.AllianceLiftUp(automodules.LiftUpMiddle));
        }, Case.LEFT, () -> {
            addSetpoint(-40,65,135);
            addAutoModule(automodules.AllianceLiftUp(automodules.LiftUpBottom));
        });
        addAutoModule(automodules.ResetLiftAndOuttake);
        addSetpoint(30,62,90);
    }


    @Autonomous(name = "TerraAutoDuckBlue", group = "auto")
    public static class TerraAutoDuckBlue extends TerraAutoDuck {{ fieldSide = FieldSide.BLUE; }}
    @Autonomous(name = "TerraAutoDuckRed", group = "auto")
    public static class TerraAutoDuckRed extends TerraAutoDuckBlue {{ fieldSide = FieldSide.RED; }}
}
