package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

public class WhenOnEventHandler extends OnPressEventHandler {
    public boolean on = false;
    private final CodeSeg runWhenOn;

    public WhenOnEventHandler(Button button, CodeSeg cs, GamepadHandler gph) {
        super(button, cs, gph);
        runWhenOn = cs;
        this.codeSeg = () -> on = !on;
    }

    @Override
    protected void run() {
        super.run();
        if (on) runWhenOn.run();
    }
}
