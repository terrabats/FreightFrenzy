package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import elements.FieldSide;
import global.Common;
import global.General;
import util.condition.Expectation;
import util.condition.Magnitude;

import static global.General.*;

public abstract class Tele extends OpMode implements Common {
    /**
     * Base class for teleops
     * NOTE: If the methods are overriden then make sure the call super.<method name>
     * this will generate by default if you use @Override
     */


    /**
     * Number of methods run so that the code can check if super calls are being made correctly
     */
    private int numberOfMethodsRun = 0;

    /**
     * Init method runs when the user clicks the init button to run a teleop
     */
    @Override
    public void init() {
        numberOfMethodsRun++;
        reference(this);
    }

    /**
     * Start method runs when the user clicks the start button after init
     */
    @Override
    public void start() {
        numberOfMethodsRun++;
        ready();
    }

    /**
     * Loop method runs over and over after start
     */
    @Override
    public void loop() {
        if(numberOfMethodsRun == 2){
            numberOfMethodsRun++;
        }
        update(true);
    }

    /**
     * Finish runs at the end of the program when the code is stopped
     */
    public void finish(){

    }

    /**
     * Internal stop method, do not override
     */
    @Override
    public final void stop() {
        finish();
//        fault.check("You didn't call to super in one of the overriden methods for teleop",
//                Expectation.EXPECTED, Magnitude.CRITICAL, numberOfMethodsRun == 3, true);

        end();
        numberOfMethodsRun = 0;
    }
}
