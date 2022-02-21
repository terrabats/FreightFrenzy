package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

/**
 * Occurs (once) when the toggler turns on
 */
public class OnTurnOnEventHandler extends OnPressEventHandler {
    public boolean on = false;

    public OnTurnOnEventHandler(Button button, CodeSeg cs, GamepadHandler gph) {
        super(button, cs, gph);
        this.codeToRun = () -> {
            on = !on;
            if (on) {
                cs.run();
            }
        };
    }
}
