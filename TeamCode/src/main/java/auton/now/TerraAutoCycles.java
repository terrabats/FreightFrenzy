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
//        scan();
        caseDetected = Case.RIGHT;
    }

    @Override
    public void define() {
        // TODO FINISH


        addWaypoint(0,30,-60);
        addSetpoint(15,55,-135);
        addAutoModule(automodules.LiftUpTop);
        addWaypoint(10,25,-115);
        addWaypoint(10,-5,-90);
        addWaypoint(-10,-10,-90);
        addWaypoint(-70,-10,-90);


//        addWaypoint(-70, 50, -90);


//        addPose(0,0,0);
//        addPose(0,30,-60);
//        addPose(15,55,-135);
//        addPose(10,25,-115);
//        addPose(10,-5,-90);
//        addPose(-10,-10,-90);
//        addPose(-70,-10,-90);
//        addPose(-70, 50, -90);

    }


    @Autonomous(name = "TerraAutoCyclesBlue", group = "auto")
    public static class TerraAutoCyclesBlue extends TerraAutoCycles {{ fieldSide = FieldSide.BLUE; }}
    @Autonomous(name = "TerraAutoCyclesRed", group = "auto")
    public static class TerraAutoCyclesRed extends TerraAutoCyclesBlue {{ fieldSide = FieldSide.RED; }}
}
