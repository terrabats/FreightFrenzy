package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import androidx.annotation.CallSuper;
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
     * Init method runs when the user clicks the init button to run a teleop
     */
    @CallSuper
    @Override
    public void init() {
        reference(this);
    }

    /**
     * Start method runs when the user clicks the start button after init
     */
    @CallSuper
    @Override
    public void start() {
        ready();
    }

    /**
     * Loop method runs over and over after start
     */
    @Override
    public void loop() {
        loop(true);
    }

    /**
     * Method for updating
     * NOTE: When you override loop make sure you call super.loop() inside it
     * @param updateTelemetry
     */
    public void loop(boolean updateTelemetry){
        update(updateTelemetry);
    }

    /**
     * Stop method runs when the program ends
     */
    @CallSuper
    @Override
    public void stop() {
        end();
    }
}
