package teleutil.button;

import teleutil.GamepadHandler;
import util.codeseg.ParameterCodeSeg;

public class OnTurnOffEventHandler extends OnPressEventHandler {
    public boolean on = false;

    public OnTurnOffEventHandler(Button button, ParameterCodeSeg cs, GamepadHandler gph) {
        super(button, cs, gph);
        this.codeSeg = args -> {
            on = !on;
            if (!on) {
                cs.run(args);
            }
        };
    }
}
