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
    }

    @Override
    public void define() {
//        addWaypoint(0,30,-60);
//        addConcurrentAutoModule(automodules.LiftUpTop);
//        addSetpoint(15,55,-135);
//        addAutoModule(automodules.ResetLiftAndOuttake);
//        addWaypoint(10,25,-115);
//        addWaypoint(10,-5,-90);
//        addWaypoint(-10,-10,-90);
//        addConcurrentAutoModule(automodules.IntakeCombined);
//        addWaypoint(-70,-10,-90);
    }


    @Autonomous(name = "TerraAutoCyclesBlue", group = "auto")
    public static class TerraAutoCyclesBlue extends TerraAutoCycles {{ fieldSide = FieldSide.BLUE; }}
    @Autonomous(name = "TerraAutoCyclesRed", group = "auto")
    public static class TerraAutoCyclesRed extends TerraAutoCyclesBlue {{ fieldSide = FieldSide.RED; }}
}
