package auton.now;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import auton.MecanumAuto;
import elements.Case;
import elements.FieldSide;

import static global.General.*;

public class TerraAutoDuck extends MecanumAuto {

    @Override
    public void initAuto() {
//        scan();
        caseDetected = Case.RIGHT;
    }

    @Override
    public void define() {
        addWaypoint(20,20,45);
        addWaypoint(50,30,90);
        addAutoModule(automodules.OneDuck);
        addConcurrentAutoModule(automodules.LiftUpTop);
        addWaypoint(-20,65,135);
        addAutoModule(automodules.ResetLiftAndOuttake);
        addWaypoint(60,70,90);
    }


    @Autonomous(name = "TerraAutoDuckBlue", group = "auto")
    public static class TerraAutoDuckBlue extends TerraAutoDuck {{ fieldSide = FieldSide.BLUE; }}
    @Autonomous(name = "TerraAutoDuckRed", group = "auto")
    public static class TerraAutoDuckRed extends TerraAutoDuckBlue {{ fieldSide = FieldSide.RED; }}
}
