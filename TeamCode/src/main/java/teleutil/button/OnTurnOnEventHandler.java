package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.CodeSeg;

public class OnTurnOnEventHandler extends OnPressEventHandler {
    public boolean on = false;

    public OnTurnOnEventHandler(Button button, CodeSeg cs, GamepadHandler gph) {
        super(button, cs, gph);
        this.codeSeg = () -> {
            on = !on;
            if (on) {
                cs.run();
            }
        };
    }
}
