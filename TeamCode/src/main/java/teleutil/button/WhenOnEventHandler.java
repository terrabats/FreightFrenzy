package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class WhenOnEventHandler extends OnPressEventHandler {
    public boolean on = false;
    private final ParameterCodeSeg runWhenOn;

    public WhenOnEventHandler(Button button, ParameterCodeSeg cs, GamepadHandler gph) {
        super(button, cs, gph);
        runWhenOn = cs;
        this.codeSeg = args -> on = !on;
    }

    @Override
    protected void runArgs(double... args) {
        super.runArgs(args);
        if (on) runWhenOn.run(args);
    }
}
