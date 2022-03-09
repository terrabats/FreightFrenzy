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
        addAutoModule(automodules.OneDuck);
    }


    @Autonomous(name = "TerraAutoDuckBlue", group = "auto")
    public static class TerraAutoDuckBlue extends TerraAutoDuck {{ fieldSide = FieldSide.BLUE; }}
    @Autonomous(name = "TerraAutoDuckRed", group = "auto")
    public static class TerraAutoDuckRed extends TerraAutoDuckBlue {{ fieldSide = FieldSide.RED; }}
}
