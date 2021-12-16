package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.DoubleParameterCodeSeg;

public class OnTurnOnEventHandler extends OnPressEventHandler {
    public boolean on = false;

    public OnTurnOnEventHandler(Button button, DoubleParameterCodeSeg cs, GamepadHandler gph) {
        super(button, cs, gph);
        this.codeSeg = args -> {
            on = !on;
            if (on) {
                cs.run(args);
            }
        };
    }
}
