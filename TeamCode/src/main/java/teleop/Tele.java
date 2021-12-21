package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import elements.FieldSide;
import global.Common;
import global.General;
import static global.General.*;

public abstract class Tele extends OpMode implements Common {
    /**
     * Base class for teleops
     * NOTE: If the methods are overriden then make sure the call super.methodname
     * this will generate by default if you use @Override
     */

    /**
     * Init method runs when the user clicks the init button to run a teleop
     */
    @Override
    public void init() {
        reference(this);
    }
    /**
     * Start method runs when the user clicks the start button after init
     */
    @Override
    public void start() {
        ready();
    }
    /**
     * Loop method runs over and over after start
     */
    @Override
    public void loop() {
        update(true);
    }

    /**
     * Stop method runs either when stop button is played or when the teleop ends
     */
    @Override
    public void stop() {
        end();
    }
}
