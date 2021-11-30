package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class OnTurnOnEventHandler extends OnPressEventHandler {
    public boolean on = false;

    public OnTurnOnEventHandler(Button button, ParameterCodeSeg cs, GamepadHandler gph) {
        super(button, cs, gph);
        this.codeSeg = args -> {
            on = !on;
            if (on) {
                cs.run(args);
            }
        };
    }
}
