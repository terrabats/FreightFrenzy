package auton;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import androidx.annotation.CallSuper;
import elements.FieldSide;
import global.Common;
import static global.General.*;

public abstract class Auto extends LinearOpMode implements Common {
    /**
     * Base class for autons
     * NOTE: If the methods are overriden then make sure the call super.<method name>
     * this will generate by default if you use @Override
     */


    @CallSuper
    public void initAuto(){
        reference(this);
    }

    @CallSuper
    public void runAuto(){ ready(); }


    @Override
    public final void runOpMode() throws InterruptedException {
        initAuto();
        waitForStart();
        runAuto();
        stopAuto();
    }

    @CallSuper
    public void stopAuto() {
        end();
    }
}
