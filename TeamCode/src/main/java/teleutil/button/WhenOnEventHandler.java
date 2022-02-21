package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

/**
 * Occurs (multiple times) when the toggler is on
 */
public class WhenOnEventHandler extends OnPressEventHandler {
    public boolean on = false;
    private final CodeSeg runWhenOn;

    public WhenOnEventHandler(Button button, CodeSeg cs, GamepadHandler gph) {
        super(button, cs, gph);
        runWhenOn = cs;
        this.codeToRun = () -> on = !on;
    }

    @Override
    protected void run() {
        super.run();
        if (on) runWhenOn.run();
    }
}
