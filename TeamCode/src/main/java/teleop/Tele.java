package teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import elements.FieldSide;
import global.Common;
import global.General;
import static global.General.*;

public abstract class Tele extends OpMode implements Common {
    // Base class for teleops


    @Override
    public void init() {
        reference(this);
    }

    @Override
    public void start() {
        ready();
    }

    @Override
    public void loop() {
        update(true);
    }

    @Override
    public void stop() {
        end();
    }


}
