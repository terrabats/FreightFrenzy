package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

/**
 * Occurs (once) when the toggler is turned off
 */
public class OnTurnOffEventHandler extends OnPressEventHandler {
    public boolean on = false;

    public OnTurnOffEventHandler(Button button, CodeSeg cs, GamepadHandler gph) {
        super(button, cs, gph);
        this.codeToRun = () -> {
            on = !on;
            if (!on) {
                cs.run();
            }
        };
    }
}
